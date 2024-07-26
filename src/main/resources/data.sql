INSERT INTO Sponsor (id, name, industry) VALUES
  (1, 'TechCorp', 'Technology'),
  (2, 'Glamour Inc.', 'Fashion'),
  (3, 'SoundWave Productions', 'Music Production'),
  (4, 'EcoPlanet', 'Environmental Conservation');

INSERT INTO Event (id, name, date) VALUES
  (1, 'TechCon', '2023-12-15'),
  (2, 'Fashion Fest', '2023-11-05'),
  (3, 'MusicFest', '2024-01-25'),
  (4, 'EcoAwareness Conclave', '2023-11-10');

INSERT INTO Event_Sponsor (eventId, sponsorId) VALUES
  (1, 1),
  (1, 2),
  (2, 2),
  (3, 3),
  (3, 4),
  (4, 4);