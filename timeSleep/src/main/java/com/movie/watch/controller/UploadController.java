package com.movie.watch.controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.movie.watch.utill.UploadFileUtils;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/upload/**")
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public @ResponseBody String board_file_uploadPOST(MultipartHttpServletRequest multipartRequest,
			ServletRequest request) throws IOException, Exception {

		logger.info("upload");
		Iterator<String> itr = multipartRequest.getFileNames();
		String str = new String();

		while (itr.hasNext()) {
			MultipartFile mpf = multipartRequest.getFile(itr.next());

			String originalFilename = mpf.getOriginalFilename();

			String uploadPath = request.getServletContext().getRealPath("/resources");

			if (str.equals("")) {
				str = UploadFileUtils.uploadFile(uploadPath, originalFilename, mpf.getBytes(), "/image");
				str = str.substring(str.indexOf("image/") - 1);
			} else {
				String temp = UploadFileUtils.uploadFile(uploadPath, originalFilename, mpf.getBytes(), "/image");
				temp = temp.substring(temp.indexOf("image/") - 1);
				str += "&&";

				str += temp;
			}
			logger.info(uploadPath);
		}
		logger.info(str);

		return str;

	}

}
