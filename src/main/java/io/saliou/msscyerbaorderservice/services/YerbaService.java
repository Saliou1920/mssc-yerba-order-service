package io.saliou.msscyerbaorderservice.services;

import io.saliou.msscyerbaorderservice.model.YerbaDto;

import java.util.Optional;
import java.util.UUID;

public interface YerbaService {

    Optional<YerbaDto> getYerbaById(UUID id);
    Optional<YerbaDto> getYerbaByUpc(String yerbaUpc);
}
