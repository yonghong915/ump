--������ʱ��ռ�
create temporary tablespace mydata_temp
tempfile '/oracle/oradata/ump/mydata_temp.dbf'
size 64m
autoextend on
next 64m maxsize 2048m
extent management local;



create smallfile temporary tablespace mydata_temp
tempfile '/oracle/oradata/ump/mydata_temp.dbf'
size 64m autoextend on next 64m maxsize 2048m extent management local uniform size 1M;


--������ռ�
create smallfile tablespace mydata
datafile '/oracle/oradata/ump/mydata.dbf'
size 64m autoextend on next 65m maxsize unlimited
logging extent management local segment space management auto;


--���ݷ�����ռ�(���·�)
create tablespace mydata_01
logging
datafile 'D:\software\oracle\oradata\orcl\ump\mydata_01.dbf'
size 20m
autoextend on
next 20m maxsize 2048m
extent management local;

create tablespace mydata_04
logging
datafile 'D:\software\oracle\oradata\orcl\ump\mydata_04.dbf'
size 20m
autoextend on
next 20m maxsize 2048m
extent management local;


--������ռ�
create tablespace mydata_index
logging
datafile '/oracle/oradata/ump/mydata_index.dbf'
size 60m
autoextend on
next 65m maxsize 2048m
extent management local;


--�����û�
create user umpapp profile default identified by umpapp1234
default tablespace MYDATA
temporary tablespace MYDATA_TEMP;

--���û���Ȩ
grant connect,resource to umpapp;
GRANT UNLIMITED TABLESPACE TO umpapp;