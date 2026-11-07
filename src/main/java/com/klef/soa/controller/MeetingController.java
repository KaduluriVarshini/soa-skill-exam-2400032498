package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.soa.entity.Meeting;
import com.klef.soa.service.MeetingService;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

        @GetMapping("/")
        public String test()
        {
            return "Meeting Service is up and running";
        }


    @Autowired
    private MeetingService meetingService;


    
    @PostMapping
    public ResponseEntity<Meeting> insertMeeting(
            @RequestBody Meeting meeting) {

        return ResponseEntity.ok(
                meetingService.insertMeeting(meeting)
        );
    }

    
    @GetMapping
    public ResponseEntity<List<Meeting>> getAllMeetings() {

        return ResponseEntity.ok(
                meetingService.getAllMeetings()
        );
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getMeetingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                meetingService.getMeetingById(id)
        );
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Meeting> updateMeeting(
            @PathVariable Long id,
            @RequestBody Meeting meeting) {

        return ResponseEntity.ok(
                meetingService.updateMeeting(id, meeting)
        );
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeeting(
            @PathVariable Long id) {

        meetingService.deleteMeeting(id);

        return ResponseEntity.ok("Meeting deleted successfully");
    }

    
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Meeting>> findByName(
            @PathVariable String name) {

        return ResponseEntity.ok(
                meetingService.findByName(name)
        );
    }

    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Meeting>> findByType(
            @PathVariable String type) {

        return ResponseEntity.ok(
                meetingService.findByType(type)
        );
    }
}