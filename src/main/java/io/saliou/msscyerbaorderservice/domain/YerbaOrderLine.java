package io.saliou.msscyerbaorderservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class YerbaOrderLine extends BaseEntity {

    @ManyToOne
    private YerbaOrder yerbaOrder;

    private UUID yerbaId;
    private Integer orderQuantity = 0;
    private Integer deliveredQuantity = 0;

    @Builder
    public YerbaOrderLine(UUID id, Long version, Timestamp createdAt, Timestamp updatedAt, YerbaOrder yerbaOrder,
                          UUID yerbaId, Integer orderQuantity, Integer deliveredQuantity) {
        super(id, version, createdAt, updatedAt);
        this.yerbaOrder = yerbaOrder;
        this.yerbaId = yerbaId;
        this.orderQuantity = orderQuantity;
        this.deliveredQuantity = deliveredQuantity;
    }
}
