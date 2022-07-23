package io.saliou.msscyerbaorderservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class YerbaOrderDto extends BaseItem{

    private UUID customerId;
    private String customerRef;
    private List<YerbaOrderLineDto> yerbaOrderLines;
    private OrderStatusEnum orderStatus;
    private String orderStatusCallbackUrl;

    @Builder
    public YerbaOrderDto(UUID id, Integer version, OffsetDateTime createdAt, OffsetDateTime updatedAt, UUID customerId, List<YerbaOrderLineDto> yerbaOrderLines,
                         OrderStatusEnum orderStatus, String orderStatusCallbackUrl, String customerRef) {
        super(id, version, createdAt, updatedAt);
        this.customerId = customerId;
        this.yerbaOrderLines = yerbaOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
        this.customerRef = customerRef;
    }
}
