package edu.katsala.competitionmanager.model;

import edu.katsala.competitionmanager.model.enums.ERoles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Сутність User для реєстрації і розподілення ролей
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// інкрементально(123)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)// щоб енум був стрінгом
    @ElementCollection(targetClass = ERoles.class, fetch = FetchType.EAGER)// окрема таблиця для ролей
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))// як позначатиметься user в цій таблиця(ід юзера)
    private Set<ERoles> roles = new HashSet<>();

    // для створення і авторизації юзера, преобразує енам ролей в ролі спрінга
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     *
     * Security
     */

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
