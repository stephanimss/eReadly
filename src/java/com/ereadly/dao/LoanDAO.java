package com.ereadly.dao;

import com.ereadly.config.DatabaseConfig;
import com.ereadly.exception.BookNotAvailableException;
import com.ereadly.model.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class LoanDAO {

    public void createLoan(Loan loan) throws BookNotAvailableException {
        if (loan == null || loan.getBook() == null || loan.getMember() == null) {
            throw new IllegalArgumentException("Loan/Book/Member tidak boleh null");
        }

        int bookId = loan.getBook().getIdBuku();     // sesuaikan jika method beda
        int userId = loan.getMember().getIdUser();   // sesuaikan jika method beda

        String qCheckStock = "SELECT stok FROM books WHERE id_buku=?";
        String qInsertLoan = "INSERT INTO loans (id_user, id_buku, tanggal_pinjam, tanggal_kembali, status) "
                           + "VALUES (?, ?, ?, ?, ?)";
        String qUpdateStock = "UPDATE books SET stok=? WHERE id_buku=?";

        try (Connection conn = DatabaseConfig.getConnection()) {
            conn.setAutoCommit(false);

            int stok;
            try (PreparedStatement ps = conn.prepareStatement(qCheckStock)) {
                ps.setInt(1, bookId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        conn.rollback();
                        throw new BookNotAvailableException("Buku tidak ditemukan");
                    }
                    stok = rs.getInt("stok");
                }
            }

            if (stok <= 0) {
                conn.rollback();
                throw new BookNotAvailableException("Stok buku habis");
            }

            if (loan.getBorrowDate() == null) loan.setBorrowDate(new Date());

            // tanggal_kembali (jatuh tempo) default +7 hari
            Date due = new Date(loan.getBorrowDate().getTime() + 7L * 24L * 60L * 60L * 1000L);

            try (PreparedStatement ps = conn.prepareStatement(qInsertLoan)) {
                ps.setInt(1, userId);
                ps.setInt(2, bookId);
                ps.setDate(3, new java.sql.Date(loan.getBorrowDate().getTime()));
                ps.setDate(4, new java.sql.Date(due.getTime()));
                ps.setString(5, "dipinjam");
                ps.executeUpdate();
            }

            int newStock = stok - 1;
            try (PreparedStatement ps = conn.prepareStatement(qUpdateStock)) {
                ps.setInt(1, newStock);
                ps.setInt(2, bookId);
                ps.executeUpdate();
            }

            conn.commit();

            // sinkron object
            loan.getBook().setStok(newStock);

        } catch (BookNotAvailableException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("createLoan gagal: " + e.getMessage(), e);
        }
    }
}
