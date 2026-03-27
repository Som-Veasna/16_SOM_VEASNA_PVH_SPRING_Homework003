INSERT INTO venues (venue_name, location) VALUES
                                              ('Grand Ballroom',        'Phnom Penh, Cambodia'),
                                              ('Sky Rooftop Hall',      'Siem Reap, Cambodia'),
                                              ('Riverside Convention',  'Kampot, Cambodia'),
                                              ('Diamond Event Center',  'Battambang, Cambodia'),
                                              ('Lotus Garden Venue',    'Sihanoukville, Cambodia'),
                                              ('Heritage Hall',         'Kratie, Cambodia'),
                                              ('Central Park Pavilion', 'Kep, Cambodia'),
                                              ('Golden Gate Arena',     'Takeo, Cambodia'),
                                              ('Sunset Terrace',        'Mondulkiri, Cambodia'),
                                              ('Crystal Palace Hall',   'Ratanakiri, Cambodia');

-- 2. attendees
INSERT INTO attendees (attendee_name, email) VALUES
                                                 ('Sokha Chea',    'sokha.chea@email.com'),
                                                 ('Dara Pich',     'dara.pich@email.com'),
                                                 ('Maly Noun',     'maly.noun@email.com'),
                                                 ('Virak Keo',     'virak.keo@email.com'),
                                                 ('Sreymom Chan',  'sreymom.chan@email.com'),
                                                 ('Kosal Heng',    'kosal.heng@email.com'),
                                                 ('Bopha Lim',     'bopha.lim@email.com'),
                                                 ('Rathana Sok',   'rathana.sok@email.com'),
                                                 ('Pisey Ung',     'pisey.ung@email.com'),
                                                 ('Chanthy Ros',   'chanthy.ros@email.com');

-- 3. events
INSERT INTO events (event_name, event_date, venue_id) VALUES
                                                          ('Tech Summit 2026',           '2026-01-15', 1),
                                                          ('Wedding Expo',               '2026-01-22', 2),
                                                          ('Startup Pitch Night',        '2026-02-05', 3),
                                                          ('Cultural Festival',          '2026-02-14', 4),
                                                          ('Music Concert Gala',         '2026-03-01', 5),
                                                          ('Business Networking Forum',  '2026-03-10', 6),
                                                          ('Art & Design Show',          '2026-03-20', 7),
                                                          ('Health & Wellness Fair',     '2026-04-02', 8),
                                                          ('Food & Beverage Expo',       '2026-04-15', 9),
                                                          ('Annual Charity Dinner',      '2026-05-01', 10);

-- 4. event_attendee
INSERT INTO event_attendee (event_id, attendee_id) VALUES
                                                       (1,  1),
                                                       (1,  2),
                                                       (2,  3),
                                                       (3,  4),
                                                       (3,  5),
                                                       (5,  6),
                                                       (6,  7),
                                                       (7,  8),
                                                       (9,  9),
                                                       (10, 10);