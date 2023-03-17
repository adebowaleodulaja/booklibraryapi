DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS book_category;

CREATE TABLE book (
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL,
	isbn VARCHAR(15),
    year_released VARCHAR(50),
    no_of_copies INTEGER NOT NULL,
	is_available BIT NOT NULL
);

CREATE TABLE category (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE book_category (
	book_id BIGINT NOT NULL,
	category_id BIGINT NOT NULL,
	FOREIGN KEY (book_id) REFERENCES book(id),
	FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO book(title, author, isbn, year_released, no_of_copies, is_available)
VALUES
    ('Finding Me', 'Viola Davis','123-4567-767', '2001', 10, 0),
    ('The Biography', 'Ammy Odell','6453-888-965', '2005', 20, 1),
    ('Algorithm Made Easy', 'Sloane Crosley','22055-3-7423', '2010', 5, 1),
    ('Silent Invasion', 'Deborah Birx','22058-44-111', '2009', 30, 0);


INSERT INTO category(name, description)
VALUES
    ('Love Novel', 'A book that clarifies what love entails'),
    ('Science Friction', 'A book that describe what science but in a non realistic way'),
    ('Computer Science', 'Books that talks about computer science'),
    ('Colonialism', 'Books that speaks acquiring full or partial political control over another country');

INSERT INTO book_category(book_id, category_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3),
    (4, 2),
    (4, 4);
