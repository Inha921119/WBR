<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="WebBattleRoyale" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 bg-black text-white h-screen">
		<div class="container mx-auto text-center">
			<div class="mb-20">현재 위치 : </div>
				<div class="text-xl flex">
					<table style="border: 2px solid white; width: 60%" >
						<tbody>
							<tr style="border: 2px solid white; text-align: center;">
								<th colspan="3">
									스테이터스
								</th>
							</tr>
							<tr>
								<td rowspan="5" style="width: 15%;"><img class="ml-2" src="/resource/images/${rq.player.image }.jpg"/></td>
								<td style="width: 35%;"></td>
								<td class="equip" style="width: 50%">[왼손 ${equipments[0].category }] : <c:choose>
																				<c:when test="${equipments[0].usedItemCode != 999}">${equipments[0].name }</c:when>
																				<c:otherwise>없음</c:otherwise>
																			</c:choose></td>
							</tr>
							<tr>
								<td>이름 : ${rq.player.name }</td>
								<td class="equip">[오른손 ${equipments[1].category }] : <c:choose>
																<c:when test="${equipments[1].usedItemCode != 999}">${equipments[1].name }</c:when>
																<c:otherwise>없음</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td>Lv : ${rq.player.level }</td>	
								<td class="equip">[${equipments[2].category }] : <c:choose>
																<c:when test="${equipments[2].usedItemCode != 999}">${equipments[2].name }</c:when>
																<c:otherwise>없음</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td class="text-green-500">경험치 : ${rq.player.exp } / ${rq.player.maxExp }</td>
								<td class="equip">[${equipments[3].category }] : <c:choose>
																<c:when test="${equipments[3].usedItemCode != 999}">${equipments[3].name }</c:when>
																<c:otherwise>없음</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td></td>
								<td class="equip">[${equipments[4].category }] : <c:choose>
																<c:when test="${equipments[4].usedItemCode != 999}">${equipments[4].name }</c:when>
																<c:otherwise>없음</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td class="text-red-400">체력</td>
								<c:set var = "sumIncreseHP" value = "0" />
								<c:set var = "sumDecreseHP" value = "0" />
								<c:forEach var="equipment" items="${equipments }" varStatus="status">     
									<c:set var= "sumIncreseHP" value="${sumIncreseHP + equipment.increseHP}"/>
									<c:set var= "sumDecreseHP" value="${sumDecreseHP + equipment.decreseHP}"/>
								</c:forEach>
								<td class="text-red-400">${rq.player.hp } / ${rq.player.maxHp + sumIncreseHP - sumDecreseHP}</td>
								<td class="equip">[${equipments[5].category }] : <c:choose>
																<c:when test="${equipments[5].usedItemCode != 999}">${equipments[5].name }</c:when>
																<c:otherwise>없음</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td class="text-yellow-400">스테미나</td>
								<td class="text-yellow-400">${rq.player.sp } / ${rq.player.maxSp }</td>
								<td class="equip">[${equipments[6].category }] : <c:choose>
																<c:when test="${equipments[6].usedItemCode != 999}">${equipments[6].name }</c:when>
																<c:otherwise>없음</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td>공격력</td>
								<td>${rq.player.attackPoint }</td>
								<td class="ownItem text-blue-500" colspan="4">
									아이템 목록
								</td>
							</tr>
							<tr>
								<td>방어력</td>
								<td>${rq.player.defencePoint }</td>
								<td class="ownItem">
								</td>
							</tr>
							<tr>
								<td>적중률</td>
								<td>${rq.player.hitRate } %</td>
								<td class="ownItem">
								</td>
							</tr>
							<tr>
								<td>회피율</td>
								<td>${rq.player.missRate } %</td>
								<td class="ownItem">
								</td>
							</tr>
							<tr>
								<td>스킬</td>
								<td rowspan="2">td</td>
							</tr>
						</tbody>
					</table>
					
					<table style="border: 2px solid white; width: 30%" >
						<tbody>
							<tr style="border: 2px solid white">
								<th>
									th
								</th>
								<th>
									th
								</th>
								<th>
									th
								</th>
							</tr>
							<tr>
								<td>
									td
								</td>
								<td>
									td
								</td>
								<td>
									td
								</td>
							</tr>
						</tbody>
					</table>
					
					<table style="border: 2px solid white; width: 10%" >
						<tbody>
							<tr style="border: 2px solid white">
								<th>
									th
								</th>
							</tr>
							<tr>
								<td>
									td
								</td>
							</tr>
						</tbody>
					</table>
				</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>