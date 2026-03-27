-- 1. venues
CREATE TABLE venues (
                        venue_id    SERIAL          PRIMARY KEY,
                        venue_name  VARCHAR(100)    NOT NULL,
                        location    VARCHAR(255)    NOT NULL
);

-- 2. attendees
CREATE TABLE attendees (
                           attendee_id     SERIAL          PRIMARY KEY,
                           attendee_name   VARCHAR(100)    NOT NULL,
                           email           VARCHAR(150)    NOT NULL UNIQUE
);

-- 3. events (CASCADE DELETE from venues)
CREATE TABLE events (
                        event_id    SERIAL          PRIMARY KEY,
                        event_name  VARCHAR(150)    NOT NULL,
                        event_date  DATE            NOT NULL,
                        venue_id    INT             NOT NULL,
                        CONSTRAINT fk_events_venue
                            FOREIGN KEY (venue_id)
                                REFERENCES venues (venue_id)
                                ON DELETE CASCADE
                                ON UPDATE CASCADE
);

-- 4. event_attendee (junction table)
CREATE TABLE event_attendee (
                                id              SERIAL  PRIMARY KEY,
                                event_id        INT     NOT NULL,
                                attendee_id     INT     NOT NULL,
                                CONSTRAINT fk_ea_event
                                    FOREIGN KEY (event_id)
                                        REFERENCES events (event_id)
                                        ON DELETE RESTRICT
                                        ON UPDATE CASCADE,
                                CONSTRAINT fk_ea_attendee
                                    FOREIGN KEY (attendee_id)
                                        REFERENCES attendees (attendee_id)
                                        ON DELETE RESTRICT
                                        ON UPDATE CASCADE
);