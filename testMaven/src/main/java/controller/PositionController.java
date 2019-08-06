package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import position.Area;
import position.City;
import position.Position;
import service.PositionService;

@Controller
public class PositionController {
	@Autowired
	PositionService positionServise;

	@RequestMapping(value = "findCity")
	@ResponseBody
	public List<City> findCity(String p_id) {
		List<City> cityList = positionServise.searchCity(p_id);
		return cityList;
	}
	
	@RequestMapping(value = "findArea")
	@ResponseBody
	public List<Area> findArea(String c_id) {
		List<Area> areaList = positionServise.searchArea(c_id);
		return areaList;
	}
	
	@RequestMapping(value = "addPosition")
	@ResponseBody
	public Position addPosition(@RequestBody Position position) {
		int newID = positionServise.add(position);
		Position position2 = positionServise.searchByID(newID);
 		return position2;
	}
}
