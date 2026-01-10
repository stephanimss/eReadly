package com.ereadly.model;

import com.ereadly.interfaces.Borrowable;
import com.ereadly.util.DateUtil;
import java.util.Date;

public class Loan implements Borrowable {
    private int id;
    private int userId;
    private int bookId;
    private String userNama; 
    private Date loanDate;
    private Date dueDate;
    private Date returnDate; 
    private String status;
    private Book book; 
    private long fine;
    private int daysLate;
    private String bookTitle; 

    public Loan() {
        this.book = new Book(); 
    }
    
    @Override
    public void borrow(Member member) {
        this.userId = member.getId();
        this.status = "BORROWED";
        this.loanDate = new Date();
        this.dueDate = com.ereadly.util.DateUtil.addDays(this.loanDate, 7);
    }

    @Override
    public void returnBook(Member member) {
        this.status = "RETURNED";
        this.returnDate = new Date(); 
        this.calculateFine(); 
    }
    
    public void calculateFine() {
        if (this.dueDate != null) {
            int lateDays = DateUtil.calculateDaysLate(this.dueDate);
            
            if (lateDays > 0) {
                this.daysLate = lateDays;
                this.fine = (long) this.daysLate * 2000;
            } else {
                this.fine = 0;
                this.daysLate = 0;
            }
        }
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getUserNama() { return userNama; }
    public void setUserNama(String userNama) { this.userNama = userNama; }

    public Date getLoanDate() { return loanDate; }
    public void setLoanDate(Date loanDate) { this.loanDate = loanDate; }

    public Date getBorrowDate() { return this.loanDate; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Book getBook() { return book; }
    public void setBook(Book book) { 
        this.book = book; 
        if (book != null) {
            this.bookTitle = book.getTitle(); 
        }
    }

    public String getBookTitle() {
        if (this.bookTitle != null && !this.bookTitle.isEmpty()) {
            return this.bookTitle;
        }
        return (book != null) ? book.getTitle() : "Judul Tidak Diketahui";
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public long getFine() { return fine; }
    public void setFine(long fine) { 
        this.fine = fine; 
    }
    
    public void setFine(double fine) {
        this.fine = (long) fine;
    }

    public int getDaysLate() { return daysLate; }
    public void setDaysLate(int daysLate) { this.daysLate = daysLate; }
}