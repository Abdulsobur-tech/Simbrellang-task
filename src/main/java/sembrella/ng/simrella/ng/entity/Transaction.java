package sembrella.ng.simrella.ng.entity;

import jakarta.persistence.*;
import sembrella.ng.simrella.ng.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

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

    private LocalDateTime TransactionDate;
}
