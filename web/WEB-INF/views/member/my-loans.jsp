<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ereadly.model.Loan" %>
<%@ page import="java.text.SimpleDateFormat" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3><i class="bi bi-clock-history me-2 text-primary"></i>Pinjaman Saya</h3>
        <a href="<%= request.getContextPath() %>/catalog" class="btn btn-primary btn-sm shadow-sm">
            <i class="bi bi-plus-lg me-1"></i> Pinjam Buku Lagi
        </a>
    </div>

    <div class="card shadow-sm border-0 overflow-hidden">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover mb-0 text-center">
                    <thead class="table-dark">
                        <tr>
                            <th class="text-start ps-4 py-3">Judul Buku</th>
                            <th class="py-3">Tanggal Pinjam</th>
                            <th class="py-3">Tenggat Kembali</th>
                            <th class="py-3">Status & Denda</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Loan> loans = (List<Loan>) request.getAttribute("loans");
                            
                            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

                            if (loans != null && !loans.isEmpty()) {
                                for (Loan l : loans) {
                                    l.calculateFine(); 
                                    
                                    boolean isBorrowed = "BORROWED".equalsIgnoreCase(l.getStatus());
                                    boolean hasFine = l.getFine() > 0;
                        %>
                        <tr>
                            <td class="align-middle fw-bold text-start ps-4 text-dark">
                                <%= l.getBookTitle() %>
                            </td>
                            <td class="align-middle text-muted">
                                <%-- Menggunakan getLoanDate() sesuai nama variabel di model --%>
                                <%= l.getLoanDate() != null ? sdf.format(l.getLoanDate()) : "-" %>
                            </td> 
                            <td class="align-middle fw-bold <%= hasFine && isBorrowed ? "text-danger" : "text-primary" %>">
                                <%= l.getDueDate() != null ? sdf.format(l.getDueDate()) : "-" %>
                            </td>
                            <td class="align-middle">
                                <% if (isBorrowed) { %>
                                    <% if (hasFine) { %>
                                        <span class="badge bg-danger mb-1 shadow-sm">
                                            <i class="bi bi-exclamation-triangle me-1"></i>Terlambat <%= l.getDaysLate() %> Hari
                                        </span>
                                        <div class="text-danger small fw-bold">Denda: Rp <%= String.format("%,d", l.getFine()) %></div>
                                    <% } else { %>
                                        <span class="badge bg-warning text-dark shadow-sm">Sedang Dipinjam</span>
                                        <div class="text-muted small mt-1">Kembalikan tepat waktu</div>
                                    <% } %>
                                <% } else { %>
                                    <span class="badge bg-success shadow-sm">Sudah Dikembalikan</span>
                                    <div class="text-muted small mt-1">Status: Selesai</div>
                                <% } %>
                            </td>
                        </tr>
                        <% 
                                } 
                            } else { 
                        %>
                        <tr>
                            <td colspan="4" class="text-center py-5 text-muted">
                                <i class="bi bi-inbox display-4 d-block mb-2 opacity-25"></i>
                                Belum ada riwayat peminjaman buku.
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <div class="mt-4 p-3 bg-light rounded border-start border-primary border-4 shadow-sm">
        <div class="d-flex">
            <i class="bi bi-info-circle-fill text-primary me-2 mt-1"></i>
            <small class="text-muted">
                <strong>Ketentuan Pengembalian:</strong> Masa pinjam berlaku selama 7 hari kalender. 
                Denda keterlambatan sebesar <strong>Rp 2.000/hari</strong> dihitung otomatis oleh sistem. 
                Silakan bawa buku fisik ke meja Admin untuk proses pengembalian resmi.
            </small>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />