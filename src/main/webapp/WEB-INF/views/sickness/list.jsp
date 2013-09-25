<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<fieldset>
	<legend>
		<s:message code="sickness.list.title" />
	</legend>
	
	<c:choose>
		<c:when test="${sicknessList != null && not empty sicknessList}">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>
							<s:message code="sickness.name" />
						</th>
						<th>
							<s:message code="sickness.cid" />
						</th>
						<th>
							<s:message code="sickness.description" />
						</th>
						<th>
							<s:message code="sickness.description" /> TODO Sintomas
						</th>
					
						<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
							<th>
								<s:message code="common.actions" />
							</th>
						</security:authorize>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${sicknessList}" var="sickness">
						<tr id="sickness_${sickness.id}">
							<td>
								<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
									<a href='<s:url value="/sickness/view"></s:url>/${sickness.id}' class="btn btn-link">
										${sickness.name}
									</a>
								</security:authorize>
								<security:authorize  ifNotGranted="ROLE_ADMIN, ROLE_DOCTOR">
									${sickness.name}
								</security:authorize>
							</td>
							<td>
								${sickness.cid}
							</td>
							<td>
								${sickness.description}
							</td>
							<td>
								<table class="table table-condensed table-bordered">
									<tbody>
										<c:forEach items="${sickness.symptoms}" var="symptom">
											<tr>
												<td>
													<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
														<a href='<s:url value="/symptom/view"></s:url>/${symptom.id}' class="btn btn-link">
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
							</td>
							<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
								<td>
									<div class="btn-group">
										<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
											<a href='<s:url value="/sickness/view"></s:url>/${sickness.id}' class="btn btn-small" data-toggle="tooltip" title="<s:message code="common.show" />">
												<i class="icon-zoom-in"></i>
											</a>
										</security:authorize>
										<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
											<a href='<s:url value="/sickness/edit"></s:url>/${sickness.id}' class="btn btn-small" data-toggle="tooltip" title="<s:message code="common.edit" />">
												<i class="icon-edit"></i>
											</a>
										</security:authorize>
										<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
																					<a href='#' class="btn btn-small" onclick="removeConfirmationDialogOpen('<s:url value="/sickness/remove"></s:url>/${sickness.id}', 'sickness_${sickness.id}');" data-toggle="tooltip" title="<s:message code="common.remove" />">
												<i class="icon-remove"></i>
											</a>
										</security:authorize>
									</div>
								</td>
							</security:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<s:message code="common.nodatafound" htmlEscape="false"/>
		</c:otherwise>
	</c:choose>
</fieldset>
