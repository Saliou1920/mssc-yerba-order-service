package io.saliou.msscyerbaorderservice.bootstrap;

import io.saliou.msscyerbaorderservice.domain.Customer;
import io.saliou.msscyerbaorderservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class YerbaOrderBootStrap implements CommandLineRunner {

    public static final String YERBA_1_UPC = "063123420001";
    public static final String YERBA_2_UPC = "063123420002";
    public static final String YERBA_3_UPC = "063123420003";
    public static final String TASTING_ROOM = "Tasting Room";

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();
    }

    private void loadCustomerData() {
        if (customerRepository.count() == 0) {
            String TASTING_ROOM = "Tasting Room";
            Customer savedCustomer = customerRepository.save(Customer.builder()
                    .customerName(TASTING_ROOM)
                    .apiKey(UUID.randomUUID())
                    .build());

            log.info("Saved Tasting Room: " + savedCustomer);
        }
    }
}
