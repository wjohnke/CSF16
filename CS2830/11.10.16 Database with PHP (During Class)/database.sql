show databases;

use CS2830;

set password for 'root'@'localhost' = password('will');

create table users(
	id int primary key auto_increment,
    username varchar(255) not null unique,
    password text not null,
    addDate datetime,
    changeDate datetime
);

insert into users (username, password, addDate, changeDate) 
	values ('will', 'will', now(), now() );

select * from users;
-- encrypting password using sha1(), not most secure uses same key each time
update users set password = sha1(password), changeDate=now() where id=1;

insert into users (username, password, addDate, changeDate)
	values ('test', sha1('pass'), now(), now() );

describe users;

alter table users add email text default null after password;

alter table users add birthday date default null after email;

alter table users add firstName text default null after id;

alter table users add lastName text default null after firstName;

