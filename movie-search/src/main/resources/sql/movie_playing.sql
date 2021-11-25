create table movie_playing(
    movie_id varchar2(20) primary key,
    movie_title varchar2(50),
    movie_date varchar2(50),
    count number
);

insert into movie_playing values('1','화성을 지켜라','2200-05-14',100);
insert into movie_playing values('2','지구를 지켜라','2200-05-14',100);
insert into movie_playing values('3','우주를 지켜라','2200-04-02',100);