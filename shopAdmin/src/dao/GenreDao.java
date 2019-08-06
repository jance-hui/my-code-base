package dao;

import java.util.List;

import entity.Genre;

public interface GenreDao {

	public List<Genre> search() ;
	
	public List<Genre> search2(String ids) ;

	public int add(Genre gener) ;
	
	public int del(String ids);
	
	public int update(List<Genre> gList);
	
}
