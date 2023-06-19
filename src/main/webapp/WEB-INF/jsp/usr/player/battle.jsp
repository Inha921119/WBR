<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="WebBattleRoyale" />
<%@ include file="../common/head.jsp" %>
	<script>
		function searchAround() {
			
			$("#notify").append("<p>주변을 탐색했다. 아무것도 찾지 못했다.</p><p>이제 무엇을 하지?</p>");
			$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
			 
			}
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
		function show_NewStatus(memberId) {
			var memberId = memberId;
			
			$.ajax({
				url:"../player/showStatus?memberId="+memberId,
				type:"get",
				datatype:"text",
				success : function(data) {
					$("#hp").text("체력 : " + data.hp + " / " + data.maxHp);
					$("#sp").text("스테미나 : " + data.sp + " / " + data.maxSp);
					$("#attack").text("공격력 : " + data.attackPoint + "(" + data.increseAttackPoint + ")");
					$("#defence").text("방어력 : " + data.defencePoint + "(" + data.increseDefencePoint + ")");
					$("#hit").text("적중률 : " + data.hitRate + "(" + data.increseHitRate + ")");
					$("#miss").text("회피율 : " + data.missRate + "(" + data.increseMissRate + ")");
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
									<li class="text-red-400" id="hp">체력 : ${rq.player.hp } / ${rq.player.maxHp + sumIncreseHP - sumDecreseHP}</li>
									<li class="text-yellow-400" id="sp">스테미나 : ${rq.player.sp } / ${rq.player.maxSp }</li>
									<li id="attack">공격력 : ${rq.player.attackPoint }</li>
									<li id="defence">방어력 : ${rq.player.defencePoint }</li>
									<li id="hit">적중률 : ${rq.player.hitRate } %</li>
									<li id="miss">회피율 : ${rq.player.missRate } %</li>
								</ul>
								<ul style="width: 50%;">
									<c:forEach var="equipment" items="${equipments }">
										<li class="equip" style="width: 100%">[${equipment.category }] : <c:choose>
																						<c:when test="${equipment.usedItemCode != 999}">
																							<span <c:if test="${equipment.rarity == '1' }">style='color: white;'</c:if>
																									<c:if test="${equipment.rarity == '2' }">style='color: #24b500;'</c:if>
																									<c:if test="${equipment.rarity == '3' }">style='color: #0073ff;'</c:if>
																									<c:if test="${equipment.rarity == '4' }">style='color: #fc008f;'</c:if>
																									<c:if test="${equipment.rarity == '5' }">style='color: gold;'</c:if>>
																								${equipment.name }
																								<c:if test="${equipment.increseHP != 0}">
																									<b class="text-red-400 text-base">
																										(${equipment.increseHP })
																									</b>
																								</c:if>
																								<c:if test="${equipment.increseSP != 0}">
																									<b class="text-yellow-400 text-base">
																										(${equipment.increseSP })
																									</b>
																								</c:if>
																								<c:if test="${equipment.increseAttackPoint != 0}">
																									<b class="text-base" style="color: #ff0000">
																										(${equipment.increseAttackPoint })
																									</b>
																								</c:if>
																								<c:if test="${equipment.increseDefencePoint != 0}">
																									<b class="text-base" style="color: #0000ff">
																										(${equipment.increseDefencePoint })
																									</b>
																								</c:if>
																								<c:if test="${equipment.category eq '무기' 
																											|| equipment.category eq '머리'
																											|| equipment.category eq '상의'
																											|| equipment.category eq '하의'
																											|| equipment.category eq '팔'
																											|| equipment.category eq '신발'}">
																									<font class="text-sm font-bold mr-2" style="color: pink;">
																											내구도 : ${equipment.durabilityPoint }
																									</font>
																								</c:if>
																							</span>
																						</c:when>
																						<c:otherwise>없음</c:otherwise>
																					</c:choose></li>
																				</c:forEach>
								</ul>
							</div>
							<div class="text-left absolute overflow-y-scroll" style="border-top: 1px solid white; width:100%; height: 18.5%;">
								<ul class="ml-2">
									<li class="flex">
										<span>스킬</span>
										<div class="ml-10" id="skill-list">
											<span id="active-effect">
												<c:choose>
													<c:when test="${rq.player.actionType == '1'}">기본</c:when>
													<c:when test="${rq.player.actionType == '2'}">선제공격</c:when>
													<c:when test="${rq.player.actionType == '3'}">방어태세</c:when>
													<c:when test="${rq.player.actionType == '4'}">주변탐색</c:when>
													<c:when test="${rq.player.actionType == '5'}">사주경계</c:when>
													<c:when test="${rq.player.actionType == '6'}">은밀기동</c:when>
												</c:choose>
											</span>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
						<div class="ml-2" style="border: 2px solid white; width: 23%; position:relative;" >
							<div>
								<ul style="border: 2px solid white">
									<li>
										아이템 목록
									</li>
								</ul>
								<ul class="text-left p-1">
									<c:forEach var="inventory" items="${inventory }">
									<li>
										<span <c:if test="${inventory.rarity == '1' }">style='color: white;'</c:if>
												<c:if test="${inventory.rarity == '2' }">style='color: #24b500;'</c:if>
												<c:if test="${inventory.rarity == '3' }">style='color: #0073ff;'</c:if>
												<c:if test="${inventory.rarity == '4' }">style='color: #fc008f;'</c:if>
												<c:if test="${inventory.rarity == '5' }">style='color: gold;'</c:if>>
											${inventory.name }
											<c:if test="${inventory.increseHP != 0}">
												<b class="text-red-400 text-base">
													(${inventory.increseHP })
												</b>
											</c:if>
											<c:if test="${inventory.increseSP != 0}">
												<b class="text-yellow-400 text-base">
													(${inventory.increseSP })
												</b>
											</c:if>
											<c:if test="${inventory.increseAttackPoint != 0}">
												<b class="text-base" style="color: #ff0000">
													(${inventory.increseAttackPoint })
												</b>
											</c:if>
											<c:if test="${inventory.increseDefencePoint != 0}">
												<b class="text-base" style="color: #0000ff">
													(${inventory.increseDefencePoint })
												</b>
											</c:if>
											<c:if test="${inventory.recoveryHP != 0}">
												<b class="text-red-400 text-base">
													(${inventory.recoveryHP })
												</b>
											</c:if>
											<c:if test="${inventory.recoverySP != 0}">
												<b class="text-yellow-400 text-base">
													(${inventory.recoverySP })
												</b>
											</c:if>
											<c:if test="${inventory.category eq '사용' 
														|| inventory.category eq '기타'}">
												<font class="text-sm font-bold mr-2" style="color: green;">
													수량 : ${inventory.quantity }
												</font>
											</c:if>
											<c:if test="${inventory.category eq '무기' 
														|| inventory.category eq '머리'
														|| inventory.category eq '상의'
														|| inventory.category eq '하의'
														|| inventory.category eq '팔'
														|| inventory.category eq '신발'}">
												<font class="text-sm font-bold mr-2" style="color: pink;">
														내구도 : ${inventory.durabilityPoint }
												</font>
											</c:if>
										</span>
										<c:if test="${inventory.category eq '사용' 
														|| inventory.category eq '기타'}">
											<button class="mybtn">
												<span>사용</span>
											</button>
										</c:if>
										<c:if test="${inventory.category eq '무기' 
														|| inventory.category eq '머리'
														|| inventory.category eq '상의'
														|| inventory.category eq '하의'
														|| inventory.category eq '팔'
														|| inventory.category eq '신발'}">
											<button class="mybtn">
												<span>장착</span>
											</button>
										</c:if>
										
										<button class="mybtn">
											<span>버림</span>
										</button>
									</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					
					<div class="ml-2" style="border: 2px solid white; width: 17%; position:relative;" >
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
											<button class="active" onclick="searchAround()">탐색</button>
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
											<button class="active" value="1" onclick="action_type(${rq.getLoginedMemberId() }, this); show_NewStatus(${rq.getLoginedMemberId() })">기본</button>
										</li>
										<li class="mb-2">
											<button class="active" value="2" onclick="action_type(${rq.getLoginedMemberId() }, this); show_NewStatus(${rq.getLoginedMemberId() })">선제공격</button>
										</li>
										<li class="mb-2">
											<button class="active" value="3" onclick="action_type(${rq.getLoginedMemberId() }, this); show_NewStatus(${rq.getLoginedMemberId() })">방어태세</button>
										</li>
										<li class="mb-2">
											<button class="active" value="4" onclick="action_type(${rq.getLoginedMemberId() }, this); show_NewStatus(${rq.getLoginedMemberId() })">주변탐색</button>
										</li>
										<li class="mb-2">
											<button class="active" value="5" onclick="action_type(${rq.getLoginedMemberId() }, this); show_NewStatus(${rq.getLoginedMemberId() })">사주경계</button>
										</li>
										<li>
											<button class="active" value="6" onclick="action_type(${rq.getLoginedMemberId() }, this); show_NewStatus(${rq.getLoginedMemberId() })">은밀기동</button>
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

<%-- 연구중
onmouseover="pop('<font>${inventory.name }</font>
												<br>종류 <font color=dcdcdc>${inventory.category}</font>
												<br><c:if test="${inventory.category eq '사용'}">
														<c:if test="${inventory.recoveryHP != 0}">
															체력회복 <font color=dcdcdc>${inventory.recoveryHP} </font>
														</c:if>
														<c:if test="${inventory.recoverySP != 0}">
															스테미나회복 <font color=dcdcdc>${inventory.recoverySP} </font>
														</c:if>
													</c:if>
												<br><c:if test="${inventory.category eq '무기' 
														|| inventory.category eq '머리'
														|| inventory.category eq '상의'
														|| inventory.category eq '하의'
														|| inventory.category eq '팔'
														|| inventory.category eq '신발'}">
														공격력 <font color=dcdcdc>${inventory.increseAttackPoint} </font>
													</c:if>
												<br><c:if test="${inventory.category eq '무기' 
														|| inventory.category eq '머리'
														|| inventory.category eq '상의'
														|| inventory.category eq '하의'
														|| inventory.category eq '팔'
														|| inventory.category eq '신발'}">
														방어력 <font color=dcdcdc>${inventory.increseDefencePoint} </font>
													</c:if>
												<br><c:if test="${inventory.category eq '무기' 
														|| inventory.category eq '머리'
														|| inventory.category eq '상의'
														|| inventory.category eq '하의'
														|| inventory.category eq '팔'
														|| inventory.category eq '신발'}">
														적중률 <font color=dcdcdc>${inventory.increseHitRate} </font>
													</c:if>
												<br><c:if test="${inventory.category eq '무기' 
														|| inventory.category eq '머리'
														|| inventory.category eq '상의'
														|| inventory.category eq '하의'
														|| inventory.category eq '팔'
														|| inventory.category eq '신발'}">
														회피율 <font color=dcdcdc>${inventory.increseMissRate} </font>
													</c:if>
													','black','20','')" 
												onmouseout="kill()"  --%>
												