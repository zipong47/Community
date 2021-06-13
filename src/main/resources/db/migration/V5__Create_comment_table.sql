create table comment
(
	id bigint auto_increment,
	parent_id bigint not null,
	type int not null,
	comment text not null,
	commenter int not null,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	like_count bigint default 0,
	constraint COMMENT_PK
		primary key (id)
);