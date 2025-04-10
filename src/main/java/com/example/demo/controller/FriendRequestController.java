package com.example.demo.controller;

import com.example.demo.dto.FriendRequestDto;
import com.example.demo.dto.ReceivedFriendRequestDto;
import com.example.demo.entity.FriendRequest;
import com.example.demo.service.FriendRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/friend")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping("/request")
    public ResponseEntity<String> sendRequest(@RequestBody FriendRequestDto dto) {
        friendRequestService.sendRequest(dto.getSenderId(), dto.getReceiverId());
        return ResponseEntity.ok("친구 요청을 보냈습니다.");
    }

    @GetMapping("/requests")
    public ResponseEntity<List<ReceivedFriendRequestDto>> getRequests(@RequestParam String receiverId) {
        return ResponseEntity.ok(friendRequestService.getPendingRequestsWithNickname(receiverId));
    }


    @PostMapping("/accept/{id}")
    public ResponseEntity<String> acceptRequest(@PathVariable Long id) {
        boolean accepted = friendRequestService.acceptRequest(id);
        return accepted ? ResponseEntity.ok("요청 수락") : ResponseEntity.badRequest().body("요청이 존재하지 않음");
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long id) {
        boolean rejected = friendRequestService.rejectRequest(id);
        return rejected ? ResponseEntity.ok("요청 거절") : ResponseEntity.badRequest().body("요청이 존재하지 않음");
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> getFriendRequestStatus(
            @RequestParam String senderId,
            @RequestParam String receiverId) {

        Optional<FriendRequest> request = friendRequestService.getFriendRequestBetween(senderId, receiverId);

        Map<String, String> result = new HashMap<>();
        result.put("status", request.map(r -> r.getStatus().name()).orElse("NONE")); // "PENDING", "ACCEPTED", "NONE"

        return ResponseEntity.ok(result);
    }
}
