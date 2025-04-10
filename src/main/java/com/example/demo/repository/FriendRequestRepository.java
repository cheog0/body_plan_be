package com.example.demo.repository;

import com.example.demo.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findByReceiverIdAndStatus(String receiverId, FriendRequest.Status status);
    Optional<FriendRequest> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
