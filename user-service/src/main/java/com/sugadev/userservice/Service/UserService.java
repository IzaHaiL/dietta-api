package com.sugadev.userservice.Service;

import com.sugadev.userservice.DTO.UserDTO;
import com.sugadev.userservice.Model.User;

import java.util.List;

public interface UserService {

    UserDTO saveUser (UserDTO userDTO);

    UserDTO getUser(Integer userId);

    List<UserDTO> getAllUser();

    UserDTO updateUser(int id, UserDTO user);

    void deleteUser(Integer userId);
}
