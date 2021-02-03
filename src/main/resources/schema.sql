DROP TABLE IF EXISTS user_profile;

CREATE TABLE user_profile
(
    id                INTEGER PRIMARY KEY,
    name              VARCHAR(64)   NOT NULL DEFAULT '',
    level             INTEGER   NOT NULL DEFAULT 1,
    experience        INTEGER   NOT NULL DEFAULT 0,
    energy            INTEGER   NOT NULL DEFAULT 25,
    rating            INTEGER   NOT NULL DEFAULT 0,
    money             INTEGER   NOT NULL DEFAULT 100,
    backpack          VARCHAR   NOT NULL DEFAULT '',
    inventory         VARCHAR   NOT NULL DEFAULT '',
    friends           VARCHAR   NOT NULL DEFAULT '',
    name_changed_date TIMESTAMP NOT NULL DEFAULT now()
);

DROP TABLE IF EXISTS uid_profile;

CREATE TABLE uid_profile
(
    uid        VARCHAR(64) NOT NULL PRIMARY KEY,
    profile_id INTEGER     NOT NULL,
    UNIQUE (uid, profile_id)
);

DROP SEQUENCE IF EXISTS user_profile_sequence;

CREATE SEQUENCE user_profile_sequence
    START WITH 1
    INCREMENT BY 1;
