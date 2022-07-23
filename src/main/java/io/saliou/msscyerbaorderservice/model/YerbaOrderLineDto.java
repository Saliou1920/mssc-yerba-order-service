package io.saliou.msscyerbaorderservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @Builder
    public YerbaOrderLineDto(UUID id, Integer version, OffsetDateTime createdAt, OffsetDateTime updatedAt, String upc,
                             String yerbaName, UUID yerbaId, Integer orderQuantity) {
        super(id, version, createdAt, updatedAt);
        this.upc = upc;
        this.yerbaName = yerbaName;
        this.yerbaId = yerbaId;
        this.orderQuantity = orderQuantity;
    }
}
