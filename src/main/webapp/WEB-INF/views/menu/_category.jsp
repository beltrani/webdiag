<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<ul class="nav">
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			<i class="icon-cog "></i>
			<span class="hidden-phone">
				<s:message code="category" />
			</span>
			<span class="caret"></span>
		</a>
                       
		<ul class="dropdown-menu">
			<li>
				<a href='<s:url value="/category/list"></s:url>'>
					<i class="icon-th-list"></i>
					<s:message code="category.list" />
				</a>
			</li>
			<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
				<li>
					<a href='<s:url value="/category/new"></s:url>'>
						<i class="icon-plus"></i>
						<s:message code="category.new" />
					</a>
				</li>
			</security:authorize>                               
		</ul>
	</li>           
</ul>
