package com.movie.watch.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

	private int startPage;
	private int endPage;
	private boolean prev, next;

	private int total;
	private Criteria cri;

	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;

		// ���� �������� 11 ��������� �Ѵٸ� 2 * 10 = 20�� ������ �������� ������ �ȴ�
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;

		this.startPage = this.endPage - 9; // ����� 11 �� �ǰ�

		// �� 112 ���̰� �ִٰ� �Ѵٸ� 12������ ���� ������ ��������� �˼� �ִ�.
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		// ���� ������ �������� 20 �ε� ���� ������ �������� �۴ٸ� �� ������ �������ش�.
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}

		// ���� ���̰� 1 �̻� ù���̰� �ƴ϶�� ��� ������ ���� �ִٴ� ���̴�. true
		this.prev = this.startPage > 1;

		// ���� ������ �������� �ȿ´ٸ� �������� �ѱ�� �ִ�. true
		this.next = this.endPage < realEnd;

	}

}
