--============================================
-- hello-mybatis
--============================================
--student 테이블 생성
create table student(
    no number,
    name varchar2(50) not null,
    tel char(11) not null,
    created_at date default sysdate,
    updated_at date default sysdate,
    deleted_at date,
    constraint pk_student_no primary key(no)
);

create sequence seq_student_no;

-- 샘플데이터
insert into student(no, name, tel) values (seq_student_no.nextVal, '홍길동', '01012341234');
insert into student(no, name, tel) values (seq_student_no.nextVal, '신사임당', '01011112222');

-- 수정
update student 
set tel = '01098761234', updated_at = sysdate 
where deleted_at is null and no = 2; -- 삭제처리된 행 변경되지않도록

-- 삭제
update student set deleted_at = sysdate where no = 1;


select * from student;
select * from student where (deleted_at is null); -- 삭제처리행 제외하기위한 조건 항상 추가


commit;

-- web -> kh계정의 테이블 접근
-- kh계정에서 테이블 읽기 권한 부여
grant select on employee to web;
grant select on department to web;
grant select on job to web;


select * from kh.employee;
select * from kh.department;
select * from kh.job;


-- system계정에서 create synonym 권한 부여
grant create synonym to web;

-- 동의어(synonym) 생성
create synonym emp for kh.employee;
create synonym dept for kh.department;
create synonym job for kh.job;

-- 동의어로 조회
select * from emp;
select * from dept;
select * from job;

select *
from (
    select
        e.*,
        decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender
    from emp e
) e
where
    1 = 1 
    and email like '%a%'
    and gender = '여'
;

-- empUpdate
-- web emp테이블 수정권한 부여
-- 관리자 또는 테이블주인 계정에서 update권한 부여
grant update on employee to web;
grant update on department to web;
grant update on job to web;

