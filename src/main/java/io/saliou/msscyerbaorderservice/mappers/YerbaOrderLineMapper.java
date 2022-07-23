package io.saliou.msscyerbaorderservice.mappers;

import io.saliou.msscyerbaorderservice.domain.YerbaOrderLine;
import io.saliou.msscyerbaorderservice.model.YerbaOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface YerbaOrderLineMapper {

    YerbaOrderLineDto yerbaOrderLineToDto(YerbaOrderLine yerbaOrderLine);
    YerbaOrderLine dtoToYerbaOrderLine(YerbaOrderLineDto yerbaOrderLineDto);

}
