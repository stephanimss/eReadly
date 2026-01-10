<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.ereadly.model.Book" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-sm border-0">
                <%-- Mengambil objek book dan judul form dari request attribute --%>
                <% 
                    Book book = (Book) request.getAttribute("book"); 
                    String formTitle = (book != null) ? "Edit Informasi Buku" : "Tambah Buku Baru";
                %>
                
                <div class="card-header bg-primary text-white py-3">
                    <h5 class="mb-0">
                        <i class="bi <%= (book != null) ? "bi-pencil-square" : "bi-plus-circle" %> me-2"></i>
                        <%= formTitle %>
                    </h5>
                </div>
                
                <div class="card-body p-4">
                    <%-- Pesan Error --%>
                    <% if (request.getSession().getAttribute("error") != null) { %>
                        <div class="alert alert-danger alert-dismissible fade show border-0 shadow-sm mb-4">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            <%= request.getSession().getAttribute("error") %>
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                        <% request.getSession().removeAttribute("error"); %>
                    <% } %>

                    <form action="${pageContext.request.contextPath}/admin/edit-book" method="post">
                        <%-- ID Buku: Jika null maka mode Tambah (value kosong), jika ada maka mode Edit --%>
                        <input type="hidden" name="id" value="<%= (book != null) ? book.getId() : "" %>">

                        <div class="mb-3">
                            <label class="form-label fw-bold">Judul Buku</label>
                            <input type="text" name="title" class="form-control" 
                                   placeholder="Masukkan judul buku"
                                   value="<%= (book != null) ? book.getTitle() : "" %>" required>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Penulis</label>
                                <input type="text" name="author" class="form-control" 
                                       placeholder="Nama penulis"
                                       value="<%= (book != null) ? book.getAuthor() : "" %>" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Kategori</label>
                                <input type="text" name="category" class="form-control" 
                                       placeholder="Contoh: Fiksi, Sains, Sejarah"
                                       value="<%= (book != null) ? book.getCategory() : "" %>" required>
                            </div>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-bold">Jumlah Stok (Eksemplar)</label>
                            <input type="number" name="stock" class="form-control" 
                                   placeholder="0"
                                   value="<%= (book != null) ? book.getStock() : "0" %>" min="0" required>
                        </div>

                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-success px-4 shadow-sm">
                                <i class="bi bi-save me-1"></i> 
                                <%= (book != null) ? "Simpan Perubahan" : "Simpan Buku Baru" %>
                            </button>
                            <a href="${pageContext.request.contextPath}/admin/manage-books" class="btn btn-outline-secondary px-4">
                                Batal
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />