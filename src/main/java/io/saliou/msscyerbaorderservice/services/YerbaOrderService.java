package io.saliou.msscyerbaorderservice.services;

import io.saliou.msscyerbaorderservice.model.YerbaOrderDto;
import io.saliou.msscyerbaorderservice.model.YerbaOrderPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface YerbaOrderService {
    YerbaOrderPagedList getYerbaOrders(UUID customerId, Pageable pageable);

    YerbaOrderDto createYerbaOrder(UUID customerId, YerbaOrderDto yerbaOrderDto);

    YerbaOrderDto getYerbaOrderById(UUID orderId, UUID customerId);

    void pickupYerbaOrder(UUID orderId, UUID customerId);

}
