USE twitter2;

CREATE TABLE IF NOT EXISTS user
(
    id       int NOT NULL AUTO_INCREMENT,
    username varchar(255),
    password varchar(255),
    accountId int,
    PRIMARY KEY (`id`),
    FOREIGN KEY (accountId) REFERENCES account(id)
);
select *
from user;

CREATE TABLE IF NOT EXISTS account
(
    id int NOT NULL AUTO_INCREMENT,
    followersId int,
    followsId int,
    PRIMARY KEY (`id`),
    FOREIGN KEY (followersId) REFERENCES follower(id),
    FOREIGN KEY (followsId) REFERENCES follow(id)
);
select *
from account;

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
drop table twitter2.tweet;
select *
from tweet;

CREATE TABLE IF NOT EXISTS follower
(
    id     int NOT NULL AUTO_INCREMENT,
    userId int,
    date   DATETIME,
    active bit,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user (id)
);
drop table twitter2.follower;
select *
from follower;

CREATE TABLE IF NOT EXISTS follow
(
    id     int NOT NULL AUTO_INCREMENT,
    userId int,
    date   DATETIME,
    active bit,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user (id)
);
drop table twitter2.follow;
select *
from follow;

CREATE TABLE IF NOT EXISTS likes
(
    id      int NOT NULL AUTO_INCREMENT,
    userId  int,
    tweetId int,
    date    DATETIME,
    active  bit,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user (id),
    FOREIGN KEY (tweetId) REFERENCES tweet (id)
);

select *
from likes;

CREATE TABLE IF NOT EXISTS response
(
    id        int NOT NULL AUTO_INCREMENT,
    tweet   int,
    PRIMARY KEY (id),
    FOREIGN KEY (tweet) REFERENCES tweet (id)
);
drop table twitter2.response;
select *
from response;