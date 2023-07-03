<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="WebBattleRoyale" />
<%@ include file="../common/head.jsp"%>

<section class="mt-8 bg-black text-red-400 h-screen">
	<div class="container mx-auto text-center text-4xl mt-24">
		<p>당신의 캐릭터는 사망하였습니다.</p>
		<p class="mt-6">진행중인 게임이 끝난 이후 새로운 캐릭터를 만들어 게임에 참가할 수 있습니다.</p>
	</div>
	<div class="text-center">
		<button class="mybtn mt-10" onclick="location.href='/';"><span>돌아가기</span></button>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>