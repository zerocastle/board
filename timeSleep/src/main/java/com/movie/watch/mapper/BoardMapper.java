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

	// ����Ʈ �ҷ�����
	public List<BoardVO> getlistWithPagin(Criteria cri);

	// �Խù� �ۼ��ϱ�
	public int insertBoard(BoardVO param);

	// �Խù� ���� ���ϱ�
	public int getTotalCount();

	// �Խù� ��ȸ
	public List<Map<String, ?>> read(@Param("bno") int bno);

}
