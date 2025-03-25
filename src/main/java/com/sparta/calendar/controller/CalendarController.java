package com.sparta.calendar.controller;

import com.sparta.calendar.dto.CalendarRequestDto;
import com.sparta.calendar.dto.CalendarResponseDto;
import com.sparta.calendar.entity.Calendar;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/calendars")
public class CalendarController {

    private final Map <Long, Calendar> calendarList = new HashMap<>();

    @PostMapping
    public CalendarResponseDto createCalendar(@RequestBody CalendarRequestDto dto) {

        //식별자가 1씩 증가하도록 만듦
        Long calendarId = calendarList.isEmpty() ? 1: Collections.max(calendarList.keySet()) + 1;
        //요청받은 데이터로 Calendar 객체 생성
        Calendar calendar= new Calendar(calendarId, dto.getTitle(),
                dto.getAuthor(),dto.getPassword(), LocalDateTime.now(),
                LocalDateTime.now());

        calendarList.put(calendarId, calendar);

        return new CalendarResponseDto(calendar);

    }

   @GetMapping("/{id}")
    public CalendarResponseDto CalendarById(@PathVariable Long id) {

       Calendar calendar = calendarList.get(id);

       return new CalendarResponseDto(calendar);


   }
   @GetMapping
   public List<CalendarResponseDto> fidAllCalendars() {
        List<CalendarResponseDto> responseList = new ArrayList<>();

        for (Calendar calendar : calendarList.values()) {
            CalendarResponseDto responseDto = new CalendarResponseDto(calendar);
            responseList.add(responseDto);

        }

        return responseList;
   }

   @PutMapping("/{id}")
    public CalendarResponseDto updateCalendar(@PathVariable Long id,
                                              @RequestBody CalendarRequestDto dto) {
        Calendar calendar = calendarList.get(id);

        calendar.update(dto);

        return new CalendarResponseDto(calendar);


   }

   @DeleteMapping("/{id}")
    public CalendarResponseDto deleteCalendar(@PathVariable Long id) {

        Calendar calendar = calendarList.get(id);
        calendarList.remove(id);
        return new CalendarResponseDto(calendar);
   }
}
