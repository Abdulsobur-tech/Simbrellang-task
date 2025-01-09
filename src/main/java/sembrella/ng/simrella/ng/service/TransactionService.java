package sembrella.ng.simrella.ng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sembrella.ng.simrella.ng.dto.TransactionDto;
import sembrella.ng.simrella.ng.entity.AuditLogging;
import sembrella.ng.simrella.ng.entity.Loan;
import sembrella.ng.simrella.ng.entity.Transaction;
import sembrella.ng.simrella.ng.entity.User;
import sembrella.ng.simrella.ng.exceptions.ResourceNotFoundException;
import sembrella.ng.simrella.ng.exceptions.UserNotFoundException;
import sembrella.ng.simrella.ng.repository.AuditLoggingRepository;
import sembrella.ng.simrella.ng.repository.LoanRepository;
import sembrella.ng.simrella.ng.repository.TransactionRepository;
import sembrella.ng.simrella.ng.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AuditLoggingRepository auditLoggingRepository;
@Autowired
private LoanRepository loanRepository;
@Autowired
private UserRepository userRepository;
    public Transaction recordTransaction(TransactionDto transactionDto){
        Optional<Loan> loan = loanRepository.findById(transactionDto.getLoanId());

        if (transactionDto.getAmount() <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive");

        }
        Optional<User> user = userRepository.findById(loan.get().getUserId());

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setType(transactionDto.getTransactionType());
        transaction.setLoan(loan.get());
        transaction.setUser(user.get());
        AuditLogging auditLogging = new AuditLogging();
        auditLogging.setTimestamp(LocalDateTime.now());
        auditLogging.setAction(transactionDto.getTransactionType().toString());
        auditLogging.setPerfomerEmail(user.get().getEmail());
        auditLogging.setPerformBy(user.get().getFirstName() + " " + user.get().getLastName());
        auditLoggingRepository.save(auditLogging);
        return transactionRepository.save(transaction);
    }
    public Transaction getTransactionById(UUID id) {
        return transactionRepository.findById(id)
                .orElseThrow(() ->
                     new ResourceNotFoundException("Transaction not found")
                );
    }

    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }
}
