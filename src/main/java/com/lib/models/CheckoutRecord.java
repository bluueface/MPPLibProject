package com.lib.models;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecord implements Serializable {
    private static final long serialVersionUID = 776588045406848288L;
    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LibraryMember member;

    public CheckoutRecord(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate, LibraryMember member) {
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        member.addRecord(this);
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LibraryMember getMember() {
        return member;
    }

    public void setMember(LibraryMember member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "CheckoutRecordEntry{" +
                "bookCopy=" + bookCopy +
                ", checkoutDate=" + checkoutDate +
                ", dueDate=" + dueDate +
                ", member=" + member +
                '}';
    }
}
