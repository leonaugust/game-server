DROP TABLE IF EXISTS user_profile;

CREATE TABLE user_profile
(
    id         INT PRIMARY KEY,
    name       VARCHAR(64) NOT NULL DEFAULT '',
    level      INT         NOT NULL DEFAULT 1,
    experience INT         NOT NULL DEFAULT 0,
    energy     INT         NOT NULL DEFAULT 25,
    rating     INT         NOT NULL DEFAULT 0,
    money      INT         NOT NULL DEFAULT 100,
    backpack   VARCHAR     NOT NULL DEFAULT '',
    inventory  VARCHAR     NOT NULL DEFAULT '',
    friends    VARCHAR     NOT NULL DEFAULT ''
);

DROP TABLE IF EXISTS uid_profile;

CREATE TABLE uid_profile
(
    uid        VARCHAR(64) NOT NULL PRIMARY KEY,
    profile_id INT         NOT NULL,
    UNIQUE (uid, profile_id)
);

DROP SEQUENCE IF EXISTS user_profile_sequence;

CREATE SEQUENCE user_profile_sequence
    START WITH 1
    INCREMENT BY 1;
