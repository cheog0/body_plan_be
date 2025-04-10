package com.example.demo.service;

import com.example.demo.dto.ReceivedFriendRequestDto;
import com.example.demo.entity.FriendRequest;
import com.example.demo.repository.FriendRequestRepository;
import com.example.demo.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final RegisterRepository registerRepository;

    @Autowired
    public FriendRequestService(FriendRequestRepository friendRequestRepository, RegisterRepository registerRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.registerRepository = registerRepository;
    }

    public void sendRequest(String senderId, String receiverId) {
        FriendRequest request = new FriendRequest();
        request.setSenderId(senderId);
        request.setReceiverId(receiverId);
        request.setStatus(FriendRequest.Status.PENDING);
        friendRequestRepository.save(request);
    }

    public List<FriendRequest> getPendingRequests(String receiverId) {
        return friendRequestRepository.findByReceiverIdAndStatus(receiverId, FriendRequest.Status.PENDING);
    }

    public List<ReceivedFriendRequestDto> getPendingRequestsWithNickname(String receiverId) {
        List<FriendRequest> requests = friendRequestRepository.findByReceiverIdAndStatus(receiverId, FriendRequest.Status.PENDING);

        return requests.stream().map(req -> {
            String nickname = registerRepository.findByUserid(req.getSenderId())
                    .map(user -> user.getNickname())
                    .orElse("알 수 없음");

            return new ReceivedFriendRequestDto(
                    req.getId(),
                    req.getSenderId(),
                    nickname,
                    req.getStatus().name()
            );
        }).toList();
    }

    public boolean acceptRequest(Long requestId) {
        Optional<FriendRequest> optional = friendRequestRepository.findById(requestId);
        if (optional.isPresent()) {
            FriendRequest request = optional.get();
            request.setStatus(FriendRequest.Status.ACCEPTED);
            friendRequestRepository.save(request);
            return true;
        }
        return false;
    }

    public boolean rejectRequest(Long requestId) {
        Optional<FriendRequest> optional = friendRequestRepository.findById(requestId);
        if (optional.isPresent()) {
            FriendRequest request = optional.get();
            request.setStatus(FriendRequest.Status.REJECTED);
            friendRequestRepository.save(request);
            return true;
        }
        return false;
    }

    public Optional<FriendRequest> getFriendRequestBetween(String senderId, String receiverId) {
        return friendRequestRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }
}
