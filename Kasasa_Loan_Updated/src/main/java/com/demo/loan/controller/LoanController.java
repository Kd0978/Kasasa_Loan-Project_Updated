package com.demo.loan.controller;

import javax.validation.Valid;

import com.demo.loan.entity.Loan;
import com.demo.loan.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> processCreateLoan(@Valid @RequestBody Loan loan) {
        Loan createdLoan = loanService.createLoan(loan);
        if (createdLoan == null)
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);

        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> fetchLoanByName(@RequestParam String name) {
        if (null == name || name.isEmpty())
            return new ResponseEntity<>("Query parameter cannot be empty or null", HttpStatus.BAD_REQUEST);

        Loan loan = loanService.getLoanByName(name);
        if (loan == null)
            return new ResponseEntity<>("Unable to find loan under the given name", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
}
