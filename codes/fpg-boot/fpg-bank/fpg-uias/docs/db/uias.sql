SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user(
   pk_id BIGINT(11) NOT NULL COMMENT '主键编码',
   user_name VARCHAR(10) NOT NULL COMMENT '登录账号',
   real_name VARCHAR(20) NOT NULL COMMENT '用户真实姓名',
   user_desc VARCHAR(200) NOT NULL COMMENT '用户描述',
   user_type CHAR(2) NOT NULL DEFAULT '01' COMMENT '用户类型',
   user_status CHAR(1) NOT NULL DEFAULT '1' COMMENT '用户状态',
   del_flag  TINYINT(1) DEFAULT 0 COMMENT '删除状态,0:正常,1:已删除',
   create_dt TIMESTAMP COMMENT '创建日期',
   create_by BIGINT(11) COMMENT '创建人',
   create_dept BIGINT(11) COMMENT '创建部门',
   update_dt TIMESTAMP COMMENT '更新日期',
   update_by BIGINT(11) COMMENT '更新人',
   update_dept  BIGINT(11) COMMENT '更新部门'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '用户信息';

alter table sys_user add constraint pk_sys_user primary key(pk_id) ;
create unique index uk_user_name on sys_user(user_name);

-- ----------------------------
-- Table structure for sys_user_auth
-- ----------------------------
DROP TABLE IF EXISTS sys_user_auth;
CREATE TABLE sys_user_auth(
   user_id BIGINT(11) NOT NULL COMMENT '主键编码',
   user_pwd VARCHAR(100) NOT NULL COMMENT '用户密码',
   salt VARCHAR(200) NOT NULL COMMENT '盐',
   del_flag  TINYINT(1) default 0 COMMENT '删除状态,0:正常,1:已删除',
   del_flag  TINYINT(1) DEFAULT 0 COMMENT '删除状态,0:正常,1:已删除',
   create_dt TIMESTAMP COMMENT '创建日期',
   create_by BIGINT(11) COMMENT '创建人',
   create_dept BIGINT(11) COMMENT '创建部门',
   update_dt TIMESTAMP COMMENT '更新日期',
   update_by BIGINT(11) COMMENT '更新人',
   update_dept  BIGINT(11) COMMENT '更新部门'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '用户安全信息';

alter table sys_user_auth add constraint pk_sys_user_auth primary key(user_id) ;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role(
   pk_id BIGINT(11) NOT NULL COMMENT '主键编码',
   role_code VARCHAR(10) NOT NULL COMMENT '角色编码',
   role_name VARCHAR(20) NOT NULL COMMENT '角色编码',
   role_desc VARCHAR(200) NOT NULL COMMENT '角色描述',
   role_type CHAR(2) NOT NULL DEFAULT '01' COMMENT '角色类型',
   del_flag  TINYINT(1) DEFAULT 0 COMMENT '删除状态,0:正常,1:已删除',
   create_dt TIMESTAMP COMMENT '创建日期',
   create_by BIGINT(11) COMMENT '创建人',
   create_dept BIGINT(11) COMMENT '创建部门',
   update_dt TIMESTAMP COMMENT '更新日期',
   update_by BIGINT(11) COMMENT '更新人',
   update_dept  BIGINT(11) COMMENT '更新部门'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '角色信息';

alter table sys_role add constraint pk_sys_role primary key(pk_id) ;
create unique index uk_role_code on sys_role(role_code);


DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role(
   pk_id BIGINT(11) NOT NULL COMMENT '主键编码',
   user_id BIGINT(11) NOT NULL COMMENT '用户编号',
   role_id BIGINT(11) NOT NULL COMMENT '角色编号',
   del_flag  TINYINT(1) DEFAULT 0 COMMENT '删除状态,0:正常,1:已删除',
   create_dt TIMESTAMP COMMENT '创建日期',
   create_by BIGINT(11) COMMENT '创建人',
   create_dept BIGINT(11) COMMENT '创建部门',
   update_dt TIMESTAMP COMMENT '更新日期',
   update_by BIGINT(11) COMMENT '更新人',
   update_dept  BIGINT(11) COMMENT '更新部门'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '用户角色信息';

alter table sys_user_role add constraint pk_sys_user_role primary key(pk_id) ;
create index idx_user_role on sys_user_role(user_id,role_id);


-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission(
   pk_id BIGINT(11) NOT NULL COMMENT '主键编码',
   permission_code VARCHAR(10) NOT NULL COMMENT '权限编码',
   permission_name VARCHAR(20) NOT NULL COMMENT '权限编码',
   permission_desc VARCHAR(200) NOT NULL COMMENT '权限描述',
   del_flag  TINYINT(1) DEFAULT 0 COMMENT '删除状态,0:正常,1:已删除',
   create_dt TIMESTAMP COMMENT '创建日期',
   create_by BIGINT(11) COMMENT '创建人',
   create_dept BIGINT(11) COMMENT '创建部门',
   update_dt TIMESTAMP COMMENT '更新日期',
   update_by BIGINT(11) COMMENT '更新人',
   update_dept  BIGINT(11) COMMENT '更新部门'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '权限信息';

alter table sys_permission add constraint pk_sys_permission primary key(pk_id) ;
create unique index uk_sys_permission on sys_permission(permission_code);


DROP TABLE IF EXISTS sys_role_permission;
CREATE TABLE sys_role_permission(
   pk_id BIGINT(11) NOT NULL COMMENT '主键编码',
   role_id BIGINT(11) NOT NULL COMMENT '角色编号',
   permission_id BIGINT(11) NOT NULL COMMENT '权限编号',
   del_flag  TINYINT(1) DEFAULT 0 COMMENT '删除状态,0:正常,1:已删除',
   create_dt TIMESTAMP COMMENT '创建日期',
   create_by BIGINT(11) COMMENT '创建人',
   create_dept BIGINT(11) COMMENT '创建部门',
   update_dt TIMESTAMP COMMENT '更新日期',
   update_by BIGINT(11) COMMENT '更新人',
   update_dept  BIGINT(11) COMMENT '更新部门'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '角色权限信息';

alter table sys_role_permission add constraint pk_sys_user_role primary key(pk_id) ;
create index idx_role_permission on sys_role_permission(role_id,permission_id);



-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS sys_resource;
CREATE TABLE sys_resource(
   pk_id BIGINT(11) NOT NULL COMMENT '主键编码',
   parent_id BIGINT(11) NOT NULL DEFAULT 0 COMMENT '主键编码',
   resource_code VARCHAR(10) NOT NULL COMMENT '资源编码',
   resource_name VARCHAR(20) NOT NULL COMMENT '资源编码',
   resource_desc VARCHAR(200) NOT NULL COMMENT '资源描述',
   resource_type TINYINT(1) NOT NULL DEFAULT 0 COMMENT '资源类型,1:菜单,2:按钮',
   resource_url VARCHAR(255) COMMENT '资源路径',
   order_num TINYINT(1) NOT NULL DEFAULT 0 COMMENT '排序序号',
   resource_icon VARCHAR(200) COMMENT '资源图标',
   is_leaf TINYINT(1) default 0 COMMENT '是否叶子节点,1:是   0:不是',
   del_flag  TINYINT(1) DEFAULT 0 COMMENT '删除状态,0:正常,1:已删除',
   create_dt TIMESTAMP COMMENT '创建日期',
   create_by BIGINT(11) COMMENT '创建人',
   create_dept BIGINT(11) COMMENT '创建部门',
   update_dt TIMESTAMP COMMENT '更新日期',
   update_by BIGINT(11) COMMENT '更新人',
   update_dept  BIGINT(11) COMMENT '更新部门'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '资源信息';

alter table sys_resource add constraint pk_sys_resource primary key(pk_id) ;
create unique index uk_sys_resource on sys_resource(resource_code);


DROP TABLE IF EXISTS sys_permission_resource;
CREATE TABLE sys_permission_resource(
   pk_id BIGINT(11) NOT NULL COMMENT '主键编码',
   permission_id BIGINT(11) NOT NULL COMMENT '权限编号',
   resource_id BIGINT(11) NOT NULL COMMENT '资源编号',
   del_flag  TINYINT(1) DEFAULT 0 COMMENT '删除状态,0:正常,1:已删除',
   create_dt TIMESTAMP COMMENT '创建日期',
   create_by BIGINT(11) COMMENT '创建人',
   create_dept BIGINT(11) COMMENT '创建部门',
   update_dt TIMESTAMP COMMENT '更新日期',
   update_by BIGINT(11) COMMENT '更新人',
   update_dept  BIGINT(11) COMMENT '更新部门'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '权限资源信息';

alter table sys_permission_resource add constraint pk_sys_permission_resource primary key(pk_id) ;
create index idx_permission_resource on sys_permission_resource(permission_id,resource_id);

