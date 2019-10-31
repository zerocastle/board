/**
 * 
 */
package com.movie.watch.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.watch.dto.Criteria;
import com.movie.watch.mapper.BoardMapper;
import com.movie.watch.mapper.FileUploadMapper;
import com.movie.watch.vo.BoardVO;

import lombok.AllArgsConstructor;

/**
 * @author kys
 *
 */
@Service
@AllArgsConstructor
public class BoardService {

	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

	// boardMapper
	private BoardMapper mapper;

	// fileMapper
	private FileUploadMapper fileMapper;

	// 리스트 불러오기
	public List<BoardVO> getlistWithPagin(Criteria cri) {
		logger.info("boardService param :  " + cri);
		return mapper.getlistWithPagin(cri);
	}

	// 게시물 작성하기
	@Transactional
	public int insertBoard(BoardVO param) {
		logger.info("boardInsertBoardService param :  " + param);

		int signal = mapper.insertBoard(param);

		// 시퀀스 키 값 반납i 
		int returnBno = param.getBno(); 

		System.out.println("반환 되는 값은 : " + returnBno);

		param.getFileUploadVO().forEach(upload -> {
			upload.setBno(returnBno);
			fileMapper.uploadFile(upload);
		});

		return signal;
	}

	// 게시물 갯수 구하기
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	// 게시물 조회
	public List<Map<String, ?>> read(@Param("bno") int bno) {
		logger.info("boardService param :  " + bno);

		return mapper.read(bno);

	}
}
