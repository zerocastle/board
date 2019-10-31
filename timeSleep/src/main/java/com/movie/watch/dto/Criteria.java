/**
 * 
 */
package com.movie.watch.dto;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kys
 *
 */

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum; // 현제 페이지
	private int amount; // 보여줄 페이지 양

	private String type;
	private String keyword;

	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public String[] getTypeArr() {
		// TODO Auto-generated method stub

		return this.type == null ? new String[] {} : type.split("");

	}

	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("").queryParam("pageNum", this.getPageNum())
				.queryParam("amount", this.getAmount()).queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		System.out.println("쿼리 스트링 파람 값" + builder.toUriString());
		return builder.toUriString();

	}
}
