<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<fieldset>
	<legend>
		<s:message code="category.edit.title" />
	</legend>
	
	<form id="categoryForm" action="<s:url value="/category/save"></s:url>" method="post" class="form-inline">
		<jsp:include page='_category-form.jsp'></jsp:include>
		
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">
				<s:message code="common.save" /> 
			</button>

			<c:choose>
				<c:when test="${category.id != null}">
					<a href='<s:url value="/category/view"></s:url>/${category.id}' class="btn">
						<i class="icon-remove-circle"></i>
						<s:message code="common.cancel" />
					</a>				
				</c:when>
				<c:otherwise>
					<a href='<s:url value="/category/list"></s:url>' class="btn">
						<i class="icon-remove-circle"></i>
						<s:message code="common.cancel" />
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</form>

</fieldset>

