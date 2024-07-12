package com.ly.fleet_management.service;

import com.ly.fleet_management.entity.Role;
import com.ly.fleet_management.entity.User;
import com.ly.fleet_management.repository.RoleRepository;
import com.ly.fleet_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User registerUser(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User roles must not be null or empty");
        }

        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            Role existingRole = roleRepository.findByName(role.getName())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + role.getName()));
            roles.add(existingRole);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true); // Enable the user by default
        return userRepository.save(user);
    }



    public User loginUser(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
