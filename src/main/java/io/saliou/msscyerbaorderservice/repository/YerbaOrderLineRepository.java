package io.saliou.msscyerbaorderservice.repository;

import io.saliou.msscyerbaorderservice.domain.YerbaOrderLine;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface YerbaOrderLineRepository extends PagingAndSortingRepository<YerbaOrderLine, UUID> {
}
