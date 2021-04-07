package com.demo.loan.repo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.demo.loan.entity.Loan;
import com.demo.loan.entity.LoanType;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = JpaTestConfig.class)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
public class LoanRepoTest  {

    @Autowired
    private LoanRepo loanRepo;

    @Test
    public void testSaveShouldSaveLoan() {
        Loan loan = new Loan();
        loan.setName("Test");
        loan.setSsn("SSN-Test-1");
        loan.setDob(new Date());
        loan.setLoanAmount(2500.0D);
        loan.setRate(12.0D);
        loan.setType(LoanType.STUDENT);
        loan.setTerm((short) 100);
        Loan saved = loanRepo.save(loan);

        assertThat(saved.getId(), notNullValue());
    }

    @Test
    @DatabaseSetup("/dataset/loans.xml")
    public void testFindByNameShouldReturnLoan() {
        final Loan loan = loanRepo.findByName("Test");
        assertThat(loan.getId(), equalTo(1));
        assertThat(loan.getSsn(), equalTo("SSN-Test-1"));
    }

}
