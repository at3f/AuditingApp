package com.fawry.auditing_v1;

import com.fawry.auditing_v1.models.Application;
import com.fawry.auditing_v1.repositories.ApplicationRepository;
import com.fawry.auditing_v1.sevices.ApplicationService;
import com.fawry.auditing_v1.sevices.ApplicationServiceImpl;
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
public class ApplicationServiceTest {
    private final Long APPLICATION_ID = 1l;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ApplicationService applicationService() {
            return new ApplicationServiceImpl();
        }
    }

    @Autowired
    private ApplicationService applicationService;
    @MockBean
    private ApplicationRepository applicationRepository;

    @Test
    public void whenSeachByAppId_CallFindByIdMethod(){
        applicationService.findApplicationById(any());
        verify(applicationRepository,times(1)).findById(any());
    }

    @Test
    public void whenSearchByUserId_returnTheSpecificUser(){
        Application application = new Application();
        application.setId(APPLICATION_ID);

        when(applicationRepository.findById(APPLICATION_ID)).thenReturn(Optional.of(application));

        assertThat(applicationService.findApplicationById(APPLICATION_ID).get().getId()).isEqualTo(APPLICATION_ID);
    }
}
