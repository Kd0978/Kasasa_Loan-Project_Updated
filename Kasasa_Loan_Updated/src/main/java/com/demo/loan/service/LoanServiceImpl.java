package com.demo.loan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.loan.entity.Loan;
import com.demo.loan.entity.LoanType;
import com.demo.loan.repo.LoanRepo;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepo loanRepo;
    private final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

    @Autowired
    public LoanServiceImpl(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    public Loan createLoan(Loan loan) {
        double apr = calculateApr(loan);
        logger.info("APR: {}", apr);
        loan.setApr(apr);

        return loanRepo.save(loan);
    }

    public Loan getLoanByName(String name) {
        return loanRepo.findByName(name);
    }

    private double calculateApr(Loan loan) {
        double interest = calculateInterest(loan.getLoanAmount(), loan.getRate(), loan.getTerm());
        logger.info("INTEREST: {}", interest);

        double feesInterestSum = interest + getLoanFees(loan.getType());
        double tempAmt1 = feesInterestSum / loan.getLoanAmount();
        double tempAmt2 = tempAmt1 / loan.getTerm();
        return tempAmt2 * 365 * 100;
    }

    private double calculateInterest(double loanAmount, double rate, short term) {
        double tempAmount = loanAmount * rate * term;
        return tempAmount / 100;
    }

    private short getLoanFees(LoanType type){
        switch (type) {
            case AUTO:
                return 500;
            case PERSONAL:
                return 750;
            case MORTGAGE:
                return 1500;
            default:
                return 0;
        }
    }
}
