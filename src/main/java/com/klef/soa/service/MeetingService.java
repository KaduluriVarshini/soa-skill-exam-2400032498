package com.klef.soa.service;

import java.util.List;

import com.klef.soa.entity.Meeting;

public interface MeetingService {

    Meeting insertMeeting(Meeting meeting);

    Meeting updateMeeting(Long id, Meeting meeting);

    void deleteMeeting(Long id);

    List<Meeting> getAllMeetings();

    Meeting getMeetingById(Long id);

    List<Meeting> findByName(String name);

    List<Meeting> findByType(String type);
}