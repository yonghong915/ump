drop table if exists s_dict;
create table s_dict(
  dict_id varchar(50) not null,
  parent_id varchar(50),
  dict_code varchar(50) not null,
  dict_value varchar(500) not null,
  dict_dsc  varchar(500),
  dict_level smallint not null default 1,
  dict_index smallint,
  is_del smallint default 0,
  crt_user bigint(20),
  crt_dept bigint(20),
  crt_time timestamp,
  mod_user bigint(20),
  mod_dept bigint(20),
  mod_time timestamp
);
comment on table s_dict is '字典项';
comment on column s_dict.dict_id is '字典项ID';
comment on column s_dict.parent_id is '父项ID';
comment on column s_dict.dict_code is '字典项编码';
comment on column s_dict.dict_value is '字典项值';
comment on column s_dict.dict_dsc is '字典项说明';
comment on column s_dict.dict_level is '节点层级';
comment on column s_dict.dict_index is '节点序号';
comment on column s_dict.is_del is '是否删除';
comment on column s_dict.crt_user is '创建人员';
comment on column s_dict.crt_dept is '创建部门';
comment on column s_dict.crt_time is '创建时间';
comment on column s_dict.mod_user is '修改人员';
comment on column s_dict.mod_dept is '修改部门';
comment on column s_dict.mod_time is '修改时间';

drop table if exists s_log;
create table s_log(
  log_id varchar(50) not null,
  log_name varchar(50),
  ip_address varchar(50),
  log_type varchar(5) not null,
  log_moudle varchar(50) not null,
  request_url varchar(200),
  request_type varchar(50),
  request_method  varchar(500),
  request_params varchar(500),
  result varchar(500),
  cost_time bigint(20),
  remark varchar(2000),
  is_del smallint default 0,
  crt_user bigint(20),
  crt_dept bigint(20),
  crt_time timestamp,
  mod_user bigint(20),
  mod_dept bigint(20),
  mod_time timestamp
);
comment on table s_log is '字典项';
comment on column s_log.log_id is '主键';



