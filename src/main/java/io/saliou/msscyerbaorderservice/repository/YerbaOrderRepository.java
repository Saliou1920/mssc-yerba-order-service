package io.saliou.msscyerbaorderservice.repository;

import io.saliou.msscyerbaorderservice.domain.Customer;
import io.saliou.msscyerbaorderservice.domain.YerbaOrder;
import io.saliou.msscyerbaorderservice.model.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.UUID;

@Repository
public interface YerbaOrderRepository extends JpaRepository<YerbaOrder, UUID> {

    Page<YerbaOrder> findAllByCustomer(Customer customer, Pageable pageable);
    List<YerbaOrder> findAllByOrderStatus(OrderStatusEnum orderStatus);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    YerbaOrder findOneById(UUID id);
}
