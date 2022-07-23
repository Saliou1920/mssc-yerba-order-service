package io.saliou.msscyerbaorderservice.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class YerbaOrderPagedList extends PageImpl<YerbaOrderDto> {
    public YerbaOrderPagedList(List<YerbaOrderDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public YerbaOrderPagedList(List<YerbaOrderDto> content) {
        super(content);
    }
}
