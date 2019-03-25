package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.News;
import com.neuedu.entity.NewsType;
import com.neuedu.vo.NewsPage;

public interface NewsService {

	List<NewsType> getNewsTypeList();
	
	boolean addNews(News news);
	
	//List<News> getNewsList(int currentPage, int pageSize);
	NewsPage getNewsPage(int currentPage, int pageSize);
	
}
