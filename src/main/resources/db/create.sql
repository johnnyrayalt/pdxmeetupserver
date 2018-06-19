CREATE DATABASE pdxmeetupsdb;
\c pdxmeetupsdb;

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  photo VARCHAR
);

CREATE TABLE events (
  id SERIAL PRIMARY KEY,
  meetupapiid INTEGER
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