<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%

	session.setAttribute("SECRET_TOKEN", String.valueOf(Math.random()));

%>

<input type="hidden" id="token" name="token" value="${sessionScope.SECRET_TOKEN}" />