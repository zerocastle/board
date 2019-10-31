/**
 * 
 */
package com.movie.watch.service;

import org.springframework.stereotype.Service;

import com.movie.watch.mapper.MemberMapper;
import com.movie.watch.vo.MemberVO;

import lombok.AllArgsConstructor;

/**
 * @author kys
 *
 */

@Service
@AllArgsConstructor
public class MemberService {

	private MemberMapper mapper;

	public MemberVO memberLoginService(MemberVO vo) {
		System.out.println("매퍼로는 가니??" + vo);
		return mapper.memberLogin(vo);

	}

}
