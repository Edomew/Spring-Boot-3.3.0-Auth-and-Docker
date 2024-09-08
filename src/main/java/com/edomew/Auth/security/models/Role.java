package com.edomew.Auth.security.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleId;
    private String authority;

    public Role(Integer roleId, String authority) {
    }

    public Role() {

    }

    public Role(String authority) {
        this.authority = authority;
    }

}
