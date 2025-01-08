package sembrella.ng.simrella.ng.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import sembrella.ng.simrella.ng.dto.LoginDto;
import sembrella.ng.simrella.ng.dto.UserDto;
import sembrella.ng.simrella.ng.entity.User;
import sembrella.ng.simrella.ng.enums.UserRoles;
import sembrella.ng.simrella.ng.exceptions.DuplicateException;
import sembrella.ng.simrella.ng.repository.UserRepository;
import sembrella.ng.simrella.ng.utils.EmailService;
import sembrella.ng.simrella.ng.utils.UserUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
@Autowired
    AuthenticationManager authManager;
@Autowired
private EmailService emailService;
    public User saveUser(User user){
if (userRepository.findByEmail(user.getEmail()).isPresent()){
    throw new DuplicateException("Email already exist " );
}

if(userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()){
    throw new DuplicateException("Phone number already exist");
}

if (user.getRoles() == null){
    user.setRoles( new HashSet<>());
}

  String hashedPassword = UserUtils.hashPassword(user.getPassword());
user.getRoles().add(UserRoles.USER);
  user.setPassword(hashedPassword);
  user.setCreated_at(LocalDateTime.now());
  emailService.sendRegistrationEmail(user.getEmail(), user.getFirstName() + " " + user.getLastName());
   return userRepository.save(user);
    }


    public UserDto getUser(UUID id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
      throw  new DuplicateException("User not found!");
        }
        User retrievedUser = user.get();
        return new UserDto(retrievedUser.getId(),
                retrievedUser.getFirstName(),
                retrievedUser.getLastName(),
                retrievedUser.getEmail(),
                retrievedUser.getPhoneNumber(),
                retrievedUser.getCreated_at(),
                retrievedUser.getUpdated_at());

    }
public List<UserDto> getUsers(){
     return userRepository.findAll().stream().map(user ->
             new UserDto(user.getId(), user.getFirstName(),
                     user.getLastName(), user.getEmail(),
                     user.getPhoneNumber(),
                     user.getCreated_at(),user.getUpdated_at()))
             .collect(Collectors.toList());
}
public User updateUser(UUID id, UserDto userDto){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new DuplicateException("User not found");
        }
        User dbUser = user.get();
        dbUser.setUpdated_at(LocalDateTime.now());
        dbUser.setFirstName(userDto.getFirstName());
        dbUser.setLastName(userDto.getLastName());
        dbUser.setEmail(userDto.getEmail());
        dbUser.setPhoneNumber(userDto.getPhoneNumber());
        return userRepository.save(dbUser);
}

public String deleteUser(UUID id){
      Optional<User> user = userRepository.findById(id);
      if(user.isEmpty()){
          throw  new DuplicateException("User not found!");
      }
      userRepository.deleteById(id);
      return "User deleted!";
}

    public String verify(LoginDto loginDto) {
     Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(loginDto.getEmail());
        }
        return "fail";
    }
}
