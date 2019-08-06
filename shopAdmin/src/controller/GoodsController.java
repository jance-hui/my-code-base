package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import entity.Brand;
import entity.Genre;
import entity.Goods;
import entity.GoodsImg;
import service.BrandService;
import service.GenreService;
import service.GoodsImgService;
import service.GoodsService;

@Controller
public class GoodsController {
	List<GoodsImg> goodsImgs = new ArrayList<>();
	@Autowired
	GoodsService goodsService;
	@Autowired
	BrandService brandService;
	@Autowired
	GenreService generService;
	@Autowired
	GoodsImgService goodsImgService;

	@RequestMapping(value="Products_List")
	public ModelAndView Products_List(Goods goods) {
		ModelAndView mv = new ModelAndView("Products_List");
		List<Goods> goodsList = goodsService.searchByGoods(goods);
		int count = goodsService.searchAll();
		mv.addObject("goodsList", goodsList);
		mv.addObject("goods", goods);
		mv.addObject("count", count);
		return mv;
	}
	
	@RequestMapping(value="picture-add")
	public ModelAndView pictureAdd() {
		ModelAndView mv = new ModelAndView("picture-add");
		List<Brand> bList = brandService.search();
		List<Genre> gList = generService.search();
		mv.addObject("brand", bList);
		mv.addObject("genre", gList);
		return mv;
	}
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam(value="file") MultipartFile myFile,HttpServletRequest request, HttpServletResponse response)throws Exception {
		String name = request.getParameter("name");// 文件名称
        String type = name.substring(name.lastIndexOf(".") + 1);// 文件类型
        UUID uuid = UUID.randomUUID();
        name = uuid.toString() + "." + type;// 重新定义图片名称
        String path ="E://shop//shopImg";//这里保存图片路径
        if (!myFile.isEmpty()) {
			myFile.transferTo(new File(path + "/" + name));
			System.out.println(name);
			GoodsImg img = new GoodsImg();
			img.setName(name);
			goodsImgs.add(img);
		}
        HttpSession session = request.getSession();
        session.setAttribute("imgName", name);
		return name;
	}
	@RequestMapping(value = "findImgName")
	@ResponseBody
	public String findImgName(HttpSession session){
		String name =(String) session.getAttribute("imgName");
		session.removeAttribute("imgName");
		return name;
	}
	
	@RequestMapping(value="addGoods")
	public String addGoods(Goods goods) {
		
		int newId = goodsService.add(goods);
		boolean flag = false;
		for(int i=0;i<goodsImgs.size();i++) {
			goodsImgs.get(i).setSort(i);
			flag = goodsImgService.add(newId,goodsImgs.get(i));
		}
		if(flag) {
			goodsImgs.removeAll(goodsImgs);
			return "redirect:Products_List.do";
		}else {
			return "redirect:picture-add.do";
		}
	}
	
	@RequestMapping(value="delGoods")
	@ResponseBody
	public String delGoods(String ids) {
		boolean flag = goodsService.del(ids);
		flag = goodsImgService.del(ids);
		return String.valueOf(flag);
	}
	
	@RequestMapping(value="goodsUpdate")
	public ModelAndView goodsUpdate(int id) {
		ModelAndView mv = new ModelAndView("goodsUpdate");
		Goods goods = goodsService.searchById(id);
		List<Brand> bList = brandService.search();
		List<Genre> gList = generService.search();
		mv.addObject("goods", goods);
		mv.addObject("brand", bList);
		mv.addObject("genre", gList);
		return mv;
	}
	@RequestMapping(value="updateGoods")
	@ResponseBody
	public String updateGoods(@RequestBody Goods goods) {
		boolean flag = goodsService.update(goods);
		return String.valueOf(flag);
	}
}
