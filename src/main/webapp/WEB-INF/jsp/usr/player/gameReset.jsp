<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="WebBattleRoyale" />
<%@ include file="../common/head.jsp"%>

<section class="mt-8 bg-black text-white h-screen">
	<div class="container mx-auto text-center text-4xl mt-24">
		<p>게임 데이터를 초기화 하고 새로운 게임을 시작할 수 있습니다.</p>
		<p class="mt-6">초기화를 하고 새로운 게임을 시작하시려면 아래의 버튼을 눌러주세요.</p>
	</div>
	<div class="text-center">
		<button class="mybtn mt-10" onclick="location.href='resetGame';"><span>게임 초기화 및 시작</span></button>
		<button class="mybtn mt-10 ml-80" onclick="location.href='/';"><span>돌아가기</span></button>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>