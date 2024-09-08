package com.edomew.Auth.security.service;

import com.edomew.Auth.security.dto.JwtAuthenticationResponse;
import com.edomew.Auth.security.dto.SignInRequest;
import com.edomew.Auth.security.dto.SignUpRequest;
import com.edomew.Auth.security.models.Role;
import com.edomew.Auth.security.models.UserMy;
import com.edomew.Auth.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws RuntimeException {
        UserMy user = new UserMy();
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByAuthority("ROLE_USER"));
        user.setAuthorities(roles);
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        System.out.println(0);
        System.out.println("Username: " + request.getUsername());
        System.out.println("Password: " + request.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        System.out.println(1);
        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        System.out.println(2);
        return new JwtAuthenticationResponse(jwt);
    }
}