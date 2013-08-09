<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<fieldset>
	<legend>
		<s:message code="symptom.list.title" />
	</legend>
	
	<c:choose>
		<c:when test="${symptomList != null && not empty symptomList}">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>
							<s:message code="symptom.name" />
						</th>
						<th>
							<s:message code="symptom.description" />
						</th>
						<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
							<th>
								<s:message code="common.actions" />
							</th>
						</security:authorize>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${symptomList}" var="symptom">
						<tr id="symptom_${symptom.id}">
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
							<td>
							${symptom.description}
							</td>
							<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
								<td>
									<div class="btn-group">
										<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
											<a href='<s:url value="/symptom/view"></s:url>/${symptom.id}' class="btn btn-small" data-toggle="tooltip" title="<s:message code="common.show" />">
												<i class="icon-zoom-in"></i>
											</a>
										</security:authorize>
										<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
											<a href='<s:url value="/symptom/edit"></s:url>/${symptom.id}' class="btn btn-small" data-toggle="tooltip" title="<s:message code="common.edit" />">
												<i class="icon-edit"></i>
											</a>
										</security:authorize>
										<security:authorize  ifAnyGranted="ROLE_ADMIN, ROLE_DOCTOR">
											<a href='#' class="btn btn-small" onclick="removeConfirmationDialogOpen('<s:url value="/category/remove"></s:url>/${category.id}', 'category_${category.id}');" data-toggle="tooltip" title="<s:message code="common.remove" />">
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
