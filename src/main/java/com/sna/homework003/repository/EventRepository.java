package com.sna.homework003.repository;

import com.sna.homework003.model.entity.Events;
import org.apache.ibatis.annotations.*;

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


}
