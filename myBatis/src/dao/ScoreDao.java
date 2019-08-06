package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Score;

public interface ScoreDao {
	
	public Score search(int id) ;
	
	public int searchCount(Score sc) ;
	
	public List<Score> searchByCondition(@Param(value="sc")Score sc ,@Param(value="begin")int begin ,@Param(value="end")int size) ;

	public int add(Score sc) ;
	public int returnNewId() ;
	
	public int update(Score sc) ;
}
