<jsp:include page="views/layout/header.jsp" />

<div class="p-5 mb-4 bg-light rounded-3">
    <div class="container-fluid py-5 text-center">
        <h1 class="display-5 fw-bold">Selamat Datang di eReadly</h1>
        <p class="col-md-12 fs-4">Platform Perpustakaan Digital</p>
        <hr class="my-4">
        <a href="${pageContext.request.contextPath}/views/auth/login.jsp" class="btn btn-primary btn-lg">Mulai</a>
    </div>
</div>

<jsp:include page="views/layout/footer.jsp" />