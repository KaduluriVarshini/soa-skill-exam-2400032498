
package com.klef.soa.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.klef.soa.MeetingServiceApplication;
import com.klef.soa.entity.Meeting;

public class MeetingRepositoryTest {

    private ConfigurableApplicationContext context;
    private MeetingRepository repository;

    @BeforeEach
    public void setUp() {

        context = new SpringApplicationBuilder(
                MeetingServiceApplication.class)
                .web(WebApplicationType.NONE)
                .run();

        repository = context.getBean(MeetingRepository.class);
    }

    @AfterEach
    public void tearDown() {

        if (context != null) {
            context.close();
        }
    }

    // Test 1: Save Meeting
    @Test
    public void testSaveMeeting() {

        Meeting meeting = createMeeting("JUnit Save", "Online");

        Meeting saved = repository.save(meeting);

        assertNotNull(saved);
        assertNotNull(saved.getMeetingId());
    }

    // Test 2: Find All Meetings
    @Test
    public void testFindAllMeetings() {

        Meeting saved = repository.save(
                createMeeting("JUnit All", "Online"));

        List<Meeting> meetings = repository.findAll();

        assertNotNull(meetings);

        boolean found = false;

        for (Meeting m : meetings) {
            if (saved.getMeetingId().equals(m.getMeetingId())) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    // Test 3: Find Meeting By ID
    @Test
    public void testFindMeetingById() {

        Meeting saved = repository.save(
                createMeeting("JUnit ID", "Offline"));

        Optional<Meeting> result =
                repository.findById(saved.getMeetingId());

        assertTrue(result.isPresent());

        assertEquals(
                saved.getMeetingId(),
                result.get().getMeetingId());
    }

    // Test 4: Find Meeting By Name
    @Test
    public void testFindByName() {

        String name = "JUnit Name " + System.nanoTime();

        repository.save(createMeeting(name, "Online"));

        List<Meeting> result = repository.findByName(name);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(name, result.get(0).getName());
    }

    // Test 5: Find Meeting By Type
    @Test
    public void testFindByType() {

        String type = "JUnit Type " + System.nanoTime();

        repository.save(createMeeting("JUnit Type", type));

        List<Meeting> result = repository.findByType(type);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(type, result.get(0).getType());
    }

    // Test 6: Delete Meeting
    @Test
    public void testDeleteMeeting() {

        Meeting saved = repository.save(
                createMeeting("JUnit Delete", "Online"));

        Long id = saved.getMeetingId();

        repository.deleteById(id);

        assertFalse(repository.existsById(id));
    }

    // Common Meeting Object
    private Meeting createMeeting(String name, String type) {

        Meeting meeting = new Meeting();

        meeting.setName(name);
        meeting.setType(type);
        meeting.setStatus("Scheduled");
        meeting.setLocation("KLU");

        return meeting;
    }
}
