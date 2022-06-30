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