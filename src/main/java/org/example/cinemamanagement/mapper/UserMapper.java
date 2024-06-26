package org.example.cinemamanagement.mapper;

import org.example.cinemamanagement.dto.PerformDTO;
import org.example.cinemamanagement.dto.UserDTO;
import org.example.cinemamanagement.model.Perform;
import org.example.cinemamanagement.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        TypeMap<User, UserDTO> typeMap = new ModelMapper().createTypeMap(User.class, UserDTO.class);
        return typeMap.map(user);
    }
}
