<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<s:message code="common.datePattern" var="datePattern" />

<jsp:useBean id="inputlength" class="br.apolo.common.util.InputLength"/>

<input type="hidden" id="id" name="id" value="${sickness.id}" />

<div class="row-fluid">
	<div class="span12">
		<label for="name">
			<s:message code="sickness.name" />
		</label>
		<input class="input-block-level" type="text" id="name" name="name" value="${sickness.name}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<div class="row-fluid">
	<div class="span12">
		<label for="description">
			<s:message code="sickness.description" />
		</label>
		<input class="input-block-level" type="text" id="description" name="description" value="${sickness.description}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<div class="row-fluid">
	<div class="span6">
		<label for="cid">
			<s:message code="sickness.cid" />
		</label>
		<input class="input-block-level" type="text" id="cid" name="cid" value="${sickness.cid}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
	
	<div class="span6">
		<label for="category" class="control-label">
			<s:message code="sickness.category" /> 
		</label>
		<select id="category.id" name="category.id" class="input-block-level applyChosen" data-placeholder='<s:message code="common.select" />' <c:if test="${readOnly}">disabled="disabled"</c:if>>
			<c:forEach items="${categoryList}" var="category">
				<option value="${category.id}" <c:if test="${category == sickness.category}">selected="selected"</c:if>>
					${category.name}
				</option>
			</c:forEach>
		</select>
	</div>
</div>


<div class="row-fluid">
	<c:choose>
		<c:when test="${not readOnly}">
			<div class="span12">
				<label for="symptom">
					<s:message code="sickness.symptoms" /> 
				</label>
				<select name="symptoms" id="symptoms" size="5" multiple="multiple" class="input-block-level applyChosen" <c:if test="${readOnly}">disabled="disabled"</c:if> data-placeholder='<s:message code="common.select" />' >
					<c:forEach items="${symptomList}" var="symptom">
						<option value="${symptom.id}" 
							<c:forEach items="${sickness.symptoms}" var="sicknessSymptom">
								<c:if test="${symptom == sicknessSymptom}">
									selected="selected"
								</c:if>
							</c:forEach>						
						>${symptom.name}</option>
					</c:forEach>
				</select>
			</div>
		</c:when>
		<c:otherwise>
			<div class="span5">
				<table class="table table-striped table-hover table-bordered">
					<caption>
						<strong>
							<s:message code="sickness.symptoms" /> 
						</strong>
					</caption>
					<tbody>
						<c:forEach items="${sickness.symptoms}" var="symptom">
							<tr>
								<td>
									<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
										<a href='<s:url value="/symptom/view"></s:url>/${symptom.id}'>
											${symptom.name}
										</a>
									</security:authorize>
									
									<security:authorize  ifNotGranted="ROLE_ADMIN, ROLE_DOCTOR">
										${symptom.name}
									</security:authorize>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<div class="row-fluid">
	<c:choose>
		<c:when test="${not readOnly}">
			<div class="span12">
				<label for="clinic">
					<s:message code="sickness.clinic" /> 
				</label>
				<select name="clinic" id="clinic" size="5" multiple="multiple" class="input-block-level applyChosen" <c:if test="${readOnly}">disabled="disabled"</c:if> data-placeholder='<s:message code="common.select" />' >
					<c:forEach items="${clinicList}" var="clinic">
						<option value="${clinic.id}" 
							<c:forEach items="${sickness.clinic}" var="sicknessClinic">
								<c:if test="${clinic == sicknessClinic}">
									selected="selected"
								</c:if>
							</c:forEach>						
						>${clinic.name}</option>
					</c:forEach>
				</select>
			</div>
		</c:when>
		<c:otherwise>
			<div class="span5">
				<table class="table table-striped table-hover table-bordered">
					<caption>
						<strong>
							<s:message code="sickness.clinic" /> 
						</strong>
					</caption>
					<tbody>
						<c:forEach items="${sickness.clinic}" var="clinic">
							<tr>
								<td>
									<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
										<a href='<s:url value="/clinic/view"></s:url>/${clinic.id}'>
											${clinic.name}
										</a>
									</security:authorize>
									
									<security:authorize  ifNotGranted="ROLE_ADMIN, ROLE_DOCTOR">
										${clinic.name}
									</security:authorize>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:otherwise>
	</c:choose>
</div>


<div class="row-fluid" <c:if test="${!readOnly}">style="display:none;"</c:if>>
	<div class="span6">
		<label for="sickness.createdBy.name">
			<s:message code="common.author" />
		</label>
		<form:input path="sickness.createdBy.id" cssClass="input-block-level" cssStyle="display:none;" />
		<form:input path="sickness.createdBy.name" cssClass="input-block-level" readonly="true" />
	</div>
	<div class="span6">
		<label for="sickness.creationDate">
			<s:message code="common.creationDate" />
		</label>
		<input 
				type="text" 
				id="creationDate" 
				name="creationDate" 
				class="input-block-level" 
				value="<fmt:formatDate value="${sickness.creationDate}" 
				pattern="${datePattern}" />" 
				readonly="readonly"
			/>
	</div>
</div>

<div class="row-fluid" <c:if test="${!readOnly}">style="display:none;"</c:if>>
	<div class="span6">
		<label for="sickness.lastUpdatedBy.name">
			<s:message code="common.lastUpdatedBy" />
		</label>
		<form:input path="sickness.lastUpdatedBy.id" cssClass="input-block-level" cssStyle="display:none;" />
		<form:input path="sickness.lastUpdatedBy.name" cssClass="input-block-level" readonly="true"/>
	</div>
	
	<div class="span6">
		<label for="sickness.lastUpdateDate">
			<s:message code="common.lastUpdateDate" />
		</label>
		<input 
				type="text" 
				id="lastUpdateDate" 
				name="lastUpdateDate" 
				class="input-block-level" 
				value="<fmt:formatDate value="${sickness.lastUpdateDate}" 
				pattern="${datePattern}" />" 
				readonly="readonly"
			/>
	</div>
</div>


