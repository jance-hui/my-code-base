package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ScoreDao;
import entity.Score;
import service.ScoreService;
@Service
public class ScoreServiceImpl implements ScoreService{
	@Autowired
	ScoreDao scDao;
	@Override
	public Score search(int id) {
		Score sc =  scDao.search(id);
		return sc;
	}

	@Override
	public int searchCount(Score sc) {
		int rs =  scDao.searchCount(sc);
		return rs;
	}

	@Override
	public List<Score> searchByCondition(Score sc, int begin, int size) {
		List<Score> scLsit =  scDao.searchByCondition(sc, begin, size);
		return scLsit;
	}

	@Override
	public int add(Score sc) {
		int rs =  scDao.add(sc);
		if(rs>0) {
			rs =  scDao.returnNewId();
		}
		return rs;
	}

	@Override
	public boolean update(Score sc) {
		int rs =  scDao.update(sc);
		return rs>0;
	}

}
