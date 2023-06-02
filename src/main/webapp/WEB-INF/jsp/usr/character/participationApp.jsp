<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="참가신청" />
<%@ include file="../common/head.jsp" %>

<script>
	let validCharName = '';

	function create_submitForm(form) {
		
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름를 입력해주세요');
			form.name.focus();
			return;
		}
		
		if (form.name.value != validCharName) {
			alert(form.name.value + '은(는) 사용할 수 없는 이름입니다');
			form.name.value = '';
			form.name.focus();
			return;
		}
		
		form.submit();
	}
	
	function charNameDupCheck(el) {
		let charNameDupCheckMsg = $('#charNameDupCheckMsg');

		charNameDupCheckMsg.empty();
		
		el.value = el.value.trim();
		
		if (el.value.length == 0) {
			charNameDupCheckMsg.removeClass('text-green-500');
			charNameDupCheckMsg.addClass('text-red-500');
			charNameDupCheckMsg.html('<span>이름은 필수 입력 정보입니다</span>');
			return;
		}
		
		$.get('charNameDupCheck', {
			name : el.value,
		}, function(data) {
			if (data.success) {
				charNameDupCheckMsg.removeClass('text-red-500');
				charNameDupCheckMsg.addClass('text-green-500');
				charNameDupCheckMsg.html(`<span>\${data.data1 }은(는) \${data.msg }</span>`);
				validCharName = data.data1;
				
			} else {
				charNameDupCheckMsg.removeClass('text-green-500');
				charNameDupCheckMsg.addClass('text-red-500');
				charNameDupCheckMsg.html(`<span>\${data.data1 }은(는) \${data.msg }</span>`);
				validCharName = '';
			}
			
		}, 'json');
	}
</script>

	<section class="mt-8 text-xl h-screen">
		<div class="container mx-auto px-3">
			<form action="createChar" method="POST" onsubmit="create_submitForm(this); return false;">
				<div class="table-box-type-1">
					<table>
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr height="105">
								<th>이름</th>
								<td>
									<input class="input input-bordered w-full max-w-xs text-black" type="text" name="name" placeholder="이름을 입력해주세요" onblur="charNameDupCheck(this);"/>
									<div id="charNameDupCheckMsg" class="text-sm mt-2 h-5 text-red-500"></div>
								</td>
							</tr>
							<tr>
								<th>성별</th>
								<td>
									<input class="mr-2" type="radio" id="male" name="gender" value=0 checked><label class="text-xl">남성</label>
								</td>
								<td>	
									<input class="mr-2" type="radio" id="female" name="gender" value=1><label class="text-xl">여성</label>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btns mt-2 flex justify-between">
					<a class="btn-text-link btn btn-active" onclick="history.back();">뒤로가기</a>
					<button class="btn-text-link btn btn-active">참가신청</button>
				</div>
			</form>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>