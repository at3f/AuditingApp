package com.fawry.auditing_v1;

import com.fawry.auditing_v1.models.Be;
import com.fawry.auditing_v1.repositories.BeRepository;
import com.fawry.auditing_v1.sevices.BeService;
import com.fawry.auditing_v1.sevices.BeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class BeServiceTest {
    private final Long BE_ID = 1l;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public BeService beService() {
            return new BeServiceImpl();
        }
    }

    @Autowired
    private BeService beService;

    @MockBean
    private BeRepository beRepository;

    @Test
    public void whenSeachByBeId_CallFindByIdMethod(){
        beService.findBeById(any());
        verify(beRepository,times(1)).findById(any());
    }

    @Test
    public void whenSearchByBeId_returnTheSpecificBe(){
        Be be = new Be();
        be.setId(BE_ID);

        when(beRepository.findById(BE_ID)).thenReturn(Optional.of(be));

        assertThat(beService.findBeById(BE_ID).get().getId()).isEqualTo(BE_ID);
    }
}
