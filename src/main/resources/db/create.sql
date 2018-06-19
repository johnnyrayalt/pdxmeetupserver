CREATE DATABASE pdxmeetupsdb;
\c pdxmeetupsdb;

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  photo VARCHAR,
  eventid INTEGER
);

CREATE TABLE events (
  id SERIAL PRIMARY KEY,
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


CREATE DATABASE pdxmeetupsdb_test WITH TEMPLATE pdxmeetupsdb;