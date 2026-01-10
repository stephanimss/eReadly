<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.ereadly.model.User" %>
<%@ page import="com.ereadly.model.Loan" %>
<%@ page import="com.ereadly.model.Notification" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-5">
    <% 
        User user = (User) session.getAttribute("user");
        
        Integer activeLoans = (Integer) request.getAttribute("activeLoans");
        Integer overdueLoans = (Integer) request.getAttribute("overdueLoans");
        Long totalFine = (Long) request.getAttribute("totalFine");
        List<Notification> notifs = (List<Notification>) request.getAttribute("notifications");
        List<Loan> recentLoans = (List<Loan>) request.getAttribute("recentLoans");

        int displayActive = (activeLoans != null) ? activeLoans : 0;
        int displayOverdue = (overdueLoans != null) ? overdueLoans : 0;
        long displayFine = (totalFine != null) ? totalFine : 0L;
    %>

    <%-- Selamat Datang --%>
    <div class="p-5 mb-4 bg-primary text-white rounded-3 shadow">
        <div class="container-fluid py-2">
            <h1 class="display-5 fw-bold">Halo, <%= (user != null) ? user.getNama() : "Pembaca" %>!</h1>
            <p class="col-md-8 fs-4">Senang melihat Anda kembali. Ingin membaca buku apa hari ini?</p>
            <a href="<%= request.getContextPath() %>/catalog" class="btn btn-light btn-lg fw-bold text-primary shadow-sm">
                <i class="bi bi-book-half me-2"></i>Buka Katalog Buku
            </a>
        </div>
    </div>

    <%-- Baris Statistik Utama --%>
    <div class="row g-4 mb-4">
        <%-- Buku Dipinjam --%>
        <div class="col-md-4">
            <div class="card h-100 border-0 shadow-sm bg-info text-white">
                <div class="card-body p-4">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <h6 class="text-uppercase small fw-bold opacity-75">Buku Dipinjam</h6>
                            <h2 class="display-4 fw-bold mb-0"><%= displayActive %></h2>
                        </div>
                        <i class="bi bi-journal-check display-4 opacity-50"></i>
                    </div>
                </div>
            </div>
        </div>

        <%-- Status Terlambat --%>
        <div class="col-md-4">
            <div class="card h-100 border-0 shadow-sm <%= displayOverdue > 0 ? "bg-warning text-dark" : "bg-success text-white" %>">
                <div class="card-body p-4">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <h6 class="text-uppercase small fw-bold opacity-75">Buku Terlambat</h6>
                            <h2 class="display-4 fw-bold mb-0"><%= displayOverdue %></h2>
                        </div>
                        <i class="bi <%= displayOverdue > 0 ? "bi-exclamation-triangle" : "bi-check-circle" %> display-4 opacity-50"></i>
                    </div>
                </div>
            </div>
        </div>

        <%-- Estimasi Denda --%>
        <div class="col-md-4">
            <div class="card h-100 border-0 shadow-sm <%= displayFine > 0 ? "bg-danger text-white" : "bg-secondary text-white" %>">
                <div class="card-body p-4">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <h6 class="text-uppercase small fw-bold opacity-75">Estimasi Denda</h6>
                            <h2 class="display-5 fw-bold mb-0">Rp <%= String.format("%,d", displayFine) %></h2>
                        </div>
                        <i class="bi bi-cash-stack display-4 opacity-50"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row g-4 mb-5">
        <%-- BAGIAN NOTIFIKASI TERBARU --%>
        <div class="col-lg-4">
            <div class="card border-0 shadow-sm h-100">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0 fw-bold text-primary">
                        <i class="bi bi-bell-fill me-2"></i>Pemberitahuan
                    </h5>
                </div>
                <div class="card-body p-0">
                    <div class="list-group list-group-flush">
                        <% 
                            if (notifs != null && !notifs.isEmpty()) {
                                for (Notification n : notifs) {
                        %>
                            <div class="list-group-item p-3">
                                <p class="mb-1 small text-dark"><%= n.getMessage() %></p>
                                <small class="text-muted" style="font-size: 0.75rem;">
                                    <i class="bi bi-clock me-1"></i><%= n.getCreatedAt() %>
                                </small>
                            </div>
                        <% 
                                }
                            } else {
                        %>
                            <div class="p-4 text-center text-muted">
                                <i class="bi bi-mailbox2 display-6 d-block mb-2"></i>
                                <small>Tidak ada pemberitahuan baru.</small>
                            </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>

        <%-- TABEL PINJAMAN TERBARU --%>
        <div class="col-lg-8">
            <div class="card border-0 shadow-sm h-100">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0 fw-bold text-dark">
                        <i class="bi bi-clock-history me-2"></i>Pinjaman Aktif
                    </h5>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover align-middle">
                            <thead class="table-light text-secondary small text-uppercase">
                                <tr>
                                    <th>Judul Buku</th>
                                    <th>Tanggal Pinjam</th>
                                    <th>Jatuh Tempo</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if (recentLoans != null && !recentLoans.isEmpty()) {
                                        for (Loan l : recentLoans) {
                                %>
                                <% SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy"); %>
                                    <tr>
                                        <td class="fw-bold"><%= l.getBookTitle() %></td>
                                        <%-- Menggunakan sdf.format() untuk membuang jam/menit/detik --%>
                                        <td class="small">
                                            <%= l.getLoanDate() != null ? sdf.format(l.getLoanDate()) : "-" %>
                                        </td>
                                        <td class="small text-danger fw-bold">
                                            <%= l.getDueDate() != null ? sdf.format(l.getDueDate()) : "-" %>
                                        </td>
                                        <td>
                                            <span class="badge rounded-pill bg-info text-dark">
                                                <%= l.getStatus() %>
                                            </span>
                                        </td>
                                    </tr>
                                <%
                                        }
                                    } else {
                                %>
                                    <tr>
                                        <td colspan="4" class="text-center py-4 text-muted small">
                                            Anda belum memiliki pinjaman aktif.
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%-- INFORMASI LAYANAN --%>
    <div class="alert alert-light border shadow-sm d-flex align-items-start" role="alert">
        <i class="bi bi-info-circle-fill fs-4 text-primary me-3"></i>
        <div>
            <h5 class="alert-heading fw-bold">Informasi Layanan</h5>
            <p class="mb-0 small text-muted">
                Batas waktu peminjaman adalah <strong>7 hari</strong>. Denda sebesar <strong>Rp 2.000/hari</strong> 
                dikenakan secara otomatis setelah melewati jatuh tempo.
            </p>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />