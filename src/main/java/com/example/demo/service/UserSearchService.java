package com.example.demo.service;

import com.example.demo.entity.UserNickname;
import com.example.demo.repository.UserSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSearchService {

    private final UserSearchRepository userSearchRepository;

    @Autowired
    public UserSearchService(UserSearchRepository userSearchRepository) {
        this.userSearchRepository = userSearchRepository;
    }

    public Optional<UserNickname> findByNickname(String nickname) {
        return userSearchRepository.findByNickname(nickname);
    }
}
