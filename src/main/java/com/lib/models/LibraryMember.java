package com.lib.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LibraryMember extends Person implements Serializable {
    private static final long serialVersionUID = -2226197306790714013L;

    private String memberId;
    private List<CheckoutRecord> records;

    public LibraryMember(String memberId, String fname, String lname, String tel, Address add) {
        super(fname, lname, tel, add);
        this.memberId = memberId;
        this.records = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }


    public List<CheckoutRecord> getCheckoutRecord() {
        return records;
    }

    public void addRecord(CheckoutRecord entry) {
        if (entry.getMember() != this) {
            entry.setMember(this);
        }
        records.add(entry);
    }

    public void setRecords(List<CheckoutRecord> records) {
        this.records.clear();
        for (CheckoutRecord record : records) {
            addRecord(record);
        }
    }

    @Override
    public String toString() {
        return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() +
                ", " + getTelephone() + " " + getAddress();
    }
}
