<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
    <div class="card shadow-sm p-4" style="width: 100%; max-width: 400px; border-top: 5px solid #007bff;">
        <h3 class="text-center mb-4 fw-bold text-primary">Login eReadly</h3>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <small><%= request.getAttribute("error") %></small>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } %>

        <% if (request.getParameter("message") != null) { %>
            <div class="alert alert-success small">
                <%= request.getParameter("message") %>
            </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" 
                       class="form-control" 
                       placeholder="nama@email.com"
                       required autofocus>
            </div>

            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="password" 
                       class="form-control" 
                       placeholder="••••••••"
                       required>
            </div>

            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="remember">
                <label class="form-check-label small" for="remember">Ingat saya</label>
            </div>

            <button type="submit" class="btn btn-primary w-100 fw-bold">Masuk</button>
        </form>

        <div class="text-center mt-4">
            <p class="mb-0 small text-muted">Belum punya akun?</p>
            <a href="${pageContext.request.contextPath}/register" class="text-decoration-none fw-bold">Daftar Sekarang</a>
        </div>
        
        <div class="text-center mt-2">
            <a href="${pageContext.request.contextPath}/" class="small text-secondary text-decoration-none">← Kembali ke Beranda</a>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />