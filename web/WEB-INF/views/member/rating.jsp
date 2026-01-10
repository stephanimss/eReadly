<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 card shadow p-4">
            <h3 class="text-center mb-4">Berikan Ulasan Anda</h3>
            <form action="${pageContext.request.contextPath}/submit-rating" method="post">
                <%-- Ambil bookId dari URL parameter --%>
                <input type="hidden" name="bookId" value="<%= request.getParameter("bookId") %>">
                
                <div class="mb-3">
                    <label class="form-label">Skor (1-5 ⭐)</label>
                    <select name="score" class="form-select" required>
                        <option value="5">⭐⭐⭐⭐⭐ (Sangat Bagus)</option>
                        <option value="4">⭐⭐⭐⭐ (Bagus)</option>
                        <option value="3">⭐⭐⭐ (Cukup)</option>
                        <option value="2">⭐⭐ (Kurang)</option>
                        <option value="1">⭐ (Buruk)</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Komentar</label>
                    <textarea name="comment" class="form-control" rows="3" placeholder="Ceritakan pengalaman membaca Anda..."></textarea>
                </div>

                <button type="submit" class="btn btn-primary w-100">Kirim Ulasan</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />