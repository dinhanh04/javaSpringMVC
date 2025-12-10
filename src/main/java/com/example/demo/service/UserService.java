package com.example.demo.service;


import com.example.demo.domain.User;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User handleSaveUser(User user){
        User saveUser = this.userRepository.save(user);

        return saveUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByEmail(String email) {
        return userRepository.getAllByEmail(email);
    }

    public User getUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public boolean checkEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public User registerDtoToUser(RegisterDTO registerDTO){
        User user = new User();
        user.setFullName(registerDTO.getFirstName()+" "+registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        return user;
    }

    public User getUserByEmail(String email){
        List<User> users = this.userRepository.getAllByEmail(email);
        if (users.size() > 0){
            return users.get(0);
        }
        return null;
    }

}
