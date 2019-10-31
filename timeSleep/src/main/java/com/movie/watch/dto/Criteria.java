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
	private int pageNum; // ���� ������
	private int amount; // ������ ������ ��

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
		System.out.println("���� ��Ʈ�� �Ķ� ��" + builder.toUriString());
		return builder.toUriString();

	}
}
