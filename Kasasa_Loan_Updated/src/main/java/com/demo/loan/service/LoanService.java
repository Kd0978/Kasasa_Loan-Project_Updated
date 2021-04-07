package com.demo.loan.service;

import com.demo.loan.entity.Loan;

public interface LoanService {

    Loan createLoan(Loan loan);

    Loan getLoanByName(String name);
}
