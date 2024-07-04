package com.lib.controllers;

import com.lib.dataaccess.DataAccess;
import com.lib.dataaccess.DataAccessFacade;
import com.lib.models.Address;
import com.lib.models.LibraryMember;
import com.lib.shared.Notify;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditMemberController {

    @FXML
    private TextField editMemberSearchTxtField;

    @FXML
    private Button editMemberSearchBtn;

    @FXML
    private TextField firstNameEditTxtField;

    @FXML
    private TextField lastNameEditTxtField;

    @FXML
    private TextField telEditTxtField;

    @FXML
    private TextField streetEditTxtField;

    @FXML
    private TextField cityEditTxtField;

    @FXML
    private TextField stateEditTxtField;

    @FXML
    private TextField zipCodeEditTxtField;

    @FXML
    private Button updateMember;

    private DataAccess da;

    private String memberId;


    @FXML
    public void loadExistingMember(ActionEvent event) {
        memberId = editMemberSearchTxtField.getText();
        da = new DataAccessFacade();
        if (!da.readMemberMap().containsKey(memberId)) {
            Notify.information("No such member found");
            clearFields();
        } else {
            updateMember.setDisable(false);
            setFields(da.readMemberMap().get(memberId));
        }
    }

    @FXML
    public void updateMember(ActionEvent event) {
        da.saveNewMember(getLibraryMember(memberId));
        Notify.confirmation("Member updated successfully");
        clearFields();
    }

    private LibraryMember getLibraryMember(String id) {
        String street = streetEditTxtField.getText();
        String city = cityEditTxtField.getText();
        String state = stateEditTxtField.getText();
        String zipCode = zipCodeEditTxtField.getText();
        return new LibraryMember(id, firstNameEditTxtField.getText(), lastNameEditTxtField.getText(), telEditTxtField.getText(), new Address(street, city, state, zipCode));
    }


    private void setFields(LibraryMember member) {
        firstNameEditTxtField.setText(member.getFirstName());
        lastNameEditTxtField.setText(member.getLastName());
        telEditTxtField.setText(member.getTelephone());
        Address address = member.getAddress();
        streetEditTxtField.setText(address != null ? address.getStreet() : "");
        cityEditTxtField.setText(address != null ? address.getCity() : "");
        stateEditTxtField.setText(address != null ? address.getState() : "");
        zipCodeEditTxtField.setText(address != null ? address.getZip() : "");
    }

    private void clearFields() {
        firstNameEditTxtField.clear();
        lastNameEditTxtField.clear();
        telEditTxtField.clear();
        streetEditTxtField.clear();
        cityEditTxtField.clear();
        stateEditTxtField.clear();
        zipCodeEditTxtField.clear();
    }
}
