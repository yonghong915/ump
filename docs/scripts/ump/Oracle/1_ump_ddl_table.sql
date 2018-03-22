----------------------------------------------
-- Export file for user WEBAPP@ORCL         --
-- Created by fangyh on 2016/4/24, 13:32:10 --
----------------------------------------------



spool ump_db.log append

-----------------------------------------------
--
--drop tables
--
-----------------------------------------------
exec proc_dropifexists('biz_address'); 
exec proc_dropifexists('sys_user');  





----------------------------------------------------
--
--
--������
--
----------------------------------------------------
create table sys_user(
  id char(32) not null,
  user_code char(11),
  user_name varchar2(100),
  status char(1),
  create_date date,
  creator varchar2(50),
  update_date date,
  updator varchar2 (50)
)
tablespace MYDATA;

-- Add comments to the table 
comment on table sys_user  is 'ϵͳ�û���Ϣ';
-- Add comments to the columns 
comment on column sys_user.id  is '����';
comment on column sys_user.user_code is '�û�����';
comment on column sys_user.user_name  is '�û�����';
comment on column sys_user.status  is '�û�״̬,0������,1����';

alter table sys_user add constraint pk_sys_user_id primary key(id) using index tablespace mydata_index;





create table biz_address(
  id char(32) not null,
  user_id char(32),
  name varchar2(50),
  mobile varchar2(11),
  mail varchar2(100),
  addr varchar2(500),
  create_date date,
  creator varchar2(50),
  update_date date,
  updator varchar2 (50)
)
tablespace MYDATA;


alter table biz_address add constraint pk_biz_address_id primary key(id) using index tablespace mydata_index;
alter table biz_address add constraint fk_bizaddr_sysuser_userid foreign key(user_id) references sys_user(id);













spool off