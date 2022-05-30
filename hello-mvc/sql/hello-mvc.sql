--============================================
-- 관리자계정
--============================================
-- web계정 생성
alter session set "_oracle_script" = true; -- c##으로 시작하지 않는 일반계정생성 허용

create user web
identified by web
default tablespace users;

grant connect, resource to web;

alter user web quota unlimited on users;


--============================================
-- web계정
--============================================
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


--회원 추가
insert into member
values (
    'honggd', '1234', '홍길동', 'U', 'M', to_date('20000909','yyyymmdd'),
    'honggd@naver.com', '01012341234', '서울시 강남구', '운동,등산,독서', default
);

insert into member
values (
    'qwerty', '1234', '쿠어티', 'U', 'F', to_date('19900215','yyyymmdd'),
    'qwerty@naver.com', '01012341234', '서울시 송파구', '운동,등산', default
);

insert into member
values (
    'admin', '1234', '관리자', 'A', 'M', to_date('19801010','yyyymmdd'),
    'admin@naver.com', '01056785678', '서울시 관악구', '게임,독서', default
);


commit;


insert into member
values (
    'yoogs', '1234', '유관순', 'U', 'M', null,
    null, '01019950408', null, 'null', default
);
--delete from member
--where member_id = 'abcd';


-- pw 1234로 변경
--update member
--set password = 'kvOffyqGmDjNUIXm8X/IIQm8+YzWKkfLw3njjegLvAITojzubkoT3myq4K3Yo5AnLW8Ig8J0Mgsf9g28/G3XUA=='
--where member_id = 'admin';






select * from member;
select count(*) from member;



-- 페이징쿼리
-- 1. rownum
-- 2. row_number

select *
from(
    select
        row_number() over(order by enroll_date desc) rnum,
        m.*
    from
        member m)m
where
    rnum between 1 and 10;

-- select * from( select row_number() over(order by enroll_date desc) rnum, m.* from member m)m where rnum between ? and ?

/*
    1페이지당 표시할 컨텐츠 : 10건 --> content 영역
    ------------------------------------
    1page : 1 ~ 10
    2page : 11 ~ 20
    3page : 21 ~ 30
    ...
    11page : 101 ~ 110
    12page : 111 ~ 120
*/


-- 게시판

create table board (
    no number,
    title varchar2(100) not null,
    member_id varchar2(20),
    content varchar2(4000) not null,
    read_count number default 0,
    reg_date date default sysdate,
    
    constraint pk_board primary key(no),
    constraint fk_board_member_id foreign key(member_id) references member(member_id) on delete set null
);
-- drop table board;

create sequence seq_board_no;
-- DROP SEQUENCE seq_board_no;

create table attachment (
    no number,
    board_no number not null,
    original_filename varchar2(255) not null,  -- 업로드한 파일명
    renamed_filename varchar2(255) not null, -- 저장된 파일명
    reg_date date default sysdate,
    
    constraint pk_attachment_no primary key(no),
    constraint fk_attachment_board_no foreign key(board_no) references board(no) on delete cascade
);

create sequence seq_attachment_no;

-- 샘플 데이터 추가하기


-- 댓글 테이블
create table board_comment (
    no number,
    comment_level number default 1, -- 댓글(1) / 대댓글(2)
    member_id varchar2(15),
    content varchar2(2000),
    board_no number, -- 참조 게시글
    comment_ref number, -- 대댓글인 경우 참조하는 댓글 no (댓글인 경우 null)
    reg_date date default sysdate,
    
    constraint pk_board_comment_no primary key(no),
    constraint fk_board_comment_number_id foreign key(member_id) references member(member_id) on delete set null,
    constraint fk_board_comment_board_no foreign key(board_no) references board(no) on delete cascade,
    constraint fk_board_comment_ref foreign key(comment_ref) references board_comment(no) on delete cascade
);

create sequence seq_board_comment_no;

-- 샘플데이터 입력
-- 댓글
insert into board_comment
values(
    SEQ_BOARD_COMMENT_NO.nextval,
    default,
    'sinsa',
    'def',
    101,
    null,
    default
);

insert into board_comment
values(
    SEQ_BOARD_COMMENT_NO.nextval,
    default,
    'admin',
    '안녕하세요. 관리자입니다.',
    101,
    null,
    default
);

insert into board_comment
values(
    SEQ_BOARD_COMMENT_NO.nextval,
    default,
    'qwerty',
    '시간이 참 빠르네요!',
    101,
    null,
    default
);

-- 대댓글
insert into board_comment
values(
    SEQ_BOARD_COMMENT_NO.nextval,
    2,
    'honggd',
    'asdfg',
    101,
    1,
    default
);

insert into board_comment
values(
    SEQ_BOARD_COMMENT_NO.nextval,
    2,
    'sinsa',
    'jklmn',
    101,
    1,
    default
);

insert into board_comment
values(
    SEQ_BOARD_COMMENT_NO.nextval,
    2,
    'honggd',
    '무슨 일인가요?',
    101,
    2,
    default
);

-- 계층형 쿼리
-- 참조당하는쪽이 부모, 참조하는쪽이 자식
-- 부모행 no - 댓글
-- 자식행 comment_ref - 대댓글
select
    level,
    lpad(' ', (level - 1)*5) || bc.content,
    bc.*
from board_comment bc
where board_no = 101
start with comment_level = 1
connect by prior no = comment_ref;

-- kh 계정에서 관리자-관리사원정보 계층형 쿼리
-- 하향식
select 
    level,
    lpad(' ', (level - 1)*5) || e.emp_name
from employee e
start with manager_id is null
connect by manager_id = prior emp_id;

-- 상향식
select
    level,
    lpad(' ', (level - 1)*5) || emp_name
from employee
where level > 1
start with emp_name = '윤은해'
connect by prior manager_id = emp_id;

commit;
-- rollback;
select * from board_comment order by no;
select * from board order by no desc;
select * from attachment;

select
    level,
    lpad(' ', (level - 1)*5) || bc.content,
    bc.*
from board_comment bc
where board_no = 101
start with comment_level = 1 -- 루트행의 조건
connect by prior no = comment_ref;

select * from board_comment bc where board_no = ? start with comment_level = 1 connect by prior no = comment_ref;

select * from( select row_number() over(order by reg_date desc) rnum, b.*, (select count(*) from attachment where board_no = b.no)attach_cnt from board b)b where rnum between 50 and 200;
