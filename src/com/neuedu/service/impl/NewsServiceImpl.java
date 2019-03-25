package com.neuedu.service.impl;

import java.util.List;

import com.neuedu.dao.NewsDAO;
import com.neuedu.dao.impl.NewsDAOImpl;
import com.neuedu.entity.News;
import com.neuedu.entity.NewsType;
import com.neuedu.service.NewsService;
import com.neuedu.vo.NewsPage;

public class NewsServiceImpl implements NewsService {

	private NewsDAO newsDAO = new NewsDAOImpl();   //×éºÏ
	
	@Override
	public List<NewsType> getNewsTypeList() {
		return newsDAO.findNewsTypeList();
	}

	@Override
	public boolean addNews(News news) {
		return newsDAO.insertNews(news);
	}

	@Override
	public NewsPage getNewsPage(int currentPage, int pageSize) {
		
		NewsPage newsPage = new NewsPage();
		
		newsPage.setPageSize(pageSize);
		newsPage.setCurrentPage(currentPage);
		
		int totalCount = newsDAO.findTotalCount();
		newsPage.setTotalCount(totalCount);
		
		int totalPage = totalCount % pageSize == 0 ?  totalCount / pageSize : totalCount / pageSize + 1;
		newsPage.setTotalPage(totalPage);
		
		List<News> list = newsDAO.findNewsList(currentPage, pageSize);
		newsPage.setList(list);
		
		int pageNumber = 5;
				
		int begin = newsPage.getCurrentPage() - pageNumber / 2;
		int end = newsPage.getCurrentPage() + pageNumber / 2;
		
		if(begin<1){
			begin = 1;
			end = pageNumber;
		}
		
		if(end > newsPage.getTotalPage()){
			end = newsPage.getTotalPage();
			begin = newsPage.getTotalPage() - pageNumber + 1;
		}
		
		if(newsPage.getTotalPage()<pageNumber){
			begin = 1;
			end = newsPage.getTotalPage();
		}
		
		newsPage.setBegin(begin);
		newsPage.setEnd(end);
		
		return newsPage;
	}

}
