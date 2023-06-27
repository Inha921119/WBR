<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="WebBattleRoyale" />
<%@ include file="../common/head.jsp" %>
	<script>
	
		originalForm = null;
		originalId = null;
		
		var timer = null;
		
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
				async: false,
				success : function(data) {
					if (data == type) {
						$("#notify").append("<p>이미 적용중인 행동유형입니다.</p><p>이제 무엇을 하지?</p>");
						$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
					} else {
						$.ajax({
							url:"../player/changeActionType?memberId="+memberId+"&type="+type,
							type:"get",
							datatype:"text",
							async: false,
							success : function(data) {
								$("#active-effect").text(data);
								$("#notify").append("<p>행동유형을 " + data + "(으)로 변경했다.</p><p>이제 무엇을 하지?</p>");
								$('#alert-section').scrollTop($('#alert-section')[0].scrollHeight);
							}
						});
					}
				}
			});
			show_NewStatus(memberId);
		}
		
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
		
		function getSkill_form(memberId, playerId, level, skillPoint) {
			var memberId = memberId;
			var playerId = playerId;
			var level = level;
			var skillPoint = skillPoint;
			
			if(originalForm != null) {
				actionTab_cancle();
			}
			
			$.get('../player/getSkillListByLv', {
				memberId : memberId,
				playerId : playerId,
				level : level,
			}, function(data){
				
				let actionTab = $('#actionTab');
				
				originalForm = actionTab.html();
				
			 	let addHtml = `
					<ul>
						<li class="flex justify-center">
							<ul class="active-list ml-2 mr-2 mt-2">
							<li class="flex justify-between text-xl text-blue-600 mb-5"><span>Point</span><span>\${skillPoint}</span></li>
							<li><ul class="overflow-y-scroll h-64">`
				
			 	for(var i=0; i < data.length; i++) {
			 		var skill = data[i];
			 		addHtml = addHtml + "<li class='flex justify-between'>"+"<span class='text-lg'>"+skill.name+"</span>"+"<span class='text-sm'>Point : "+skill.needSkillPoint+"<button class='mybtn ml-2'>습득</button></span>"+"</li>";
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
		
		function recipe_form(playerId) {
			var playerId = playerId;
			
			if(originalForm != null) {
				actionTab_cancle();
			}
			
			$.get('../player/getRecipeByPlayerId', {
				playerId : playerId,
			}, function(data){
				
				let actionTab = $('#actionTab');
				
				originalForm = actionTab.html();
				
				let addHtml = `
					<ul>
						<li class="flex justify-center">
							<ul class="active-list ml-2 mr-2 mt-2">
							<li class="flex justify-between text-xl mb-5"><span>조합 가능한 아이템</span></li>
							<li><ul class="overflow-y-scroll h-64">`
				
		 		if (data.length == 0) {
		 			addHtml = addHtml + "<li class='flex justify-between'><span class='text-lg'>없음</span></li>";
		 		} else{
		 			for(var i=0; i < data.length; i++) {
				 		var item = data[i];
				 		if (item.recipeItem3 != 0) {
				 			addHtml = addHtml + "<li class='flex justify-between'><span class='text-sm'>" + item.name + " = " + item.recipeItem1Name + " + " + item.recipeItem2Name + " + " + item.recipeItem3Name;
				 		} else{
					 		addHtml = addHtml + "<li class='flex justify-between'><span class='text-sm'>" + item.name + " = " + item.recipeItem1Name + " + " + item.recipeItem2Name;
				 		}
			 		}
			 		addHtml = addHtml + `</span><span class='text-sm'><button class='mybtn ml-2'>조합</button></span></li>`
				}
			 		addHtml = addHtml + `</ul></li><li class="flex mb-2"><button class="active mt-5" onclick="actionTab_cancle()"><span>돌아가기</span></button>
								</li>
							</ul>
						</li>
					</ul>`;
				
				actionTab.empty().html("");
				actionTab.append(addHtml);
			}, 'json');
		}	
		
		function actionTab_cancle() {
			let actionTab = $('#actionTab');
			actionTab.html(originalForm);
			
			$('#actionTab').load(location.href+' #actionTab');

			originalForm = null;
		}
		
		function stopTimer() {
			  clearInterval(timer);
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
											<c:forEach var="skill" items="${skills}">
												<span> / ${skill.name}</span>
											</c:forEach>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
						<div class="ml-2" style="border: 2px solid white; width: 23%; position:relative;">
							<div>
								<ul style="border: 2px solid white">
									<li>
										아이템 목록
									</li>
								</ul>
								<ul class="text-left p-1"  id="itemList">
									<c:forEach var="inventory" items="${inventory }" varStatus="status">
									<li>
										<c:if test="${inventory.quantity != 0 }">
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
													<font class="text-sm font-bold mr-2" style="color: green;" id="quan-${status.count }">
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
												<button class="mybtn" onclick="useItem(${rq.getLoginedMemberId() }, ${rq.player.id}, ${inventory.itemId }, ${status.count })">
													<span>사용</span>
												</button>
											</c:if>
											<c:if test="${inventory.category eq '무기' 
															|| inventory.category eq '머리'
															|| inventory.category eq '상의'
															|| inventory.category eq '하의'
															|| inventory.category eq '팔'
															|| inventory.category eq '신발'}">
												<button class="mybtn" onclick="equipItem(${rq.getLoginedMemberId() }, ${rq.player.id}, ${inventory.itemId }, ${status.count })">
													<span>장착</span>
												</button>
											</c:if>
											
											<button class="mybtn" onclick="deleteItem(${rq.getLoginedMemberId() }, ${rq.player.id}, ${inventory.itemId }, ${inventory.id }, ${status.count })">
												<span>버림</span>
											</button>
										</c:if>
									</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					
					<div class="ml-2" style="border: 2px solid white; width: 17%; position:relative;" >
						<div id="actionTab">
							<ul style="border: 2px solid white" id="locationTab">
									<li>
										위치 이동 : <select class="text-black border-black" name="location" onchange="locationMove(${rq.getLoginedMemberId() }, this)">
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
											<button class="active" onclick="recipe_form(${rq.player.id})">조합법 연구</button>
										</li>
										<li class="flex mb-2">
											<button class="active" onclick="getSkill_form(${rq.getLoginedMemberId() }, ${rq.player.id}, ${rq.player.level}, ${rq.player.skillPoint})">스킬 습득</button>
										</li>
										<li class="flex mb-2">
											<button class="active text-red-400" style="width:50%" onclick="heal_form(${rq.getLoginedMemberId() }, 0)"><span>치료</span></button>
											<button class="active text-yellow-400" style="width:50%" onclick="heal_form(${rq.getLoginedMemberId() }, 1)"><span>휴식</span></button>
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
												