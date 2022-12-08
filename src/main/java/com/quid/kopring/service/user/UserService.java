package com.quid.kopring.service.user;

import com.quid.kopring.domain.user.UserRepository;
import com.quid.kopring.dto.user.request.UserCreateRequest;
import com.quid.kopring.dto.user.request.UserUpdateRequest;
import com.quid.kopring.dto.user.response.UserResponse;
import com.quid.kopring.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request) {
        User newUser = new User(null, request.getName(), request.getAge(), new ArrayList<>());
        userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
            .map(UserResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public void updateUserName(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
            .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }

}
