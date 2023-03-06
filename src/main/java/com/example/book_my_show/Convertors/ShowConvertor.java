package com.example.book_my_show.Convertors;

import com.example.book_my_show.Entities.Show;
import com.example.book_my_show.EntryDtos.ShowEntryDto;

public class ShowConvertor {

    public static Show convertDtoToEntity(ShowEntryDto showEntryDto) {
        Show show = Show.builder().showType(showEntryDto.getShowType())
                .showTime(showEntryDto.getShowTime()).showDate(showEntryDto.getShowDate())
                .build();
        return show;
    }
}
