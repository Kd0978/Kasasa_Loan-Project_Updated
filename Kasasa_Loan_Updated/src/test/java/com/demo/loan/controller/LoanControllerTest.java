package com.demo.loan.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.loan.entity.Loan;
import com.demo.loan.entity.LoanType;
import com.demo.loan.service.LoanService;

@ExtendWith(MockitoExtension.class)
class LoanControllerTest {

    @Mock
    private LoanService service;
    private MockMvc mockMvc;

    @InjectMocks
    private LoanController controller;

    private JacksonTester<Loan> loanJacksonTester;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testProcessCreateLoan() throws Exception {
        Loan loan = new Loan();
        loan.setName("Test");
        loan.setSsn("SSN-Test-1");
        loan.setDob(new Date());
        loan.setLoanAmount(2500.0D);
        loan.setRate(12.0D);
        loan.setType(LoanType.STUDENT);
        loan.setTerm((short) 100);

        Loan saved = new Loan();
        BeanUtils.copyProperties(loan, saved);

        when(service.createLoan(any(Loan.class))).thenReturn(saved);
        MockHttpServletResponse response = mockMvc.perform(
            post("/loan")
            .contentType(MediaType.APPLICATION_JSON)
            .content(loanJacksonTester.write(loan).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus(), equalTo(HttpStatus.CREATED.value()));
    }
}
