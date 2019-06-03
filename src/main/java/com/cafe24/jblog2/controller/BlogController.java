package com.cafe24.jblog2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog2.security.Auth;
import com.cafe24.jblog2.security.AuthUser;
import com.cafe24.jblog2.service.BlogService;
import com.cafe24.jblog2.service.CategoryService;
import com.cafe24.jblog2.service.FileUploadService;
import com.cafe24.jblog2.service.PostService;
import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.CategoryVo;
import com.cafe24.jblog2.vo.PostVo;
import com.cafe24.jblog2.vo.UserVo;

@Controller
@RequestMapping("/{blog_id:(?!assets|images).*}")
public class BlogController {
	
	@Autowired
	BlogService blogService;

	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	FileUploadService fileuploadService;
	
	@RequestMapping(
			value = { "", "/{path_category_no}", "/{path_category_no}/{path_post_no}" }, method = RequestMethod.GET )
	public String main(
			@PathVariable("blog_id") String blog_id,
			@PathVariable Optional<Integer> path_category_no,
			@PathVariable Optional<Integer> path_post_no,
			Model model) {
		
		Integer category_no = (path_category_no.isPresent() ? path_category_no.get() : 1);
		Integer post_no = (path_post_no.isPresent() ? path_post_no.get() : 1);

		System.out.println(category_no);
		System.out.println(post_no);
		
		PostVo postVo = new PostVo(post_no,category_no);
		
		// 선택된 게시물을 가져오기 위한 vo
		PostVo select_post = postService.getPostInfo(postVo);
		BlogVo blogVo = blogService.getBlogInfo(blog_id);
		List<PostVo> category_post = postService.getCategoryPost(category_no);
		List<CategoryVo> categoryList = categoryService.getCategoryList(blog_id);
		
		System.out.println("select_post : " + select_post);
		System.out.println("category_post : " + category_post);
		
		model.addAttribute("blogVo",blogVo);
		model.addAttribute("select_post", select_post);
		model.addAttribute("category_post", category_post);
		model.addAttribute("categoryList", categoryList);
		
		return "/blog/blog-main";
	}

	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String admin(@PathVariable("blog_id") String blog_id, @AuthUser UserVo authUser, Model model) {
		if (!authUser.getId().equals(blog_id)) {
			return "redirect:/blog/" + blog_id;
		}

		BlogVo blogVo = blogService.getBlogInfo(blog_id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("logo", blogVo.getLogo());

		return "blog/blog-admin-basic";
	}

	@Auth
	@RequestMapping(value = "/admin/modify", method = RequestMethod.POST)
	public String blogProfileChange(@PathVariable("blog_id") String blog_id,
			@RequestParam(value = "title", required = true, defaultValue = "") String title,
			@RequestParam(value = "logo-file") MultipartFile multipartFile, Model model) {
		BlogVo blogProfileVo = new BlogVo();
		String logo = fileuploadService.restore(multipartFile);

		model.addAttribute("logo", logo);
		blogProfileVo.setBlog_id(blog_id);
		blogProfileVo.setTitle(title);
		blogProfileVo.setLogo(logo);

		blogService.blogProfileChange(blogProfileVo);

		return "redirect:/" + blog_id;
	}

	@Auth
	@RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
	public String blogAddCategory(@PathVariable("blog_id") String blog_id,
			@ModelAttribute CategoryVo categoryVo, Model model) {
		System.out.println(categoryVo);
		
		BlogVo blogVo = blogService.getBlogInfo(blog_id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("logo", blogVo.getLogo());
		
		categoryService.addCategory(categoryVo);
		return "redirect:/" + blog_id+"/admin/category";
	}

	@Auth
	@RequestMapping(value = "/admin/category/delete/{no}", method = RequestMethod.GET)
	public String blogDeleteCategory(
			@PathVariable("blog_id") String blog_id,
			@PathVariable("no") int category_no,
			@ModelAttribute CategoryVo categoryVo,
			Model model) {
		
		BlogVo blogVo = blogService.getBlogInfo(blog_id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("logo", blogVo.getLogo());
		
		postService.deleteByCategory(category_no);
		categoryService.deleteCategory(category_no);
		
		return "redirect:/" + blog_id+"/admin/category";
	}
	
	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String blogCategory(@PathVariable("blog_id") String blog_id, @AuthUser UserVo authUser, Model model) {

		if (!authUser.getId().equals(blog_id)) {
			return "redirect:/" + blog_id;
		}

		BlogVo blogVo = blogService.getBlogInfo(blog_id);
		List<CategoryVo> categoryList = categoryService.getCategoryList(blog_id);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("logo", blogVo.getLogo());
		model.addAttribute("categoryList",categoryList);
		
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String blogWritePage(@PathVariable("blog_id") String blog_id, @AuthUser UserVo authUser, Model model) {

		if (!authUser.getId().equals(blog_id)) {
			return "redirect:/blog/" + blog_id;
		}

		System.out.println("밑단실행여부");

		BlogVo blogVo = blogService.getBlogInfo(blog_id);
		List<CategoryVo> categoryList = categoryService.getCategoryList(blog_id);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("logo", blogVo.getLogo());
		model.addAttribute("categoryList",categoryList);
		
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String blogWrite(@PathVariable("blog_id") String blog_id,
								@AuthUser UserVo authUser,
								Model model,
								@ModelAttribute PostVo postVo) {
		
		if (!authUser.getId().equals(blog_id)) {
			return "redirect:/blog/" + blog_id;
		}
		
		BlogVo blogVo = blogService.getBlogInfo(blog_id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("logo", blogVo.getLogo());

		postService.postWrite(postVo);
		return "redirect:/" + blog_id;
	}
}
