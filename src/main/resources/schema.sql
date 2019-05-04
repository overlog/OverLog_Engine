CREATE TABLE IF NOT EXISTS log(
  id	serial primary key,
  type	VARCHAR,
  text VARCHAR,
  date timestamp,
  userID  bigint not null
);

CREATE TABLE IF NOT EXISTS users(
  id	serial primary key,
  username	VARCHAR,
  passwd VARCHAR,
  mail  VARCHAR
);

CREATE TABLE IF NOT EXISTS alert(
	id serial primary key,
    type VARCHAR,
    starttime timestamp,
    endtime timestamp,
    amount bigint,
    userID bigint
);