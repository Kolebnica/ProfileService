INSERT INTO profiles(id) VALUES(1)
INSERT INTO profiles(id) VALUES(2)
INSERT INTO profiles(id) VALUES(3)

-- ALTER SEQUENCE profiles_id_seq RESTART WITH 6;

--

INSERT INTO playlists(id, profile_id, title, description, created, updated) VALUES(1, 1, 'The very first playlist', 'Some description', now(), now())
INSERT INTO playlists(id, profile_id, title, description, created, updated) VALUES(2, 1, 'Some other playlist', 'n/a', now(), now())
INSERT INTO playlists(id, profile_id, title, description, created, updated) VALUES(3, 3, 'Empty playlist', 'Empty :(', now(), now())

ALTER SEQUENCE playlists_id_seq RESTART WITH 4;

--

INSERT INTO playlists_songlist(playlists_id, songlist) VALUES(1, 1),(1, 2),(1, 3),(1, 4),(1, 5)
INSERT INTO playlists_songlist(playlists_id, songlist) VALUES(2, 1),(2, 18),(2, 19),(2, 20)
