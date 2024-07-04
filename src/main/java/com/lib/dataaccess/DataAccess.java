package com.lib.dataaccess;

import com.lib.models.Book;
import com.lib.models.CheckoutRecord;
import com.lib.models.LibraryMember;

import java.util.HashMap;

public interface DataAccess {
    public HashMap<String, Book> readBooksMap();

    public HashMap<String, User> readUserMap();

    public HashMap<String, LibraryMember> readMemberMap();

    public void saveNewMember(LibraryMember member);

    public void saveNewBook(Book book);

    public void saveNewCheckoutRecord(CheckoutRecord record);

    public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
}
