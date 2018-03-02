<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<c:forEach var="user" varStatus="userindex" items="${datas}">
	<tr>
		<td><input type="checkbox" name="h-item" value="${user.id}" ></td>
		<td>
			${user.id}
			<c:if test="${userindex.index==0 }">
				<input type="hidden" id="itemCount" value="${itemCount }">
			</c:if>
		</td>	
		<td class="tmui-keys">${user.uname}</td>
		<td class="tmui-tips" tip="${user.email}">${user.email}</td>
		<td class="tmui-tips" tip="${tz:formatDate(user.createTime,'yyyy-MM-dd HH:mm:ss')}">
			${tz:timeFormat(user.createTime)}
		</td>
		<td>
			<tz:if test="${user.isDelete==0}">
				<tz:then><a href="javascript:void(0);" opid="${user.id }" mark="isDelete" val="1" onclick="tzAdmin.op(this)" class="red tzui-op">否</a></tz:then>
				<tz:else><a href="javascript:void(0);" opid="${user.id }" mark="isDelete" val="0" onclick="tzAdmin.op(this)" class="green tzui-op">是</a></tz:else>
			</tz:if>
		</td>
		<td>
			<tz:if test="${user.isDisable==0}">
				<tz:then><a href="javascript:void(0);" opid="${user.id }" mark="isDisable" val="1" onclick="tzAdmin.op(this)" class="red tzui-op">否</a></tz:then>  
				<tz:else><a href="javascript:void(0);" opid="${user.id }" mark="isDisable" val="0" onclick="tzAdmin.op(this)" class="green tzui-op">是</a></tz:else>
			</tz:if>
		</td>
		<td>
			<a href="javascript:void(0);" class="tmui-tips" tip="编辑" data-opid="${user.id }" onclick="tzAdmin.edit(this)"><i class="fa fa-fw fa-pencil-square-o"></i></a>
			<a href="javascript:void(0);" class="tmui-tips" tip="删除" data-opid="${user.id }" onclick="tzAdmin.remove(this)"><i class="fa fa-fw fa-user-times"></i></a>
		</td>
	</tr>
</c:forEach>