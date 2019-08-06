package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PositionDao;
import position.Area;
import position.City;
import position.Position;
import position.Province;
import service.PositionService;

@Service
public class PositionServiceImpl implements PositionService{

	@Autowired
	PositionDao positionDao;

	public List<Province> searchPro() {
		List<Province> proList = positionDao.searchPro();
		return proList;
	}

	@Override
	public List<City> searchCity(String p_id) {
		List<City> cityList = positionDao.searchCity(p_id);
		return cityList;
	}

	@Override
	public List<Area> searchArea(String c_id) {
		List<Area> areaList = positionDao.searchArea(c_id);
		return areaList;
	}

	@Override
	public int add(Position position) {
		int rs = positionDao.add(position);
		if(rs>0) {
			rs = positionDao.returnNewID();
		}
		return rs;
	}

	@Override
	public List<Position> searchPosition(int id) {
		List<Position> positionList = positionDao.searchPosition(id);
		return positionList;
	}

	@Override
	public Position searchByID(int newID) {
		Position position = positionDao.searchByID(newID);
		return position;
	}
	
}
