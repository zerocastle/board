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

	// ����Ʈ �ҷ�����
	public List<BoardVO> getlistWithPagin(Criteria cri) {
		logger.info("boardService param :  " + cri);
		return mapper.getlistWithPagin(cri);
	}

	// �Խù� �ۼ��ϱ�
	@Transactional
	public int insertBoard(BoardVO param) {
		logger.info("boardInsertBoardService param :  " + param);

		int signal = mapper.insertBoard(param);

		// ������ Ű �� �ݳ�i 
		int returnBno = param.getBno(); 

		System.out.println("��ȯ �Ǵ� ���� : " + returnBno);

		param.getFileUploadVO().forEach(upload -> {
			upload.setBno(returnBno);
			fileMapper.uploadFile(upload);
		});

		return signal;
	}

	// �Խù� ���� ���ϱ�
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	// �Խù� ��ȸ
	public List<Map<String, ?>> read(@Param("bno") int bno) {
		logger.info("boardService param :  " + bno);

		return mapper.read(bno);

	}
}
