<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Modify" />
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/toastUiEditorLib.jsp" %>
	<section class="mt-8 text-xl h-screen">
		<div class="container mx-auto px-3 text-black">
			<form action="doModify" method="POST" onsubmit="submitForm(this); return false;">
				<input type="hidden" name="id" value="${article.id }"/>
				<div class="table-box-type-1">
					<table>
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>번호</th>
								<td>${article.id }</td>
							</tr>
							<tr>
								<th>작성날짜</th>
								<td>${article.regDate }</td>
							</tr>
							<tr>
								<th>수정날짜</th>
								<td>${article.updateDate }</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${article.writerName }</td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input class="input input-bordered w-full" type="text" name="title" value="${article.title }" placeholder="제목을 입력해주세요"/></td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<div class="toast-ui-editor">
										<script type="text/x-template">${article.body }</script>
									</div>
									<input name="body" type="hidden" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<div class="btns flex justify-between items-end mt-2">
				<a class="btn btn-active" type="button" onclick="history.back();">뒤로가기</a>
				<button class="btn btn-active">수정</button>
			</div>
		</form>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>