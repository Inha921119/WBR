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
			SELECT i1.*
				, IFNULL(i2.name, 0) AS recipeItem1Name
				, IFNULL(i3.name, 0) AS recipeItem2Name
				, IFNULL(i4.name, 0) AS recipeItem3Name
				FROM item AS i1
				LEFT JOIN item AS i2
				ON i1.recipeItem1 = i2.itemCode
				LEFT JOIN item AS i3
				ON i1.recipeItem2 = i3.itemCode
				LEFT JOIN item AS i4
				ON i1.recipeItem3 = i4.itemCode
				WHERE i1.itemCode = #{itemCode};
			""")
	public ItemVO getItemByCode(int itemCode);

	@Select("""
			SELECT i1.*
				, IFNULL(i2.name, 0) AS recipeItem1Name
				, IFNULL(i3.name, 0) AS recipeItem2Name
				, IFNULL(i4.name, 0) AS recipeItem3Name
				FROM item AS i1
				LEFT JOIN item AS i2
				ON i1.recipeItem1 = i2.itemCode
				LEFT JOIN item AS i3
				ON i1.recipeItem2 = i3.itemCode
				LEFT JOIN item AS i4
				ON i1.recipeItem3 = i4.itemCode;
			""")
	public List<ItemVO> getItemListRecipe();
	
	@Select("""
			SELECT *
				FROM item
				ORDER BY RAND()
				LIMIT 1;
			""")
	public ItemVO getRandomItem();
}
