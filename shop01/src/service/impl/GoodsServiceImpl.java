package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GoodsDao;
import entity.Goods;
import service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	GoodsDao goodsDao;
	@Override
	public List<Goods> search() {
		List<Goods> gList = goodsDao.search();
		return gList;
	}

	@Override
	public List<Goods> searchByGoods(Goods goods,int begin,int size) {
		List<Goods> gList = goodsDao.searchByGoods(goods,begin,size);
		return gList;
	}
	
	@Override
	public Goods searchById( int id) {
		Goods goods = goodsDao.searchById(id);
		return goods;
	}
	@Override
	public int searchAll() {
		int rs = goodsDao.searchAll();
		return rs;
	}

	@Override
	public boolean add(Goods goods) {
		int rs = goodsDao.add(goods);
		return rs>0;
	}

	@Override
	public boolean del(String ids) {
		int rs = goodsDao.del(ids);
		return rs>0;
	}

	@Override
	public boolean update(Goods goods) {
		int rs = goodsDao.update(goods);
		return rs>0;
	}

	@Override
	public int searchCountByGoods(Goods goods) {
		int rs = goodsDao.searchCountByGoods(goods);
		return rs;
	}

	@Override
	public List<Goods> searchByIds(String goodsID) {
		List<Goods> goodsList = goodsDao.searchByIds(goodsID);
		return goodsList;
	}

	@Override
	public boolean amendNum(int id, int num) {
		int rs = goodsDao.amendNum(id, num);
		return rs>0;
	}

}
