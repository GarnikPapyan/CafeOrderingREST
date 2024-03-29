package org.example.cafe.cafeorderingsystem.service;

import org.example.cafe.cafeorderingsystem.entity.User;
import org.example.cafe.cafeorderingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> gerAllUsers(){
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User getById(Long id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            user = optional.get();
        }
        return user;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
//    public User update(User user){
//        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
//
//        return user;
//    }
}
