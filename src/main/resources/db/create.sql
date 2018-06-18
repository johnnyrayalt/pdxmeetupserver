CREATE DATABASE pdxmeetups;
\c pdxmeetups;

-- ---
-- Table 'users'
--
-- ---

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  photo VARCHAR
);

-- ---
-- Table 'events'
--
-- ---

CREATE TABLE events (
  id SERIAL PRIMARY KEY
);

-- ---
-- Table 'topics'
--
-- ---

CREATE TABLE topics (
  id SERIAL PRIMARY KEY,
  name VARCHAR
);

-- ---
-- Table 'users_events'
--
-- ---

CREATE TABLE users_events (
  id SERIAL PRIMARY KEY,
  userid INTEGER,
  eventid INTEGER
);

-- ---
-- Table 'users_topics'
--
-- ---

CREATE TABLE users_topics (
  id SERIAL PRIMARY KEY,
  userid INTEGER,
  topicid INTEGER
);

-- ---
-- Table 'topics_events'
--
-- ---

CREATE TABLE topics_events (
  id SERIAL PRIMARY KEY,
  topicid INTEGER,
  eventid INTEGER
);

CREATE DATABASE pdxmeetups_test WITH TEMPLATE pdxmeetups;