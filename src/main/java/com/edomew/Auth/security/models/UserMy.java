package com.edomew.Auth.security.models;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserMy implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "next_val", sequenceName = "next_val", allocationSize = 1)
    @Column(name = "user_id")
    private Integer userId;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns =@JoinColumn(name = "role_id"))
    private Set<Role> authorities;

    public UserMy( String username, String password, Set<Role> authorities) {

        this.username = username;
        this.password = password;
        this.authorities = authorities;

    }

    public UserMy() {
        this.authorities = new HashSet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
