package dao;

import java.util.List;

import entity.Brand;

public interface BrandDao {

	public List<Brand> search() ;
	
	public List<Brand> search2(String ids) ;

	public int add(Brand brand) ;
	
	public int del(String ids);
	
	public int update(Brand brand);
	
	public Brand searchByID(int id);
	
}
