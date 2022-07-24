package io.saliou.msscyerbaorderservice.services;

import io.saliou.msscyerbaorderservice.bootstrap.YerbaOrderBootStrap;
import io.saliou.msscyerbaorderservice.domain.Customer;
import io.saliou.msscyerbaorderservice.model.YerbaOrderDto;
import io.saliou.msscyerbaorderservice.model.YerbaOrderLineDto;
import io.saliou.msscyerbaorderservice.repository.CustomerRepository;
import io.saliou.msscyerbaorderservice.repository.YerbaOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class TestingRoomService {

    private final CustomerRepository customerRepository;
    private final YerbaOrderService yerbaOrderService;
    private final YerbaOrderRepository yerbaOrderRepository;
    private final List<String> yerbaUpcs = new ArrayList<>(3);

    public TestingRoomService(CustomerRepository customerRepository,
                              YerbaOrderService yerbaOrderService,
                              YerbaOrderRepository yerbaOrderRepository) {
        this.customerRepository = customerRepository;
        this.yerbaOrderService = yerbaOrderService;
        this.yerbaOrderRepository = yerbaOrderRepository;

        yerbaUpcs.add(YerbaOrderBootStrap.YERBA_1_UPC);
        yerbaUpcs.add(YerbaOrderBootStrap.YERBA_2_UPC);
        yerbaUpcs.add(YerbaOrderBootStrap.YERBA_3_UPC);
    }

    @Transactional
    @Scheduled(fixedRate = 5000)
    public void placeTastingRoomOrder() {
        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(YerbaOrderBootStrap.TASTING_ROOM);
        if (customerList.size() == 1) {
            Customer customer = customerList.get(0);
            log.info("Placing order for customer {}", customer.getCustomerName());
            placeOrder(customer);
        } else {
            log.error("No customer found for {}", YerbaOrderBootStrap.TASTING_ROOM);
        }



    }

    private void placeOrder(Customer customer) {
        String yerbaToOrder = yerbaUpcs.get((int) (Math.random() * yerbaUpcs.size()));

        YerbaOrderLineDto yerbaOrderLineDto = YerbaOrderLineDto.builder()
                .upc(yerbaToOrder)
                .orderQuantity(new Random().nextInt(10) + 1)
                .build();
        List<YerbaOrderLineDto> yerbaOrderLineDtoList = new ArrayList<>();
        yerbaOrderLineDtoList.add(yerbaOrderLineDto);

        YerbaOrderDto yerbaOrderDto = YerbaOrderDto.builder()
                .customerId(customer.getId())
                .customerRef(UUID.randomUUID().toString())
                .yerbaOrderLines(yerbaOrderLineDtoList)
                .build();

        YerbaOrderDto savedYerbaOrderDto = yerbaOrderService.createYerbaOrder(customer.getId(), yerbaOrderDto);
    }
}
