create table reply (
	no number primary key,
	writer varchar2(20),
	wdate date,
	content varchar2(200),
	notice_id number
);

insert into reply values (1, 'kim', sysdate, '댓글test', 1);
