package com.klef.soa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Meeting;
import com.klef.soa.repository.MeetingRepository;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    public Meeting insertMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    @Override
    public Meeting updateMeeting(Long id, Meeting meeting) {

        Meeting existingMeeting = meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        existingMeeting.setName(meeting.getName());
        existingMeeting.setType(meeting.getType());
        existingMeeting.setStatus(meeting.getStatus());
        existingMeeting.setLocation(meeting.getLocation());

        return meetingRepository.save(existingMeeting);
    }

    @Override
    public void deleteMeeting(Long id) {

        if (!meetingRepository.existsById(id)) {
            throw new RuntimeException("Meeting not found");
        }

        meetingRepository.deleteById(id);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @Override
    public Meeting getMeetingById(Long id) {

        return meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));
    }

    @Override
    public List<Meeting> findByName(String name) {
        return meetingRepository.findByName(name);
    }

    @Override
    public List<Meeting> findByType(String type) {
        return meetingRepository.findByType(type);
    }
}