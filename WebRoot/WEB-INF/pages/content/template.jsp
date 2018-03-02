<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<c:forEach var="content" varStatus="contentindex" items="${datas}">
	<tr>
		<td><input type="checkbox" name="h-item" value="${content.id}"/></td>
		<td>
			${content.id}
			<c:if test="${contentindex.index==0 }">
				<input type="hidden" id="itemCount" value="${itemCount }">
			</c:if>
		</td>
		<td class="tmui-keys">${content.title }</td>
		<td class="tmui-tips" tip="${tz:formatDate(content.createTime,'yyyy-MM-dd HH:mm:ss')}">
			${tz:timeFormat(content.createTime)}
		</td>
		<td>
			${content.userId }
		</td>
		<td>
			<tz:if test="${content.isDelete==0}">
				<tz:then><a href="javascript:void(0);" opid="${content.id }" mark="isDelete" val="1" onclick="tzAdmin.op(this)" class="red tzui-op">否</a></tz:then>
				<tz:else><a href="javascript:void(0);" opid="${content.id }" mark="isDelete" val="0" onclick="tzAdmin.op(this)" class="green tzui-op">是</a></tz:else>
			</tz:if>
		</td>
		<td>
			<tz:if test="${content.isTop==0}">
				<tz:then><a href="javascript:void(0);" opid="${content.id }" mark="isTop" val="1" onclick="tzAdmin.op(this)" class="red tzui-op">否</a></tz:then>
				<tz:else><a href="javascript:void(0);" opid="${content.id }" mark="isTop" val="0" onclick="tzAdmin.op(this)" class="green tzui-op">是</a></tz:else>
			</tz:if>
		</td>
		<td>
			<tz:if test="${content.isPush==0}">
				<tz:then><a href="javascript:void(0);" opid="${content.id }" mark="isPush" val="1" onclick="tzAdmin.op(this)" class="red tzui-op">否</a></tz:then>
				<tz:else><a href="javascript:void(0);" opid="${content.id }" mark="isPush" val="0" onclick="tzAdmin.op(this)" class="green tzui-op">是</a></tz:else>
			</tz:if>
		</td>
		<td>
			<tz:if test="${content.isComment==0}">
				<tz:then><a href="javascript:void(0);" opid="${content.id }" mark="isComment" val="1" onclick="tzAdmin.op(this)" class="red tzui-op">否</a></tz:then>
				<tz:else><a href="javascript:void(0);" opid="${content.id }" mark="isComment" val="0" onclick="tzAdmin.op(this)" class="green tzui-op">是</a></tz:else>
			</tz:if>
		</td>
		<td>
			<tz:if test="${content.issuance==0}">
				<tz:then><a href="javascript:void(0);" opid="${content.id }" mark="issuance" val="1" onclick="tzAdmin.op(this)" class="red tzui-op">否</a></tz:then>
				<tz:else><a href="javascript:void(0);" opid="${content.id }" mark="issuance" val="0" onclick="tzAdmin.op(this)" class="green tzui-op">是</a></tz:else>
			</tz:if>
		</td>
		<td>
		<!-- class="fa fa-fw fa-ambulance" -->
			<a href="javascript:void(0);" class="tmui-tips" tip="编辑" data-opid="${content.id }" onclick="tzAdmin.edit(this)"><i class="fa fa-fw fa-pencil-square-o"></i></a>
			<a href="javascript:void(0);" class="tmui-tips" tip="删除" data-opid="${content.id }" onclick="tzAdmin.remove(this)"><i class="fa fa-fw  fa-trash"></i></a>
		</td>
	</tr>
</c:forEach>
