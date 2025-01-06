package sembrella.ng.simrella.ng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sembrella.ng.simrella.ng.dto.LoanDto;
import sembrella.ng.simrella.ng.dto.UpdateLoanStatusDto;
import sembrella.ng.simrella.ng.dto.UserDto;
import sembrella.ng.simrella.ng.entity.Loan;
import sembrella.ng.simrella.ng.entity.User;
import sembrella.ng.simrella.ng.enums.LoanStatus;
import sembrella.ng.simrella.ng.exceptions.LoanEligibilityException;
import sembrella.ng.simrella.ng.exceptions.UserNotFoundException;
import sembrella.ng.simrella.ng.repository.LoanRepository;
import sembrella.ng.simrella.ng.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private UserRepository userRepository;
    public Loan applyForLoan(LoanDto loanDto){
        Optional<User> user = userRepository.findById(loanDto.getUserId());
               if(user.isEmpty()){
                   throw new UserNotFoundException("No user with this id: " + loanDto.getUserId());
               }
         Optional <Loan> savedLoan = loanRepository.findByUserId(loanDto.getUserId());
        if (savedLoan.isPresent() && !"REPAID".equals(savedLoan.get().getStatus())) {
           throw new LoanEligibilityException("You currently have an outstanding loan with status: " + savedLoan.get().getStatus() );
        }
        Loan loan = new Loan();
        loan.setUserId(loanDto.getUserId());
        loan.setLoanAmount(loanDto.getLoanAmount());
        loan.setCreated_at(LocalDateTime.now());
        loan.setTenureInMonth(loanDto.getLoanTenureInMonths());
        loan.setInterestRate(calculateInterestRate(loanDto.getLoanAmount(), loanDto.getLoanTenureInMonths()));
        loan.setStatus(LoanStatus.PENDING);
        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoan(){
        return loanRepository.findAll();
    }
    public Loan getCustomerById(UUID userId){
        Optional <Loan> savedCustomer = loanRepository.findByUserId(userId);
        if (savedCustomer.isEmpty()) {
            throw new UserNotFoundException("You currently have an outstanding loan with status: ");
        }
        return savedCustomer.get();
    }
    public Loan updateCustomerStatus(UpdateLoanStatusDto updateLoanStatusDto){
        if (updateLoanStatusDto == null || updateLoanStatusDto.getId() == null || updateLoanStatusDto.getStatus() == null) {
            throw new IllegalArgumentException("Loan ID and status must not be null");
        }
        Optional<Loan> loanOptional = loanRepository.findById(updateLoanStatusDto.getId());
        if(loanOptional.isEmpty()){
            throw new UserNotFoundException("Loan not found");
        }
        Loan loan = loanOptional.get();
        loan.setStatus(updateLoanStatusDto.getStatus());
        loan.setUpdated_at(LocalDateTime.now());
        return loanRepository.save(loan);
    }

    private double calculateInterestRate(double amount, int tenure){
        double interestRate =15.0;
if(amount > 50000){
    interestRate += 1.5;
}
if(tenure > 12){
    interestRate += 1.0;
}
return interestRate;
    }
}
