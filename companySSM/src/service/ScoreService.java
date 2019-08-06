package service;

import java.util.List;

import entity.Score;

public interface ScoreService {

	public Score search(int id) ;
	
	public int searchCount(Score sc) ;
	
	public List<Score> searchByCondition(Score sc ,int begin ,int size) ;

	public int add(Score sc) ;
	
	public boolean update(Score sc) ;

}
