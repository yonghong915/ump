
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




-- Create table
create table SYS_PERMISSION
(
  permission_id   VARCHAR2(32) not null,
  permission_code VARCHAR2(32) not null,
  permission_name VARCHAR2(50) not null,
  permission_desc VARCHAR2(100),
  permission_type VARCHAR2(10) not null,
  creator         VARCHAR2(32) not null,
  create_time     TIMESTAMP(6) default systimestamp not null,
  updator         VARCHAR2(32) not null,
  update_time     TIMESTAMP(6) default systimestamp,
  status          CHAR(1) default '1',
  del_flag        CHAR(1) default 'n'
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
comment on column SYS_PERMISSION.permission_id
  is '权限编码';
comment on column SYS_PERMISSION.permission_code
  is '权限CODE';
comment on column SYS_PERMISSION.permission_name
  is '权限名称';
comment on column SYS_PERMISSION.permission_desc
  is '权限描述';
comment on column SYS_PERMISSION.permission_type
  is '权限类型add/del/view/edit';
comment on column SYS_PERMISSION.creator
  is '创建人';
comment on column SYS_PERMISSION.create_time
  is '创建时间';
comment on column SYS_PERMISSION.updator
  is '更新人';
comment on column SYS_PERMISSION.update_time
  is '更新时间';
comment on column SYS_PERMISSION.status
  is '状态,1有效,0无效';
comment on column SYS_PERMISSION.del_flag
  is '是否删除,n否,y是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_PERMISSION
  add constraint PK_SYS_PERMISSION primary key (PERMISSION_ID)
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
alter table SYS_PERMISSION
  add constraint UK_PERMISSIONNAME_SYS_PERM unique (PERMISSION_CODE)
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
create table SYS_ROLE_PERMISSION
(
  id            VARCHAR2(32) not null,
  role_id       VARCHAR2(32) not null,
  permission_id VARCHAR2(32) not null,
  creator       VARCHAR2(32) not null,
  create_time   TIMESTAMP(6) default systimestamp not null,
  updator       VARCHAR2(32) not null,
  update_time   TIMESTAMP(6) default systimestamp not null,
  status        CHAR(1) default '1' not null,
  del_flag      CHAR(1) default 'n' not null
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
comment on column SYS_ROLE_PERMISSION.id
  is '主键';
comment on column SYS_ROLE_PERMISSION.role_id
  is '角色编码';
comment on column SYS_ROLE_PERMISSION.permission_id
  is '权限编码';
comment on column SYS_ROLE_PERMISSION.creator
  is '创建人';
comment on column SYS_ROLE_PERMISSION.create_time
  is '创建时间';
comment on column SYS_ROLE_PERMISSION.updator
  is '更新人';
comment on column SYS_ROLE_PERMISSION.update_time
  is '更新时间';
comment on column SYS_ROLE_PERMISSION.status
  is '状态,1有效,0无效';
comment on column SYS_ROLE_PERMISSION.del_flag
  is '是否删除,n否,y是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_ROLE_PERMISSION
  add constraint PK_SYS_ROLE_PERM primary key (ID)
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
alter table SYS_ROLE_PERMISSION
  add constraint UN_ROLE_PERM_SYS_ROLE_PERM unique (ROLE_ID, PERMISSION_ID)
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
alter table SYS_ROLE_PERMISSION
  add constraint FK_PERM_ID_SYS_PERM foreign key (PERMISSION_ID)
  references SYS_PERMISSION (PERMISSION_ID);
alter table SYS_ROLE_PERMISSION
  add constraint FK_ROLE_ID_SYS_ROLE foreign key (ROLE_ID)
  references SYS_ROLE (ROLE_ID);



-- Create table
create table SYS_RESOURCE
(
  resource_id          VARCHAR2(32) not null,
  resource_code        VARCHAR2(32) not null,
  parent_resource_code VARCHAR2(32),
  resource_name        VARCHAR2(50) not null,
  resource_desc        VARCHAR2(100),
  resource_type        CHAR(2) default '01' not null,
  resource_url         VARCHAR2(200),
  resource_icon        VARCHAR2(50),
  resource_image       VARCHAR2(50),
  order_no             NUMBER(2) default 0 not null,
  creator              VARCHAR2(32) not null,
  create_time          TIMESTAMP(6) default systimestamp not null,
  updator              VARCHAR2(32) not null,
  update_time          TIMESTAMP(6) default systimestamp not null,
  status               CHAR(1) default '1' not null,
  del_flag             CHAR(1) default 'n' not null
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
comment on column SYS_RESOURCE.resource_id
  is '资源编码';
comment on column SYS_RESOURCE.resource_code
  is '资源CODE';
comment on column SYS_RESOURCE.resource_name
  is '资源名称';
comment on column SYS_RESOURCE.resource_desc
  is '资源描述';
comment on column SYS_RESOURCE.resource_type
  is '资源类型,01菜单,02按钮';
comment on column SYS_RESOURCE.resource_url
  is '资源URL';
comment on column SYS_RESOURCE.order_no
  is '排序号';
comment on column SYS_RESOURCE.creator
  is '创建人';
comment on column SYS_RESOURCE.create_time
  is '创建时间';
comment on column SYS_RESOURCE.updator
  is '更新人';
comment on column SYS_RESOURCE.update_time
  is '更新时间';
comment on column SYS_RESOURCE.status
  is '状态,1有效,0无效';
comment on column SYS_RESOURCE.del_flag
  is '是否删除,n否,y是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_RESOURCE
  add constraint PK_SYS_RESOURCE primary key (RESOURCE_ID)
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
alter table SYS_RESOURCE
  add constraint UK_RES_CODE_SYS_RES unique (RESOURCE_CODE)
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
alter table SYS_RESOURCE
  add constraint FK_PARANT_RES_CODE_SYS_RES foreign key (PARENT_RESOURCE_CODE)
  references SYS_RESOURCE (RESOURCE_CODE);


-- Create table
create table SYS_PERMISSION_RESOURCE
(
  id            VARCHAR2(32) not null,
  permission_id VARCHAR2(32) not null,
  resource_id   VARCHAR2(32) not null,
  creator       VARCHAR2(32) not null,
  create_time   TIMESTAMP(6) default systimestamp not null,
  updator       VARCHAR2(32) not null,
  update_time   TIMESTAMP(6) default systimestamp not null,
  status        CHAR(1) default '1' not null,
  del_flag      CHAR(2) default 'n' not null
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
comment on column SYS_PERMISSION_RESOURCE.id
  is '主键';
comment on column SYS_PERMISSION_RESOURCE.permission_id
  is '权限编码';
comment on column SYS_PERMISSION_RESOURCE.resource_id
  is '资源编码';
comment on column SYS_PERMISSION_RESOURCE.creator
  is '创建人';
comment on column SYS_PERMISSION_RESOURCE.create_time
  is '创建时间';
comment on column SYS_PERMISSION_RESOURCE.updator
  is '更新人';
comment on column SYS_PERMISSION_RESOURCE.update_time
  is '更新时间';
comment on column SYS_PERMISSION_RESOURCE.status
  is '状态,1有效,0无效';
comment on column SYS_PERMISSION_RESOURCE.del_flag
  is '是否删除,n否,y是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_PERMISSION_RESOURCE
  add constraint PK_SYS_PERMISSION_RESOURCE primary key (ID)
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
alter table SYS_PERMISSION_RESOURCE
  add constraint UK_PERMID_ROLEID unique (PERMISSION_ID, RESOURCE_ID)
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
create table SYS_CODE_TYPE
(
  id             VARCHAR2(32) not null,
  code_type_code VARCHAR2(32) not null,
  code_type_name VARCHAR2(100) not null,
  code_type_desc VARCHAR2(200),
  order_no       NUMBER(4) default 0 not null,
  creator        VARCHAR2(32) not null,
  create_time    TIMESTAMP(6) default systimestamp not null,
  updator        VARCHAR2(32) not null,
  update_time    TIMESTAMP(6) default systimestamp not null,
  status         CHAR(1) default '1' not null,
  del_flag       CHAR(1) default 'n' not null
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
comment on column SYS_CODE_TYPE.id
  is '主键';
comment on column SYS_CODE_TYPE.code_type_code
  is '类型编码';
comment on column SYS_CODE_TYPE.code_type_name
  is '类型名称';
comment on column SYS_CODE_TYPE.code_type_desc
  is '类型描述';
comment on column SYS_CODE_TYPE.order_no
  is '排序号';
comment on column SYS_CODE_TYPE.creator
  is '创建人';
comment on column SYS_CODE_TYPE.create_time
  is '创建时间';
comment on column SYS_CODE_TYPE.updator
  is '更新人';
comment on column SYS_CODE_TYPE.update_time
  is '更新时间';
comment on column SYS_CODE_TYPE.status
  is '状态,1有效,0无效';
comment on column SYS_CODE_TYPE.del_flag
  is '是否删除,n否,y是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_CODE_TYPE
  add constraint PK_SYS_CODE_TYPE primary key (ID)
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
alter table SYS_CODE_TYPE
  add constraint UK_TYPE_CODE_SYS_CODETYPE unique (CODE_TYPE_CODE)
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
create table SYS_CODE_INFO
(
  id              VARCHAR2(32) not null,
  code_info_code  VARCHAR2(32) not null,
  code_info_value VARCHAR2(32) not null,
  code_info_desc  VARCHAR2(100),
  code_type_code  VARCHAR2(32) not null,
  parent_code_id  VARCHAR2(32) not null,
  order_no       NUMBER(4) not null,
  creator         VARCHAR2(32) not null,
  create_time     TIMESTAMP(6) default systimestamp not null,
  updator         VARCHAR2(32) not null,
  update_time     TIMESTAMP(6) default systimestamp not null,
  status          CHAR(1) default '1' not null,
  del_flag        CHAR(1) default 'n' not null
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
comment on column SYS_CODE_INFO.id
  is '主键';
comment on column SYS_CODE_INFO.order_no
  is '排序号';
comment on column SYS_CODE_INFO.creator
  is '创建人';
comment on column SYS_CODE_INFO.create_time
  is '创建时间';
comment on column SYS_CODE_INFO.updator
  is '更新人';
comment on column SYS_CODE_INFO.update_time
  is '更新时间';
comment on column SYS_CODE_INFO.status
  is '状态,1有效,0无效';
comment on column SYS_CODE_INFO.del_flag
  is '是否删除,n否,y是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_CODE_INFO
  add constraint PK_SYS_CODE_INFO primary key (ID)
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
alter table SYS_CODE_INFO
  add constraint UK_CODEINFOCODE_TYPECODE unique (CODE_INFO_CODE, CODE_TYPE_CODE)
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
alter table SYS_CODE_INFO
  add constraint FK_CODETYPECODE_SYS_CODETYPE foreign key (CODE_TYPE_CODE)
  references SYS_CODE_TYPE (ID);
