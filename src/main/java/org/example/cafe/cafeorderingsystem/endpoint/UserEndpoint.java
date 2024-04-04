package org.example.cafe.cafeorderingsystem.endpoint;

import lombok.RequiredArgsConstructor;
import org.example.cafe.cafeorderingsystem.dto.CreateUserRequestDto;
import org.example.cafe.cafeorderingsystem.dto.UserAuthRequestDto;
import org.example.cafe.cafeorderingsystem.dto.UserAuthResponseDto;
import org.example.cafe.cafeorderingsystem.dto.UserDto;
import org.example.cafe.cafeorderingsystem.entity.Role;
import org.example.cafe.cafeorderingsystem.entity.User;
import org.example.cafe.cafeorderingsystem.repository.UserRepository;
import org.example.cafe.cafeorderingsystem.service.UserMapperImpl;
import org.example.cafe.cafeorderingsystem.service.UserService;
import org.example.cafe.cafeorderingsystem.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserService userService;
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapperImpl userMapper;


    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponseDto> auth(@RequestBody UserAuthRequestDto userAuthRequestDto){

        Optional<User> byEmail = userRepository.findByEmail(userAuthRequestDto.getEmail());
        if(byEmail.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = byEmail.get();
        if(!passwordEncoder.matches(userAuthRequestDto.getPassword(),user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtTokenUtil.generateToken(userAuthRequestDto.getEmail());
        return ResponseEntity.ok(new UserAuthResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequestDto createUserRequestDto){
        Optional<User> byEmail = userRepository.findByEmail(createUserRequestDto.getEmail());
        if(byEmail.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = userMapper.map(createUserRequestDto);
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(createUserRequestDto.getPassword()));
        userService.save(user);
        return  ResponseEntity.ok(userMapper.mapToDto(user));
    }

}
