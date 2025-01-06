package sembrella.ng.simrella.ng.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import sembrella.ng.simrella.ng.enums.TransactionType;

import java.util.UUID;

public class TransactionDto {
    private UUID loanId;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public UUID getLoanId() {
        return loanId;
    }

    public void setLoanId(UUID loanId) {
        this.loanId = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
