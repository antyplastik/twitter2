USE twitter2;

CREATE TABLE IF NOT EXISTS user
(
  id       int NOT NULL AUTO_INCREMENT,
  username varchar(255),
  password varchar(255),
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS tweet
(
  id      int NOT NULL AUTO_INCREMENT,
  content varchar(255),
  date    DATETIME,
  userId  int,
  likeId  int,
  PRIMARY KEY (id),
  FOREIGN KEY (userId) REFERENCES user (id),
  FOREIGN KEY (likeId) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS follower
(
  id     int NOT NULL AUTO_INCREMENT,
  userId int,
  date DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (userId) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS likes
(
  id     int NOT NULL AUTO_INCREMENT,
  userId int,
  date   DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (userId) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS response (
  id int NOT NULL AUTO_INCREMENT,
  tweetId int,
  userId int,
  PRIMARY KEY (id),
  FOREIGN KEY (tweetId) REFERENCES tweet(id),
  FOREIGN KEY (userId) REFERENCES user(id)
);
