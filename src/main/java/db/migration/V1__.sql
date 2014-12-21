create table userses (
  id int8 not null primary key,
	login varchar(40) not null UNIQUE,
	password int8 not null,
	fullName text not null,
	info text);

create table accidenttype (
	id int8 not null primary key,
	value varchar(40));

create table device (
	id int8 not null primary key,
	user_id bigint not null REFERENCES userses(id) ON DELETE CASCADE,
	lat double precision,
	lng double precision);

create table accident (
	id int8 not null primary key,
	accidenttypeid bigint not null REFERENCES accident_types(id) ON DELETE SET null,
	deviceid bigint not null REFERENCES devices(deviceid) ON DELETE set null,
	lat double precision not null,
	lng double precision not null,
	accidentdate DATE NOT NULL,
	resolvestatus boolean not null default FALSE);
	create sequence hibernate_sequence;