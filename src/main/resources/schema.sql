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

create table if not exist alert(
	id serial primary key,
    type text,
    starttime timestamp,
    endtime timestamp
);