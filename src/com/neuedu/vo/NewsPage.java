package com.neuedu.vo;

import java.util.List;

import com.neuedu.entity.News;

public class NewsPage {
	
	private Integer pageSize;   //��ҳ��С
	private Integer currentPage;   //��ǰҳ��
	private Integer totalCount;   //�ܼ�¼��
	private Integer totalPage;   //��ҳ��
	
	private List<News> list;   //�����б�

	private Integer begin;  //��ʼҳ��
	private Integer end;  //����ҳ��
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<News> getList() {
		return list;
	}

	public void setList(List<News> list) {
		this.list = list;
	}

	public Integer getBegin() {
		return begin;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
	

}
