DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS book_category;

CREATE TABLE book (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL,
	isbn VARCHAR(15),
    year_released VARCHAR(50),
    no_of_copies INTEGER NOT NULL,
    created VARCHAR(255) NOT NULL,
    updated VARCHAR(255) NOT NULL
);

CREATE TABLE category (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    created VARCHAR(255) NOT NULL,
    updated VARCHAR(255) NOT NULL
);

CREATE TABLE book_category (
	book_id BIGINT NOT NULL,
	category_id BIGINT NOT NULL,
	FOREIGN KEY (book_id) REFERENCES book(id),
	FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO book(title, publisher, author, isbn, year_released, no_of_copies, created, updated)
VALUES
    ('Finding Me', 'Frank James', 'Viola Davis','123-4567-767', '2001', 10, '2023-03-18T18:32:27.719Z', '2023-03-18T18:32:27.719Z'),
    ('The Biography', 'Frank James', 'Ammy Odell','6453-888-965', '2005', 20, '2023-03-18T20:32:27.719Z', '2023-03-20T18:32:27.719Z'),
    ('Algorithm Made Easy', 'Peter Cats', 'Sloane Crosley','22055-3-7423', '2010', 5, '2023-03-18T20:32:27.719Z', '2023-03-18T20:32:27.719Z'),
    ('Silent Invasion', 'Brown Ford', 'Deborah Birx','22058-44-111', '2009', 30, '2023-03-18T20:32:27.719Z', '2023-03-18T20:32:27.719Z');


INSERT INTO category(name, description, created, updated)
VALUES
    ('Love Novel', 'A book that clarifies what love entails', '2023-03-16T16:45:27.719Z', '2023-03-16T16:45:27.719Z'),
    ('Science Friction', 'A book that describe what science but in a non realistic way', '2023-03-16T16:45:27.719Z', '2023-03-16T16:45:27.719Z'),
    ('Computer Science', 'Books that talks about computer science', '2023-03-16T16:45:27.719Z', '2023-03-16T16:45:27.719Z'),
    ('Colonialism', 'Books that speaks acquiring full or partial political control over another country', '2023-03-16T16:45:27.719Z', '2023-03-16T16:45:27.719Z');

INSERT INTO book_category(book_id, category_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 2);
