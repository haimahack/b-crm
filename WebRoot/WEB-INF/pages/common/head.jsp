<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="h-header">
	<c:choose>
		<c:when test="${not empty sessionScope.get('session_user')}">
			<ul>
				<li class="h-f1"><i class="fa fa-fw fa-user"></i><a href="javascript:;"> ${sessionScope.get('session_user_username')}</a></li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul>
				<li><a href="javascript:void(0);"><i class="fa fa-fw fa-user"></i> 请登录</a></li>
			</ul>
		</c:otherwise>
	</c:choose>
</div>