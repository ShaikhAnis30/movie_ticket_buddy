package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.UserConvertor;
import com.example.book_my_show.Entities.User;
import com.example.book_my_show.EntryDtos.UserEntryDto;
import com.example.book_my_show.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(UserEntryDto userEntryDto) {
        User user = UserConvertor.convertDtoToEntity(userEntryDto);
        userRepository.save(user);
    }
}
