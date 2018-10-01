show databases;
 use CS2830;
 
 describe users;
 
 select * from users;
 
 insert into users (username, password, addDate, changeDate)
	values ('test', sha1('pass'), now(), now() );

select id from users where username='test' and password=(sha1('pass'));

update users set id=10 where username='test';

select *from users;

update users set password=sha1('pass') where username='test';

