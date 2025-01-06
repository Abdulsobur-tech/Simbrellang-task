package sembrella.ng.simrella.ng.entity;

import jakarta.persistence.*;
import sembrella.ng.simrella.ng.enums.TransactionType;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {
    @GeneratedValue
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType Type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime TransactionDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public Transaction(UUID id, Loan loan, double amount, TransactionType type, User user, LocalDateTime transactionDate) {
        this.id = id;
        this.loan = loan;
        this.amount = amount;
        Type = type;
        this.user = user;
        TransactionDate = transactionDate;
    }

    public Transaction() {
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return Type;
    }

    public void setType(TransactionType type) {
        Type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        TransactionDate = transactionDate;
    }
}
