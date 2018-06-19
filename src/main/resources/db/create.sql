CREATE DATABASE pdxmeetups;
\c pdxmeetups;

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  photo VARCHAR,
  listofeventids TEXT
);


CREATE TABLE topics (
  id SERIAL PRIMARY KEY,
  topic VARCHAR
);


CREATE TABLE users_topics (
  id SERIAL PRIMARY KEY,
  userid INTEGER,
  topicid INTEGER
);


CREATE DATABASE pdxmeetups_test WITH TEMPLATE pdxmeetups;