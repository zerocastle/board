/**
 * 
 */
package com.movie.watch.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.movie.watch.dto.Criteria;
import com.movie.watch.vo.BoardVO;

/**
 * @author kys
 *
 */
public interface BoardMapper {

	// 리스트 불러오기
	public List<BoardVO> getlistWithPagin(Criteria cri);

	// 게시물 작성하기
	public int insertBoard(BoardVO param);

	// 게시물 갯수 구하기
	public int getTotalCount();

	// 게시물 조회
	public List<Map<String, ?>> read(@Param("bno") int bno);

}
