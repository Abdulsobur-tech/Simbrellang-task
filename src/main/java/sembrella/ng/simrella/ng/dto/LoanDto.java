package sembrella.ng.simrella.ng.dto;

import java.util.UUID;

public class LoanDto {
    private UUID userId;
    private double loanAmount;
    private int loanTenureInMonths;

    public LoanDto() {
    }

    public LoanDto(UUID userId, double loanAmount, int loanTenureInMonths) {
        this.userId = userId;
        this.loanAmount = loanAmount;
        this.loanTenureInMonths = loanTenureInMonths;
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

    public int getLoanTenureInMonths() {
        return loanTenureInMonths;
    }

    public void setLoanTenureInMonths(int loanTenureInMonths) {
        this.loanTenureInMonths = loanTenureInMonths;
    }
}
