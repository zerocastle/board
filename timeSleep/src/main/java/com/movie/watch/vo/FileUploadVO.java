package com.movie.watch.vo;

import lombok.Data;

@Data
public class FileUploadVO {
	
	private int filePK;
	private String uploadPath;
	private String fileName;
	
	private int bno;

}
