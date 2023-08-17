package com.movierating.userapp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/addUser", consumes="application/json")
    public ResponseEntity<String> addUser(@RequestBody User userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Long id = userService.saveUser(user);
        return new ResponseEntity<String>("User Added "+ id, HttpStatus.OK);
    }


//    @GetMapping(
//            value = "/{userId}",
//            produces="application/json")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getRatingsByUser(@PathVariable Long userId){
        User user = userService.getUserById(userId);
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
}
