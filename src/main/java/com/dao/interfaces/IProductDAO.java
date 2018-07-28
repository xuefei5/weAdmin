package com.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bean.Product;

@Mapper
public interface IProductDAO{	
	
	/*添加商品对象*/
	@Insert("insert into product(name,tip,price,imgRef,addTime,viewcount,state)values(#{name},#{tip},#{price},#{imgRef},#{addTime},#{viewcount},'1')")
	public int insert(Product product);
	
	/*根据id查询商品对象*/
	@Select("select * from product where id = #{id} and state='1'")
	public Product qryById(@Param("id")int id);
	
	/*根据商品名模糊查询商品对象*/
	@Select("select * from product where name like CONCAT('%',#{name},'%') and state='1'")
	public List<Product> qryByName(@Param("name")String name );

	/*更新商品对象*/
	@Update("UPDATE product SET name = #{name},tip = #{tip},price = #{price},imgRef = #{imgRef} where id = #{id}")
	public int update(Product product);
	
	/*删除商品对象*/
	@Update("UPDATE product SET state='0' where  id = #{id}")
	public int delete(@Param("id")int id);
	
	/*分页查询商品对象*/
	@Select("select * from product where state='1' order by registerTime desc limit #{start} , #{end} ")
	public List<Product> qryProductByPageNum(@Param("start")int id,@Param("end")int end);

	/*查询商品数据条数*/
	@Select("select count(*) from product")
	public int qryProductCount();
}
