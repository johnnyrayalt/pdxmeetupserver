CREATE DATABASE pdxmeetupsdb;
\c pdxmeetupsdb;

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  photo VARCHAR
);

CREATE TABLE events (
  id SERIAL PRIMARY KEY,
  meetupapiid VARCHAR,
  userid INTEGER
);

CREATE TABLE keywords (
  id SERIAL PRIMARY KEY,
  keyword VARCHAR
);

CREATE DATABASE pdxmeetupsdb_test WITH TEMPLATE pdxmeetupsdb;