package sembrella.ng.simrella.ng.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sembrella.ng.simrella.ng.dto.TransactionDto;
import sembrella.ng.simrella.ng.entity.Transaction;
import sembrella.ng.simrella.ng.service.TransactionService;

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
}
