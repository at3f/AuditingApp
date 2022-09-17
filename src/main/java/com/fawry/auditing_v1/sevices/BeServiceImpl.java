package com.fawry.auditing_v1.sevices;

import com.fawry.auditing_v1.models.Be;
import com.fawry.auditing_v1.repositories.BeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BeServiceImpl implements BeService {

    @Autowired
    private BeRepository beRepository;

    @Override
    public Optional<Be> findBeById(Long id){
        return beRepository.findById(id);
    }
}
