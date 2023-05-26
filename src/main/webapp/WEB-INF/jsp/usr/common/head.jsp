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
	<div class="navbar">
		<div class="flex-1">
			<a href="/" class="btn btn-ghost normal-case text-xl"><img src="/resource/images/logo.png"/></a>
		</div>
		<div class="flex-none" id="category">
			<ul class="px-1">
				<li class="h-full px-3 flex items-center btn btn-ghost normal-case text-xl mt-4">게임소식
					<ul>
						<li><a href="/usr/article/list?boardId=1"><span>공지사항</span></a></li>
						<li><a href="/usr/article/list?boardId=3"><span>업데이트</span></a></li>
						<li><a href="/usr/article/list?boardId=4"><span>이벤트</span></a></li>
					</ul>
				</li>
				<li class="h-full px-3 flex items-center btn btn-ghost normal-case text-xl mt-4">커뮤니티
					<ul>
						<li><a href="/usr/article/list?boardId=2"><span>자유게시판</span></a></li>
						<li><a href="/usr/article/list?boardId=2"><span>명예의전당</span></a></li>
					</ul>
				</li>
				<li class="h-full px-3 flex items-center btn btn-ghost normal-case text-xl mt-4">가이드
					<ul>
						<li><a href="/usr/article/list?boardId=2"><span>아이템 목록 및 조합법</span></a></li>				
						<li><a href="/usr/article/list?boardId=2"><span>스킬목록</span></a></li>				
						<li><a href="/usr/article/list?boardId=2"><span>경기구역 지도 및 설명</span></a></li>				
					</ul>
				</li>
				<c:if test="${rq.getLoginedMemberId() == 0 }">
					<li><a class="h-full px-3 flex items-center btn btn btn-ghost normal-case text-xl" href="/usr/member/login"><span>로그인</span></a></li>
					<li><a class="h-full px-3 flex items-center btn btn btn-ghost normal-case text-xl" href="/usr/member/join"><span>회원가입</span></a></li>
				</c:if>
				<c:if test="${rq.getLoginedMemberId() != 0  }">
					<li><a class="h-full px-3 flex items-center btn btn-ghost normal-case text-xl" href="/usr/member/myPage?id=${rq.getLoginedMemberId()}"><span>마이페이지</span></a></li>
					<li><a class="h-full px-3 flex items-center btn btn-ghost normal-case text-xl" href="/usr/member/myPage?id=${rq.getLoginedMemberId()}"><span>참가신청</span></a></li>
					<li><a class="h-full px-3 flex items-center btn btn-ghost normal-case text-xl" href="/usr/member/doLogout"><span>로그아웃</span></a></li>
				</c:if>
			</ul>
		</div>
	</div>