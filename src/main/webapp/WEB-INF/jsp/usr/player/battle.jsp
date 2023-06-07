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
								<td class="equip" style="width: 50%">[왼손] : <c:choose>
																				<c:when test="${equipment.leftHand == 0}">없음</c:when>
																				<c:otherwise>${equipment.leftHand }</c:otherwise>
																			</c:choose></td>
							</tr>
							<tr>
								<td>이름</td>
								<td class="equip">[오른손] : <c:choose>
																<c:when test="${equipment.rightHand == 0}">없음</c:when>
																<c:otherwise>${equipment.rightHand }</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td>Lv</td>	
								<td class="equip">[머리] : <c:choose>
																<c:when test="${equipment.head == 0}">없음</c:when>
																<c:otherwise>${equipment.head }</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td>경험치</td>
								<td class="equip">[상의] : <c:choose>
																<c:when test="${equipment.top == 0}">없음</c:when>
																<c:otherwise>${equipment.top }</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td></td>
								<td class="equip">[팔] : <c:choose>
																<c:when test="${equipment.arm == 0}">없음</c:when>
																<c:otherwise>${equipment.arm }</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td>체력</td>
								<td>${rq.player.hp } / ${rq.player.maxhp }</td>
								<td class="equip">[하의] : <c:choose>
																<c:when test="${equipment.pants == 0}">없음</c:when>
																<c:otherwise>${equipment.pants }</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td>스테미나</td>
								<td>${rq.player.sp } / ${rq.player.maxsp }</td>
								<td class="equip">[신발] : <c:choose>
																<c:when test="${equipment.foot == 0}">없음</c:when>
																<c:otherwise>${equipment.foot }</c:otherwise>
															</c:choose></td>
							</tr>
							<tr>
								<td>공격력</td>
								<td>${rq.player.attackPoint }</td>
								<td class="equip">
									td 위치
								</td>
							</tr>
							<tr>
								<td>방어력</td>
								<td>${rq.player.defencePoint }</td>
								<td class="equip">
									td 위치
								</td>
							</tr>
							<tr>
								<td>적중률</td>
								<td>${rq.player.hitRate } %</td>
								<td class="equip">
									td 위치
								</td>
							</tr>
							<tr>
								<td>회피율</td>
								<td>${rq.player.missRate } %</td>
								<td class="equip">
									td 위치
								</td>
							</tr>
							<tr>
								<td>스킬</td>
								<td>td</td>
								<td class="equip">
									td 위치
								</td>
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