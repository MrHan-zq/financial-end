<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<select class="col-xs-10 col-sm-8" id="sonMenuSelect" name="sonId">
	<option value="">&nbsp;</option>
	<c:forEach var="sonMenu" items="${sonMenuList}" varStatus="s">
		<option value="${sonMenu.id}">${sonMenu.name}</option>
	</c:forEach>
</select>


