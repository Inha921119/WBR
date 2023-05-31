<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="참가신청" />
<%@ include file="../common/head.jsp" %>

<script>
	let validLoginId = '';

	function create_submitForm(form) {
		
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();
			return;
		}
		
		console.log(validLoginId);
		
		if (form.loginId.value != validLoginId) {
			alert(form.loginId.value + '은(는) 사용할 수 없는 아이디입니다');
			form.loginId.value = '';
			form.loginId.focus();
			return;
		}
		
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요');
			form.loginPw.focus();
			return;
		}
		
		form.loginPwChk.value = form.loginPwChk.value.trim();
		if (form.loginPwChk.value.length == 0) {
			alert('비밀번호 확인을 입력해주세요');
			form.loginPwChk.focus();
			return;
		}
		
		if (form.loginPw.value != form.loginPwChk.value) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.value = '';
			form.loginPwChk.value = '';
			form.loginPw.focus();
			return;
		}
		
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요');
			form.name.focus();
			return;
		}
		
		form.nickname.value = form.nickname.value.trim();
		if (form.nickname.value.length == 0) {
			alert('닉네임을 입력해주세요');
			form.nickname.focus();
			return;
		}
		
		form.cellphoneNum.value = form.cellphoneNum.value.trim();
		if (form.cellphoneNum.value.length == 0) {
			alert('전화번호를 입력해주세요');
			form.cellphoneNum.focus();
			return;
		}
		
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요');
			form.email.focus();
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
				validLoginId = data.data1;
				
			} else {
				charNameDupCheckMsg.removeClass('text-green-500');
				charNameDupCheckMsg.addClass('text-red-500');
				charNameDupCheckMsg.html(`<span>\${data.data1 }은(는) \${data.msg }</span>`);
				validLoginId = '';
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
									<div id="loginIdDupCheckMsg" class="text-sm mt-2 h-5 text-red-500"></div>
								</td>
							</tr>
							<tr>
								<th>성별</th>
								<td><input class="input input-bordered w-full max-w-xs text-black" type="password" name="sex"/></td>
							</tr>
							
							<tr>
								<td colspan="2"><button class="btn-text-link btn btn-active">참가신청</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div class="btns mt-2">
				<button class="btn-text-link btn btn-active" type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>