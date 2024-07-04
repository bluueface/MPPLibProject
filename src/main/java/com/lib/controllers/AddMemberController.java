package com.lib.controllers;

import com.lib.dataaccess.DataAccess;
import com.lib.dataaccess.DataAccessFacade;
import com.lib.models.Address;
import com.lib.models.LibraryMember;
import com.lib.shared.Notify;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddMemberController {
    @FXML
    private TextField memberIdTxtField;

    @FXML
    private TextField firstNameTxtField;

    @FXML
    private TextField lastNameTxtField;

    @FXML
    private TextField telTxtField;

    @FXML
    private TextField streetTxtField;

    @FXML
    private TextField cityTxtField;

    @FXML
    private TextField stateTxtField;

    @FXML
    private TextField zipCodeTxtField;

    @FXML
    private void saveNewMember(ActionEvent event) throws IOException {
        DataAccess da = new DataAccessFacade();
        LibraryMember member = getLibraryMember();

        if (member.getMemberId().isBlank()) {
            Notify.error("Please enter member id");
        } else if (member.getFirstName().isBlank()) {
            Notify.error("Please enter member first name");
        } else if (member.getLastName().isBlank()) {
            Notify.error("Please enter member last name");
        } else if (da.readMemberMap().containsKey(member.getMemberId())) {
            Notify.error("Member with same id already exists");
        } else {
            da.saveNewMember(member);
            Notify.confirmation("Member saved successfully");
            clearFields();
        }
    }

    private LibraryMember getLibraryMember() {
        String memberId = memberIdTxtField.getText();
        String firstName = firstNameTxtField.getText();
        String lastName = lastNameTxtField.getText();
        String tel = telTxtField.getText();
        String street = streetTxtField.getText();
        String city = cityTxtField.getText();
        String state = stateTxtField.getText();
        String zipCode = zipCodeTxtField.getText();
        Address address = new Address(street, city, state, zipCode);
        return new LibraryMember(memberId, firstName, lastName, tel, address);
    }

    private void clearFields() {
        memberIdTxtField.clear();
        firstNameTxtField.clear();
        lastNameTxtField.clear();
        telTxtField.clear();
        streetTxtField.clear();
        cityTxtField.clear();
        stateTxtField.clear();
        zipCodeTxtField.clear();
    }
}
