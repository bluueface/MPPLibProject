package com.lib.controllers;

import com.lib.dataaccess.DataAccess;
import com.lib.dataaccess.DataAccessFacade;
import com.lib.models.CheckoutRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OverviewController {

    @FXML
    private TextField printRecordTxtField;

    @FXML
    private TextField overdueTextField;

    @FXML
    private Label printRecordLabel;

    @FXML
    private Label overdueLabel;

    @FXML
    private TableView<CheckoutRecord> overdueTableView;

    @FXML
    private TableColumn<CheckoutRecord, String> isbnColumn;

    @FXML
    private TableColumn<CheckoutRecord, String> titleColumn;

    @FXML
    private TableColumn<CheckoutRecord, Integer> copyNumberColumn;

    @FXML
    private TableColumn<CheckoutRecord, String> libraryMemberColumn;

    @FXML
    private TableColumn<CheckoutRecord, LocalDate> dueDateColumn;

    // Initialize method to set up TableView columns
    @FXML
    public void initialize() {
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        copyNumberColumn.setCellValueFactory(new PropertyValueFactory<>("copyNumber"));
        libraryMemberColumn.setCellValueFactory(new PropertyValueFactory<>("checkedBy"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    }

    @FXML
    private void printRecord() {
        String memberId = printRecordTxtField.getText();
        DataAccess da = new DataAccessFacade();
        if (!da.readMemberMap().containsKey(memberId)) {
            printRecordLabel.setText("Member not found");
        } else {
            printRecordLabel.setText(String.format(
                    "The checkout record for the member %s is %s",
                    memberId, da.readMemberMap().get(memberId).getCheckoutRecord().size()
            ));
        }
    }

    @FXML
    private void overdue() {
        String isbn = overdueTextField.getText();
        DataAccess da = new DataAccessFacade();
        if (!da.readBooksMap().containsKey(isbn)) {
            overdueLabel.setText("Book not found");
        } else {
            List<CheckoutRecord> overdueRecords = new ArrayList<>();
            for (CheckoutRecord record : da.readCheckoutRecordMap().values()) {
                if (record.getBookCopy().getBook().getIsbn().equals(isbn) &&
                        isOverdue(record.getDueDate(), record.getBookCopy().isAvailable())) {
                    overdueRecords.add(record);
                }
            }

            if (overdueRecords.isEmpty()) {
                overdueLabel.setText("No overdue records found");
            } else {
                ObservableList<CheckoutRecord> data = FXCollections.observableArrayList(overdueRecords);
                overdueTableView.setItems(data);
            }
        }
    }

    private boolean isOverdue(LocalDate dueDate, boolean isAvailable) {
        return dueDate.isBefore(LocalDate.now()) && !isAvailable;
    }
}
