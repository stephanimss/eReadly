<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ereadly.model.Loan" %>
<%@ page import="java.text.SimpleDateFormat" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<% 
    List<Loan> loans = (List<Loan>) request.getAttribute("loans");
    int totalData = (loans != null) ? loans.size() : 0;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
%>

<div class="container-fluid px-4 mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2><i class="bi bi-journal-check me-2"></i>Manajemen Pinjaman</h2>
        <span class="badge bg-primary fs-6 shadow-sm">Total Data: <%= totalData %></span>
    </div>

    <%-- Notifikasi Sukses/Gagal dari Session --%>
    <% if (session.getAttribute("successMessage") != null) { %>
        <div class="alert alert-success alert-dismissible fade show shadow-sm">
            <i class="bi bi-check-circle-fill me-2"></i><%= session.getAttribute("successMessage") %>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% session.removeAttribute("successMessage"); %>
    <% } %>
    
    <% if (session.getAttribute("errorMessage") != null) { %>
        <div class="alert alert-danger alert-dismissible fade show shadow-sm">
            <i class="bi bi-exclamation-triangle-fill me-2"></i><%= session.getAttribute("errorMessage") %>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% session.removeAttribute("errorMessage"); %>
    <% } %>

    <div class="card shadow-sm border-0">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-dark">
                        <tr>
                            <th class="ps-4">Member (Peminjam)</th>
                            <th>Buku</th>
                            <th class="text-center">Tgl Pinjam</th>
                            <th class="text-center">Tenggat Kembali</th>
                            <th class="text-center">Status & Denda Berjalan</th>
                            <th class="text-center">Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (totalData > 0) {
                                for (Loan l : loans) {
                        %>
                        <tr>
                            <td class="ps-4">
                                <div class="fw-bold text-primary">
                                    <i class="bi bi-person-badge me-1"></i><%= l.getUserNama() != null ? l.getUserNama() : "Unknown User" %>
                                </div>
                                <small class="text-muted">ID User: <%= l.getUserId() %></small>
                            </td>
                            <td>
                                <div class="fw-bold"><%= l.getBookTitle() != null ? l.getBookTitle() : "Judul Tidak Ditemukan" %></div>
                                <small class="text-muted">ID Transaksi: #<%= l.getId() %></small>
                            </td>
                            <td class="text-center">
                                <%= l.getLoanDate() != null ? sdf.format(l.getLoanDate()) : "-" %>
                            </td>
                            <td class="text-center">
                                <%-- Warna merah jika terlambat (denda > 0) --%>
                                <span class="<%= l.getFine() > 0 ? "text-danger fw-bold" : "" %>">
                                    <%= l.getDueDate() != null ? sdf.format(l.getDueDate()) : "-" %>
                                </span>
                            </td>
                            <td class="text-center">
                                <% if ("BORROWED".equalsIgnoreCase(l.getStatus())) { %>
                                    <% if (l.getFine() > 0) { %>
                                        <span class="badge bg-danger mb-1">Terlambat</span>
                                        <div class="text-danger fw-bold small">Denda: Rp <%= String.format("%,d", l.getFine()) %></div>
                                    <% } else { %>
                                        <span class="badge bg-warning text-dark">Sedang Dipinjam</span>
                                        <div class="text-muted small italic">Belum Ada Denda</div>
                                    <% } %>
                                <% } else { %>
                                    <span class="badge bg-success">Sudah Kembali</span>
                                    <% if (l.getFine() > 0) { %>
                                        <div class="text-muted small">Denda Dibayar: Rp <%= String.format("%,d", l.getFine()) %></div>
                                    <% } %>
                                <% } %>
                            </td>
                            <td class="text-center">
                                <% if ("BORROWED".equalsIgnoreCase(l.getStatus())) { %>
                                    <form action="${pageContext.request.contextPath}/admin/return-book" method="post" class="d-inline">
                                        <input type="hidden" name="loanId" value="<%= l.getId() %>">
                                        <button type="submit" class="btn btn-sm btn-success px-3 shadow-sm" 
                                                onclick="return confirm('Verifikasi pengembalian buku \'<%= l.getBookTitle() %>\'?')">
                                            <i class="bi bi-check2-square me-1"></i> Proses Kembali
                                        </button>
                                    </form>
                                <% } else { %>
                                    <button class="btn btn-sm btn-secondary disabled opacity-50">
                                        <i class="bi bi-file-earmark-check me-1"></i> Transaksi Selesai
                                    </button>
                                <% } %>
                            </td>
                        </tr>
                        <%  
                                }
                            } else { 
                        %>
                        <tr>
                            <td colspan="6" class="text-center py-5 text-muted">
                                <i class="bi bi-folder-x display-4 d-block mb-3"></i>
                                Belum ada data transaksi peminjaman di sistem.
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />