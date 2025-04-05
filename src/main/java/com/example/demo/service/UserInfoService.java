package com.example.demo.service;

import com.example.demo.entity.Register;
import com.example.demo.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final RegisterRepository registerRepository;

    @Autowired
    public UserInfoService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public Register getUserByUserid(String userid) {
        return registerRepository.findByUserid(userid).orElse(null);
    }
}
