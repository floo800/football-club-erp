package com.mnc.football.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mnc.football.web.rest.TestUtil;

public class ChildrenDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChildrenDTO.class);
        ChildrenDTO childrenDTO1 = new ChildrenDTO();
        childrenDTO1.setId(1L);
        ChildrenDTO childrenDTO2 = new ChildrenDTO();
        assertThat(childrenDTO1).isNotEqualTo(childrenDTO2);
        childrenDTO2.setId(childrenDTO1.getId());
        assertThat(childrenDTO1).isEqualTo(childrenDTO2);
        childrenDTO2.setId(2L);
        assertThat(childrenDTO1).isNotEqualTo(childrenDTO2);
        childrenDTO1.setId(null);
        assertThat(childrenDTO1).isNotEqualTo(childrenDTO2);
    }
}
