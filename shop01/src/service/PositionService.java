package service;

import java.util.List;

import position.Area;
import position.City;
import position.Position;
import position.Province;

public interface PositionService {
	
	public List<Province> searchPro() ;
	
	public List<City> searchCity(String p_id) ;
	
	public List<Area> searchArea(String c_id) ;

	public int add(Position position) ;

	public List<Position> searchPosition(int id);

	public Position searchByID(int newID);
}
