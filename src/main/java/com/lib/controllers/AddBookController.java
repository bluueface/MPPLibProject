package com.lib.controllers;

import com.lib.dataaccess.DataAccess;
import com.lib.dataaccess.DataAccessFacade;
import com.lib.models.Author;
import com.lib.models.Book;
import com.lib.shared.Notify;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AddBookController {
    @FXML
    private TextField bookISBNTxtField;

    @FXML
    private TextField bookTitleTxtField;

    @FXML
    private TextField maxTxtField;

    @FXML
    private TextField copiesTxtField;

    @FXML
    private void addBook(ActionEvent event) {
        DataAccess da = new DataAccessFacade();
        String isbn = bookISBNTxtField.getText();
        String title = bookTitleTxtField.getText();
        int copies = Integer.parseInt(copiesTxtField.getText().isBlank() ? "0" : copiesTxtField.getText());
        int maxCheckoutLength = Integer.parseInt(maxTxtField.getText().isBlank() ? "0" : maxTxtField.getText());
        Book book = new Book(isbn, title, maxCheckoutLength, new ArrayList<Author>());
        for (int i = 0; i < copies; i++) {
            book.addCopy();
        }
        if (book.getIsbn().isBlank()) {
            Notify.error("Please enter book ISBN");
        } else if (book.getTitle().isBlank()) {
            Notify.error("Please enter member book title");
        } else if (da.readBooksMap().containsKey(book.getIsbn())) {
            Notify.error("Book with same ISBN already exists");
        } else {
            da.saveNewBook(book);
            Notify.confirmation("Book saved successfully");
            clearFields();
        }
    }

    private void clearFields() {
        bookISBNTxtField.clear();
        bookTitleTxtField.clear();
        bookTitleTxtField.clear();
        copiesTxtField.clear();
        maxTxtField.clear();
    }
}
