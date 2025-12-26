<%-- 
    Document   : login
    Created on : 25 Dec 2025, 22.29.25
    Author     : Luluil Maknun
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="/views/layout/header.jsp" />

<div class="container d-flex justify-content-center align-items-center" style="min-height:80vh;">
    <div class="card shadow p-4" style="width:400px;">
        <h3 class="text-center mb-3">Login eReadly</h3>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email"
                       name="email"
                       class="form-control"
                       placeholder="email@example.com"
                       required>
            </div>

            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password"
                       name="password"
                       class="form-control"
                       placeholder="********"
                       required>
            </div>

            <button type="submit" class="btn btn-primary w-100">
                Login
            </button>
        </form>

        <%-- Error message dari AuthServlet --%>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <div class="alert alert-danger mt-3 text-center">
                <%= error %>
            </div>
        <%
            }
        %>

    </div>
</div>

<jsp:include page="/views/layout/footer.jsp" />
