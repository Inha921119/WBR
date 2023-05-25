package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
	@Insert("""
			INSERT INTO article
				SET regDate = NOW(),
					updateDate = NOW(),
					memberId = #{memberId},
					boardId = #{boardId},
					title = #{title},
					`body` = #{body};
			""")
	public void writeArticle(int memberId, int boardId, String title, String body);
	
	@Select("""
			SELECT LAST_INSERT_ID();
			""")
	public int getLastInsertId();
	
	@Select("""
			SELECT * 
				FROM article
			WHERE id = #{id};
			""")
	public Article getArticleById(int id);
	
	@Select("""
			<script>
				WITH AM AS (
				    SELECT A.*, M.nickname AS writerName 
				        FROM article AS A
				        INNER JOIN `member` AS M
				        ON A.memberId = M.id
				        WHERE 1 = 1
						<if test="boardId != 0">
							AND A.boardId = #{boardId}
						</if>
						<if test="searchKeyword != ''">
							<choose>
								<when test="searchKeywordType == 'title'">
									AND A.title LIKE CONCAT('%', #{searchKeyword}, '%')
								</when>
								<when test="searchKeywordType == 'body'">
									AND A.body LIKE CONCAT('%', #{searchKeyword}, '%')
								</when>
								<otherwise>
									AND (
										A.title LIKE CONCAT('%', #{searchKeyword}, '%')
										OR A.body LIKE CONCAT('%', #{searchKeyword}, '%')
									)
								</otherwise>
							</choose>
						</if>
					)
					SELECT AM.*
			            	, IFNULL(SUM(RP.point), 0) AS sumReactionPoint
			            	, IFNULL(SUM(IF(RP.point &gt; 0, RP.point, 0)), 0) AS goodReactionPoint
			           		, IFNULL(SUM(IF(RP.point &lt; 0, RP.point, 0)), 0) AS badReactionPoint
			        	FROM AM
		            	LEFT JOIN reactionPoint AS RP
		            	ON RP.relTypeCode = 'article'
		            	AND RP.relId = AM.id
					    GROUP BY AM.id
					    ORDER BY AM.id DESC
					    LIMIT #{limitStart}, #{itemsInAPage}
			</script>
			""")
	public List<Article> getArticles(int boardId, String searchKeywordType, String searchKeyword, int limitStart, int itemsInAPage);
	
	@Update("""
			<script>
				UPDATE article
				<set>
					updateDate = NOW(),
					<if test="title != null and title != ''">
						title = #{title},
					</if>
					<if test="body != null and body != ''">
						`body` = #{body}
					</if>
				</set>
				WHERE id = #{id};
			</script>
			""")
	public void modifyArticle(int id, String title, String body);
	
	@Delete("""
			DELETE FROM article
			WHERE id = #{id};
			""")
	public void deleteArticle(int id);
	
	@Select("""
			<script>
				WITH AM AS (
					SELECT A.*, M.nickname AS writerName
						FROM article AS A
						INNER JOIN `member` AS M
						ON A.memberId = M.id
						WHERE A.id = #{id}
				)
				SELECT AM.*
					, IFNULL(SUM(RP.point), 0) AS sumReactionPoint
					, IFNULL(SUM(IF(RP.point &gt; 0, RP.point, 0)), 0) AS goodReactionPoint
					, IFNULL(SUM(IF(RP.point &lt; 0, RP.point, 0)), 0) AS badReactionPoint
					FROM AM
					LEFT JOIN reactionPoint AS RP
					ON RP.relTypeCode = 'article'
					AND RP.relId = AM.id
					GROUP BY AM.id;
			</script>
			""")
	public Article getForPrintArticle(int id);
	
	@Select("""
			<script>
				SELECT COUNT(*)
					FROM article
				WHERE boardId = #{boardId}
					<if test="searchKeyword != ''">
						<choose>
							<when test="searchKeywordType == 'title'">
								AND title LIKE CONCAT('%', #{searchKeyword}, '%')
							</when>
							<when test="searchKeywordType == 'body'">
								AND `body` LIKE CONCAT('%', #{searchKeyword}, '%')
							</when>
							<otherwise>
								AND (
									title LIKE CONCAT('%', #{searchKeyword}, '%')
									OR `body` LIKE CONCAT('%', #{searchKeyword}, '%')
								)
							</otherwise>
						</choose>
					</if>
			</script>
			""")
	public int getArticlesCnt(int boardId, String searchKeywordType, String searchKeyword);
	
	@Update("""
			UPDATE article
			SET hitCount = hitCount + 1
			WHERE id = #{id};
			""")
	public int increaseHitCount(int id);
}
