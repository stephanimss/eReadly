package com.ereadly.dao;

import com.config.DatabaseConfig;
import com.ereadly.model.Loan;
import com.ereadly.model.Book;
import com.ereadly.model.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    private BookDAO bookDAO = new BookDAO();

    public boolean borrowBook(Loan loan, Member member) throws SQLException {
        loan.borrow(member);

        String insertLoanSql = "INSERT INTO loans (user_id, book_id, loan_date, due_date, status) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = DatabaseConfig.getConnection();
            conn.setAutoCommit(false);

            bookDAO.reduceStock(conn, loan.getBookId());

            try (PreparedStatement ps = conn.prepareStatement(insertLoanSql)) {
                ps.setInt(1, loan.getUserId());
                ps.setInt(2, loan.getBookId());
                ps.setTimestamp(3, new java.sql.Timestamp(loan.getLoanDate().getTime()));

                long sevenDaysInMillis = 7L * 24 * 60 * 60 * 1000;
                ps.setTimestamp(4, new java.sql.Timestamp(loan.getLoanDate().getTime() + sevenDaysInMillis));

                ps.setString(5, loan.getStatus());
                ps.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            closeConnectionSafely(conn);
        }
    }

    public boolean returnBook(Loan loan, Member member) throws SQLException {
        loan.returnBook(member);

        String updateLoanSql = "UPDATE loans SET status = ? WHERE id = ?";

        Connection conn = null;
        try {
            conn = DatabaseConfig.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(updateLoanSql)) {
                ps.setString(1, loan.getStatus());
                ps.setInt(2, loan.getId());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Gagal update: ID Pinjaman " + loan.getId() + " tidak ditemukan.");
                }
            }

            bookDAO.addStock(conn, loan.getBookId());

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            closeConnectionSafely(conn);
        }
    }

    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT l.*, b.title, u.nama FROM loans l "
                + "LEFT JOIN books b ON l.book_id = b.id "
                + "LEFT JOIN users u ON l.user_id = u.id "
                + "ORDER BY l.id DESC"; // Urutkan dari yang terbaru

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                loans.add(mapRowToLoan(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    public List<Loan> getActiveLoans(int userId) {
        List<Loan> loans = new ArrayList<>();
        String sql
                = "SELECT l.*, b.title, u.nama "
                + "FROM loans l "
                + "LEFT JOIN books b ON l.book_id = b.id "
                + "LEFT JOIN users u ON l.user_id = u.id "
                + "WHERE l.user_id = ? AND UPPER(l.status) = 'BORROWED' "
                + "ORDER BY l.loan_date DESC";

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    loans.add(mapRowToLoan(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loans;
    }

    public int countActiveLoans(int userId) {
        String sql = "SELECT COUNT(*) FROM loans WHERE user_id = ? AND UPPER(status) = 'BORROWED'";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Loan getLoanById(int id) throws SQLException {
        Loan loan = null;
        String sql = "SELECT l.*, b.title FROM loans l LEFT JOIN books b ON l.book_id = b.id WHERE l.id = ?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    loan = mapRowToLoan(rs);
                }
            }
        }
        return loan;
    }

    public List<Loan> getLoansByUser(int userId) {
        List<Loan> loans = new ArrayList<>();
        String sql
                = "SELECT l.*, b.title, u.nama "
                + "FROM loans l "
                + "LEFT JOIN books b ON l.book_id = b.id "
                + "LEFT JOIN users u ON l.user_id = u.id "
                + "WHERE l.user_id = ? "
                + "ORDER BY l.loan_date DESC";

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    loans.add(mapRowToLoan(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loans;
    }

    private Loan mapRowToLoan(ResultSet rs) throws SQLException {
        Loan loan = new Loan();
        loan.setId(rs.getInt("id"));
        loan.setUserId(rs.getInt("user_id"));
        loan.setBookId(rs.getInt("book_id"));
        loan.setLoanDate(rs.getTimestamp("loan_date"));
        loan.setDueDate(rs.getTimestamp("due_date"));
        loan.setStatus(rs.getString("status"));

        try {
            loan.setFine((long) rs.getDouble("fine"));
        } catch (SQLException ignore) {
            loan.setFine(0);
        }

        try {
            loan.setBookTitle(rs.getString("title"));
        } catch (SQLException ignore) {
            loan.setBookTitle(null);
        }

        try {
            loan.setUserNama(rs.getString("nama"));
        } catch (SQLException ignore) {
            loan.setUserNama(null);
        }

        return loan;
    }

    private void closeConnectionSafely(Connection conn) {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
