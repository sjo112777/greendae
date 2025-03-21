package kr.co.greendae.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    // 🔹 학사 일정 데이터를 JSON으로 반환
    @GetMapping("/schedule")
    public List<Map<String, Object>> getScheduleEvents() {
        List<Map<String, Object>> eventList = new ArrayList<>();

        eventList.add(createEvent("좋은하루", "2025-03-15"));
        eventList.add(createEvent("좋은하루2", "2025-03-20"));
        eventList.add(createEvent("좋은하루3", "2025-03-25"));

        return eventList; // Spring Boot가 자동으로 JSON 변환
    }

    private Map<String, Object> createEvent(String title, String date) {
        Map<String, Object> event = new HashMap<>();
        event.put("title", title);
        event.put("start", date);
        return event;
    }
}
