package com.example.demo.service;

import com.example.demo.dto.UserUpdateRequestDto;
import com.example.demo.entity.Register;
import com.example.demo.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {

    private final RegisterRepository registerRepository;

    @Autowired
    public UserUpdateService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public Register updateUser(String userid, UserUpdateRequestDto dto) {
        Register user = registerRepository.findByUserid(userid).orElse(null);
        if (user == null) return null;

        if (dto.getNickname() != null) user.setNickname(dto.getNickname());
        if (dto.getHeight() != null) user.setHeight(dto.getHeight());
        if (dto.getWeight() != null) user.setWeight(dto.getWeight());
        if (dto.getBirthDate() != null) user.setBirthDate(dto.getBirthDate());

        return registerRepository.save(user);
    }
}
