package com.humanresources.webservice.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.humanresources.webservice.shared.Views;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "{humanresources.constraint.name.NotNull.message}")
    @Column(name = "name", nullable = false)
    @JsonView(Views.Public.class)
    private String name;
    @NotNull(message = "{humanresources.constraint.surname.NotNull.message}")
    @Column(name = "surname", nullable = false)
    @JsonView(Views.Public.class)
    private String surname;
    @NotNull(message = "{humanresources.constraint.email.NotNull.message}")
    @Column(name = "email", nullable = false)
    @JsonView(Views.Public.class)
    private String email;

    @NotNull(message = "{humanresources.constraint.pass.NotNull.message}")
    @Column(name = "pass", nullable = false)
    private String pass;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_user");
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return null;
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
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
