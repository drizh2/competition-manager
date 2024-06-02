package edu.katsala.competitionmanager.service;

import edu.katsala.competitionmanager.dao.UserDAO;
import edu.katsala.competitionmanager.model.User;
import edu.katsala.competitionmanager.model.enums.ERoles;
import edu.katsala.competitionmanager.payload.request.CreateUserRequest;
import edu.katsala.competitionmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;
    private final CustomPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.getPasswordEncoder().encode(request.getPassword()));
        user.getRoles().add(ERoles.ROLE_USER);

        // преобразованіє ролей в ролі спринга
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

        user.setAuthorities(authorities);

        return userDAO.saveUser(user);
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays
                .stream(ERoles.values())
                .map(ERoles::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(ERoles.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    // для завантаження юзера для security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username not found!");
        }

        return build(user);
    }

    public static User build(User user) {
        // преобразованіє ролей в ролі спринга
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

        return new User(user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}