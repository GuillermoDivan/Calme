package com.s1410.calme.Domain.Entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.s1410.calme.Domain.Utils.RolesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assistents")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public final class Assistent extends User {

    @Column(unique = true)
    String email;
    String password;
    RolesEnum role = RolesEnum.ASSISTENT;
    Boolean validUser = false;

    @OneToMany(mappedBy = "assistent" , fetch = FetchType.EAGER)
    @JsonManagedReference
    List<RelationAA> relationsAA; //relation assistent to assisted

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.toString()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return getEmail();
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
