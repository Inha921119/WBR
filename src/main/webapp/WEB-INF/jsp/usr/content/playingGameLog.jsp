<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="진행중인 게임" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 text-xl h-screen">
		<c:forEach var="gameLog" items="${gameLogs }" varStatus="status">
			<li class="gameLog ml-10 mt-2" >
				<span class="text-base">${gameLog.regDate.substring(0,4) }년 ${gameLog.regDate.substring(6,7) }월 ${gameLog.regDate.substring(8,10) }일 ${gameLog.regDate.substring(11,13) }시 ${gameLog.regDate.substring(14,16) }분</span>
				<span class="text-base ml-2">${gameLog.logBody }</span>
			</li>
		</c:forEach>
	</section>
<%@ include file="../common/foot.jsp" %>