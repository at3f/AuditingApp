package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.models.Application;
import com.fawry.auditing_v1.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    public Optional<Application> findApplicationById(Long id){
        return applicationRepository.findById(id);
    }
    public List<Application> findApplications(){
        return applicationRepository.findAll();
    }
}
