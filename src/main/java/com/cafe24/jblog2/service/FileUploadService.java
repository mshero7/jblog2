package com.cafe24.jblog2.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static final String URL = "/images";
	private static final String SAVE_PATH = "/jblog2-uploads";
	
	public String restore(MultipartFile multipartFile) {
		String url = "";
		byte[] fileData;
		OutputStream os = null;
		
		if (multipartFile.isEmpty()) {
			return url;
		}

		// 파일이름은 오리지널로 저장하는 것이 아님.
		String originalFilename = multipartFile.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		String saveFileName = generateSaveFileName(extName);
		System.out.println("saveFileName  "+ saveFileName);
		try {
			fileData = multipartFile.getBytes();
			os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			
			url = URL + "/" + saveFileName;
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("*************" +url);
		return url;
	}

	private String generateSaveFileName(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();

		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("."+ extName);

		return filename;
	}
}
