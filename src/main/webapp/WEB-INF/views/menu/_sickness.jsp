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
				<s:message code="sickness" />
			</span>
			<span class="caret"></span>
		</a>
                       
		<ul class="dropdown-menu">
			<li>
				<a href='<s:url value="/sickness/list"></s:url>'>
					<i class="icon-th-list"></i>
					<s:message code="sickness.list" />
				</a>
			</li>
			
			<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
				<li>
					<a href='<s:url value="/sickness/new"></s:url>'>
						<i class="icon-plus"></i>
						<s:message code="sickness.new" />
					</a>
				</li>
			</security:authorize>   
				<li>
					<a href='<s:url value="/sickness/search-form"></s:url>'>
						<i class="icon-search"></i>
						<s:message code="common.search" />
					</a>
				</li>                          
		</ul>
	</li>           
</ul>
