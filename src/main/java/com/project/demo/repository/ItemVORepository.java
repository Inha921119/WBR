package com.project.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.demo.vo.ItemVO;

@Mapper
public interface ItemVORepository {
	
	@Select("""
			SELECT *
				FROM item;
			""")
	public ItemVO getItemList();
	
	
	@Select("""
			SELECT *
				FROM item
				WHERE itemCode = #{itemCode};
			""")
	public ItemVO getItemByCode(int itemCode);
}
