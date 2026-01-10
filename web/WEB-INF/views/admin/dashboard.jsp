<%@ page import="com.ereadly.model.User" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-5">
    <% User user = (User) session.getAttribute("user"); %>
    <div class="p-5 mb-4 bg-light rounded-3 shadow-sm">
        <div class="container-fluid py-2">
            <h1 class="display-5 fw-bold">Halo, <%= user.getNama() %>!</h1>
            <p class="col-md-8 fs-4">Selamat datang di eReadly. Mari temukan buku favoritmu hari ini.</p>
            <a href="${pageContext.request.contextPath}/catalog" class="btn btn-primary btn-lg">Buka Katalog</a>
        </div>
    </div>

    <div class="row g-4 text-center">
        <div class="col-md-4">
            <div class="card border-primary h-100">
                <div class="card-body">
                    <h5 class="card-title text-primary">Pinjaman Aktif</h5>
                    <p class="display-6">2</p> <a href="${pageContext.request.contextPath}/member/my-loans" class="btn btn-outline-primary btn-sm">Lihat Detail</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card border-warning h-100">
                <div class="card-body">
                    <h5 class="card-title text-warning">Tenggat Waktu</h5>
                    <p class="fs-5">Bumi (3 Hari lagi)</p>
                </div>
            </div>
        </div>
    </div>
</div>