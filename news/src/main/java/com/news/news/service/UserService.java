package com.news.news.service;

import com.news.news.components.JwtTokenUntil;
import com.news.news.dto.UserDTO;
import com.news.news.exception.DataNotFoundException;
import com.news.news.model.Role;
import com.news.news.model.User;
import com.news.news.repositories.RoleRepository;
import com.news.news.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUntil jwtTokenUntil;
    private final AuthenticationManager authenticationManager;
    @Override
    public User creatUser(UserDTO userDTO) throws DataNotFoundException {
        String phoneNumber = userDTO.getPhoneNumber();
        // Kiểm tra xem số điện thoại đã tồn tại hay chưa
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        User newUser = User.builder().fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .address(userDTO.getAddress())
                .password(userDTO.getPassword())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found"));
        newUser.setRole(role);
        newUser.onCreate();
        newUser.setActive(true);
        if (userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0) {
            String password = userDTO.getPassword();
            String encoderPassword = passwordEncoder.encode(password);
            newUser.setPassword(encoderPassword);
        }
        return userRepository.save(newUser);
    }

    @Override
    public String login(String numberPhone, String password) throws DataNotFoundException {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(numberPhone);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("data not found");
        }
        User existUser = optionalUser.get();

        if (existUser.getFacebookAccountId() == 0
                && existUser.getGoogleAccountId() == 0) {
            if (!passwordEncoder.matches(password, existUser.getPassword())) {
                throw new BadCredentialsException("WRONG_PHONE_PASSWORD");
            }
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(numberPhone, password, existUser.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUntil.generateToken(existUser);
    }
}