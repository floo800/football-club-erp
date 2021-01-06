package com.mnc.football.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mnc.football.web.rest.TestUtil;

public class ChildrenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Children.class);
        Children children1 = new Children();
        children1.setId(1L);
        Children children2 = new Children();
        children2.setId(children1.getId());
        assertThat(children1).isEqualTo(children2);
        children2.setId(2L);
        assertThat(children1).isNotEqualTo(children2);
        children1.setId(null);
        assertThat(children1).isNotEqualTo(children2);
    }
}
