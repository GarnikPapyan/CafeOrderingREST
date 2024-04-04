package org.example.cafe.cafeorderingsystem.mapper;


import org.example.cafe.cafeorderingsystem.dto.CreateUserRequestDto;
import org.example.cafe.cafeorderingsystem.dto.UserDto;
import org.example.cafe.cafeorderingsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User map(CreateUserRequestDto dto);


    UserDto mapToDto(User entity);


}
