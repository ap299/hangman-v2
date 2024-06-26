-- Create User table
CREATE TABLE user (
  id Number NOT NULL,
  username VARCHAR(255) NOT NULL ,
  password VARCHAR(255) NOT NULL,
  score INT DEFAULT 0,
  PRIMARY KEY (id)
);

-- Create Room table
CREATE TABLE room (
  id Number  NOT NULL ,
  room_id VARCHAR(255) NOT NULL ,
  word VARCHAR(255) NOT NULL,
  masked_word VARCHAR(255) NOT NULL,
  incorrect_guesses INT DEFAULT 0,
  max_incorrect_guesses INT DEFAULT 6,
  PRIMARY KEY (id)
);
