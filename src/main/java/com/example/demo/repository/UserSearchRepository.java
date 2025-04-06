package com.example.demo.repository;

import com.example.demo.entity.UserNickname;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSearchRepository extends JpaRepository<UserNickname, Long> {
    Optional<UserNickname> findByNickname(String nickname);
}
