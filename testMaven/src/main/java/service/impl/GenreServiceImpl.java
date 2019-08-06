package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GenreDao;
import entity.Genre;
import service.GenreService;

@Service
public class GenreServiceImpl implements GenreService{
	@Autowired
	GenreDao generDao;
	@Override
	public List<Genre> search() {
		List<Genre> gList = generDao.search();
		return gList;
	}

	@Override
	public List<Genre> search2(String ids) {
		List<Genre> gList = generDao.search();
		return gList;
	}

	@Override
	public boolean add(Genre gener) {
		int rs = generDao.add(gener);
		return rs>0;
	}

	@Override
	public boolean del(String ids) {
		int rs = generDao.del(ids);
		return rs>0;
	}

	@Override
	public boolean update(List<Genre> gList) {
		int rs = generDao.update(gList);
		return rs>0;
	}

}
