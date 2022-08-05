package io.saliou.msscyerbaorderservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer extends BaseEntity {

    private String customerName;
    @Column(length = 36, columnDefinition = "varchar(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID apiKey;

    @OneToMany(mappedBy = "customer")
    private Set<YerbaOrder> yerbaOrders;

    @Builder
    public Customer(UUID id, Long version, Timestamp createdAt, Timestamp updatedAt, String customerName, UUID apiKey, Set<YerbaOrder> yerbaOrders) {
        super(id, version, createdAt, updatedAt);
        this.customerName = customerName;
        this.apiKey = apiKey;
        this.yerbaOrders = yerbaOrders;
    }
}
