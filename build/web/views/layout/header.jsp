<%-- 
    Document   : header
    Created on : 25 Dec 2025, 22.30.22
    Author     : Luluil Maknun
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.ereadly.model.User" %>

<%
    User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <title>eReadly</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/index.jsp">eReadly</a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <% if (user == null) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/views/auth/login.jsp">Login</a>
                    </li>
                <% } else { %>
                    <li class="nav-item">
                        <span class="nav-link text-white">
                            Halo, <%= user.getNama() %>
                        </span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
