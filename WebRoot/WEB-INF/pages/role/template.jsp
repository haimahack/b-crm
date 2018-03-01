<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<c:forEach var="role" varStatus="roleindex" items="${datas}">
	<tr>
		<td>
			${role.id}
			<c:if test="${roleindex.index==0 }">
				<input type="hidden" id="itemCount" value="${itemCount }">
			</c:if>
		</td>
		<td>${role.rname}</td>	
		<td>${role.userId}</td>
		<td class="tmui-tips" tip="${tz:formatDate(role.createTime,'yyyy-MM-dd HH:mm:ss')}">
			${tz:timeFormat(role.createTime)}
		</td>
		<td>${role.description}</td>
		<td>
			<tz:if test="${role.isDelete==0}">
				<tz:then><a href="javascript:void(0);" opid="${role.id }" mark="isDelete" val="1" onclick="tzAdmin.op(this)" class="red tzui-op">否</a></tz:then>
				<tz:else><a href="javascript:void(0);" opid="${role.id }" mark="isDelete" val="0" onclick="tzAdmin.op(this)" class="green tzui-op">是</a></tz:else>
			</tz:if>
		</td>
		<td>
			<a href="javascript:void(0);" class="tmui-tips" tip="分配角色" data-opid="${role.id }" onclick="tzRole.user(this)" ><i class="fa fa-fw fa-share-alt"></i></a>
			<a href="javascript:void(0);" class="tmui-tips" tip="权限" data-opid="${role.id }" onclick="tzRole.permission(this)" ><i class="fa fa-fw fa-key"></i></a>
		</td>
	</tr>
</c:forEach>
