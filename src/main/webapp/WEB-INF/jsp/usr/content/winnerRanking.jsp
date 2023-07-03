<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="명예의 전당" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 text-xl h-full">
	<c:forEach var="player" items="${winPlayers }" varStatus="status">
		<div class="container mx-auto text-center">
		<div class="mt-8 mb-8 text-2xl">${player.winRound }회차 <span class="text-yellow-400">우승자</span> :
			${player.name } <span class="text-red-400 text-lg">(살해 수 : ${player.killPoint })</span></div>
		<div class="text-xl flex justify-center" style="height: 420px;">
			<div style="border: 2px solid white; width: 45%; position: relative;">
				<div>
					<ul style="border: 2px solid white; text-align: center;">
						<li>스테이터스</li>
					</ul>
					<div class="flex" style="width: 100%;">
						<ul style="width: 50%;" id="status">
							<li class="flex mb-2 mt-2">
								<div style="width: 40%">
									<ul>
										<li>
											<img class="ml-2"src="/resource/images/${player.image }.jpg" />
										</li>
									</ul>
								</div>
								<div class="mt-5" style="width: 70%">
									<ul class="text-center">
										<li>이름 : ${player.name }</li>
									</ul>
									<ul>
										<li>Lv : ${player.level }</li>
									</ul>
									<ul>
										<li class="text-green-500">경험치 : ${player.exp } /
											${player.maxExp }</li>
									</ul>
								</div>
							</li>
							<li class="text-red-400" id="hp">체력 : ${player.hp } /
								${player.maxHp}</li>
							<li class="text-yellow-400" id="sp">스테미나 : ${player.sp }
								/ ${player.maxSp }</li>
							<li id="attack">공격력 : ${player.attackPoint }
								(${player.increseAttackPoint })</li>
							<li id="defence">방어력 : ${player.defencePoint }
								(${player.increseDefencePoint })</li>
							<li id="hit">적중 : ${player.hitRate }
								(${player.increseHitRate })</li>
							<li id="miss">회피 : ${player.missRate }
								(${player.increseMissRate })</li>
						</ul>
						<ul style="width: 50%;">
							<li><ul id="equipmentList">
									<c:forEach var="equipment" items="${winnerEquipments }" varStatus="status">
										<c:if test="${player.winRound == equipment.winRound }">
										<li class="equip" style="width: 100%">[${equipment.category }]
											: <c:choose>
												<c:when test="${equipment.usedItemCode < 990}">
													<span
														<c:if test="${equipment.rarity == '1' }">style='color: white;'</c:if>
														<c:if test="${equipment.rarity == '2' }">style='color: #24b500;'</c:if>
														<c:if test="${equipment.rarity == '3' }">style='color: #0073ff;'</c:if>
														<c:if test="${equipment.rarity == '4' }">style='color: #fc008f;'</c:if>
														<c:if test="${equipment.rarity == '5' }">style='color: gold;'</c:if>
														id="ename-${status.index }"> ${equipment.name } <c:if
															test="${equipment.increseHP != 0}">
															<b class="text-red-400 text-base"
																id="eiHP-${status.index }"> (${equipment.increseHP })
															</b>
														</c:if> <c:if test="${equipment.increseSP != 0}">
															<b class="text-yellow-400 text-base"
																id="eiSP-${status.index }"> (${equipment.increseSP })
															</b>
														</c:if> <c:if test="${equipment.increseAttackPoint != 0}">
															<b class="text-base" style="color: #ff0000"
																id="eiAP-${status.index }">
																(${equipment.increseAttackPoint }) </b>
														</c:if> <c:if test="${equipment.increseDefencePoint != 0}">
															<b class="text-base" style="color: #0000ff"
																id="eiDP-${status.index }">
																(${equipment.increseDefencePoint }) </b>
														</c:if> <c:if
															test="${equipment.category eq '무기' 
																	|| equipment.category eq '머리'
																	|| equipment.category eq '상의'
																	|| equipment.category eq '하의'
																	|| equipment.category eq '팔'
																	|| equipment.category eq '신발'}">
															<font class="text-sm font-bold mr-2" style="color: pink;"
																id="eDP-${status.index }"> 내구도 :
																${equipment.durabilityPoint } </font>
														</c:if>
													</span>
												</c:when>
												<c:otherwise>없음</c:otherwise>
											</c:choose>
										</li>
										</c:if>
									</c:forEach>
								</ul></li>
						</ul>
					</div>
					<div class="text-left absolute overflow-y-scroll"
						style="border-top: 1px solid white; width: 100%; height: 18.5%;">
						<ul class="ml-2">
							<li class="flex"><span>스킬</span>
								<div class="ml-10" id="skill-list">
									<span id="active-effect"> <c:choose>
											<c:when test="${player.actionType == '1'}">기본</c:when>
											<c:when test="${player.actionType == '2'}">선제공격</c:when>
											<c:when test="${player.actionType == '3'}">방어태세</c:when>
											<c:when test="${player.actionType == '4'}">주변탐색</c:when>
											<c:when test="${player.actionType == '5'}">사주경계</c:when>
											<c:when test="${player.actionType == '6'}">은밀기동</c:when>
										</c:choose>
									</span>
									<c:forEach var="skill" items="${winnerSkills}">
										<c:if test="${player.winRound == skill.winRound }">
											<span> / ${skill.name}</span>
										</c:if>
									</c:forEach>
								</div></li>
						</ul>
					</div>
				</div>
			</div>

			<div class="ml-2"
				style="border: 2px solid white; width: 23%; position: relative;">
				<div id="itemList">
					<ul style="border: 2px solid white">
						<li>아이템 목록</li>
					</ul>
					<ul class="text-left p-1 overflow-y-scroll h-96" >
						<c:forEach var="inventory" items="${winnerInventorys }" varStatus="status">
							<c:if test="${player.winRound == inventory.winRound }">
							<li><c:if test="${inventory.quantity >= 1 && inventory.delStatus == 1}">
									<span
										<c:if test="${inventory.rarity == '1' }">style='color: white;'</c:if>
										<c:if test="${inventory.rarity == '2' }">style='color: #24b500;'</c:if>
										<c:if test="${inventory.rarity == '3' }">style='color: #0073ff;'</c:if>
										<c:if test="${inventory.rarity == '4' }">style='color: #fc008f;'</c:if>
										<c:if test="${inventory.rarity == '5' }">style='color: gold;'</c:if>>
										${inventory.name } <c:if test="${inventory.increseHP != 0}">
											<b class="text-red-400 text-base"> (${inventory.increseHP })
											</b>
										</c:if> <c:if test="${inventory.increseSP != 0}">
											<b class="text-yellow-400 text-base">
												(${inventory.increseSP }) </b>
										</c:if> <c:if test="${inventory.increseAttackPoint != 0}">
											<b class="text-base" style="color: #ff0000">
												(${inventory.increseAttackPoint }) </b>
										</c:if> <c:if test="${inventory.increseDefencePoint != 0}">
											<b class="text-base" style="color: #0000ff">
												(${inventory.increseDefencePoint }) </b>
										</c:if> <c:if test="${inventory.recoveryHP != 0}">
											<b class="text-red-400 text-base">
												(${inventory.recoveryHP }) </b>
										</c:if> <c:if test="${inventory.recoverySP != 0}">
											<b class="text-yellow-400 text-base">
												(${inventory.recoverySP }) </b>
										</c:if> <c:if
											test="${inventory.category eq '사용' 
															|| inventory.category eq '기타'}">
											<font class="text-sm font-bold mr-2" style="color: green;"
												id="quan-${status.count }"> 수량 : ${inventory.quantity }
											</font>
										</c:if> <c:if
											test="${inventory.category eq '무기' 
															|| inventory.category eq '머리'
															|| inventory.category eq '상의'
															|| inventory.category eq '하의'
															|| inventory.category eq '팔'
															|| inventory.category eq '신발'}">
											<font class="text-sm font-bold mr-2" style="color: pink;">
												내구도 : ${inventory.durabilityPoint } </font>
										</c:if>
									</span>
									<c:if
										test="${inventory.category eq '무기' 
															|| inventory.category eq '머리'
															|| inventory.category eq '상의'
															|| inventory.category eq '하의'
															|| inventory.category eq '팔'
															|| inventory.category eq '신발'}">
									</c:if>
								</c:if></li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
			</div>
			</div>
		</c:forEach>
	</section>
<%@ include file="../common/foot.jsp" %>