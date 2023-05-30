<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>
<!-- 파비콘 불러오기 -->
<link rel="shortcut icon" href="/resource/images/favicon.ico" />
<!-- 테일윈드 불러오기 -->
<!-- 노말라이즈, 라이브러리 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.min.css" />
<!-- 데이지 UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@2.31.0/dist/full.css" rel="stylesheet" type="text/css" />
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js" defer="defer"></script>

</head>
<body class="bg-black text-white h-full">
	<div class="flex justify-between ">
		<div class="inline-block mt-2">
			<a href="/" class="btn btn-ghost normal-case text-xl"><img src="/resource/images/logo.png"/></a>
		</div>
		<div class="inline-block mt-2">
			<ul class="px-1" id="category">
				<li class="h-full px-3 flex items-center whitespace-nowrap text-sm font-bold cursor-pointer mt-4">게임소식
					<ul class="py-4">
						<li><a href="/usr/article/list?boardId=1">공지사항</a></li>
						<li><a href="/usr/article/list?boardId=3">업데이트</a></li>
						<li><a href="/usr/article/list?boardId=4">이벤트</a></li>
						<li><a href="/usr/content/playingGameLog">진행중인 게임</a></li>
					</ul>
				</li>
				<li class="h-full px-3 flex items-center whitespace-nowrap text-sm font-bold cursor-pointer mt-4">커뮤니티
					<ul class="py-4">
						<li><a href="/usr/article/list?boardId=2">자유게시판</a></li>
						<li><a href="/usr/content/winnerRanking">명예의전당</a></li>
					</ul>
				</li>
				<li class="h-full px-3 flex items-center whitespace-nowrap text-sm font-bold cursor-pointer mt-4">가이드
					<ul class="py-4">
						<li><a href="/usr/content/itemListAndRecipe">아이템 목록 및 조합법</a></li>				
						<li><a href="/usr/content/skillList">스킬목록</a></li>				
						<li><a href="/usr/content/map">경기구역 지도 및 설명</a></li>				
					</ul>
				</li>				
				<c:if test="${rq.getLoginedMemberId() == 0 }">
					<li class="h-full px-3 flex items-center whitespace-nowrap text-sm font-bold cursor-pointer mt-4"><a href="/usr/member/login">로그인</a></li>
					<li class="h-full px-3 flex items-center whitespace-nowrap text-sm font-bold cursor-pointer mt-4"><a href="/usr/member/join">회원가입</a></li>
				</c:if>
				<c:if test="${rq.getLoginedMemberId() != 0  }">
					<li class="h-full px-3 flex items-center whitespace-nowrap text-sm font-bold cursor-pointer mt-4"><a href="/usr/member/myPage?id=${rq.getLoginedMemberId()}">마이페이지</a></li>
					<li class="h-full px-3 flex items-center whitespace-nowrap text-sm font-bold cursor-pointer mt-4"><a href="/usr/member/myPage?id=${rq.getLoginedMemberId()}">참가신청</a></li>
					<li class="h-full px-3 flex items-center whitespace-nowrap text-sm font-bold cursor-pointer mt-4"><a href="/usr/member/doLogout">로그아웃</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	
	<c:if test="${pageTitle != 'WebBettelRoyale' }">
		<section class="my-3 text-2xl">
			<div class="container font-bold mx-auto px-3">
				<h1>${pageTitle }&nbsp;</h1>
			</div>
		</section>
	</c:if>