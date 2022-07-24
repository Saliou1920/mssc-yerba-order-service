package io.saliou.msscyerbaorderservice.services;

import io.saliou.msscyerbaorderservice.domain.Customer;
import io.saliou.msscyerbaorderservice.domain.YerbaOrder;
import io.saliou.msscyerbaorderservice.mappers.YerbaOrderMapper;
import io.saliou.msscyerbaorderservice.model.OrderStatusEnum;
import io.saliou.msscyerbaorderservice.model.YerbaOrderDto;
import io.saliou.msscyerbaorderservice.model.YerbaOrderPagedList;
import io.saliou.msscyerbaorderservice.repository.CustomerRepository;
import io.saliou.msscyerbaorderservice.repository.YerbaOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class YerbaOrderServiceImpl implements YerbaOrderService {

    private final YerbaOrderRepository yerbaOrderRepository;
    private final CustomerRepository customerRepository;
    private final YerbaOrderMapper yerbaOrderMapper;

    @Override
    public YerbaOrderPagedList getYerbaOrders(UUID customerId, Pageable pageable) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()) {
            log.error("Customer with id {} not found", customerId);
            throw new IllegalArgumentException("Customer with id " + customerId + " not found");
        }
        Page<YerbaOrder> yerbaOrderPage = yerbaOrderRepository.findAllByCustomer(customerOptional.get(), pageable);
        return new YerbaOrderPagedList(yerbaOrderPage
                .stream()
                .map(yerbaOrderMapper::yerbaOrderToDto)
                .collect(Collectors.toList()), PageRequest.of(
                yerbaOrderPage.getPageable().getPageNumber(),
                yerbaOrderPage.getPageable().getPageSize()),
                yerbaOrderPage.getTotalElements());
    }

    @Override
    public YerbaOrderDto createYerbaOrder(UUID customerId, YerbaOrderDto yerbaOrderDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()) {
            log.error("Customer with id {} not found", customerId);
            throw new IllegalArgumentException("Customer with id " + customerId + " not found");
        }
        YerbaOrder yerbaOrder = yerbaOrderMapper.dtoToYerbaOrder(yerbaOrderDto);
        yerbaOrder.setCustomer(customerOptional.get());
        yerbaOrder.setOrderStatus(OrderStatusEnum.NEW);
        yerbaOrder.getYerbaOrderLines().forEach(yerbaOrderLine -> yerbaOrderLine.setYerbaOrder(yerbaOrder));
        return yerbaOrderMapper.yerbaOrderToDto(yerbaOrderRepository.save(yerbaOrder));
    }

    @Override
    public YerbaOrderDto getYerbaOrderById(UUID orderId, UUID customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()) {
            log.error("Customer with id {} not found", customerId);
            throw new IllegalArgumentException("Customer with id " + customerId + " not found");
        }
        Optional<YerbaOrder> yerbaOrderOptional = yerbaOrderRepository.findById(orderId);
        if (!yerbaOrderOptional.isPresent()) {
            log.error("YerbaOrder with id {} not found", orderId);
            throw new IllegalArgumentException("YerbaOrder with id " + orderId + " not found");
        }
        if (!yerbaOrderOptional.get().getCustomer().getId().equals(customerId)) {
            log.error("YerbaOrder with id {} not found", orderId);
            throw new IllegalArgumentException("YerbaOrder with id " + orderId + " not found");
        }
        return yerbaOrderMapper.yerbaOrderToDto(yerbaOrderOptional.get());
    }

    @Override
    public void pickupYerbaOrder(UUID orderId, UUID customerId) {
        YerbaOrder yerbaOrder = yerbaOrderMapper.dtoToYerbaOrder(getYerbaOrderById(orderId, customerId));
        yerbaOrder.setOrderStatus(OrderStatusEnum.COMPLETE);
        yerbaOrderRepository.save(yerbaOrder);
    }
}
