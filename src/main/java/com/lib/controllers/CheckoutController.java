package com.lib.controllers;

import com.lib.dataaccess.DataAccess;
import com.lib.dataaccess.DataAccessFacade;
import com.lib.models.Book;
import com.lib.models.BookCopy;
import com.lib.models.CheckoutRecord;
import com.lib.models.LibraryMember;
import com.lib.shared.Notify;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class CheckoutController {
    @FXML
    private TextField memberIdTxtField;

    @FXML
    private TextField bookISBNTxtField;

    @FXML
    private Label checkoutLabel;

    @FXML
    private Button checkoutButton;

    @FXML
    private void checkoutBook() {
        String memberId = memberIdTxtField.getText();
        String bookISBN = bookISBNTxtField.getText();

        if (memberId.isBlank()) {
            checkoutLabel.setText("Enter Member ID");
        } else if (bookISBN.isBlank()) {
            checkoutLabel.setText("Enter book ISBN");
        } else {
            StringBuilder builder = new StringBuilder();
            DataAccess da = new DataAccessFacade();
            if (!da.readMemberMap().containsKey(memberId)) {
                builder.append("Member not found");
                builder.append("\n");
            }

            if (!da.readBooksMap().containsKey(bookISBN)) {
                builder.append("Book not found");
                builder.append("\n");
            }
            Book book = da.readBooksMap().get(bookISBN);
            if (book != null && !book.isAvailable()) {
                builder.append("No available copies");
                builder.append("\n");
            }

            if (!builder.toString().isEmpty()) {
                checkoutLabel.setText(builder.toString());
            } else {
                LibraryMember member = da.readMemberMap().get(memberId);
                BookCopy availableCopy = book.getNextAvailableCopy();
                LocalDate checkoutDate = LocalDate.now();
                LocalDate dueDate = checkoutDate.plusDays(book.getMaxCheckoutLength());
                CheckoutRecord record = new CheckoutRecord(availableCopy, checkoutDate, dueDate, member);
                member.addRecord(record);
                availableCopy.changeAvailability();
                da.saveNewBook(book);
                da.saveNewMember(member);
                da.saveNewCheckoutRecord(record);
                Notify.confirmation("Checkout record saved successfully");
                memberIdTxtField.clear();
                bookISBNTxtField.clear();
            }

        }
    }
}
