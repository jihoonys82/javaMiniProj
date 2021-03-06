  CREATE TABLE "EMPLOYEE" 
   (	"EMPLOYEEID" NUMBER NOT NULL ENABLE, 
	"EMPLOYEENAME" VARCHAR2(20 BYTE), 
	"BIRTHDATE" VARCHAR2(20 BYTE), 
	"TEAM" VARCHAR2(50 BYTE), 
	"EMPLOYLEVEL" VARCHAR2(20 BYTE), 
	"ROLE" VARCHAR2(20 BYTE), 
	"MOBILE" VARCHAR2(20 BYTE), 
	"WORKPHONE" VARCHAR2(20 BYTE), 
	"EMAIL" VARCHAR2(50 BYTE), 
	"LOCATION" VARCHAR2(50 BYTE), 
	"PASSWORD" VARCHAR2(20 BYTE), 
	"PHOTOPATH" VARCHAR2(200 BYTE), 
	"LOSTIDQUESTION" VARCHAR2(100 BYTE), 
	"LOSTIDANSWER" VARCHAR2(100 BYTE), 
	 CONSTRAINT "EMPLOYEE_PK" PRIMARY KEY ("EMPLOYEEID")
);     

CREATE SEQUENCE EMP_SEQ INCREMENT BY 1 MINVALUE 20000 CACHE 20;

Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (8888,'김테스터','2018.07.06','개발1팀','대리','간식담당','010-1234-5678','02-1234-5678','tester@test.com','KH강남2층','1','no_avatar.jpg','조장이름은?','정지훈');
Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (9001,'수호','1991.5.22','개발1팀','사원','010-2335-1421','02-2344-7024','suho10@naver.com','강남구 테헤란로 14길 6 남도빌딩','soho1234','exo_su_ho.jpg','가장소중한것은?','수호');
Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (9002,'카이','1994.01.14','개발1팀','사원','010-5212-4215','02-2344-7024','kai10@naver.com','강남구 테헤란로 14길 6 남도빌딩','kai1234','exo_kai.jpg','가장소중한것은?','카이');
Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (9003,'디오','1993.01.12','개발2팀','대리','010-2154-1201','02-2344-7024','dio10@naver.com','강남구 테헤란로 14길 6 남도빌딩','dio1234','exo_dio.jpg','가장소중한것은?','디오');
Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (9004,'첸','1992.09.21','개발2팀','대리','010-5214-2125','02-2344-7024','chen10@naver.com','강남구 테헤란로 14길 6 남도빌딩','chen1234','exo_chen.jpg','가장소중한것은?','첸');
Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (9005,'백현','1992.05.06','개발3팀','과장','010-2369-7813','02-2344-7024','baek10@naver.com','강남구 테헤란로 14길 6 남도빌딩','baek1234','exo_back_hyun.jpg','가장소중한것은?','백현');
Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (9006,'유주','1997.10.04','개발3팀','사원','010-5241-2986','02-2344-7024','yuju10@naver.com','강남구 테헤란로 14길 6 남도빌딩','yuju1234','Girlfriend_yu_ju.jpg','가장소중한것은?','유주');
Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (9007,'예린','1996.08.19','개발3팀','대리','010-7745-8965','02-2344-7024','yerin@naver.com','강남구 테헤란로 14길 6 남도빌딩','yerin1234','Girlfriend_ye_rin.jpg','가장소중한것은?','예린');
Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (9008,'엄지','1998.08.19','개발4팀','사원','010-5545-6623','02-2344-7024','umji@naver.com','강남구 테헤란로 14길 6 남도빌딩','umji1234','Girlfriend_um_ji.jpg','가장소중한것은?','엄지');
Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (9009,'신비','1998.06.03','운영팀','사원','010-2212-4558','02-2344-7024','shinbi@naver.com','강남구 테헤란로 14길 6 남도빌딩','shinbi1234','Girlfriend_shin_bi.jpg','가장소중한것은?','신비');
Insert into EMPLOYEE (EMPLOYEEID,EMPLOYEENAME,BIRTHDATE,TEAM,EMPLOYLEVEL,MOBILE,WORKPHONE,EMAIL,LOCATION,PASSWORD,PHOTOPATH,LOSTIDQUESTION,LOSTIDANSWER) values (9010,'은하','1997.05.30','인사팀','사원','010-2322-1589','02-2344-7024','eunha@naver.com','강남구 테헤란로 14길 6 남도빌딩','eunha1234','Girlfriend_eun_ha.jpg','가장소중한것은?','은하');

CREATE TABLE "TEAM" 
   (	"TEAMNAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"TEAMROLE" VARCHAR2(100 BYTE), 
	"TEAMLEADERID" VARCHAR2(20 BYTE), 
	 CONSTRAINT "TEAM_PK" PRIMARY KEY ("TEAMNAME")
); 

Insert into TEAM (TEAMNAME,TEAMROLE,TEAMLEADERID) values ('개발1팀','SW개발',8888);
Insert into TEAM (TEAMNAME,TEAMROLE,TEAMLEADERID) values ('개발2팀','프로젝트',9003);
Insert into TEAM (TEAMNAME,TEAMROLE,TEAMLEADERID) values ('개발3팀','시스템통합',9005);
Insert into TEAM (TEAMNAME,TEAMROLE,TEAMLEADERID) values ('개발4팀','엔진개발',9008);
Insert into TEAM (TEAMNAME,TEAMROLE,TEAMLEADERID) values ('운영팀','시스템운영',9009);
Insert into TEAM (TEAMNAME,TEAMROLE,TEAMLEADERID) values ('인사팀','인사',9010);
