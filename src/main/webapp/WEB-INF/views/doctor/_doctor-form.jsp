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
<form:hidden path="doctor.user.id"/>

<div class="row-fluid">
	<div class="span12">
		<label for="name">
			<s:message code="doctor.name" />
		</label>
		<input class="input-block-level" type="text" id="name" name="name" value="${doctor.name}" <c:if test="${readOnly}">readonly="true"</c:if> />
	</div>
</div>

<div class="row-fluid">
	<div class="span12">
		<label for="email">
			<s:message code="user.email" />
		</label>
		<input type="text" id="user.email" name="user.email" class="input-block-level" value="${doctor.user.email}" <c:if test="${readOnly}">readonly="true"</c:if>/>
	</div>
</div>

<div class="row-fluid">
	<c:choose>
		<c:when test="${not readOnly}">
			<div class="span12">
				<label for="name">
					<s:message code="user.groups" />
				</label>
				
				<form:select path="doctor.user.groups" size="5" multiple="multiple" cssClass="input-block-level applyChosen">
					<c:forEach items="${groupList}" var="group">
						<option value="${group.id}" 
							<c:forEach items="${doctor.user.groups}" var="userGroup">
								<c:if test="${group == userGroup}">
									selected="selected"
								</c:if>
							</c:forEach>						
						>${group.name}</option>
					</c:forEach>					
				</form:select>
				
			</div>
		</c:when>
		<c:otherwise>
			<div class="span5">
				<table class="table table-striped table-hover table-bordered">
					<caption>
						<strong>
							<s:message code="user.groups" />
						</strong>
					</caption>
					<tbody>
						<c:forEach items="${doctor.user.groups}" var="group">
							<tr>
								<td>
									<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_USER_LIST, ROLE_USER_PERMISSION_LIST, ROLE_USER_PERMISSION_VIEW">
										<a href='<s:url value="/user-group/view"></s:url>/${group.id}'>
											${group.name}
										</a>
									</security:authorize>
									
									<security:authorize  ifNotGranted="ROLE_ADMIN, ROLE_USER_LIST, ROLE_USER_PERMISSION_LIST, ROLE_USER_PERMISSION_VIEW">
										${group.name}
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

<br />

<div class="row-fluid">
	<c:choose>
		<c:when test="${not readOnly}">
			<div class="span12">
				<label for="specialties">
					<s:message code="doctor.specialties" />
				</label>
				
				<form:select path="doctor.specialties" size="5" multiple="multiple" cssClass="input-block-level applyChosen">
					<c:forEach items="${specialtyList}" var="specialty">
						<option value="${specialty.id}" 
							<c:forEach items="${doctor.specialties}" var="docSpecialty">
								<c:if test="${specialty == docSpecialty}">
									selected="selected"
								</c:if>
							</c:forEach>						
						>${specialty.name}</option>
					</c:forEach>					
				</form:select>
				
			</div>
		</c:when>
		<c:otherwise>
			<div class="span5">
				<table class="table table-striped table-hover table-bordered">
					<caption>
						<strong>
							<s:message code="doctor.specialties" />
						</strong>
					</caption>
					<tbody>
						<c:forEach items="${doctor.specialties}" var="specialty">
							<tr>
								<td>
									<a href='<s:url value="/specialty/view"></s:url>/${specialty.id}'>
										${specialty.name}
									</a>
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
					<s:message code="doctor.clinics" />
				</label>
				
				<form:select path="doctor.clinics" size="5" multiple="multiple" cssClass="input-block-level applyChosen">
					<c:forEach items="${clinicList}" var="clinic">
						<option value="${clinic.id}" 
							<c:forEach items="${doctor.clinics}" var="doctorClinics">
								<c:if test="${clinic == doctorClinics}">
									selected="selected"
								</c:if>
							</c:forEach>						
						>${clinic.name}</option>
					</c:forEach>					
				</form:select>
				
			</div>
		</c:when>
		<c:otherwise>
			<div class="span5">
				<table class="table table-striped table-hover table-bordered">
					<caption>
						<strong>
							<s:message code="doctor.clinics" />
						</strong>
					</caption>
					<tbody>
						<c:forEach items="${doctor.clinics}" var="clinic">
							<tr>
								<td>
									<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_USER_LIST, ROLE_USER_PERMISSION_LIST, ROLE_USER_PERMISSION_VIEW">
										<a href='<s:url value="/clinic/view"></s:url>/${clinic.id}'>
											${clinic.name}
										</a>
									</security:authorize>
									
									<security:authorize  ifNotGranted="ROLE_ADMIN, ROLE_USER_LIST, ROLE_USER_PERMISSION_LIST, ROLE_USER_PERMISSION_VIEW">
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