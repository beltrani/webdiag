<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<form id="searchForm" action="<s:url value="/sickness/search"></s:url>" method="post">
	<div class="row-fluid">
		<div class="span3">
			<label for="name" class="control-label">
				<s:message code="sickness.name" />
			</label>
			<input type="text" id="name" name="name" min="3" class="input-block-level" value="${name}"/>
		</div>
		
		<div class="span2">
			<label for="name" class="control-label">
				TODO CID
			</label>
			<input type="text" id="cid" name="cid" min="3" class="input-block-level" value="${cid}"/>
		</div>
		
		<div class="span5">
			<label for="keywords" class="control-label">
				TODO Sintomas
			</label>
			<select name="symptoms" id="symptoms" size="5" multiple="multiple" class="input-block-level applyChosen" <c:if test="${readOnly}">disabled="disabled"</c:if> data-placeholder='<s:message code="common.select" />' >
				<c:forEach items="${symptomList}" var="symptom">
					<option value="${symptom.id}" 
						<c:forEach items="${symptoms}" var="sicknessSymptom">
							<c:if test="${symptom == sicknessSymptom}">
								selected="selected"
							</c:if>
						</c:forEach>						
					>${symptom.name}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="span2">
			<label class="control-label">
				&nbsp;
			</label>
			<button id="submit" type="submit" class="btn btn-primary">
				<s:message code="common.search" /> 
			</button>

		</div>
	</div>
</form>
