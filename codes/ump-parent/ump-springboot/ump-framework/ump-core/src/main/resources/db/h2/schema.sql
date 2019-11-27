DROP TABLE IF EXISTS WORKER_NODE;
CREATE TABLE WORKER_NODE(
ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
HOST_NAME VARCHAR(64) NOT NULL COMMENT 'host name',
PORT VARCHAR(64) NOT NULL COMMENT 'port',
TYPE INT NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
LAUNCH_DATE DATE NOT NULL COMMENT 'launch date',
MODIFIED TIMESTAMP NOT NULL COMMENT 'modified time',
CREATED TIMESTAMP NOT NULL COMMENT 'created time',
PRIMARY KEY(ID)
);

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
)