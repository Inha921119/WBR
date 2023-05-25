<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.name } 게시판" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 text-xl h-screen">
		<div class="container mx-auto px-3 text-black">
				<div class="mb-2 flex justify-between items-end">
					<div><span>총 : ${articlesCnt } 개</span></div>
					<form>
						<input type="hidden" name="boardId" value="${board.id }"/>
						<select data-value="${searchKeywordType }" class="select select-bordered" name='searchKeywordType'>
						     <option value='title' selected> 제목 </option>
						     <option value='body'> 내용 </option>
						     <option value='title,body'> 제목 + 내용 </option>
						</select>
						<c:if test="${searchKeyword.trim() != '' }">
							<input class="ml-2 w-80 input input-bordered" type="text" name="searchKeyword" value="${searchKeyword }" maxlength="20"/>
						</c:if>
						<c:if test="${searchKeyword.trim() == '' }">
							<input class="ml-2 w-80 input input-bordered" type="text" name="searchKeyword" placeholder="검색어를 입력해주세요" maxlength="20"/>
						</c:if>
						<button class="ml-2 btn btn-active">검색</button>
					</form>
				</div>
			<div class="overflow-x-auto">
				<table class="table w-full">
					<colgroup>
						<col width="60"/>
						<col width="200"/>
						<col />
						<col width="60"/>
						<col width="40"/>
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>날짜</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>추천</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles }">
							<tr class="hover bg-gray-700">
								<td>${article.id }</td>
								<td>${article.regDate.substring(2,16) }</td>
								<td><a href="detail?id=${article.id }">${article.title }</a></td>
								<td>${article.writerName }</td>
								<td>${article.hitCount }</td>
								<td>${article.sumReactionPoint }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="mt-2 flex justify-end">
				<c:if test="${rq.getLoginedMemberId() != 0 }">
						<a class="btn btn-active" href="/usr/article/write">WRITE</a>
				</c:if>
			</div>
			<div class="mt-2 flex justify-center">
				<div class="btn-group">
					<c:set var="pageMenuLen" value="5" />
					<c:set var="startPage" value="${page - pageMenuLen >= 1 ? page - pageMenuLen : 1 }" />
					<c:set var="endPage" value="${page + pageMenuLen <= pagesCount ? page + pageMenuLen : pagesCount }" />
					
				 	<c:set var="pageBaseUri" value="?boardId=${board.id }&searchKeywordType=${searchKeywordType }&searchKeyword=${searchKeyword }">
					</c:set>
					
					<c:if test="${page == 1  }">
						<a class="btn btn-sm btn-disabled" >«</a>
						<a class="btn btn-sm btn-disabled" >&lt;</a>
					</c:if>
					
					<c:if test="${page > 1  }">
						<a class="btn btn-sm" href="${pageBaseUri }&page=1">«</a>
						<a class="btn btn-sm" href="${pageBaseUri }&page=${page - 1 }">&lt;</a>
					</c:if>	
				
					<c:forEach begin="${startPage }" end="${endPage }" var="i">
						<a class="btn btn-sm ${page == i ? 'btn-active' : '' }" href="${pageBaseUri }&page=${i }">${i }</a>
					</c:forEach>
					
					<c:if test="${page < pagesCount  }">
						<a class="btn btn-sm" href="${pageBaseUri }&page=${page + 1 }">&gt;</a>
						<a class="btn btn-sm" href="${pageBaseUri }&page=${pagesCount }">»</a>
					</c:if>	
					
					<c:if test="${page == pagesCount  }">
						<a class="btn btn-sm btn-disabled" >&gt;</a>
						<a class="btn btn-sm btn-disabled" >»</a>
					</c:if>
					
				</div>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>