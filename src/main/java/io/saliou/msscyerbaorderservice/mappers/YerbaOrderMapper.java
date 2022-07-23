package io.saliou.msscyerbaorderservice.mappers;

import io.saliou.msscyerbaorderservice.domain.YerbaOrder;
import io.saliou.msscyerbaorderservice.model.YerbaOrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {YerbaOrderLineMapper.class, DateMapper.class})
public interface YerbaOrderMapper {

    YerbaOrderDto yerbaOrderToDto(YerbaOrder yerbaOrder);

    YerbaOrder dtoToYerbaOrder(YerbaOrderDto yerbaOrderDto);

}
