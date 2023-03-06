package com.example.book_my_show.Controllers;

import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto) {
        try {
            showService.addShow(showEntryDto);
            return new ResponseEntity<>("Show added Successfully", HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-time")
    public ResponseEntity<String> getShowTime(@RequestParam("mId")int movieId, @RequestParam("tId")int theaterId) {
        try {
            String response = showService.getShowTiming(movieId,theaterId);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
