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

    @Autowired
    private MeetingService meetingService;

    // Insert
    @PostMapping
    public ResponseEntity<Meeting> insertMeeting(
            @RequestBody Meeting meeting) {

        return ResponseEntity.ok(
                meetingService.insertMeeting(meeting)
        );
    }

    // Display All
    @GetMapping
    public ResponseEntity<List<Meeting>> getAllMeetings() {

        return ResponseEntity.ok(
                meetingService.getAllMeetings()
        );
    }

    // Display By ID
    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getMeetingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                meetingService.getMeetingById(id)
        );
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Meeting> updateMeeting(
            @PathVariable Long id,
            @RequestBody Meeting meeting) {

        return ResponseEntity.ok(
                meetingService.updateMeeting(id, meeting)
        );
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeeting(
            @PathVariable Long id) {

        meetingService.deleteMeeting(id);

        return ResponseEntity.ok("Meeting deleted successfully");
    }

    // Derived Query - Find By Name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Meeting>> findByName(
            @PathVariable String name) {

        return ResponseEntity.ok(
                meetingService.findByName(name)
        );
    }

    // Derived Query - Find By Type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Meeting>> findByType(
            @PathVariable String type) {

        return ResponseEntity.ok(
                meetingService.findByType(type)
        );
    }
}