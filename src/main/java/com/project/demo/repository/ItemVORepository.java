package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.demo.vo.ItemVO;

@Mapper
public interface ItemVORepository {
	
	@Select("""
			SELECT *
				FROM item;
			""")
	public List<ItemVO> getItemList();
	
	
	@Select("""
			SELECT *
				FROM item
				WHERE itemCode = #{itemCode};
			""")
	public ItemVO getItemByCode(int itemCode);


	@Select("""
			SELECT *
				FROM item
				WHERE recipeItem1 = 204
				AND recipeItem2 = 510;
			""")
	public List<ItemVO> getRecipeByItemCode(int recipeItem1, int recipeItem2, int recipeItem3);
}
