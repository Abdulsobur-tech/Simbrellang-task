package sembrella.ng.simrella.ng.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "audit_logging")
public class AuditLogging {
    @Id
    @GeneratedValue()
    private UUID id;


    private String action;
   private  String performBy;
   private String perfomerEmail;
    private LocalDateTime timestamp;

    public AuditLogging() {
    }

    public AuditLogging(UUID id, String action, String performBy,String perfomerEmail, LocalDateTime timestamp) {
        this.id = id;
        this.action = action;
        this.performBy = performBy;
        this.timestamp = timestamp;
        this.perfomerEmail =perfomerEmail;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPerformBy() {
        return performBy;
    }

    public void setPerformBy(String performBy) {
        this.performBy = performBy;
    }

    public String getPerfomerEmail() {
        return perfomerEmail;
    }

    public void setPerfomerEmail(String perfomerEmail) {
        this.perfomerEmail = perfomerEmail;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
