<%@ page import="java.util.List" %>
<%@ page import="com.ereadly.model.Book" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container-fluid px-4 mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2><i class="bi bi-gear-fill me-2"></i>Kelola Koleksi Buku</h2>
        <a href="${pageContext.request.contextPath}/admin/edit-book" class="btn btn-primary shadow-sm">
            <i class="bi bi-plus-lg me-1"></i> Tambah Buku Baru
        </a>
    </div>

    <%-- Alert Notifikasi --%>
    <% if (session.getAttribute("message") != null) { %>
        <div class="alert alert-success alert-dismissible fade show shadow-sm border-0 mb-4">
            <i class="bi bi-check-circle-fill me-2"></i>
            <%= session.getAttribute("message") %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% session.removeAttribute("message"); %>
    <% } %>

    <% if (session.getAttribute("error") != null) { %>
        <div class="alert alert-danger alert-dismissible fade show shadow-sm border-0 mb-4">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            <%= session.getAttribute("error") %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% session.removeAttribute("error"); %>
    <% } %>

    <div class="card shadow-sm border-0">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-dark">
                        <tr>
                            <th class="ps-4 py-3">Judul Buku</th>
                            <th class="py-3">Penulis</th>
                            <th class="py-3 text-center">Kategori</th>
                            <th class="py-3 text-center">Stok</th>
                            <th class="py-3 text-center">Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Book> books = (List<Book>) request.getAttribute("books");
                            if (books != null && !books.isEmpty()) {
                                for (Book b : books) {
                        %>
                        <tr>
                            <td class="ps-4">
                                <div class="fw-bold text-dark"><%= b.getTitle() %></div>
                                <small class="text-muted">ID: <%= b.getId() %></small>
                            </td>
                            <td class="text-secondary"><%= b.getAuthor() %></td>
                            <td class="text-center">
                                <span class="badge bg-secondary opacity-75 fw-normal px-3">
                                    <%= b.getCategory() %>
                                </span>
                            </td>
                            <td class="text-center">
                                <span class="badge <%= b.getStock() > 0 ? "bg-info text-dark" : "bg-danger" %> px-3">
                                    <%= b.getStock() %> pcs
                                </span>
                            </td>
                            <td class="text-center">
                                <div class="btn-group">
                                    <a href="${pageContext.request.contextPath}/admin/edit-book?id=<%= b.getId() %>" 
                                       class="btn btn-sm btn-warning shadow-sm px-3"
                                       title="Ubah Data Buku">
                                        <i class="bi bi-pencil-square me-1"></i> Edit
                                    </a>
                                    
                                    <a href="${pageContext.request.contextPath}/admin/edit-book?action=delete&id=<%= b.getId() %>" 
                                       class="btn btn-sm btn-danger shadow-sm px-3"
                                       onclick="return confirm('Apakah Anda yakin ingin menghapus buku [<%= b.getTitle() %>]?')"
                                       title="Hapus Buku">
                                        <i class="bi bi-trash-fill me-1"></i> Hapus
                                    </a>
                                </div>
                            </td>
                        </tr>
                        <% 
                                } 
                            } else { 
                        %>
                        <tr>
                            <td colspan="5" class="text-center py-5">
                                <div class="text-muted">
                                    <i class="bi bi-inbox-fill display-1 d-block mb-3 opacity-25"></i>
                                    <p class="fs-5">Belum ada koleksi buku yang tersedia.</p>
                                </div>
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