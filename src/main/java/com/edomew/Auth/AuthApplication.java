package com.edomew.Auth;

import com.edomew.Auth.security.models.Role;
import com.edomew.Auth.security.models.UserMy;
import com.edomew.Auth.security.repository.RoleRepository;
import com.edomew.Auth.security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!roleRepository.findByAuthority("ROLE_ADMIN").getAuthority().isEmpty()) {
                return;
            }
            Role adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
            roleRepository.save(new Role("ROLE_USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            UserMy admin = new UserMy("Edomew", passwordEncoder.encode("Edomews"), roles);

            userRepository.save(admin);

        };
    }
}
