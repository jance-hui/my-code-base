package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GoodsImgDao;
import entity.GoodsImg;
import service.GoodsImgService;

@Service
public class GoodsImgServiceImpl implements GoodsImgService{

	@Autowired
	GoodsImgDao gImgDao;

	@Override
	public List<GoodsImg> search() {
		List<GoodsImg> gImgList = gImgDao.search();
		return gImgList;
	}
	
	@Override
	public GoodsImg searchByID(int id) {
		GoodsImg gImg = gImgDao.searchByID(id);
		return gImg;
	}

	@Override
	public List<GoodsImg> searchByGoods(int gID) {
		List<GoodsImg> gImgList = gImgDao.searchByGoods(gID);
		return gImgList;
	}

	@Override
	public boolean add(GoodsImg gImg) {
		int rs = gImgDao.add(gImg);
		return rs>0;
	}

	@Override
	public boolean del(String ids) {
		int rs = gImgDao.del(ids);
		return rs>0;
	}

	@Override
	public boolean update(GoodsImg gImg) {
		int rs = gImgDao.update(gImg);
		return rs>0;
	}

	@Override
	public List<GoodsImg> searchByIDs(String goodsID) {
		List<GoodsImg> imgList = gImgDao.searchByIDs(goodsID);
		return imgList;
	}
}
