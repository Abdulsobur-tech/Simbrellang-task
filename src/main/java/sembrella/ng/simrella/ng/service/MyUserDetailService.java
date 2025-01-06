package sembrella.ng.simrella.ng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sembrella.ng.simrella.ng.dto.UserPrincipal;
import sembrella.ng.simrella.ng.entity.User;
import sembrella.ng.simrella.ng.repository.UserRepository;

import java.util.Optional;
@Service
@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty()) {
            System.out.println("User not found!");
            throw new UsernameNotFoundException("User not found!");
        }
        User user = userOptional.get();
        return new UserPrincipal(user);
    }
}
