<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.ereadly.model.User" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>eReadly – Sistem Perpustakaan Digital</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    
    <style>
        body { font-family: 'Inter', -apple-system, sans-serif; background-color: #f8f9fa; }
        .navbar-brand { letter-spacing: -0.5px; }
        .nav-link { font-weight: 500; transition: 0.3s; }
        .nav-link:hover { opacity: 0.8; }
        .badge-role { font-size: 0.65rem; text-transform: uppercase; vertical-align: middle; }
        .dropdown-item:active { background-color: #0d6efd; }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">

<%
    User user = (User) session.getAttribute("user");
    
    String brandLink = request.getContextPath() + "/catalog"; 
    if (user != null) {
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            brandLink = request.getContextPath() + "/admin/manage-loans";
        } else {
            brandLink = request.getContextPath() + "/member/dashboard";
        }
    }
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm py-2">
    <div class="container">
        <a class="navbar-brand fw-bold d-flex align-items-center" href="<%= brandLink %>">
            <i class="bi bi-book-half me-2 fs-3"></i> eReadly
        </a>
        
        <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto align-items-center">
                <% if (user != null) { %>
                    <% if ("ADMIN".equalsIgnoreCase(user.getRole())) { %>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/manage-loans">
                                <i class="bi bi-list-check me-1"></i> Manajemen Pinjaman
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/manage-books">
                                <i class="bi bi-gear-fill me-1"></i> Kelola Buku
                            </a>
                        </li>
                    <% } else { %>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/member/dashboard">
                                <i class="bi bi-speedometer2 me-1"></i> Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/catalog">
                                <i class="bi bi-journal-bookmark me-1"></i> Katalog
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/member/my-loans">
                                <i class="bi bi-clock-history me-1"></i> Pinjaman Saya
                            </a>
                        </li>
                    <% } %>

                    <li class="nav-item dropdown ms-lg-3 mt-2 mt-lg-0">
                        <a class="nav-link dropdown-toggle btn btn-light text-primary fw-bold px-3 py-2" 
                           href="#" id="userDropdown" role="button" data-bs-toggle="dropdown">
                            <i class="bi bi-person-circle me-1"></i> <%= user.getNama() %>
                            <span class="badge bg-primary ms-1 badge-role"><%= user.getRole() %></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end shadow border-0 mt-2">
                            <li>
                                <a class="dropdown-item text-danger fw-bold" href="${pageContext.request.contextPath}/logout">
                                    <i class="bi bi-box-arrow-right me-2"></i> Logout
                                </a>
                            </li>
                        </ul>
                    </li>

                <% } else { %>
                    <li class="nav-item">
                        <a class="btn btn-outline-light fw-bold px-4" href="${pageContext.request.contextPath}/login">
                            <i class="bi bi-person-circle me-1"></i> Login
                        </a>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>

<main class="flex-grow-1 py-4">