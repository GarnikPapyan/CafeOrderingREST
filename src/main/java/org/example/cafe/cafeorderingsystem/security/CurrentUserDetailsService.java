package org.example.cafe.cafeorderingsystem.security;

import lombok.RequiredArgsConstructor;
import org.example.cafe.cafeorderingsystem.entity.User;
import org.example.cafe.cafeorderingsystem.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CurrentUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("UserName not found ");
        }
        return new CurrentUser(user.get());
    }
}
