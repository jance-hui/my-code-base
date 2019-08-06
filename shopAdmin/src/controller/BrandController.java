package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Brand;
import service.BrandService;
import service.GoodsService;

@Controller
public class BrandController {
	@Autowired
	BrandService brandService;
	@Autowired
	GoodsService goodsService;

	@RequestMapping(value="Brand_Manage")
	public ModelAndView Brand_Manage() {
		ModelAndView mv = new ModelAndView("Brand_Manage");
		List<Brand> brands = brandService.search();
		mv.addObject("brands", brands);
		return mv;
	}
	
	@RequestMapping(value="Add_Brand")
	public ModelAndView Add_Brand() {
		ModelAndView mv = new ModelAndView("Add_Brand");
		return mv;
	}
	@RequestMapping(value="addBrand")
	public ModelAndView addBrand(Brand brand) {
		brandService.add(brand);
		ModelAndView mv = new ModelAndView("Brand_Manage");
		List<Brand> brands = brandService.search();
		mv.addObject("brands", brands);
		return mv;
	}
	
	@RequestMapping(value="Brand_detailed")
	public ModelAndView Brand_detailed(int id) {
		ModelAndView mv = new ModelAndView("Brand_detailed");
		Brand brand = brandService.searchByID(id);
		mv.addObject("brand", brand);
		return mv;
	}
	
	@RequestMapping(value="brandUpdate")
	public ModelAndView brandUpdate(int id) {
		ModelAndView mv = new ModelAndView("brandUpdate");
		Brand brand = brandService.searchByID(id);
		mv.addObject("brand", brand);
		return mv;
	}
	@RequestMapping(value="updateBrand")
	@ResponseBody
	public String updateBrand(@RequestBody Brand brand) {
		boolean flag = brandService.update(brand);
		return String.valueOf(flag);
	}
	@RequestMapping(value="delBrand")
	@ResponseBody
	public String delBrand(String ids) {
		boolean flag = goodsService.updateBrand(ids);
		flag = brandService.del(ids);
		return String.valueOf(flag);
	}
}
