<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<c:forEach var="stat" varStatus="statindex" items="${datas}">
	<tr>
		<td><input type="checkbox" name="h-item" value="${stat.id}" ></td>
		<td>
			${stat.id}
			<c:if test="${statindex.index==0 }">
				<input type="hidden" id="itemCount" value="${itemCount }">
			</c:if>
		</td>
		<td class="tmui-tips" tip="${stat.module }">${stat.module }</td>
		<td class="tmui-tips" tip="${stat.method }">${stat.method }</td>
		<td class="tmui-tips" tip="${stat.classname }">${stat.classname }</td>
		<td class="tmui-tips" tip="${tz:formatDate(stat.createTime,'yyyy-MM-dd HH:mm:ss')}">
			${tz:timeFormat(stat.createTime)}
		</td>
		<td>${stat.username }</td>
		<td>${stat.ip }</td>
		<td>
			<a href="javascript:void(0);" class="tmui-tips" tip="删除" data-opid="${stat.id }" onclick="tzAdmin.remove(this)"><i class="fa fa-fw  fa-trash"></i></a>
		</td>
	</tr>
</c:forEach>
