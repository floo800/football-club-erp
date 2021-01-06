package com.mnc.football.service.mapper;


import com.mnc.football.domain.*;
import com.mnc.football.service.dto.ChildrenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Children} and its DTO {@link ChildrenDTO}.
 */
@Mapper(componentModel = "spring", uses = {TeamMapper.class})
public interface ChildrenMapper extends EntityMapper<ChildrenDTO, Children> {

    @Mapping(source = "team.id", target = "teamId")
    ChildrenDTO toDto(Children children);

    @Mapping(source = "teamId", target = "team")
    Children toEntity(ChildrenDTO childrenDTO);

    default Children fromId(Long id) {
        if (id == null) {
            return null;
        }
        Children children = new Children();
        children.setId(id);
        return children;
    }
}
