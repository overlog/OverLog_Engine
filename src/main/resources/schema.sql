CREATE TABLE IF NOT EXISTS log(
  id	serial primary key,
  type	VARCHAR,
  text VARCHAR,
  userID  bigint not null
);

CREATE TABLE IF NOT EXISTS users(
  id	serial primary key,
  username	VARCHAR,
  passwd VARCHAR
);

