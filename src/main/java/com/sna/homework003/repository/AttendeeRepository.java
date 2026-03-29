package com.sna.homework003.repository;

import com.sna.homework003.model.dto.AttendeeDTO;
import com.sna.homework003.model.entity.Attendees;
import com.sna.homework003.model.entity.Venues;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId",column = "attendee_id"),
            @Result(property = "attendeeName",column = "attendee_name")
    })
    @Select("""
select * from attendees
""")
    List<Attendees> getAllAttendees();

    @Select("""
select * from attendees where attendee_id = #{attendeeId}
""")
    @ResultMap("attendeeMapper")
    Attendees getAttendeeById(Integer attendeeId);
     @Select("""
insert into attendees (attendee_name, email) values (#{attendee.attendeeName},#{attendee.email}) returning *;
""")
     @ResultMap("attendeeMapper")
    Attendees saveAttendee(@Param("attendee")AttendeeDTO attendeeDTO);
     @Select("""
    update attendees set attendee_name=#{attendee.attendeeName},email=#{attendee.email} where attendee_id=#{attendeeId} returning *;
""")
     @ResultMap("attendeeMapper")
     Attendees updateAttendeeById(Integer attendeeId, @Param("attendee") AttendeeDTO attendeeDTO);
    @Select("""
     SELECT
            a.attendee_id,a.attendee_name, a.email,e.event_id,e.event_name,e.event_date
        FROM attendees a
        INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
        INNER JOIN events e          ON ea.event_id   = e.event_id
        WHERE e.event_id = #{eventId}
        """)
    @ResultMap("attendeeMapper")
    List<Attendees> getAllAttendeeByEventId(Integer eventId);
    @Select("SELECT * FROM attendees WHERE email = #{email}")
    @ResultMap("attendeeMapper")
    Attendees getAttendeeByEmail(String email);
    @Select("SELECT * FROM attendees WHERE attendee_name = #{attendeeName}")
    @ResultMap("attendeeMapper")
    Attendees getAttendeeByName(String attendeeName);
}


