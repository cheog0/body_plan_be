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

    public boolean register(RegisterRequestDto dto) {
        Optional<Register> existingUser = registerRepository.findByUserid(dto.getUserid());
        if (existingUser.isPresent()) {
            return false;
        }
        Register newUser = new Register(dto);
        registerRepository.save(newUser);
        return true;
    }

    public boolean existsByNickname(String nickname) {
        return registerRepository.existsByNickname(nickname);
    }
}
