package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Goods;

public interface GoodsDao {

	public List<Goods> search() ;
	
	public Goods searchById(int id) ;

	public int searchAll() ;
	
	public List<Goods> searchByGoods(@Param(value="goods")Goods goods,@Param(value="begin")int begin,@Param(value="end")int size) ;

	public int add(Goods goods) ;
	
	public int del(String ids);
	
	public int update(Goods goods);

	public int searchCountByGoods(Goods goods);

	public List<Goods> searchByIds(String goodsID);
	
	public int amendNum(@Param(value="id")int id,@Param(value="num")int num);
	
}
