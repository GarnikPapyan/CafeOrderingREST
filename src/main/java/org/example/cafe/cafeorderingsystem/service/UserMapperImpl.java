package org.example.cafe.cafeorderingsystem.service;

import org.example.cafe.cafeorderingsystem.dto.CreateUserRequestDto;
import org.example.cafe.cafeorderingsystem.dto.UserDto;
import org.example.cafe.cafeorderingsystem.entity.User;
import org.example.cafe.cafeorderingsystem.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public User map(CreateUserRequestDto dto) {
        User user = new User();
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }

    @Override
    public UserDto mapToDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setEmail(entity.getEmail());
        userDto.setRole(entity.getRole());
        return userDto;
    }
}
