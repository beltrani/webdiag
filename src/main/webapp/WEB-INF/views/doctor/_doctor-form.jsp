<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<s:message code="common.datePattern" var="datePattern" />

<jsp:useBean id="inputlength" class="br.apolo.common.util.InputLength"/>

<input type="hidden" id="id" name="id" value="${doctor.id}" />

<div class="row-fluid">
	<div class="span12">
		<label for="name">
			<s:message code="doctor.name" />
		</label>
		<input class="input-block-level" type="text" id="name" name="name" value="${doctor.name}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<br />

<div class="row-fluid">
	<div class="span12">
		<label for="specialties">
			<s:message code="doctor.specialties" />
		</label>
		<input class="input-block-level" type="text" id="specialty" name="specialty" value="${doctor.specialties}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<br />

<div class="row-fluid">
	<div class="span6">
		<label for="crm">
			<s:message code="doctor.crm" />
		</label>
		<input class="input-block-level" type="text" id="crm" name="crm" value="${doctor.crm}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>


<div class="row-fluid">
	<div class="span6">
		<label for="state">
			<s:message code="doctor.state" />
		</label>
		<input class="input-block-level" type="text" id="state" name="state" value="${doctor.state}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<div class="row-fluid" <c:if test="${!readOnly}">style="display:none;"</c:if>>
	<div class="span6">
		<label for="doctor.createdBy.name">
			<s:message code="common.author" />
		</label>
		<form:input path="doctor.createdBy.id" cssClass="input-block-level" cssStyle="display:none;" />
		<form:input path="doctor.createdBy.name" cssClass="input-block-level" readonly="true" />
	</div>
	<div class="span6">
		<label for="doctor.creationDate">
			<s:message code="common.creationDate" />
		</label>
		<input 
				type="text" 
				id="creationDate" 
				name="creationDate" 
				class="input-block-level" 
				value="<fmt:formatDate value="${doctor.creationDate}" 
				pattern="${datePattern}" />" 
				readonly="readonly"
			/>
	</div>
</div>

<div class="row-fluid" <c:if test="${!readOnly}">style="display:none;"</c:if>>
	<div class="span6">
		<label for="doctor.lastUpdatedBy.name">
			<s:message code="common.lastUpdatedBy" />
		</label>
		<form:input path="doctor.lastUpdatedBy.id" cssClass="input-block-level" cssStyle="display:none;" />
		<form:input path="doctor.lastUpdatedBy.name" cssClass="input-block-level" readonly="true"/>
	</div>
	<div class="span6">
		<label for="doctor.lastUpdateDate">
			<s:message code="common.lastUpdateDate" />
		</label>
		<input 
				type="text" 
				id="lastUpdateDate" 
				name="lastUpdateDate" 
				class="input-block-level" 
				value="<fmt:formatDate value="${doctor.lastUpdateDate}" 
				pattern="${datePattern}" />" 
				readonly="readonly"
			/>
	</div>
</div>