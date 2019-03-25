package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.News;
import com.neuedu.entity.NewsType;

public interface NewsDAO {

	List<NewsType> findNewsTypeList();
	
	boolean insertNews(News news);
	
	//查询分页列表
	List<News> findNewsList(int currentPage, int pageSize);
	
	//查询总记录数
	int findTotalCount();
	
}
