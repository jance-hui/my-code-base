package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Genre;
import service.GenreService;

@Controller
public class GenreController {
	@Autowired
	GenreService generService;

	@RequestMapping(value="Category_Manage")
	public ModelAndView Category_Manage() {
		ModelAndView mv = new ModelAndView("Category_Manage");
		return mv;
	}
	
	@RequestMapping(value="product-category-add")
	public ModelAndView productAdd() {
		ModelAndView mv = new ModelAndView("product-category-add");
		return mv;
	}
	@RequestMapping(value="addGenre")
	public ModelAndView addGenre(Genre genre) {
		ModelAndView mv = new ModelAndView("product-category-add");
		generService.add(genre);
		return mv;
	}
	@RequestMapping(value="findGenre")
	@ResponseBody
	public List<Genre> findGenre() {
		List<Genre> genres = generService.search();
		return genres;
	}
}
