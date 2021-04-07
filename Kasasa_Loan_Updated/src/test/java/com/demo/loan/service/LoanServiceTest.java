package com.demo.loan.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import com.demo.loan.entity.Loan;
import com.demo.loan.entity.LoanType;
import com.demo.loan.repo.LoanRepo;

public class LoanServiceTest {

    @Mock
    private LoanRepo loanRepo;

    @InjectMocks
    private LoanServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveShouldReturnSavedLoan() {
        Loan loan = new Loan();
        loan.setName("Test");
        loan.setSsn("SSN-Test-1");
        loan.setDob(new Date());
        loan.setLoanAmount(2500.0D);
        loan.setRate(12.0D);
        loan.setType(LoanType.STUDENT);
        loan.setTerm((short) 100);

        Loan savedStub = new Loan();
        BeanUtils.copyProperties(loan, savedStub);
        savedStub.setId(1);

        when(loanRepo.save(loan)).thenReturn(savedStub);

        Loan saved = service.createLoan(loan);

        assertThat(saved.getId(), equalTo(1));
    }

    @Test
    public void testGetLoanByName() {
        Loan stub = new Loan();
        stub.setId(1);
        stub.setName("Test");
        stub.setSsn("SSN-Test-1");
        stub.setDob(new Date());
        stub.setLoanAmount(2500.0D);
        stub.setRate(12.0D);
        stub.setType(LoanType.STUDENT);
        stub.setTerm((short) 100);

        when(loanRepo.findByName(any(String.class))).thenReturn(stub);
        Loan loan = service.getLoanByName("Test");
        assertThat(loan.getSsn(), equalTo("SSN-Test-1"));
    }
}
