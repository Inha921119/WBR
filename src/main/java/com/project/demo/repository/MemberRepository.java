package com.project.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.demo.vo.Member;

@Mapper
public interface MemberRepository {
	
	@Insert("""
			INSERT INTO `member`
				SET regDate = NOW(),
					updateDate = NOW(),
					loginId = #{loginId},
					loginPw = #{loginPw},
					`name` = #{name},
					nickname = #{nickname},
					cellphoneNum = #{cellphoneNum},
					email = #{email}
			""")
	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT *
				FROM `member`
				WHERE id = #{id};
			""")
	public Member getMemberById(int id);

	@Select("""
			SELECT *
				FROM `member`
				WHERE loginId = #{loginId};
			""")
	public Member getMemberByLoginId(String loginId);

	@Select("""
			SELECT *
				FROM `member`
				WHERE nickname = #{nickname};
			""")
	public Member getMemberByNickname(String nickname);

	@Select("""
			SELECT *
				FROM `member`
				WHERE `name` = #{name}
				AND email = #{email}
			""")
	public Member getMemberByNameAndEmail(String name, String email);

	@Update("""
			UPDATE `member`
				SET updateDate = NOW(),
					nickname = #{nickname},
					cellphoneNum = #{cellphoneNum},
					email = #{email}
				WHERE id = #{loginedMemberId};
			""")
	public void doModify(int loginedMemberId, String nickname, String cellphoneNum, String email);

	@Update("""
			UPDATE `member`
				SET updateDate = NOW(),
					loginPw = #{loginPw}
				WHERE id = #{loginedMemberId}
			""")
	public void doPasswordModify(int loginedMemberId, String loginPw);
	
	@Update("""
			UPDATE `member`
				SET existPlayer = 1
				WHERE id = #{loginedMemberId};
			""")
	public void increseExistPlayer(int loginedMemberId);
	
	@Update("""
			UPDATE `member`
				SET existPlayer = 0
				WHERE id = #{loginedMemberId};
			""")
	public void decreseExistPlayer(int loginedMemberId);
	
	@Update("""
			UPDATE `member`
				SET existPlayer = 0
			""")
	public void resetExistPlayer();
	
}