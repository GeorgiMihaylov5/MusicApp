INSERT INTO Genres (Name) VALUES ('Pop');
INSERT INTO Genres (Name) VALUES ('Rock');
INSERT INTO Genres (Name) VALUES ('Chalga');
INSERT INTO Genres (Name) VALUES ('Classical');

INSERT INTO Artists (Name, Country) VALUES ('Ariana Grande', 'US');
INSERT INTO Artists (Name, Country) VALUES ('Ed Sheeran', 'UK');
INSERT INTO Artists (Name, Country) VALUES ('Dessitax', 'BG');

INSERT INTO Songs (title, artistId, album, genreId, releaseYear, duration)
VALUES 
    ('Musala', 3, 'Album 1', 3, 2018, 3.23);