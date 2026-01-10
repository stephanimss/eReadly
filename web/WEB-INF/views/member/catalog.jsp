<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ereadly.model.Book" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-4">
    <h2 class="mb-4"><i class="bi bi-journal-bookmark-fill me-2"></i>Katalog Buku</h2>

    <% if (session.getAttribute("successMessage") != null) { %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle-fill me-2"></i>
            <%= session.getAttribute("successMessage") %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% session.removeAttribute("successMessage"); %>
    <% } %>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <% 
            List<Book> books = (List<Book>) request.getAttribute("books");
            if(books != null && !books.isEmpty()) {
                for(Book b : books) { 
        %>
            <div class="col">
                <div class="card h-100 shadow-sm border-0">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title fw-bold text-dark"><%= b.getTitle() %></h5>
                        
                        <div class="mb-3">
                            <a href="<%= request.getContextPath() %>/book-reviews?id=<%= b.getId() %>" class="text-decoration-none">
                                <div class="d-inline-flex align-items-center">
                                    <span class="badge bg-warning text-dark py-2 px-3 shadow-sm">
                                        <i class="bi bi-star-fill me-2"></i>
                                        <% 
                                            double rating = b.getAverageRating();
                                            out.print(rating > 0 ? String.format("%.1f", rating) : "0");
                                        %> / 5
                                    </span>
                                    <small class="ms-2 text-primary fw-bold text-decoration-underline">Lihat Ulasan</small>
                                </div>
                            </a>
                        </div>

                        <p class="badge bg-info text-dark align-self-start mb-2"><%= b.getCategory() %></p>
                        <p class="card-text text-muted small mb-1">
                            <i class="bi bi-person-fill me-1"></i>Oleh: <%= b.getAuthor() %>
                        </p>
                        <p class="mb-3">
                            Status: 
                            <span class="badge <%= b.getStock() > 0 ? "bg-light text-success border-success" : "bg-light text-danger border-danger" %> border">
                                Tersisa <%= b.getStock() %> Buku
                            </span>
                        </p>
                        
                        <form action="<%= request.getContextPath() %>/borrow" method="post" class="mt-auto">
                            <input type="hidden" name="bookId" value="<%= b.getId() %>">
                            <button type="submit" class="btn btn-primary w-100 shadow-sm" 
                                    <%= b.getStock() <= 0 ? "disabled" : "" %>>
                                <i class="bi <%= b.getStock() <= 0 ? "bi-x-circle" : "bi-cart-plus" %> me-2"></i>
                                <%= b.getStock() <= 0 ? "Stok Habis" : "Pinjam Sekarang" %>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        <% 
                } 
            } else { 
        %>
            <div class="col-12 text-center py-5">
                <i class="bi bi-emoji-frown display-1 text-muted"></i>
                <p class="text-muted mt-3">Maaf, katalog saat ini kosong.</p>
                <a href="<%= request.getContextPath() %>/member/dashboard" class="btn btn-outline-primary">Kembali ke Dashboard</a>
            </div>
        <% } %>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />