package io.saliou.msscyerbaorderservice.mappers;

import io.saliou.msscyerbaorderservice.domain.YerbaOrderLine;
import io.saliou.msscyerbaorderservice.model.YerbaDto;
import io.saliou.msscyerbaorderservice.model.YerbaOrderLineDto;
import io.saliou.msscyerbaorderservice.services.YerbaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public abstract class YerbaOrderLineMapperDecorator  implements YerbaOrderLineMapper {
    private YerbaService yerbaService;
    private YerbaOrderLineMapper yerbaOrderLineMapper;

    @Autowired
    private void setYerbaService(YerbaService yerbaService) {
        this.yerbaService = yerbaService;
    }

    @Autowired
    @Qualifier("delegate")
    private void setYerbaOrderLineMapper(YerbaOrderLineMapper yerbaOrderLineMapper) {
        this.yerbaOrderLineMapper = yerbaOrderLineMapper;
    }

    @Override
    public YerbaOrderLineDto yerbaOrderLineToDto(YerbaOrderLine yerbaOrderLine) {
        YerbaOrderLineDto yerbaOrderLineDto = yerbaOrderLineMapper.yerbaOrderLineToDto(yerbaOrderLine);
        Optional<YerbaDto> yerbaDto = yerbaService.getYerbaByUpc(yerbaOrderLine.getUpc());

        yerbaDto.ifPresent(yerbaDto1 -> {
            yerbaOrderLineDto.setYerbaName(yerbaDto1.getName());
            yerbaOrderLineDto.setPrice(yerbaDto1.getPrice());
            yerbaOrderLineDto.setUpc(yerbaDto1.getUpc());
            yerbaOrderLineDto.setPrice(yerbaDto1.getPrice());
        });

        return yerbaOrderLineDto;
    }
}
