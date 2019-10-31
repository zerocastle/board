package com.movie.watch.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {

	private int bno;
	private String title;
	private String content;
	private Date regDate;
	private Date updateDate;
	private int cnt;
	
	
	private String id;

	private List<FileUploadVO> fileUploadVO;

}
