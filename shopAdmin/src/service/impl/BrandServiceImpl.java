package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BrandDao;
import entity.Brand;
import service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	BrandDao brandDao;
	@Override
	public List<Brand> search() {
		List<Brand> bList = brandDao.search();
		return bList;
	}

	@Override
	public List<Brand> search2(String ids) {
		List<Brand> bList = brandDao.search2(ids);
		return bList;
	}

	@Override
	public boolean add(Brand brand) {
		int rs = brandDao.add(brand);
		return rs>0;
	}

	@Override
	public boolean del(String ids) {
		int rs = brandDao.del(ids);
		return rs>0;
	}

	@Override
	public boolean update(Brand brand) {
		int rs = brandDao.update(brand);
		return rs>0;
	}

	@Override
	public Brand searchByID(int id) {
		Brand brand = brandDao.searchByID(id);
		return brand;
	}

}
