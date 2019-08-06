package service;

import java.util.List;

import entity.Genre;

public interface GenreService {

	public List<Genre> search() ;
	
	public List<Genre> search2(String ids) ;

	public boolean add(Genre gener) ;
	
	public boolean del(String ids);
	
	public boolean update(List<Genre> gList);
	
}
