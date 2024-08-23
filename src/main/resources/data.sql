INSERT INTO
  Sponsor (name, industry)
VALUES
  ('TechCorp', 'Technology'),
  ('Glamour Inc.', 'Fashion'),
  ('SoundWave Productions', 'Music Production'),
  ('EcoPlanet', 'Environmental Conservation');

INSERT INTO
  Event (name, date)
VALUES
  ('TechCon', '2023-12-15'),
  ('Fashion Fest', '2023-11-05'),
  ('MusicFest', '2024-01-25'),
  ('EcoAwareness Conclave', '2023-11-10');

INSERT INTO
  Event_Sponsor (eventId, sponsorId)
VALUES
  (1, 1),
  (1, 2),
  (2, 2),
  (3, 3),
  (3, 4),
  (4, 4);