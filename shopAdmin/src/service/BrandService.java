package service;

import java.util.List;

import entity.Brand;

public interface BrandService {

	public List<Brand> search() ;
	
	public List<Brand> search2(String ids) ;

	public boolean add(Brand brand) ;
	
	public boolean del(String ids);
	
	public boolean update(Brand brand);

	public Brand searchByID(int id);
	
}
