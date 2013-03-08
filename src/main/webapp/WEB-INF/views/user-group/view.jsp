<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<fieldset>
	<legend>
		<s:message code="user.group.view.title" />
	</legend>
	
	<jsp:include page='_permission-form.jsp'></jsp:include>
	
	<div class="form-actions">
		<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_USER_PERMISSION_EDIT">
			<a href='<s:url value="/user-group/edit"></s:url>/${userGroup.id}' class="btn">
				<i class="icon-edit"></i>
				<s:message code="common.edit" />
			</a>
		</security:authorize>

		<a href='<s:url value="/user-group/list"></s:url>' class="btn">
			<i class="icon-backward"></i>
			<s:message code="common.back" />
		</a>
	</div>

</fieldset>
