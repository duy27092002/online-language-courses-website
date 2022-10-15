package com.javaproject.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SaveLocalFile {
	public static String FOLDER_IMAGE = "C:\\Users\\HP\\Desktop\\BTL-java\\online-language-courses-website\\language-courses\\src\\main\\resources\\static\\web\\img\\";

	public String saveFile(MultipartFile multipartFile) {
		if (multipartFile.isEmpty() || multipartFile == null) {
			return null;
		}

		String fileName = multipartFile.getOriginalFilename();
		Path uploadPath = Paths.get(FOLDER_IMAGE);
		if (!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return null;
	}
}
