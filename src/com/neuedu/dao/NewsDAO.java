package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.News;
import com.neuedu.entity.NewsType;

public interface NewsDAO {

	List<NewsType> findNewsTypeList();
	
	boolean insertNews(News news);
	
	//��ѯ��ҳ�б�
	List<News> findNewsList(int currentPage, int pageSize);
	
	//��ѯ�ܼ�¼��
	int findTotalCount();
	
}
