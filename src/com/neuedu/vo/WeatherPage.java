package com.neuedu.vo;

import java.util.List;

import com.neuedu.entity.Weather;


public class WeatherPage {
	private Integer pageSize; // 分页大小
	private Integer currentPage=0; // 当前页码
	private Integer totalCount; // 总记录数
	private Integer totalPage; // 总页数

	private List<Weather> list; // 数据列表
	private static WeatherPage weatherPage=new WeatherPage();
	private WeatherPage() {
	}
	public static WeatherPage getInstance() {
		return weatherPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void addPage(Integer current) {
		this.currentPage=currentPage+current;
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

	public List<Weather> getList() {
		return list;
	}

	public void setList(List<Weather> list) {
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

	private Integer begin; // 起始页码
	private Integer end; // 结束页码
}
