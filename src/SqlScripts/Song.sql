CREATE TABLE Songs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    artistId INT,
    album VARCHAR(255),
    genreId INT,
    releaseYear INT,
    duration INT
);

INSERT INTO Songs (title, artistId, album, genreId, releaseYear, duration)
VALUES 
    ('Song 1', 1, 'Album 1', 1, 2020, 180),
    ('Song 2', 2, 'Album 2', 2, 2018, 210),
    ('Song 3', 1, 'Album 3', 1, 2019, 190),
    ('Song 4', 3, 'Album 4', 3, 2021, 220);
