<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<fieldset>
	<legend>
		<s:message code="doctor.list.title" />
	</legend>
	
	<c:choose>
		<c:when test="${doctorList != null && not empty doctorList}">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>
							<s:message code="doctor.name" />
						</th>
						<th>
							<s:message code="doctor.specialties" />
						</th>
						<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
							<th>
								<s:message code="common.actions" />
							</th>
						</security:authorize>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${doctorList}" var="doctor">
						<tr id="doctor_${doctor.id}">
							<td>
								<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
									<a href='<s:url value="/doctor/view"></s:url>/${doctor.id}' class="btn btn-link">
										${doctor.name}
									</a>
								</security:authorize>
								<security:authorize  ifNotGranted="ROLE_ADMIN, ROLE_DOCTOR">
									${doctor.name}
								</security:authorize>
							</td>
							<td>
							${doctor.specialties}
							</td>
							<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
								<td>
									<div class="btn-group">
										<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
											<a href='<s:url value="/doctor/view"></s:url>/${doctor.id}' class="btn btn-small" data-toggle="tooltip" title="<s:message code="common.show" />">
												<i class="icon-zoom-in"></i>
											</a>
										</security:authorize>
										<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
											<a href='<s:url value="/doctor/edit"></s:url>/${doctor.id}' class="btn btn-small" data-toggle="tooltip" title="<s:message code="common.edit" />">
												<i class="icon-edit"></i>
											</a>
										</security:authorize>
										<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
											<a href='#' class="btn btn-small" onclick="removeConfirmationDialogOpen('<s:url value="/doctor/remove"></s:url>/${doctor.id}', 'doctor_${doctor.id}');" data-toggle="tooltip" title="<s:message code="common.remove" />">
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
