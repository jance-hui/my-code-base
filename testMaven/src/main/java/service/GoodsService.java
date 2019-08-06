package service;

import java.util.List;

import entity.Goods;

public interface GoodsService {

	public List<Goods> search() ;
	
	public Goods searchById( int id) ;
	
	public int searchAll() ;
	
	public List<Goods> searchByGoods(Goods goods,int begin,int size) ;

	public boolean add(Goods goods) ;
	
	public boolean del(String ids);
	
	public boolean update(Goods goods);

	public int searchCountByGoods(Goods goods);

	public List<Goods> searchByIds(String goodsID);
	
	public boolean amendNum(int id,int num);
}
