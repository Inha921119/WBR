<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="WebBattleRoyale" />
<%@ include file="../common/head.jsp" %>

	<script>
		function locationMove(memberId, lo) {
			var memberId = memberId;
			var location = lo.value;
			var locationName = "";
	
			$.ajax({
					url:"../player/moveLocation?memberId="+memberId+"&location="+location,
					type:"get",
					datatype:"text",
					success : function(data) {
						$("#nowLocation").html("현재 위치 : " + data);
						$("#notify").append("<p>" + data + "(으)로 이동을 했다.</p><p>이제 무엇을 하지?</p>");
						$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
					}
				});
			 
			}
		function action_type(memberId, ty) {
			var memberId = memberId;
			var type = ty.value;
			
			$.ajax({
				url:"../player/getNowActionType?memberId="+memberId,
				type:"get",
				datatype:"text",
				success : function(data) {
					if (data == type) {
						$("#notify").append("<p>이미 적용중인 행동유형입니다.</p><p>이제 무엇을 하지?</p>");
						$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
					} else {
						$.ajax({
							url:"../player/changeActionType?memberId="+memberId+"&type="+type,
							type:"get",
							datatype:"text",
							success : function(data) {
								$("#active-effect").text(data);
								$("#notify").append("<p>행동유형을 " + data + "(으)로 변경했다.</p><p>이제 무엇을 하지?</p>");
								$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
							}
						});
					}
				}
			});
		}
	</script>
	
	
	<section class="mt-8 bg-black text-white h-screen">
		<div class="container mx-auto text-center">
			<div class="mb-20 text-2xl" id="nowLocation">현재 위치 : ${rq.player.lname }</div>
				<div class="text-xl flex" style="height: 420px;">
					<div style="border: 2px solid white; width: 45%; position:relative;" >
						<div>
							<ul style="border: 2px solid white; text-align: center;">
								<li>스테이터스</li>
							</ul>
							<div class="flex" style="width: 100%;">
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
							<div class="text-left absolute overflow-y-scroll" style="border-top: 1px solid white; width:100%; height: 18.5%;">
								<ul class="ml-2">
									<li class="flex">
										<span>스킬</span>
										<div class="ml-10" id="skill-list">
											<span id="active-effect">기본</span>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
						<div class="ml-2" style="border: 2px solid white; width: 20%; position:relative;" >
							<div>
								<ul style="border: 2px solid white">
									<li>
										아이템 목록
									</li>
								</ul>
								<ul class="text-left p-1">
									<li>
										<span>
											약초
											<b class="text-red-400">
												(5)
											</b>
											<font class="num">
												수량 10
											</font>
										</span>
										<button class="mybtn">
											사용
										</button>
										<button class="mybtn">
											버림
										</button>
									</li>
									<li>
										<button>구급상자</button>
									</li>
								</ul>
							</div>
						</div>
					
					<div class="ml-2" style="border: 2px solid white; width: 20%; position:relative;" >
						<div>
							<ul style="border: 2px solid white">
									<li>
										위치 이동 : <select class="text-black" name="location" onchange="locationMove(${rq.getLoginedMemberId() }, this)">
														<option value="1" <c:if test="${rq.player.lname eq '컨테이너 창고'}">selected</c:if>>컨테이너 창고</option>
														<option value="2" <c:if test="${rq.player.lname eq '헬기착륙장'}">selected</c:if>>헬기착륙장</option>
														<option value="3" <c:if test="${rq.player.lname eq '폐병원'}">selected</c:if>>폐병원</option>
														<option value="4" <c:if test="${rq.player.lname eq '폐공원'}">selected</c:if>>폐공원</option>
														<option value="5" <c:if test="${rq.player.lname eq '유적지'}">selected</c:if>>유적지</option>
														<option value="6" <c:if test="${rq.player.lname eq '신전'}">selected</c:if>>신전</option>
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
					
					<div class="ml-2" style="border: 2px solid white; width: 15%; position:relative;" >
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
											<button class="active" value="1" onclick="action_type(${rq.getLoginedMemberId() }, this)">기본</button>
										</li>
										<li class="mb-2">
											<button class="active" value="2" onclick="action_type(${rq.getLoginedMemberId() }, this)">선제공격</button>
										</li>
										<li class="mb-2">
											<button class="active" value="3" onclick="action_type(${rq.getLoginedMemberId() }, this)">방어태세</button>
										</li>
										<li class="mb-2">
											<button class="active" value="4" onclick="action_type(${rq.getLoginedMemberId() }, this)">주변탐색</button>
										</li>
										<li class="mb-2">
											<button class="active" value="5" onclick="action_type(${rq.getLoginedMemberId() }, this)">사주경계</button>
										</li>
										<li>
											<button class="active" value="6" onclick="action_type(${rq.getLoginedMemberId() }, this)">은밀기동</button>
										</li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="mt-2" style="border: 2px solid white; width: 100%;">알림창</div>
				<div class="alert-section" id="alert-section">
					<ul class="h-40">
						<li class="text-left ml-2" id="notify"><p>지금은 무엇을 하지?</p></li>
					</ul>
				</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>