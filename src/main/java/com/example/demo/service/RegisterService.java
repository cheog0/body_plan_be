package com.example.demo.service;

import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.entity.Register;
import com.example.demo.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {
    private final RegisterRepository registerRepository;

    @Autowired
    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public boolean register(RegisterRequestDto registerRequestDto) {
        Optional<Register> existingUser = registerRepository.findByUserid(registerRequestDto.getUserid());

        if (existingUser.isPresent()) {
            return false; // 이미 존재하는 아이디
        }

        Register newUser = new Register(registerRequestDto);
        registerRepository.save(newUser);
        return true;
    }
}
