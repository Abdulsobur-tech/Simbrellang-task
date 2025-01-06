package sembrella.ng.simrella.ng.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sembrella.ng.simrella.ng.dto.LoanDto;
import sembrella.ng.simrella.ng.dto.UpdateLoanStatusDto;
import sembrella.ng.simrella.ng.entity.Loan;
import sembrella.ng.simrella.ng.exceptions.UserNotFoundException;
import sembrella.ng.simrella.ng.service.LoanService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/loan")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public ResponseEntity<Loan> applyForLoan(@RequestBody LoanDto loanDto){
        Loan loan = loanService.applyForLoan(loanDto);
        return ResponseEntity.ok(loan);
    }
    @GetMapping("/customers")
    public  ResponseEntity<List<Loan>> getAllLoans(){
        List<Loan> loans = loanService.getAllLoan();
      return   ResponseEntity.ok(loans);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getCustomerDetails(@PathVariable UUID id){
        Loan customerDetails = loanService.getCustomerById(id);
        return  ResponseEntity.ok(customerDetails);
    }
    @PutMapping("/update-status")
    public ResponseEntity<?> updateLoanStatus(@RequestBody UpdateLoanStatusDto updateLoanStatusDto) {
        try {
            Loan loan = loanService.updateCustomerStatus(updateLoanStatusDto);
            return ResponseEntity.ok(loan); // Return 200 OK with the updated loan object
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
        }
    }
}
