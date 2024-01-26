package com.adelium.web.service.impl;

import com.adelium.web.entity.User;
import com.adelium.web.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.adelium.web.dto.UserDto;

import com.adelium.web.repository.RoleRepository;
import com.adelium.web.repository.UserRepository;
import com.adelium.web.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

//    public UserServiceImpl(UserRepository userRepository,
//                           RoleRepository roleRepository,
//                           PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
    @Override
    public void saveUser(UserDto userDto) {
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");
        Role role;
        if(optionalRole.isPresent()){
            role = optionalRole.get();
        }else{
            throw new RuntimeException("Role not found");
        }
        User user = User.builder()
                .username(userDto.getUsername())
                .lastname(userDto.getLastname())
                .firstname(userDto.getFirstname())
                .roles(Set.of(role))
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}