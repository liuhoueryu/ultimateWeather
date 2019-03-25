package com.neuedu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.NewsDAO;
import com.neuedu.entity.News;
import com.neuedu.entity.NewsType;
import com.neuedu.entity.User;
import com.neuedu.util.DBManager;

public class NewsDAOImpl implements NewsDAO {

	private DBManager dbManager = DBManager.getInstance();
	
	@Override
	public List<NewsType> findNewsTypeList() {
		
		String sql = "select * from newstype";
		
		ResultSet rs = dbManager.execQuery(sql);
		
		List<NewsType> list = new ArrayList<>();
		
		try {
			while(rs.next()){  
				
				NewsType newsType = new NewsType();
				
				newsType.setTypeid(rs.getInt(1));
				newsType.setTypename(rs.getString(2));
				
				list.add(newsType);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}
		
		return null;
	}

	@Override
	public boolean insertNews(News news) {

		String sql = "insert into news values(null, ?, ?, ?, ?, ?)";
		
		return dbManager.execUpdate(sql, news.getTitle(), news.getContent(), news.getNewsType().getTypeid(),
				news.getUser().getUserid(), news.getPubtime());
	}

	@Override
	public List<News> findNewsList(int currentPage, int pageSize) {
		
		//select * from news join newstype as nt on news.typeid = nt.typeid join user on news.userid = user.userid
	    //select * from news, newstype as nt, user where news.typeid = nt.typeid and news.userid = user.userid 
		
		String sql = "select newsid, title, content, typename, username, pubtime from news join newstype as nt on news.typeid = nt.typeid join user on news.userid = user.userid";
		sql += " limit ?, ?";
		
		ResultSet rs = dbManager.execQuery(sql, (currentPage-1)*pageSize, pageSize);
		
		List<News> list = new ArrayList<>();
		
		try {
			while(rs.next()){  
				
				News news = new News();
				
				news.setNewsid(rs.getInt(1));
				news.setTitle(rs.getString(2));
				news.setContent(rs.getString(3));
				
				NewsType newsType = new NewsType();
				newsType.setTypename(rs.getString(4));
				news.setNewsType(newsType);
				
				User user = new User();
				user.setUsername(rs.getString(5));
				news.setUser(user);
				
				news.setPubtime(rs.getTimestamp(6));
				list.add(news);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}
		
		return null;
	}

	@Override
	public int findTotalCount() {
		
		String sql = "select count(*) from news";
		
		ResultSet rs = dbManager.execQuery(sql);
		
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}


	
}
