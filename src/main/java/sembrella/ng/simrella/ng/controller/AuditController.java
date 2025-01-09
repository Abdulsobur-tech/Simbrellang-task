package sembrella.ng.simrella.ng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sembrella.ng.simrella.ng.entity.AuditLogging;
import sembrella.ng.simrella.ng.service.AuditLoggingService;

import java.util.UUID;

@RestController
@RequestMapping("api/audit")
public class AuditController {
    @Autowired
    private AuditLoggingService auditLoggingService;
    @GetMapping("/{id}")
    public ResponseEntity<AuditLogging> getAuditById(@PathVariable UUID id){
        AuditLogging auditLogging = auditLoggingService.getAuditById(id);
        return ResponseEntity.ok(auditLogging);
    }
}
