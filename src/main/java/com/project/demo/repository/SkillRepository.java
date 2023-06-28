package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.demo.vo.Skill;

@Mapper
public interface SkillRepository {
	
	@Select("""
			SELECT *
				FROM skillList
				WHERE needLearnLevel <= #{level}
				AND id NOT IN (SELECT skillId FROM skill WHERE playerId = #{playerId})
			""")
	List<Skill> getSkillListByPIdAndLv(int playerId, int level);
	
	@Select("""
			SELECT s.*
				, sl.name
				, sl.skillType
				, sl.needSkillPoint
				, sl.needLearnLevel
				, sl.weaponType
				, sl.attackPoint
				, sl.defencePoint
				, sl.useHP
				, sl.increseHP
				, sl.decreseHP
				, sl.useSP
				, sl.increseSP
				, sl.decreseSP
				, sl.increseAttackPoint
				, sl.decreseAttackPoint
				, sl.increseDefencePoint
				, sl.decreseDefencePoint
				, sl.increseHitRate
				, sl.decreseHitRate
				, sl.increseMissRate
				, sl.decreseMissRate
				, sl.increseFindItemRate
				, sl.decreseFindItemRate
				, sl.increseFindEnemyRate
				, sl.decreseFindEnemyRate
				FROM skill AS s
				INNER JOIN skillList AS sl
				ON s.skillId = sl.id
				WHERE playerId = #{playerId};
			""")
	List<Skill> getSkillByPlayerId(int playerId);

	@Insert("""
			INSERT INTO skill
				SET regDate = NOW()
					, updateDate = NOW()
					, playerId = #{playerId}
					, skillId = #{skillId};
			""")
	public void learnSkill(int playerId, int skillId);
	
	@Select("""
			SELECT *
				FROM skillList
				WHERE id = #{skillId};
			""")
	public Skill getOneSkillById(int skillId);
	
}
