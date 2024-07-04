package com.lib.controllers;

import com.lib.dataaccess.DataAccess;
import com.lib.dataaccess.DataAccessFacade;
import com.lib.models.Book;
import com.lib.shared.Notify;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddBookCopyController {
    @FXML
    private TextField addCopyTxtField;

    @FXML
    private Label addCopyLabel;

    @FXML
    private void addBookCopy(ActionEvent event) {
        String isbn = addCopyTxtField.getText();
        DataAccess da = new DataAccessFacade();

        if (isbn.isBlank()) {
            addCopyLabel.setText("Enter book ISBN");
        } else if ((!da.readBooksMap().containsKey(isbn))) {
            addCopyLabel.setText("Book not found");

        } else {
            Book book = da.readBooksMap().get(isbn);
            book.addCopy();
            da.saveNewBook(book);
            Notify.confirmation("Copy added successfully");
            addCopyTxtField.clear();
            addCopyLabel.setText("");
        }
    }

}
