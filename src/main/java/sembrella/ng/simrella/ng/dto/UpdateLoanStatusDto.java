package sembrella.ng.simrella.ng.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import sembrella.ng.simrella.ng.enums.LoanStatus;

import java.util.UUID;

public class UpdateLoanStatusDto {
    private UUID id;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    public UpdateLoanStatusDto(UUID id, LoanStatus status) {
        this.id = id;
        this.status = status;
    }

    public UpdateLoanStatusDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
