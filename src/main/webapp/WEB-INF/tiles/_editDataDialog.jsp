<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div id="editData" class="modal hide fade" tabindex="-1" data-role="dialog">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4>
			<s:message code="common.edit" />
		</h4>
	</div>
	
	<div class="modal-body">
		<input type="text" id="dataInput" class="input-block-level"/>
		<input type="hidden" id="editingFieldName" />
	</div>
	
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">
			<i class="icon-remove-circle"></i>
			<s:message code="common.cancel" />
		</a>
		<a href="#" class="btn btn-primary" onclick="editData();">
			<i class="icon-check"></i>
			<s:message code="common.ok" />
		</a>
	</div>
</div>