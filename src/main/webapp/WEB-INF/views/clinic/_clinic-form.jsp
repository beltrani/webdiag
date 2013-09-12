<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<s:message code="common.datePattern" var="datePattern" />

<jsp:useBean id="inputlength" class="br.apolo.common.util.InputLength"/>

<input type="hidden" id="id" name="id" value="${clinic.id}" />

<div class="row-fluid">
	<div class="span12">
		<label for="name">
			<s:message code="clinic.name" />
		</label>
		<input class="input-block-level" type="text" id="name" name="name" value="${clinic.name}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<div class="row-fluid">
	<div class="span12">
		<label for="address">
			<s:message code="clinic.address" />
		</label>
		<input class="input-block-level" type="text" id="address" name="address" value="${clinic.address}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<br />

<div class="row-fluid">
	<div class="span6">
		<label for="number">
			<s:message code="clinic.number" />
		</label>
		<input class="input-block-level" type="text" id="number" name="number" value="${clinic.number}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<br />

<div class="row-fluid">
	<div class="span6">
		<label for="city">
			<s:message code="clinic.city" />
		</label>
		<input class="input-block-level" type="text" id="city" name="city" value="${clinic.city}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>


<div class="row-fluid">
	<div class="span6">
		<label for="state">
			<s:message code="clinic.state" />
		</label>
		<input class="input-block-level" type="text" id="state" name="state" value="${clinic.state}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<div class="row-fluid">
	<div class="span6">
		<label for="phone">
			<s:message code="clinic.phone" />
		</label>
		<input class="input-block-level" type="text" id="phone" name="phone" value="${clinic.phone}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<div class="row-fluid" <c:if test="${!readOnly}">style="display:none;"</c:if>>
	<div class="span6">
		<label for="clinic.createdBy.name">
			<s:message code="common.author" />
		</label>
		<form:input path="clinic.createdBy.id" cssClass="input-block-level" cssStyle="display:none;" />
		<form:input path="clinic.createdBy.name" cssClass="input-block-level" readonly="true" />
	</div>
	<div class="span6">
		<label for="clinic.creationDate">
			<s:message code="common.creationDate" />
		</label>
		<input 
				type="text" 
				id="creationDate" 
				name="creationDate" 
				class="input-block-level" 
				value="<fmt:formatDate value="${clinic.creationDate}" 
				pattern="${datePattern}" />" 
				readonly="readonly"
			/>
	</div>
</div>

<div class="row-fluid" <c:if test="${!readOnly}">style="display:none;"</c:if>>
	<div class="span6">
		<label for="clinic.lastUpdatedBy.name">
			<s:message code="common.lastUpdatedBy" />
		</label>
		<form:input path="clinic.lastUpdatedBy.id" cssClass="input-block-level" cssStyle="display:none;" />
		<form:input path="clinic.lastUpdatedBy.name" cssClass="input-block-level" readonly="true"/>
	</div>
	<div class="span6">
		<label for="clinic.lastUpdateDate">
			<s:message code="common.lastUpdateDate" />
		</label>
		<input 
				type="text" 
				id="lastUpdateDate" 
				name="lastUpdateDate" 
				class="input-block-level" 
				value="<fmt:formatDate value="${clinic.lastUpdateDate}" 
				pattern="${datePattern}" />" 
				readonly="readonly"
			/>
	</div>
</div>