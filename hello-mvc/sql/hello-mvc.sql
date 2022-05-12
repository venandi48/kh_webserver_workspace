--========================================
-- 관리자계정
--========================================
-- web계정 생성
alter session set "_oracle_script" = true; -- c##으로 시작하지않는 일반계정생성 허용

-- 일반사용자(kh) 추가
create user web
identified by web -- 비밀번호
default tablespace users; -- 실제 데이터 저장공간

grant connect, resource to web;
alter user web quota unlimited on users;

--========================================
-- web계정
--========================================
-- 회원테이블 생성

create table member (
    member_id varchar2(15),
    password varchar2(300) not null,
    member_name varchar2(50) not null,
    member_role char(1) default 'U' not null,
    gender char(1),
    birthday date,
    email varchar2(200),
    phone char(11) not null,
    address varchar2(200),
    hobby varchar2(200),
    enroll_date date default sysdate,
    constraint pk_member_id primary key(member_id),
    constraint ck_member_role check(member_role in ('U', 'A')),
    constraint ck_member_gender check(gender in ('M', 'F')),
    constraint uq_member_email unique(email)
);
