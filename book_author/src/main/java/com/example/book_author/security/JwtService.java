package com.example.book_author.security;

import com.example.book_author.dto.UserDto;
import com.example.book_author.dto.UserForm;
import com.example.book_author.entity.User;
import com.example.book_author.enums.RoleName;
import com.example.book_author.exception.AppException;
import com.example.book_author.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class JwtService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public JwtService(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElseThrow(() -> new NoSuchElementException("username not found by email"));

    }

    public UserDto registerUser(UserForm form) {
        boolean user= userRepository.existsByUsernameAndDeletedFalse(form.getUsername());
        if (!user){
            throw new AppException("there is no such user with " + form.getUsername());
        }
      User user1 = new User();
        user1.setFirstName(form.getFirstName());
        user1.setFirstName(form.getLastName());
        user1.setUsername(form.getUsername());
        user1.setPassword(passwordEncoder.encode(form.getPassword()));
        user1.setRoleName(RoleName.USER);
        User savedUser = userRepository.save(user1);
        return UserDto.toUserDto(savedUser);
    }
}
