package com.example.book_my_show.Controllers;

import com.example.book_my_show.EntryDtos.UserEntryDto;
import com.example.book_my_show.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto) {
        userService.addUser(userEntryDto);
        return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
    }
}
