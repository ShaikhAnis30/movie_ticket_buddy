package com.example.book_my_show.Convertors;

import com.example.book_my_show.Entities.User;
import com.example.book_my_show.EntryDtos.UserEntryDto;

public class UserConvertor {
    public static User convertDtoToEntity(UserEntryDto userEntryDto) {
        User user = User.builder().name(userEntryDto.getName()).age(userEntryDto.getAge())
                .email(userEntryDto.getEmail()).mobile(userEntryDto.getMobile())
                .address(userEntryDto.getAddress())
                .build();
        //at last we have to do .build();
        return user;
    }
}
