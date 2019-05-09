----------------------------------------------
-- Export file for user WEBAPP@ORCL         --
-- Created by fangyh on 2019/3/6, 21:32:10 --
----------------------------------------------

create table B_LINE(
  id char(32) not null,
  user_code char(11),
  user_name varchar2(100),
  status char(1),
  create_date date,
  creator varchar2(50),
  update_date date,
  updator varchar2 (50)
);

comment on table B_LINE  is '产品线';
comment on column B_LINE.id  is '主键';
comment on column B_LINE.user_code is '用户编码';
comment on column B_LINE.user_name  is '用户姓名';
comment on column B_LINE.status  is '用户状态,0不可用,1可用';


create table B_GROUP(
  

);

comment on table B_GROUP  is '产品组';

create table B_CMPT(
  

);
comment on table B_GROUP  is '产品部件';


create table B_CMPT(
  

);
comment on table B_GROUP  is '产品部件';



create table B_ATTR(
  

);
comment on table B_ATTR  is '产品属性';



create table B_ATTR_VAL(
  

);
comment on table B_ATTR_VAL  is '产品属性列表';


create table B_TPL(
  

);
comment on table B_TPL  is '产品模板';

create table B_PD(
  

);
comment on table B_PD  is '产品信息';













