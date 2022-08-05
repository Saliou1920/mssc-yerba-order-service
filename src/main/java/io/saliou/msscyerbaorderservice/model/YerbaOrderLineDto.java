package io.saliou.msscyerbaorderservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class YerbaOrderLineDto extends BaseItem {

    private String upc;
    private String yerbaName;
    private UUID yerbaId;
    private Integer orderQuantity = 0;
    private String yerbaType;
    private BigDecimal price;

    @Builder
    public YerbaOrderLineDto(UUID id, Integer version, OffsetDateTime createdAt, OffsetDateTime updatedAt, String upc,
                             String yerbaName, UUID yerbaId, Integer orderQuantity, String yerbaType, BigDecimal price) {
        super(id, version, createdAt, updatedAt);
        this.upc = upc;
        this.yerbaName = yerbaName;
        this.yerbaId = yerbaId;
        this.orderQuantity = orderQuantity;
        this.yerbaType = yerbaType;
        this.price = price;
    }
}
