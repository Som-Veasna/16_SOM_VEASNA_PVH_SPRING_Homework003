package com.sna.homework003.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EventAttendeeRepository {
    @Insert("""
insert into event_attendee(event_id,attendee_id) values(#{eventId},#{attendeeId});
""")
    void saveEventAttendee(Integer eventId, Integer attendeeId);
    @Delete("""
delete from event_attendee where event_id=#{eventId};
""")
     void  removeEventAttendeeByEventId(Integer eventId);
}
