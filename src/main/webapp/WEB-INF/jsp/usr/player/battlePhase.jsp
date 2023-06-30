<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="WebBattleRoyale" />
<%@ include file="../common/head.jsp" %>
	<script>
	
		originalForm = null;
		originalId = null;
		
		var timer = null;
		
				
		function show_NewStatus(memberId) {
			var memberId = memberId;
			
			$.ajax({
				url:"../player/showStatus?memberId="+memberId,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
					$("#hp").text("체력 : " + data.hp + " / " + data.maxHp);
					$("#sp").text("스테미나 : " + data.sp + " / " + data.maxSp);
					$("#attack").text("공격력 : " + data.attackPoint + "  (" + data.increseAttackPoint + ")");
					$("#defence").text("방어력 : " + data.defencePoint + "  (" + data.increseDefencePoint + ")");
					$("#hit").text("적중 : " + data.hitRate + "  (" + data.increseHitRate + ")");
					$("#miss").text("회피 : " + data.missRate + "  (" + data.increseMissRate + ")");
				}
			});
		}
		
		function action_attack(memberId, playerId1, playerId2) {
			var memberId = memberId;
			var playerId1 = playerId1;
			var playerId2 = playerId2;
			var skillId = 0;
			var kill = 0;
			var levelUp = 0;
			
			$.ajax({
				url:"../player/battlePhaseAttack?playerId1="+playerId1+"&playerId2="+playerId2+"&skillId="+skillId,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
					if(data.success) {
						if(data.data2.deathStatus == 1) {
							kill = 1;
							levelUp = data.numData2;
							$("#notify").append("<p>" + data.data2.name + "에게 " + data.numData1 + "의 데미지를 입혔다</p>");
							$("#notify").append("<p>" + data.data2.name + "이(가) " + data.data1.name + "의 공격으로 사망하였다.</p>");
							if (levelUp == 1) {
								$("#notify").append("<p>" + data.data1.name + "은(는) 레벨업을 하였다.</p>");
								$("#notify").append("<p>스킬포인트 1을 얻었다.</p>");
							}
							$("#notify").append("<p>" + data.data2.name + "의 아이템을 습득할 수 있다.</p>");
							
							let actionTab = $('#actionTab');
							let player2Status = $('#player2-status');
							
							let addHtml_player2status = `
								<form action="../player/getEnemyItem" method="POST" name="get-enemy-items-form">
									<input type="hidden" name="ids" value="1,2,0"/>
								</form>
								<ul style="border: 2px solid white">
									<li>전리품 목록</li>
								</ul>
								<ul class="text-left overflow-y-scroll h-96">`
								
								for(var i=0; i < data.data3.length; i++) {
									var inventory = data.data3[i];
									
									addHtml_player2status = addHtml_player2status
															+ "<li> <input class='ml-2 get-enemy-items' type='checkbox' name='getEnemyItem_" + i + "' value='" + inventory.itemId + "," + inventory.quantity + "," + inventory.itemDP + "'>";
															
									if (inventory.rarity == 0){
										addHtml_player2status = addHtml_player2status + "<span class='ml-2' style='color: white;'>없음";
									}
									if (inventory.rarity == 1){
										addHtml_player2status = addHtml_player2status + "<span class='ml-2' style='color: white;'>";
									}
									if (inventory.rarity == 2){
										addHtml_player2status = addHtml_player2status + "<span class='ml-2' style='color: #24b500;'>";
									}
									if (inventory.rarity == 3){
										addHtml_player2status = addHtml_player2status + "<span class='ml-2' style='color: #0073ff;'>";
									}
									if (inventory.rarity == 4){
										addHtml_player2status = addHtml_player2status + "<span class='ml-2' style='color: #fc008f;'>";
									}
									if (inventory.rarity == 5){
										addHtml_player2status = addHtml_player2status + "<span class='ml-2' style='color: gold;'>";
									}
									if (inventory.rarity != 0){
										addHtml_player2status = addHtml_player2status + inventory.name;
										if (inventory.categoryNum == 1 || inventory.categoryNum == 8){
											addHtml_player2status = addHtml_player2status
																	+ "<font class='text-sm font-bold mr-2' style='color: green;' id='quan-"
																	+ i
																	+ "'> 수량 : "
																	+ inventory.quantity
																	+ "</font>";
										}
										if (inventory.categoryNum != 1 && inventory.categoryNum != 8){
											addHtml_player2status = addHtml_player2status
																	+ "<font class='text-sm font-bold mr-2' style='color: pink;' id='itemDP-"
																	+ i
																	+ "'> 내구도 : "
																	+ inventory.itemDP
																	+ "</font>";
										}
									}
									addHtml_player2status = addHtml_player2status + "</span></li>";
								}
								addHtml_player2status = addHtml_player2status + "</ul>";
								
							player2Status.empty().html("");
							player2Status.append(addHtml_player2status);
						 	
							let addHtml_actionTab = `
								<ul style="border: 2px solid white">
									<li>전리품 습득</li>
								</ul>
								<ul>
									<li class="flex justify-center">
										<ul class="active-list ml-2 mr-2 mt-2">
											<li class="mb-2">
												<button class="active" onclick="btnGetEnemyItem();">습득</button>
											</li>
											<li class="mb-2">
												<button class="active" onclick="action_run(${rq.getLoginedMemberId() }, ${player2.memberId }, ${player2.deathStatus })">그냥간다</button>
											</li>
										</ul>
									</li>
								</ul>`;
										
							actionTab.empty().html("");
							actionTab.append(addHtml_actionTab);
						} else {
							$("#notify").append("<p>" + data.data2.name + "에게 " + data.numData1 + "의 데미지를 입혔다</p>");
							$("#notify").append("<p>3초뒤에 스테이터스 페이지로 돌아갑니다.</p>");
							$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
						}
				    } else {
						$("#notify").append("<p>" + data.msg + "</p>");
						$("#notify").append("<p>강제로 도주합니다.</p>");
						$("#notify").append("<p>3초뒤에 스테이터스 페이지로 돌아갑니다.</p>");
						$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
				    }
				}
			});
			show_NewStatus(memberId);
			if (kill == 0) {
				setTimeout(function() {
					replace_battle(memberId); 
					}, 3000);// 3초 후 실행
			}
		}
		
		function btnGetEnemyItem() {
			const values = $('.get-enemy-items:checked').map((index, el) => el.value).toArray();
			if (values.length == 0) {
				alert('선택한 아이템이 없습니다');
				return;
			}
			$('input[name=ids]').val(values.join(','));
			$('form[name=get-enemy-items-form]').submit();
		}
		
		
		function action_skill(memberId, playerId1, playerId2, skillId) {
			var memberId = memberId;
			var playerId1 = playerId1;
			var playerId2 = playerId2;
			var skillId = skillId;
			var resultCode = "";
			
			$.ajax({
				url:"../player/battlePhaseAttack?playerId1="+playerId1+"&playerId2="+playerId2+"&skillId="+skillId,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
					console.log(data);
					if(data.success) {
						$("#notify").append("<p>" + data.data2.name + "에게 " + data.numData1 + "의 데미지를 입혔다</p>");
						$("#notify").append("<p>3초뒤에 스테이터스 페이지로 돌아갑니다.</p>");
						$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
					} else if(data.resultCode == "F-2") {
						resultCode = data.resultCode;
						$("#notify").append("<p>" + data.msg + "</p>");
						$("#notify").append("<p>다른 행동을 선택해 주세요.</p>");
						$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
					} else {
						$("#notify").append("<p>" + data.msg + "</p>");
						$("#notify").append("<p>강제로 도주합니다.</p>");
						$("#notify").append("<p>3초뒤에 스테이터스 페이지로 돌아갑니다.</p>");
						$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
				    }
				}
			});
			show_NewStatus(memberId);
			if (resultCode != "F-2") {
				setTimeout(function() {
					replace_battle(memberId); 
					}, 3000);// 3초 후 실행
			}
		}
		
		
		function action_skill_form(memberId, playerId1, playerId2) {
			var memberId = memberId;
			var playerId1 = playerId1;
			var playerId2 = playerId2;
			
			if(originalForm != null) {
				actionTab_cancle();
			}
			
			$.get('../player/getSkillByPlayerId', {
				playerId : playerId1,
			}, function(data){
				
				let actionTab = $('#actionTab');
				
				originalForm = actionTab.html();
				
				let addHtml = `
					<ul>
						<li class="flex justify-center">
							<ul class="active-list ml-2 mr-2 mt-2">
							<li class="flex justify-between text-xl mb-5"><span>스킬 목록</span></li>
							<li><ul class="overflow-y-scroll h-64">`;
				
			 	for(var i=0; i < data.length; i++) {
			 		var skill = data[i];
			 		var damage = skill.attackPoint + skill.increseAttackPoint;
			 		addHtml = addHtml + "<li class='flex justify-between'>"+"<span class='text-lg'>"+skill.name+"<span class='text-sm text-red-400'>("+damage+")</span></span><button class='mybtn ml-2' onclick='action_skill(" + memberId + ","  + playerId1 + ","  + playerId2 + "," + skill.id +")'>사용</button></li>";
				}
			 	
			 	addHtml = addHtml + `</ul></li><li class="flex mb-2">
					    	<button class="active mt-5" onclick="actionTab_cancle()"><span>돌아가기</span></button>
							</li>
						</ul>
					</li>
				</ul>`;
				
				actionTab.empty().html("");
				actionTab.append(addHtml);
			}, 'json');
		}	
		
		function action_run(id, memberId2, deathStatus) {
			var id = id;
			var memberId = memberId2;
			var deathStatus = deathStatus;
			
			$.ajax({
				url:"../player/showStatus?memberId="+memberId,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
						if (deathStatus = 1) {
							$("#notify").append("<p>승리를 만끽하며 돌아간다.</p>");
							$("#notify").append("<p>3초뒤에 스테이터스 페이지로 돌아갑니다.</p>");
							$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
						} else {
							$("#notify").append("<p>" + data.name + "에게서 도망쳤다.</p>");
							$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
						}
					}
			});
			if (deathStatus = 1) {
				setTimeout(function() {
					replace_battle(id);
					}, 3000);// 3초 후 실행
			} else {
				replace_battle(id);
			}
			show_NewStatus(id);
		}
		
		function getEnemyItem(form) {
			console.log(form.size);
			console.log(form.getEnemyItem_0.value);
			console.log(form.getEnemyItem_1.value);
			console.log(form.getEnemyItem_2.value);
			
			/* form.submit(); */
		}
		
		function replace_battle(id) {
			var id = id;
			var url = `/usr/player/battle?id=\${id}`;
			location.replace(url);
		}
		
		function actionTab_cancle() {
			let actionTab = $('#actionTab');
			actionTab.html(originalForm);
			
			$('#locationTab').load(location.href+' #locationTab');

			originalForm = null;
		}
		
		function stopTimer() {
			  clearInterval(timer);
		}
	</script>
	
	<section class="mt-8 bg-black text-white h-screen">
		<div class="container mx-auto text-center">
			<div class="mb-20 text-4xl text-red-400" id="nowLocation">전투 발생</div>
				<div class="text-xl flex" style="height: 420px;">
					<div style="border: 2px solid white; width: 40%; position:relative;" >
						<div>
							<ul style="border: 2px solid white; text-align: center;">
								<li>스테이터스</li>
							</ul>
							<div class="flex" style="width: 100%;">
								<ul style="width: 50%;" id="status">
									<li class="flex mb-2 mt-2" >
										<div style="width: 40%">
											<ul>
												<li><img class="ml-2" src="/resource/images/${player1.image }.jpg"/></li>
											</ul>
										</div>
										<div class="mt-5" style="width: 70%">
											<ul class="text-center"><li>이름 : ${player1.name }</li></ul>
											<ul><li>Lv : ${player1.level }</li></ul>	
											<ul><li class="text-green-500">경험치 : ${player1.exp } / ${player1.maxExp }</li></ul>
										</div>
									</li>
									<li class="text-red-400" id="hp">체력 : ${player1.hp } / ${player1.maxHp}</li>
									<li class="text-yellow-400" id="sp">스테미나 : ${player1.sp } / ${player1.maxSp }</li>
									<li id="attack">공격력 : ${player1.attackPoint }  (${player1.increseAttackPoint })</li>
									<li id="defence">방어력 : ${player1.defencePoint }  (${player1.increseDefencePoint })</li>
									<li id="hit">적중 : ${player1.hitRate }  (${player1.increseHitRate })</li>
									<li id="miss">회피 : ${player1.missRate }  (${player1.increseMissRate })</li>
								</ul>
								<ul style="width: 50%;">
									<li><ul id="equipmentList">
									<c:forEach var="equipment1" items="${equipments1 }" varStatus="status">
										<li class="equip" style="width: 100%">[${equipment1.category }] : <c:choose>
																						<c:when test="${equipment1.usedItemCode < 990}">
																							<span <c:if test="${equipment1.rarity == '1' }">style='color: white;'</c:if>
																									<c:if test="${equipment1.rarity == '2' }">style='color: #24b500;'</c:if>
																									<c:if test="${equipment1.rarity == '3' }">style='color: #0073ff;'</c:if>
																									<c:if test="${equipment1.rarity == '4' }">style='color: #fc008f;'</c:if>
																									<c:if test="${equipment1.rarity == '5' }">style='color: gold;'</c:if>
																									id="ename-${status.index }">
																								${equipment1.name }
																								<c:if test="${equipment1.increseHP != 0}">
																									<b class="text-red-400 text-base" id="eiHP-${status.index }">
																										(${equipment1.increseHP })
																									</b>
																								</c:if>
																								<c:if test="${equipment1.increseSP != 0}">
																									<b class="text-yellow-400 text-base" id="eiSP-${status.index }">
																										(${equipment1.increseSP })
																									</b>
																								</c:if>
																								<c:if test="${equipment1.increseAttackPoint != 0}">
																									<b class="text-base" style="color: #ff0000" id="eiAP-${status.index }">
																										(${equipment1.increseAttackPoint })
																									</b>
																								</c:if>
																								<c:if test="${equipment1.increseDefencePoint != 0}">
																									<b class="text-base" style="color: #0000ff" id="eiDP-${status.index }">
																										(${equipment1.increseDefencePoint })
																									</b>
																								</c:if>
																								<c:if test="${equipment1.category eq '무기' 
																											|| equipment1.category eq '머리'
																											|| equipment1.category eq '상의'
																											|| equipment1.category eq '하의'
																											|| equipment1.category eq '팔'
																											|| equipment1.category eq '신발'}">
																									<font class="text-sm font-bold mr-2" style="color: pink;" id="eDP-${status.index }">
																											내구도 : ${equipment1.durabilityPoint }
																									</font>
																								</c:if>
																							</span>
																						</c:when>
																						<c:otherwise>없음</c:otherwise>
																					</c:choose></li>
																				</c:forEach>
																			</ul>
																		</li>
								</ul>
							</div>
							<div class="text-left absolute overflow-y-scroll" style="border-top: 1px solid white; width:100%; height: 18.5%;">
								<ul class="ml-2">
									<li class="flex">
										<span>스킬</span>
										<div class="ml-10" id="skill-list">
											<span id="active-effect">
												<c:choose>
													<c:when test="${player1.actionType == '1'}">기본</c:when>
													<c:when test="${player1.actionType == '2'}">선제공격</c:when>
													<c:when test="${player1.actionType == '3'}">방어태세</c:when>
													<c:when test="${player1.actionType == '4'}">주변탐색</c:when>
													<c:when test="${player1.actionType == '5'}">사주경계</c:when>
													<c:when test="${player1.actionType == '6'}">은밀기동</c:when>
												</c:choose>
											</span>
											<c:forEach var="skill1" items="${skills1}">
												<span> / ${skill1.name}</span>
											</c:forEach>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="relative" style="width: 5%;"><span class="text-3xl" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);">VS</span></div>
					
					<div style="border: 2px solid white; width: 40%; position:relative;" >
						<div id="player2-status">
							<ul style="border: 2px solid white; text-align: center;">
								<li>스테이터스</li>
							</ul>
							<div class="flex" style="width: 100%;">
								<ul style="width: 50%;" id="status">
									<li class="flex mb-2 mt-2" >
										<div style="width: 40%">
											<ul>
												<li><img class="ml-2" src="/resource/images/${player2.image }.jpg"/></li>
											</ul>
										</div>
										<div class="mt-5" style="width: 70%">
											<ul class="text-center"><li>이름 : ${player2.name }</li></ul>
											<ul><li>Lv : 비공개</li></ul>	
											<ul><li>살해 수 : ${player2.killPoint } 명</li></ul>
										</div>
									</li>
									<li class="text-red-400" id="hp">체력 : 비공개</li>
									<li class="text-yellow-400" id="sp">스테미나 : 비공개</li>
									<li id="attack">공격력 : 비공개</li>
									<li id="defence">방어력 : 비공개</li>
									<li id="hit">적중 : 비공개</li>
									<li id="miss">회피 : 비공개</li>
								</ul>
								<ul style="width: 50%;">
									<li><ul id="equipmentList">
									<c:forEach var="equipment2" items="${equipments2 }" varStatus="status">
										<li class="equip" style="width: 100%">[${equipment2.category }] : 
																						<c:if test="${equipment2.category eq '무기' 
																							|| equipment2.category eq '상의'}">
																							<span id="ename-${status.index }">
																								${equipment2.name }
																							</span>
																						</c:if>
																						<c:if test="${equipment2.category eq '머리'
																							|| equipment2.category eq '하의'
																							|| equipment2.category eq '팔'
																							|| equipment2.category eq '신발'}">
																							<span id="ename-${status.index }">
																								비공개
																							</span>
																						</c:if>
																					</li>
																				</c:forEach>
																			</ul>
																		</li>
								</ul>
							</div>
							<div class="text-left absolute overflow-y-scroll" style="border-top: 1px solid white; width:100%; height: 18.5%;">
								<ul class="ml-2">
									<li class="flex">
										<span>스킬</span>
										<div class="ml-10" id="skill-list">
											<span id="active-effect">
												비공개
											</span>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
					
					<div class="ml-2" style="border: 2px solid white; width: 15%; position:relative;" >
						<div id="actionTab">
							<ul style="border: 2px solid white">
								<li>
									전투행동
								</li>
							</ul>
							<ul>
								<li class="flex justify-center">
									<ul class="active-list ml-2 mr-2 mt-2">
										<li class="mb-2">
											<button class="active" onclick="action_attack(${rq.getLoginedMemberId() }, ${player1.id }, ${player2.id })">공격</button>
										</li>
										<li class="mb-2">
											<button class="active" onclick="action_skill_form(${rq.getLoginedMemberId() }, ${player1.id }, ${player2.id })">스킬사용</button>
										</li>
										<li class="mb-2">
											<button class="active" onclick="action_run(${rq.getLoginedMemberId() }, ${player2.memberId })">도주</button>
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
						<li class="text-left ml-2" id="notify"><p>${player2.name }을(를) 발견했다.</p><p>무엇을 하지?</p></li>
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
												