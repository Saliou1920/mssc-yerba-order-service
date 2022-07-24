package io.saliou.msscyerbaorderservice.controller;

import io.saliou.msscyerbaorderservice.model.YerbaOrderDto;
import io.saliou.msscyerbaorderservice.model.YerbaOrderPagedList;
import io.saliou.msscyerbaorderservice.services.YerbaOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v1/customer/{customerId}/")
@RestController
@RequiredArgsConstructor
public class YerbaOrderController {

    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;

    private final YerbaOrderService yerbaOrderService;

    @GetMapping("orders")
    public ResponseEntity<YerbaOrderPagedList> getYerbaOrders(
             @PathVariable UUID customerId,
             @RequestParam(value = "pageSize", required = false ) Integer pageSize,
             @RequestParam(value = "pageNumber", required = false ) Integer pageNumber) {
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        return ResponseEntity.ok(yerbaOrderService.getYerbaOrders(customerId, PageRequest.of(pageNumber, pageSize)));
    }

    @PostMapping("orders")
    public ResponseEntity<YerbaOrderDto> createYerbaOrder(@PathVariable UUID customerId,
                                                          @Valid @RequestBody YerbaOrderDto yerbaOrderDto) {
        return ResponseEntity.ok(yerbaOrderService.createYerbaOrder(customerId, yerbaOrderDto));
    }

    @GetMapping("orders/{orderId}")
    public ResponseEntity<YerbaOrderDto> getYerbaOrderById(@PathVariable UUID orderId, @PathVariable UUID customerId) {
        return ResponseEntity.ok(yerbaOrderService.getYerbaOrderById(orderId, customerId));
    }

    @PutMapping("orders/{orderId}/pickup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pickupYerbaOrder(@PathVariable UUID orderId, @PathVariable UUID customerId) {
        yerbaOrderService.pickupYerbaOrder(orderId, customerId);
    }

}
