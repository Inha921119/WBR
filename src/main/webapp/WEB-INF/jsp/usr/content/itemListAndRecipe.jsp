<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="아이템 목록 및 조합법" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 text-base h-screen ">
		<div class="">
			<div class="category flex justify-center">
				<div class="hp mr-20">
					<span class="text-lg font-bold">체력회복</span>
					<br />
					<br />
					<span>약초</span>
					<br />
					<span>붕대 = 천쪼가리 + 천쪼가리</span>
					<br />
					<span>회복약 = 빈 캡슐 + 약초</span>
					<br />
					<span>구급상자 = 빈 상자 + 약초 + 붕대</span>
				</div>
				<div class="sp mr-20">
					<span class="text-lg font-bold">스테미나회복</span>
					<br />
					<br />
					<span>빵</span>
					<br />
					<span>컵라면</span>
					<br />
					<span>에너지바</span>
					<br />
					<span>물</span>
					<br />
					<span>음료수</span>
					<br />
					<span>통조림</span>
					<br />
					<span>전투식량</span>
				</div>
				<div class="weapon mr-20">
					<span class="text-lg font-bold">무기</span>
					<br />
					<br />
					<span>단검</span>
					<br />
					<span>호미</span>
					<br />
					<span>낫</span>
					<br />
					<span>돌도끼</span>
					<br />
					<span>철도끼</span>
					<br />
					<span>목검</span>
					<br />
					<span>나무배트</span>
					<br />
					<span>경광봉</span>
					<br />
					<span>대나무창</span>
					<br />
					<span>삼지창</span>
					<br />
					<span>골프채</span>
					<br />
					<span>쌍절곤</span>
					<br />
					<span>정글도</span>
					<br />
					<span>철퇴</span>
					<br />
					<span>사슬채찍</span>
					<br />
					<span>사슬낫</span>
					<br />
					<span>3단봉</span>
					<br />
					<span>장검</span>
					<br />
					<span>너클</span>
					<br />
					<span>냄비뚜껑</span>
					<br />
					<span>후라이팬</span>
					<br />
					<span>경찰방패</span>
					<br />
					<span>활</span>
					<br />
					<span>석궁</span>
					<br />
					<span>권총</span>
					<br />
					<span>소총</span>
					<br />
					<span>돌</span>
					<br />
					<span>사냥돌</span>
					<br />
					<span>화염병</span>
					<br />
					<span>수리검</span>
					<br />
					<span>승표 = 수리검 + 밧줄</span>
					<br />
					<span>유성추 = 쇠공 + 밧줄</span>
					<br />
					<span>투창</span>
					<br />
					<span>다이너마이트</span>
				</div>
				<div class="armor mr-20">
					<span class="text-lg font-bold">방어구</span>
					<br />
					<br />
					<span>안전모</span>
					<br />
					<span>싸이클헬멧</span>
					<br />
					<span>오토바이헬멧</span>
					<br />
					<span>방독면</span>
					<br />
					<span>사슬헬멧</span>
					<br />
					<span>강철헬멧</span>
					<br />
					<span>방탄모자</span>
					<br />
					<span>안전조끼</span>
					<br />
					<span>패딩</span>
					<br />
					<span>가죽재킷</span>
					<br />
					<span>보호복</span>
					<br />
					<span>방화복</span>
					<br />
					<span>사슬갑옷</span>
					<br />
					<span>방검복</span>
					<br />
					<span>강철갑옷</span>
					<br />
					<span>방탄조끼</span>
					<br />
					<span>청바지</span>
					<br />
					<span>패딩바지</span>
					<br />
					<span>군용바지</span>
					<br />
					<span>사슬갑옷하의</span>
					<br />
					<span>강철갑옷하의</span>
					<br />
					<span>팔토시</span>
					<br />
					<span>아대</span>
					<br />
					<span>싸이클 팔꿈치 보호대</span>
					<br />
					<span>사슬갑옷 팔 보호대</span>
					<br />
					<span>강철갑옷 팔 보호대</span>
					<br />
					<span>운동화</span>
					<br />
					<span>장화</span>
					<br />
					<span>전투화</span>
					<br />
					<span>사슬부츠</span>
					<br />
					<span>강철부츠</span>
				</div>
				<div class="etc">
					<span class="text-lg font-bold">재료</span>
					<br />
					<br />
					<span>실</span>
					<br />
					<span>솜</span>
					<br />
					<span>천쪼가리</span>
					<br />
					<span>나무</span>
					<br />
					<span>대나무</span>
					<br />
					<span>숫돌</span>
					<br />
					<span>부직포</span>
					<br />
					<span>유리병</span>
					<br />
					<span>쇠공</span>
					<br />
					<span>밧줄</span>
					<br />
					<span>화살</span>
					<br />
					<span>못</span>
					<br />
					<span>망치</span>
					<br />
					<span>공구세트</span>
					<br />
					<span>빈캡슐</span>
					<br />
					<span>빈상자</span>
					<br />
					<span>사슬</span>
					<br />
					<span>철판</span>
					<br />
					<span>화약</span>
					<br />
					<span>총알</span>
				</div>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>