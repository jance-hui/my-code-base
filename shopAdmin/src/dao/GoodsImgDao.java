package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.GoodsImg;

public interface GoodsImgDao {

	public List<GoodsImg> search() ;

	public GoodsImg searchByID(int id) ;
	
	public List<GoodsImg> searchByGoods(int gID) ;

	public int add(@Param(value="goodsId")Integer goodsId,@Param(value="gImg") GoodsImg gImg) ;
	
	public int del(String ids);
	
	public int update(GoodsImg gImg);

	public List<GoodsImg> searchByIDs(String goodsID);
	
}
