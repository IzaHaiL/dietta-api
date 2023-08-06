package com.sugadev.userservice.Service;

import com.sugadev.userservice.DTO.RoleDTO;
import com.sugadev.userservice.DTO.UserDTO;
import com.sugadev.userservice.Model.User;
import com.sugadev.userservice.Model.UserRole;
import com.sugadev.userservice.Repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    ModelMapper modelMapper;
    private RestTemplate restTemplate;


    public UserDTO saveUser(UserDTO userDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public UserDTO getUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return modelMapper.map(user, UserDTO.class);
    }
    @RolesAllowed({"ROLE_ADMIN"})
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
    public UserDTO updateUser(int id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detail not found by id : " + id));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setNoTelp(userDTO.getNoTelp());
        existingUser.setBeratBadan(userDTO.getBeratBadan());
        existingUser.setTinggiBadan(userDTO.getTinggiBadan());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            // Hash the new password before updating
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
            existingUser.setPassword(hashedPassword);
        }

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDTO.class);
    }
    @RolesAllowed({"ROLE_ADMIN"})
    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
