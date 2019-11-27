drop table if exists b_cust;
create table b_cust(
  pk_id bigint(20) not null,
  cust_no varchar(20),
  is_del smallint default 0,
  crt_user varchar(32),
  crt_dept varchar(32),
  crt_time timestamp,
  mod_user varchar(32),
  mod_dept varchar(32),
  mod_time timestamp
);
comment on table b_cust is '客户信息';
comment on column b_cust.pk_id is '主键';