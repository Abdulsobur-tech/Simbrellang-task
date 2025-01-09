package sembrella.ng.simrella.ng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sembrella.ng.simrella.ng.entity.AuditLogging;
import sembrella.ng.simrella.ng.exceptions.UserNotFoundException;
import sembrella.ng.simrella.ng.repository.AuditLoggingRepository;

import java.util.Optional;
import java.util.UUID;
@Service
public class AuditLoggingService {
   @Autowired
    private AuditLoggingRepository auditLoggingRepository;
    public AuditLogging getAuditById(UUID id){
       AuditLogging auditLogging = auditLoggingRepository.findById(id)
               .orElseThrow(() -> new UserNotFoundException("Audit not found"));

       return auditLogging;
    }
}
