package com.mnc.football.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ChildrenMapperTest {

    private ChildrenMapper childrenMapper;

    @BeforeEach
    public void setUp() {
        childrenMapper = new ChildrenMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(childrenMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(childrenMapper.fromId(null)).isNull();
    }
}
