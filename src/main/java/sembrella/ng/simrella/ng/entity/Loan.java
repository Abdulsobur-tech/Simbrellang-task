package sembrella.ng.simrella.ng.entity;

import jakarta.persistence.*;
import sembrella.ng.simrella.ng.enums.LoanStatus;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "loan")
public class Loan {
    @Id
   @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = false)
    private double loanAmount;
    private int tenureInMonth;
    private double interestRate;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    public Loan(UUID id, UUID userId, double loanAmount, int tenureInMonth, double interestRate, LoanStatus status, LocalDateTime created_at,LocalDateTime updated_at) {
        this.id = id;
        this.userId = userId;
        this.loanAmount = loanAmount;
        this.tenureInMonth = tenureInMonth;
        this.interestRate = interestRate;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Loan() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getTenureInMonth() {
        return tenureInMonth;
    }

    public void setTenureInMonth(int tenureInMonth) {
        this.tenureInMonth = tenureInMonth;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
