package ir.fathi.individual.client.ledger;

import ir.fathi.constants.AuditableEntity;
import ir.fathi.enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "clientـledger")
public class ClientLedger extends AuditableEntity {

    private LocalDateTime depositDate;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private Integer balance;
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Enrollment enrollment;

}
