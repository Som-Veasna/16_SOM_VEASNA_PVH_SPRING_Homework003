package com.sna.homework003.repository;

import com.sna.homework003.model.dto.VenueDTO;
import com.sna.homework003.model.entity.Venues;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId",column = "venue_id"),
            @Result(property = "venueName",column = "venue_name")
    })
    @Select("""
select * from venues order by venue_id asc  offset #{size} *(#{page}-1)
                                                         limit #{size}\s;
""")
    List<Venues> getAllVenues(Integer size,Integer page);

     @Select("""
     select * from venues where venue_id = #{venueId}
""")
     @ResultMap("venueMapper")
    Venues getVenueById(@Param("venueId") Integer id);

     @Select("""
insert into venues values (default, #{venue.venueName}, #{venue.location}) returning *;
""")
     @ResultMap("venueMapper")
     Venues saveVenue(@Param("venue") VenueDTO venueDTO);
    @Select("""
update venues set venue_name = #{venue.venueName}, location = #{venue.location} where venue_id = #{id} returning *;
""")
    @ResultMap("venueMapper")
     Venues updateVenueById(Integer id, @Param("venue") VenueDTO venueDTO);

    @Select("SELECT * FROM venues WHERE venue_name = #{venueName}")
    @ResultMap("venueMapper")
    Venues getVenueByName(String venueName);
    @Select("SELECT * FROM venues WHERE location = #{location}")
    @ResultMap("venueMapper")
    Venues getVenueByLocation(String location);

    @Delete("""
delete from venues where venue_id = #{venueId}
""")
    void deleteVenueById(Integer venueId);






}

