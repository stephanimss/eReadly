<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
    <div class="card shadow-sm p-4" style="width: 100%; max-width: 450px; border-top: 5px solid #28a745;">
        <h3 class="text-center mb-4 fw-bold text-success">Daftar Akun eReadly</h3>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger small">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="mb-3">
                <label class="form-label">Nama Lengkap</label>
                <input type="text" name="nama" class="form-control" placeholder="Masukkan nama lengkap" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" placeholder="nama@email.com" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Min. 6 karakter" required>
            </div>

            <button type="submit" class="btn btn-success w-100 fw-bold">Daftar Sekarang</button>
        </form>

        <div class="text-center mt-4">
            <p class="mb-0 small text-muted">Sudah memiliki akun?</p>
            <a href="${pageContext.request.contextPath}/login" class="text-decoration-none fw-bold text-success">Login di sini</a>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />