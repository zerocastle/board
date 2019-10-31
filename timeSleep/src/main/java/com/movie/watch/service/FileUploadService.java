package com.movie.watch.service;

import org.springframework.stereotype.Service;

import com.movie.watch.mapper.FileUploadMapper;
import com.movie.watch.vo.FileUploadVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FileUploadService {

	private FileUploadMapper mapper;

	// 파일 업로드하기
	public int uploadFile(FileUploadVO param) {
		return mapper.uploadFile(param);
	}

}
