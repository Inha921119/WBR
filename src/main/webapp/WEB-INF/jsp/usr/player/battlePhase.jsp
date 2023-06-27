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
		
		function useItem(memberId, playerId, itemId, scount) {
			var memberId = memberId;
			var playerId = playerId;
			var itemId = itemId;
			var scount = scount;
			var quan = 1
			
			$.ajax({
				url:"../player/useItem?playerId="+playerId+"&itemId="+itemId,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
					$("#quan-" + scount).text("수량 : " + data.quantity);
					quan = data.quantity;
					if (data.itemId <= 50) {
						$("#notify").append("<p>" + data.name + "을(를) 사용하여 체력이 " + data.recoveryHP + " 회복되었다</p>");
					}else if (data.itemId > 50) {
						$("#notify").append("<p>" + data.name + "을(를) 사용하여 스테미나가 " + data.recoverySP + " 회복되었다</p>");
					}
					$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
					if (data.quantity == 0) {
				    	$('#itemList').load(location.href+' #itemList');
				    }
				}
			});
			show_NewStatus(memberId);
		}
		
		function deleteItem(memberId, playerId, itemId, invenId, scount) {
			var memberId = memberId;
			var playerId = playerId;
			var itemId = itemId;
			var invenId = invenId;
			var scount = scount;
			var quan = 1
			
			$.ajax({
				url:"../player/deleteItem?playerId="+playerId+"&itemId="+itemId+"&invenId="+invenId,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
					$("#quan-" + scount).text("수량 : " + data.quantity);
					quan = data.quantity;
					$("#notify").append("<p>" + data.name + "을(를) 버렸다</p>");
					$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
				    $('#itemList').load(location.href+' #itemList');
				}
			});
			show_NewStatus(memberId);
		}
		
		function equipItem(memberId, playerId, itemId, scount) {
			var memberId = memberId;
			var playerId = playerId;
			var itemId = itemId;
			var scount = scount;
			
			$.ajax({
				url:"../player/equipItem?playerId="+playerId+"&itemId="+itemId,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
					for (var i in data) {
						$("#ename-" + i).text(data[i].name);
						$("#eiHP-" + i).text("(" + data[i].increseHP + ")");
						$("#eiSP-" + i).text("(" + data[i].increseSP + ")");
						$("#eiAP-" + i).text("(" + data[i].increseAttackPoint + ")");
						$("#eiDP-" + i).text("(" + data[i].increseDefencePoint + ")");
						$("#eDP-" + i).text("(" + data[i].durabilityPoint + ")");
					}
					$('#itemList').load(location.href+' #itemList');
				    $('#equipmentList').load(location.href+' #equipmentList');
					$("#notify").append("<p>장비를 장착하였다</p>");
					$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
				}
			});
			show_NewStatus(memberId);
		}
		
		function equipOff(memberId, playerId, itemId, equipId, scount) {
			var memberId = memberId;
			var playerId = playerId;
			var itemId = itemId;
			var equipId = equipId;
			var scount = scount;
			
			$.ajax({
				url:"../player/equipOff?playerId="+playerId+"&itemId="+itemId+"&equipId="+equipId,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
					for (var i in data) {
						$("#ename-" + i).text(data[i].name);
						$("#eiHP-" + i).text("(" + data[i].increseHP + ")");
						$("#eiSP-" + i).text("(" + data[i].increseSP + ")");
						$("#eiAP-" + i).text("(" + data[i].increseAttackPoint + ")");
						$("#eiDP-" + i).text("(" + data[i].increseDefencePoint + ")");
						$("#eDP-" + i).text("(" + data[i].durabilityPoint + ")");
					}
					$('#itemList').load(location.href+' #itemList');
				    $('#equipmentList').load(location.href+' #equipmentList');
					$("#notify").append("<p>장비를 해제하였다</p>");
					$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
				}
			});
			show_NewStatus(memberId);
		}
		function heal(memberId, sec, type) {
			var memberId = memberId;
			var healSec = sec;
			var type = type;
			
			$.ajax({
				url:"../player/heal?memberId="+memberId+"&healSec="+sec+"&type="+type,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
					if (type == 0) {
						$("#notify").append("<p>체력을 " + healSec + "만큼 회복하였다</p>");
					}else if (type == 1) {
						$("#notify").append("<p>스테미나를 " + healSec + "만큼 회복하였다</p>");
					}
					$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
					}
			});
			show_NewStatus(memberId);
		}
		
		function heal_form(memberId, type) {
			
			var oldTime = Date.now();
			var type = type;
			var sec = 0;
			var typeName = "";
			var typeStat = "";
			var typeColor = "";
			
			
			if(originalForm != null) {
				actionTab_cancle();
			}
			
			$.get('../player/getPlayerByMemberId', {
				memberId : memberId,
			}, function(data){
				
				let actionTab = $('#actionTab');
				
				originalForm = actionTab.html();
				
				timer = setInterval(() => {
				var currentTime = Date.now();
			    // 경과한 밀리초 가져오기
			    var diff = currentTime - oldTime;
			    
			    // 초(second) 단위 변환하기
			    sec = Math.floor(diff / 1000);
			    
			    // HTML에 문자열 넣기
			    document.querySelector('#currentTime').innerHTML = `\${sec}초 경과`;
			    if (type == 0) {
			    	document.querySelector('#healButton').innerHTML = `<button class="active text-red-400" onclick="heal(\${memberId}, \${sec}, \${type}); actionTab_cancle(); stopTimer(\${timer})"><span>치료완료</span></button>`;
			    } else if (type == 1) {
			    	document.querySelector('#healButton').innerHTML = `<button class="active text-yellow-400" onclick="heal(\${memberId}, \${sec}, \${type}); actionTab_cancle(); stopTimer(\${timer})"><span>휴식완료</span></button>`;
			    }
				    
				}, 1000);	
			
				if (type == 0) {
					typeName = "치료";
					typeStat = "체력";
					typeColor = "red";
			    } else if (type == 1) {
			    	typeName = "휴식";
					typeStat = "스테미나";
					typeColor = "yellow";
			    }
				
			 	
			 	let addHtml = `
					<ul>
						<li class="flex justify-center">
							<ul class="active-list ml-2 mr-2 mt-10">
								<li class="text-\${typeColor}-400 text-center"><span>\${typeStat}을(를) 회복중입니다.</span></li>
								<li><span class="text-\${typeColor}-400" id="currentTime">\${sec}초 경과</span></li>
								<li class="flex mb-2" id="healButton">
							    	<button class="active text-\${typeColor}-400" onclick="heal(\${memberId}, \${sec}, \${type}); actionTab_cancle(); stopTimer(\${timer})"><span>\${typeName}완료</span></button>
								</li>
							</ul>
						</li>
					</ul>`;
				
				actionTab.empty().html("");
				actionTab.append(addHtml);
			}, 'json');
		}	
		
		function action_attack(memberId, playerId1, playerId2) {
			var memberId = memberId;
			var playerId1 = playerId1;
			var playerId2 = playerId2;
			
			$.ajax({
				url:"../player/battlePhaseAttack?playerId1="+playerId1+"&playerId2="+playerId2,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
					$("#notify").append("<p>" + data.data2.name + "에게 " + data.numData1 + "의 데미지를 입혔다</p>");
					$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
					}
			});
			show_NewStatus(memberId);
			replace_battle(memberId);
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
							<li><ul class="overflow-y-scroll h-64">`
				
			 	for(var i=0; i < data.length; i++) {
			 		var skill = data[i];
			 		var damage = skill.attackPoint + skill.increseAttackPoint;
			 		addHtml = addHtml + "<li class='flex justify-between'>"+"<span class='text-lg'>"+skill.name+"<span class='text-sm text-red-400'>("+damage+")</span></span><button class='mybtn ml-2'>사용</button></li>";
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
		
		function action_run(id, memberId2) {
			var id = id;
			var memberId = memberId2;
			
			$.ajax({
				url:"../player/showStatus?memberId="+memberId,
				type:"get",
				datatype:"text",
				async: false,
				success : function(data) {
					$("#notify").append("<p>" + data.name + "에게서 도망쳤다.</p>");
					$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
					}
			});
			replace_battle(id);
			show_NewStatus(memberId);
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
									<li id="attack">공격력 : ${rq.player.attackPoint }  (${rq.player.increseAttackPoint })</li>
									<li id="defence">방어력 : ${rq.player.defencePoint }  (${rq.player.increseDefencePoint })</li>
									<li id="hit">적중 : ${rq.player.hitRate }  (${rq.player.increseHitRate })</li>
									<li id="miss">회피 : ${rq.player.missRate }  (${rq.player.increseMissRate })</li>
								</ul>
								<ul style="width: 50%;">
									<li><ul id="equipmentList">
									<c:forEach var="equipment" items="${equipments }" varStatus="status">
										<li class="equip" style="width: 100%">[${equipment.category }] : <c:choose>
																						<c:when test="${equipment.usedItemCode < 990}">
																							<span <c:if test="${equipment.rarity == '1' }">style='color: white;'</c:if>
																									<c:if test="${equipment.rarity == '2' }">style='color: #24b500;'</c:if>
																									<c:if test="${equipment.rarity == '3' }">style='color: #0073ff;'</c:if>
																									<c:if test="${equipment.rarity == '4' }">style='color: #fc008f;'</c:if>
																									<c:if test="${equipment.rarity == '5' }">style='color: gold;'</c:if>
																									id="ename-${status.index }">
																								${equipment.name }
																								<c:if test="${equipment.increseHP != 0}">
																									<b class="text-red-400 text-base" id="eiHP-${status.index }">
																										(${equipment.increseHP })
																									</b>
																								</c:if>
																								<c:if test="${equipment.increseSP != 0}">
																									<b class="text-yellow-400 text-base" id="eiSP-${status.index }">
																										(${equipment.increseSP })
																									</b>
																								</c:if>
																								<c:if test="${equipment.increseAttackPoint != 0}">
																									<b class="text-base" style="color: #ff0000" id="eiAP-${status.index }">
																										(${equipment.increseAttackPoint })
																									</b>
																								</c:if>
																								<c:if test="${equipment.increseDefencePoint != 0}">
																									<b class="text-base" style="color: #0000ff" id="eiDP-${status.index }">
																										(${equipment.increseDefencePoint })
																									</b>
																								</c:if>
																								<c:if test="${equipment.category eq '무기' 
																											|| equipment.category eq '머리'
																											|| equipment.category eq '상의'
																											|| equipment.category eq '하의'
																											|| equipment.category eq '팔'
																											|| equipment.category eq '신발'}">
																									<font class="text-sm font-bold mr-2" style="color: pink;" id="eDP-${status.index }">
																											내구도 : ${equipment.durabilityPoint }
																									</font>
																								</c:if>
																								<button class="mybtn" onclick="equipOff(${rq.getLoginedMemberId() }, ${rq.player.id}, ${equipment.usedItemCode }, ${equipment.id }, ${status.index })">
																									<span>해제</span>
																								</button>
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
													<c:when test="${rq.player.actionType == '1'}">기본</c:when>
													<c:when test="${rq.player.actionType == '2'}">선제공격</c:when>
													<c:when test="${rq.player.actionType == '3'}">방어태세</c:when>
													<c:when test="${rq.player.actionType == '4'}">주변탐색</c:when>
													<c:when test="${rq.player.actionType == '5'}">사주경계</c:when>
													<c:when test="${rq.player.actionType == '6'}">은밀기동</c:when>
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
						<div>
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
												