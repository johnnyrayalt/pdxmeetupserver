CREATE DATABASE pdxmeetups;
\c pdxmeetups;

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  photo VARCHAR
);


CREATE TABLE events (
  id SERIAL PRIMARY KEY
);


CREATE TABLE topics (
  id SERIAL PRIMARY KEY,
  name VARCHAR
);


CREATE TABLE users_events (
  id SERIAL PRIMARY KEY,
  userid INTEGER,
  eventid INTEGER
);


CREATE TABLE users_topics (
  id SERIAL PRIMARY KEY,
  userid INTEGER,
  topicid INTEGER
);


CREATE TABLE topics_events (
  id SERIAL PRIMARY KEY,
  topicid INTEGER,
  eventid INTEGER
);

CREATE DATABASE pdxmeetups_test WITH TEMPLATE pdxmeetups;