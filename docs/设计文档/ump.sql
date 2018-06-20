
create table sys_user(
  user_id varchar2(32),
  user_name varchar2(32),
  user_pwd varchar2(32),
  user_desc varchar2(100),
  user_en_name varchar2(50),
  user_cn_name varchar2(100),
  creator varchar2(32),
  create_time timestamp(6),
  updator varchar2(32),
  update_time timestamp(6),
  status char(1),
  del_flag char(1)
)
-- Create table
create table SYS_USER
(
  user_id      VARCHAR2(32) not null,
  user_name    VARCHAR2(32) not null,
  user_pwd     VARCHAR2(32) not null,
  user_desc    VARCHAR2(100),
  user_en_name VARCHAR2(50),
  user_cn_name VARCHAR2(100),
  creator      VARCHAR2(32) not null,
  create_time  TIMESTAMP(6) default systimestamp not null,
  updator      VARCHAR2(32) not null,
  update_time  TIMESTAMP(6) default systimestamp not null,
  status       CHAR(1) default '1' not null,
  del_flag     CHAR(1) default 'n' not null
)
tablespace MYDATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SYS_USER.user_id
  is '用户编码';
comment on column SYS_USER.user_name
  is '用户登录名';
comment on column SYS_USER.user_pwd
  is '用户密码';
comment on column SYS_USER.user_desc
  is '描述';
comment on column SYS_USER.user_en_name
  is '用户英文名';
comment on column SYS_USER.user_cn_name
  is '用户中文名';
comment on column SYS_USER.creator
  is '创建人';
comment on column SYS_USER.create_time
  is '创建时间';
comment on column SYS_USER.updator
  is '更新人';
comment on column SYS_USER.update_time
  is '更新时间';
comment on column SYS_USER.status
  is '状态,1有效,0无效';
comment on column SYS_USER.del_flag
  is '是否删除,n否,y是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_USER
  add constraint PK_SYS_USER primary key (USER_ID)
  using index 
  tablespace MYDATA_INDEX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_USER
  add constraint UK_USERNAME_SYS_USER unique (USER_NAME)
  using index 
  tablespace MYDATA_INDEX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table
create table SYS_ROLE
(
  role_id     VARCHAR2(32) not null,
  role_code   VARCHAR2(32) not null,
  role_name   VARCHAR2(32) not null,
  role_desc   VARCHAR2(100),
  creator     VARCHAR2(32) not null,
  create_time TIMESTAMP(6) default systimestamp not null,
  updator     VARCHAR2(32) not null,
  update_time TIMESTAMP(6) default systimestamp not null,
  status      CHAR(1) default '1' not null,
  del_flag    CHAR(1) default 'n' not null
)
tablespace MYDATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SYS_ROLE.role_id
  is '角色编码';
comment on column SYS_ROLE.role_code
  is '角色code';
comment on column SYS_ROLE.role_name
  is '角色名称';
comment on column SYS_ROLE.role_desc
  is '角色描述';
comment on column SYS_ROLE.creator
  is '创建人';
comment on column SYS_ROLE.create_time
  is '创建时间';
comment on column SYS_ROLE.updator
  is '更新人';
comment on column SYS_ROLE.update_time
  is '更新时间';
comment on column SYS_ROLE.status
  is '状态,1有效,0无效';
comment on column SYS_ROLE.del_flag
  is '是否删除,n否,y是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_ROLE
  add constraint PK_SYS_ROLE primary key (ROLE_ID)
  using index 
  tablespace MYDATA_INDEX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_ROLE
  add constraint UK_USERCODE_SYS_ROLE unique (ROLE_CODE)
  using index 
  tablespace MYDATA_INDEX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

--sys_guid()

-- Create table
create table SYS_USER_ROLE
(
  id          VARCHAR2(32) not null,
  user_id     VARCHAR2(32) not null,
  role_id     VARCHAR2(32) not null,
  creator     VARCHAR2(32) not null,
  create_time TIMESTAMP(6) default systimestamp not null,
  updator     VARCHAR2(32) not null,
  update_time TIMESTAMP(6) default systimestamp not null,
  status      CHAR(1) default '1' not null,
  del_flag    CHAR(1) default 'n' not null
)
tablespace MYDATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SYS_USER_ROLE.id
  is '主键';
comment on column SYS_USER_ROLE.user_id
  is '用户编码';
comment on column SYS_USER_ROLE.role_id
  is '角色编码';
comment on column SYS_USER_ROLE.creator
  is '创建人';
comment on column SYS_USER_ROLE.create_time
  is '创建时间';
comment on column SYS_USER_ROLE.updator
  is '更新人';
comment on column SYS_USER_ROLE.update_time
  is '更新时间';
comment on column SYS_USER_ROLE.status
  is '状态,1有效,0无效';
comment on column SYS_USER_ROLE.del_flag
  is '是否删除,n否,y是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_USER_ROLE
  add constraint PK_SYS_USER_ROLE primary key (ID)
  using index 
  tablespace MYDATA_INDEX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_USER_ROLE
  add constraint UK_USERID_ROLEID unique (USER_ID, ROLE_ID)
  using index 
  tablespace MYDATA_INDEX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_USER_ROLE
  add constraint FK_ROLE_ID_USER_ROLE foreign key (ROLE_ID)
  references SYS_ROLE (ROLE_ID);
alter table SYS_USER_ROLE
  add constraint FK_USER_ID_USER_ROLE foreign key (USER_ID)
  references SYS_USER (USER_ID);

