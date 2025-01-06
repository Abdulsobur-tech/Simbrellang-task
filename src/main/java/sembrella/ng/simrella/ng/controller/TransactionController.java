package sembrella.ng.simrella.ng.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sembrella.ng.simrella.ng.dto.TransactionDto;
import sembrella.ng.simrella.ng.entity.Transaction;
import sembrella.ng.simrella.ng.service.TransactionService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/record")
    public ResponseEntity<Transaction> transaction(@RequestBody TransactionDto transactionDto){
        Transaction transaction =  transactionService.recordTransaction(transactionDto);
        return  ResponseEntity.ok(transaction);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable UUID id){
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }
    @GetMapping("/histories")
    public ResponseEntity<List<Transaction>> getTransaction() {
        List<Transaction> transaction = transactionService.getTransactions();
        return ResponseEntity.ok(transaction);
    }
}
