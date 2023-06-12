<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="WebBattleRoyale" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 bg-black text-white h-screen">
		<div class="container mx-auto text-center">
			<div class="mb-20 text-2xl">현재 위치 : </div>
				<div class="text-xl flex" style="height: 420px;">
					<div style="border: 2px solid white; width: 45%; height: 100%;" >
						<div>
							<ul style="border: 2px solid white; text-align: center; height: 10%;">
								<li>
									스테이터스
								</li>
							</ul>
							<div class="flex" style="width: 100%; height: 80%;">
								<ul style="width: 50%;">
									<li class="flex mb-2 mt-2" >
										<div style="width: 40%">
											<ul>
												<li><img class="ml-2" src="/resource/images/${rq.player.image }.jpg"/></li>
											</ul>
										</div>
										<div class="mt-5" style="width: 70%">
											<ul class="text-center"><li>이름 : ${rq.player.name }</li></ul>
											<ul><li>Lv : ${rq.player.level }</li></ul>	
											<ul><li class="text-green-500">경험치 : ${rq.player.exp } / ${rq.player.maxExp }</li></ul>
										</div>
									</li>
									<c:set var ="sumIncreseHP" value = "0" />
									<c:set var ="sumDecreseHP" value = "0" />
									<c:forEach var="equipment" items="${equipments }" varStatus="status">     
										<c:set var="sumIncreseHP" value="${sumIncreseHP + equipment.increseHP}"/>
										<c:set var="sumDecreseHP" value="${sumDecreseHP + equipment.decreseHP}"/>
									</c:forEach>
									<li class="text-red-400">체력 : ${rq.player.hp } / ${rq.player.maxHp + sumIncreseHP - sumDecreseHP}</li>
									<li class="text-yellow-400">스테미나 : ${rq.player.sp } / ${rq.player.maxSp }</li>
									<li>공격력 : ${rq.player.attackPoint }</li>
									<li>방어력 : ${rq.player.defencePoint }</li>
									<li>적중률 : ${rq.player.hitRate } %</li>
									<li>회피율 : ${rq.player.missRate } %</li>
								</ul>
								<ul style="width: 40%;">
									<li class="equip" style="width: 50%">[왼손] : <c:choose>
																					<c:when test="${equipments[0].usedItemCode != 999}">${equipments[0].name }</c:when>
																					<c:otherwise>없음</c:otherwise>
																				</c:choose></li>
									<li class="equip">[오른손] : <c:choose>
																	<c:when test="${equipments[1].usedItemCode != 999}">${equipments[1].name }</c:when>
																	<c:otherwise>없음</c:otherwise>
																</c:choose></li>
									<li class="equip">[머리] : <c:choose>
																	<c:when test="${equipments[2].usedItemCode != 999}">${equipments[2].name }</c:when>
																	<c:otherwise>없음</c:otherwise>
																</c:choose></li>
									<li class="equip">[상의] : <c:choose>
																	<c:when test="${equipments[3].usedItemCode != 999}">${equipments[3].name }</c:when>
																	<c:otherwise>없음</c:otherwise>
																</c:choose></li>
									<li class="equip">[하의] : <c:choose>
																	<c:when test="${equipments[4].usedItemCode != 999}">${equipments[4].name }</c:when>
																	<c:otherwise>없음</c:otherwise>
																</c:choose></li>
									<li class="equip">[팔] : <c:choose>
																	<c:when test="${equipments[5].usedItemCode != 999}">${equipments[5].name }</c:when>
																	<c:otherwise>없음</c:otherwise>
																</c:choose></li>
									<li class="equip">[신발] : <c:choose>
																	<c:when test="${equipments[6].usedItemCode != 999}">${equipments[6].name }</c:when>
																	<c:otherwise>없음</c:otherwise>
																</c:choose></li>
								</ul>
							</div>
							<ul class="text-left ml-2" style="border-top: 1px solid white; height: 10%;">
								<li>스킬 </li>
							</ul>
						</div>
					</div>
					
					<div class="ml-2" style="border: 2px solid white; width: 20%" >
						<div>
							<ul style="border: 2px solid white">
								<li>
									아이템 목록
								</li>
							</ul>
							<ul class="text-left p-1">
								<li>
									약초
								</li>
								<li>
									구급상자
								</li>
							</ul>
						</div>
					</div>
					
					<div class="ml-2" style="border: 2px solid white; width: 20%" >
						<div>
							<ul style="border: 2px solid white">
								<li>
									위치 이동 : <select class="text-black" name="location" >
													<option value="1">컨테이너 창고</option>
													<option value="2">헬기착륙장</option>
													<option value="3">폐병원</option>
													<option value="4">폐공원</option>
													<option value="5">유적지</option>
													<option value="6">신전</option>
												</select>
								</li>
							</ul>
							<ul>
								<li class="flex justify-center">
									<ul class="active-list ml-2 mr-2 mt-10">
										<li class="mb-2">
											<button class="active">탐색</button>
										</li>
										<li class="mb-2">
											<button class="active">조합법 연구</button>
										</li>
										<li class="flex mb-2">
											<button class="active">스킬 습득</button>
										</li>
										<li class="flex mb-2">
											<button class="active text-red-400" style="width:50%">치료</button>
											<button class="active text-yellow-400" style="width:50%">휴식</button>
										</li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
					
					<div class="ml-2" style="border: 2px solid white; width: 15%" >
						<div>
							<ul style="border: 2px solid white">
								<li>
									행동유형
								</li>
							</ul>
							<ul>
								<li class="flex justify-center">
									<ul class="active-list ml-2 mr-2 mt-2">
										<li class="mb-2">
											<button class="active">기본</button>
										</li>
										<li class="mb-2">
											<button class="active">선제공격</button>
										</li>
										<li class="mb-2">
											<button class="active">방어태세</button>
										</li>
										<li class="mb-2">
											<button class="active">주변탐색</button>
										</li>
										<li class="mb-2">
											<button class="active">사주경계</button>
										</li>
										<li>
											<button class="active">은밀기동</button>
										</li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="alert-section mt-2">
					<div>알림창</div>
					<div style="border: 1px solid white"></div>
					<ul class="h-40">
						<li class="text-left ml-2">
							지금은 무엇을 하지?
						</li>
					</ul>
				</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>