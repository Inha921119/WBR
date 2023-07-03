<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="스킬 목록" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 text-xl h-screen flex justify-evenly">
		<div>
			<ul>
				<li>
					<ul id="skillList">
						<c:forEach var="skill" items="${skillList }">
							<li class="skill mt-2 flex justify-between" style="width: 600px">
								<c:if test="${skill.skillType == 1 }">
									<div>
										<span class="text-green-400">[액티브]</span>
										<span class="ml-2">${skill.name }</span>
									</div>
									<div>
										<span class="ml-2 text-sm">공격력 : ${skill.attackPoint }</span>
										<span class="ml-2 text-sm">사용 무기 : 
											<c:if test="${skill.weaponType == 0 }">없음</c:if>
											<c:if test="${skill.weaponType == 1 }">주먹</c:if>
											<c:if test="${skill.weaponType == 2 }">둔기</c:if>
											<c:if test="${skill.weaponType == 3 }">칼/검</c:if>
											<c:if test="${skill.weaponType == 4 }">폭탄</c:if>
											<c:if test="${skill.weaponType == 5 }">투척</c:if>
											<c:if test="${skill.weaponType == 6 }">활/석궁</c:if>
											<c:if test="${skill.weaponType == 7 }">총</c:if>
										</span>
										<span class="ml-2 text-sm">Lv : ${skill.needLearnLevel }</span>
										<span class="ml-2 text-sm">Point : ${skill.needSkillPoint }</span>
									</div>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
		<div>
			<ul >
				<li>
					<ul id="skillList">
						<c:forEach var="skill" items="${skillList }">
							<li class="skill mt-2" style="width: 600px">
								<div class="flex justify-between">
									<c:if test="${skill.skillType == 2 }">
										<div>
											<span class="text-blue-400">[패시브]</span>
											<span class="ml-2">${skill.name }</span>
										</div>
										<div>
											<span class="ml-2 text-sm">관련 무기 : 
													<c:if test="${skill.weaponType == 0 }">없음</c:if>
													<c:if test="${skill.weaponType == 1 }">주먹</c:if>
													<c:if test="${skill.weaponType == 2 }">둔기</c:if>
													<c:if test="${skill.weaponType == 3 }">칼/검</c:if>
													<c:if test="${skill.weaponType == 4 }">폭탄</c:if>
													<c:if test="${skill.weaponType == 5 }">투척</c:if>
													<c:if test="${skill.weaponType == 6 }">활/석궁</c:if>
													<c:if test="${skill.weaponType == 7 }">총</c:if>
												</span>
											<span class="ml-2 text-sm">Lv : ${skill.needLearnLevel }</span>
											<span class="ml-2 text-sm">Point : ${skill.needSkillPoint }</span>
										</div>
										
									</c:if>
								</div>
							</li>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>