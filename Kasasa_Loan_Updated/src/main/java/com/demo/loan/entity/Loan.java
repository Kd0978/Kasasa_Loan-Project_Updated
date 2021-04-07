package com.demo.loan.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tbl_loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Name should not be null")
    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @NotNull(message = "SSN should not be null")
    private String ssn;

    @NotNull(message = "Date of birth should not be null")
    private Date dob;

    @NotNull(message = "Loan amount should not be null")
    private Double loanAmount;

    @NotNull(message = "Rate should not be null")
    private Double rate;

    @NotNull(message = "Type should not be null")
    private LoanType type;

    @NotNull(message = "Term should not be null")
    private short term;

    private Double apr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public short getTerm() {
        return term;
    }

    public void setTerm(short term) {
        this.term = term;
    }

    public Double getApr() {
        return apr;
    }

    public void setApr(Double apr) {
        this.apr = apr;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", dob=" + dob +
                ", loanAmount=" + loanAmount +
                ", rate=" + rate +
                ", type=" + type +
                ", term=" + term +
                ", apr=" + apr +
                '}';
    }
}
