package com.ereadly.model;

import com.ereadly.interfaces.Borrowable;
import java.util.Date;

public class Loan implements Borrowable {

    private int loanId;
    private Member member;
    private Book book;
    private Date borrowDate;

    public Loan() {}

    public Loan(int loanId, Member member, Book book, Date borrowDate) {
        this.loanId = loanId;
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
    }

    @Override
    public void borrow(Member member) {
        if (book == null) {
            throw new IllegalStateException("Book belum diset pada Loan");
        }

        // WAJIB: cek ketersediaan sebelum dipinjam
        // Sesuai DB ereadly: pakai stok (stok <= 0 berarti tidak tersedia)
        if (book.getStok() <= 0) {
            throw new IllegalStateException("Stok buku habis");
        }

        this.member = member;
        this.borrowDate = new Date();

        // Update object sementara (update DB dilakukan LoanDAO)
        book.setStok(book.getStok() - 1);
    }

    @Override
    public void returnBook(Member member) {
        if (book == null) {
            throw new IllegalStateException("Book belum diset pada Loan");
        }

        // Update object sementara (update DB dilakukan DAO)
        book.setStok(book.getStok() + 1);
    }

    // Getter & Setter
    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public Date getBorrowDate() { return borrowDate; }
    public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }
}
