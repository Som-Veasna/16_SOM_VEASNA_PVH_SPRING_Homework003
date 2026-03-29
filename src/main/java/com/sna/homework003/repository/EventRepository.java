package com.sna.homework003.repository;

import com.sna.homework003.model.dto.EventDTO;
import com.sna.homework003.model.entity.Events;
import org.apache.ibatis.annotations.*;

import java.time.Instant;
import java.util.List;

@Mapper
public interface EventRepository {
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId",column = "event_id"),
            @Result(property = "eventName",column = "event_name"),
            @Result(property = "eventDate",column = "event_date"),
            @Result(property = "venue",column = "venue_id",
                    one=@One(select = "com.sna.homework003.repository.VenueRepository.getVenueById")
            ),
            @Result(property = "attendee",column = "event_id",
            many = @Many(select = "com.sna.homework003.repository.AttendeeRepository.getAllAttendeeByEventId")
            )
    })
    @Select("""
select * from events
""")
    List<Events> getAllEvents();

    @Select("""
     insert into events(event_name, event_date, venue_id) values (#{re.eventName},#{re.eventDate},#{re.venueId}) returning *;
""")
    @ResultMap("eventMapper")
    Events saveEvent(@Param("re") EventDTO request);

    @Select("""
select * from events where event_id=#{eventId}
""")
    @ResultMap("eventMapper")
    Events getEventById(Integer eventId);
    @Select("""
update events set event_name=#{re.eventName},event_date=#{re.eventDate},venue_id=#{re.venueId} where event_id=#{eventId} returning *;
""")
    @ResultMap("eventMapper")
    Events updateEvent(Integer eventId, @Param("re") EventDTO request);
    @Select("SELECT * FROM events WHERE event_name = #{eventName} AND event_date = #{eventDate}")
    @ResultMap("eventMapper")
    Events getEventByNameAndDate(@Param("eventName") String eventName, @Param("eventDate") Instant eventDate);
}
