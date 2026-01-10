<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="p-5 mb-4 bg-light rounded-3">
    <div class="container-fluid py-5 text-center">
        <h1 class="display-5 fw-bold">Selamat Datang di eReadly</h1>
        <p class="fs-4">Platform Perpustakaan Digital</p>

        <hr class="my-4">

        <div class="d-flex justify-content-center gap-3">
            <a href="${pageContext.request.contextPath}/login"
               class="btn btn-primary btn-lg">
                Login
            </a>

            <a href="${pageContext.request.contextPath}/register"
               class="btn btn-outline-secondary btn-lg">
                Register
            </a>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
