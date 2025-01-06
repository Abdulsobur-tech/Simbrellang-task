package sembrella.ng.simrella.ng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import sembrella.ng.simrella.ng.dto.LoginDto;
import sembrella.ng.simrella.ng.dto.UserDto;
import sembrella.ng.simrella.ng.entity.User;
import sembrella.ng.simrella.ng.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> RegisterUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto);
        return userService.verify(loginDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id){
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto){
        User user = userService.updateUser(id, userDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        String message = userService.deleteUser(id);
        return ResponseEntity.ok(message);
    }
}
