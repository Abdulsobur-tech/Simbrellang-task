package sembrella.ng.simrella.ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sembrella.ng.simrella.ng.entity.Loan;

import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan,UUID> {
   Optional <Loan> findByUserId(UUID userId);
}
