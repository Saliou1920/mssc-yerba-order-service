package io.saliou.msscyerbaorderservice.domain;

import io.saliou.msscyerbaorderservice.model.OrderStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class YerbaOrder extends BaseEntity {

    private String customerRef;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "yerbaOrder", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<YerbaOrderLine> yerbaOrderLines;

    private OrderStatusEnum orderStatus = OrderStatusEnum.NEW;
    private String orderStatusCallbackUrl;

    @Builder
    public YerbaOrder(UUID id, Long version, Timestamp createdAt, Timestamp updatedAt,
                      String customerRef, Customer customer, List<YerbaOrderLine> yerbaOrderLines,
                      OrderStatusEnum orderStatus, String orderStatusCallbackUrl) {
        super(id, version, createdAt, updatedAt);
        this.customerRef = customerRef;
        this.customer = customer;
        this.yerbaOrderLines = yerbaOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
    }
}
