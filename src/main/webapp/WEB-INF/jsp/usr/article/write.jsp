<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Write" />
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/toastUiEditorLib.jsp" %>
	<section class="mt-8 text-xl h-screen">
		<div class="container mx-auto px-3 text-black">
			<form action="doWrite" method="POST" onsubmit="submitForm(this); return false;">
				<div class="table-box-type-1 bg-white">
					<table>
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>게시판</th>
								<td>
									<label>
										<input type='radio' name='boardId' value='1' />
										공지사항
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>
										<input type='radio' name='boardId' value='2' checked />
										자유 게시판
									</label>
								</td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input class="input input-bordered w-full text-black" type="text" name="title" placeholder="제목을 입력해주세요"/></td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<div class="toast-ui-editor bg-white">
										<script type="text/x-template"></script>
									</div>
									<input name="body" type="hidden" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<div class="btns flex justify-between items-end mt-2">
				<a class="btn btn-active" type="button" onclick="history.back();">뒤로가기</a>
				<button class="btn btn-active">작성</button>
			</div>
		</form>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>