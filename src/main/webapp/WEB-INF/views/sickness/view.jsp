<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<s:message code="common.datePattern" var="datePattern" />

<fieldset>
	<legend>
		<s:message code="sickness.view.title" />
	</legend>
	
	<jsp:include page='_sickness-form.jsp'></jsp:include>
	
	<c:choose>
		<c:when test="${sicknessHistoryList != null && not empty sicknessHistoryList}">
			<fieldset>
				<legend>
					<s:message code="sickness.history" />
				</legend>
		
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th>
								<s:message code="common.creationDate" />
							</th>
									
							<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
								<th>
									<s:message code="common.actions" />
								</th>
							</security:authorize>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${sicknessHistoryList}" var="sicknessHist">
							<tr id="sickness_${sicknessHist.id}">
								<td>
									<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
										<a href='<s:url value="/sickness/view"></s:url>/${sicknessHist.id}' class="btn btn-link">
											<fmt:formatDate value="${sicknessHist.creationDate}" pattern="${datePattern}" />
										</a>
									</security:authorize>
									<security:authorize  ifNotGranted="ROLE_ADMIN, ROLE_DOCTOR">
										<fmt:formatDate value="${sicknessHist.creationDate}" pattern="${datePattern}" />
									</security:authorize>
								</td>
								<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
									<td>
										<div class="btn-group">
											<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
												<a href='<s:url value="/sickness/view"></s:url>/${sicknessHist.id}' class="btn btn-small" data-toggle="tooltip" title="<s:message code="common.show" />">
													<i class="icon-zoom-in"></i>
												</a>
											</security:authorize>
										</div>
									</td>
								</security:authorize>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</fieldset>
		</c:when>
	</c:choose>
	
	
	<div class="form-actions">
		<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
			<a href='<s:url value="/sickness/edit"></s:url>/${sickness.id}' class="btn">
				<i class="icon-edit"></i>
				<s:message code="common.edit" />
			</a>
		</security:authorize>

		<a href='#' class="btn back" > 
			<i class="icon-backward"></i>
			<s:message code="common.back" />
		</a>
	</div>

</fieldset>
