<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ereadly.model.Rating" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-4 mb-5">
    <% if (session.getAttribute("message") != null) { %>
        <div class="alert alert-success alert-dismissible fade show">
            <i class="bi bi-check-circle me-2"></i><%= session.getAttribute("message") %>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% session.removeAttribute("message"); %>
    <% } %>

    <div class="card shadow-sm border-0">
        <div class="card-header bg-white py-3 d-flex justify-content-between align-items-center">
            <h3 class="mb-0"><i class="bi bi-chat-left-text me-2"></i>Ulasan Pembaca</h3>
            <%-- Tombol Tambah Ulasan --%>
            <button class="btn btn-primary fw-bold" data-bs-toggle="modal" data-bs-target="#modalRating">
                <i class="bi bi-plus-lg"></i> Tambah Ulasan
            </button>
        </div>
        
        <div class="card-body p-4">
            <% 
                List<Rating> reviews = (List<Rating>) request.getAttribute("reviews");
                if (reviews != null && !reviews.isEmpty()) {
                    for (Rating r : reviews) {
            %>
                <div class="mb-4 pb-3 border-bottom">
                    <div class="d-flex justify-content-between align-items-center mb-2">
                        <span class="fw-bold text-primary">
                            <i class="bi bi-person-circle me-1"></i><%= r.getUserName() %>
                        </span>
                        <span class="badge bg-warning text-dark px-3 py-2"><%= r.getScore() %> / 5</span>
                    </div>
                    <p class="fst-italic text-secondary mb-1">"<%= r.getComment() %>"</p>
                    <small class="text-muted"><%= r.getCreatedAt() %></small>
                </div>
            <% 
                    }
                } else { 
            %>
                <div class="text-center py-5">
                    <p class="text-muted">Belum ada ulasan untuk buku ini.</p>
                </div>
            <% } %>
            
            <a href="${pageContext.request.contextPath}/catalog" class="btn btn-outline-secondary mt-3">
                <i class="bi bi-arrow-left"></i> Kembali ke Katalog
            </a>
        </div>
    </div>
</div>

<div class="modal fade" id="modalRating" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <form action="${pageContext.request.contextPath}/submit-rating" method="post" class="modal-content">
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title">Berikan Ulasan Anda</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="bookId" value="<%= request.getParameter("id") %>">
                <div class="mb-3">
                    <label class="form-label fw-bold">Skor Rating</label>
                    <select name="score" class="form-select" required>
                        <option value="5">5/5 (Sangat Bagus)</option>
                        <option value="4">4/5 (Bagus)</option>
                        <option value="3">3/5 (Cukup)</option>
                        <option value="2">2/5 (Kurang)</option>
                        <option value="1">1/5 (Buruk)</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label fw-bold">Komentar</label>
                    <textarea name="comment" class="form-control" rows="3" placeholder="Tulis pendapat Anda..." required></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Batal</button>
                <button type="submit" class="btn btn-primary px-4">Kirim Ulasan</button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />