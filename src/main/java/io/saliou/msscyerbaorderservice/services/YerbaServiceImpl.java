package io.saliou.msscyerbaorderservice.services;

import io.saliou.msscyerbaorderservice.model.YerbaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;


@Service
public class YerbaServiceImpl implements YerbaService {

    public final String YERBA_PATH_V1= "/api/v1/yerba/";
    public final String YERBA_UPC_PATH_V1= "/api/v2/yerbaUpc/";

    @Value("${yerba.inventory.yerba-inventory-service-host}")
    private String yerbaInventoryServiceHost;

    private final RestTemplate restTemplate;

    public YerbaServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<YerbaDto> getYerbaById(UUID id) {
        return Optional.ofNullable(
                restTemplate.getForObject(yerbaInventoryServiceHost + YERBA_PATH_V1 + id.toString(), YerbaDto.class)
        );
    }

    @Override
    public Optional<YerbaDto> getYerbaByUpc(String yerbaUpc) {
        return Optional.ofNullable(
                restTemplate.getForObject(yerbaInventoryServiceHost + YERBA_UPC_PATH_V1 + yerbaUpc, YerbaDto.class)
        );
    }
}
