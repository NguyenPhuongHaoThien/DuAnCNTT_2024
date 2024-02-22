package tdtu.edu.vn.service.ebook;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.model.Role;
import tdtu.edu.vn.model.User;
import tdtu.edu.vn.repository.RoleRepository;
import tdtu.edu.vn.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public void register(String username, String email, String password, String confirmPassword){
        User user = new User(username, email, password, confirmPassword);
        user.setPassword(passwordEncoder.encode(password)); // Encrypt the password
        user.setConfirmPassword(confirmPassword); //
        Role userRole = roleRepository.findByName("ROLE_USER"); // Find the ROLE_USER from the database
        if (userRole == null) {
            throw new RuntimeException("Error: Role is not found.");
        }
        user.setRole(userRole); // Set the ROLE_USER for the user
        user.setCreateday(new Date()); // Gán ngày hiện tại
        System.out.println("Register: " + user.getUsername() + " " + user.getEmail() + " " + user.getPassword() + " " + user.getConfirmPassword());
        userRepository.save(user);
    }



    public boolean checkLogin(User user) {
        User userDb = (User) findByEmail(user.getEmail());
        if (userDb == null) {
            return false;
        }

        System.out.println("Check login: " + user.getEmail() + " " + user.getPassword());
        return passwordEncoder.matches(user.getPassword(), userDb.getPassword());
    }

    private User findByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }



    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }


    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }
}
