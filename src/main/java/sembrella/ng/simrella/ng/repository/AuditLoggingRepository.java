package sembrella.ng.simrella.ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sembrella.ng.simrella.ng.entity.AuditLogging;

import java.util.UUID;

public interface AuditLoggingRepository extends JpaRepository<AuditLogging, UUID> {
}
