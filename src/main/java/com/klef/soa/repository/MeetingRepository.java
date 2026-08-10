package com.klef.soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

	List<Meeting> findByName(String name);

    List<Meeting> findByType(String type);
}