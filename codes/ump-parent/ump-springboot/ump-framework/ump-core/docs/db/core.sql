SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS sys_log;
CREATE TABLE sys_log(
   pk_id BIGINT(11) NOT NULL COMMENT '主键编码',
   log_type int(2) default NULL COMMENT '日志类型（1登录日志，2操作日志）',
   log_content varchar(1000) default NULL COMMENT '日志内容',
   operate_type int(2) default NULL COMMENT '操作类型',
   ip varchar(100) default NULL COMMENT 'IP',
   method varchar(500) default NULL COMMENT '请求java方法',
   request_url varchar(255) default NULL COMMENT '请求路径',
   request_param varchar(255) default NULL COMMENT '请求参数',
   request_type varchar(10) default NULL COMMENT '请求类型',
   cost_time bigint(20) default NULL COMMENT '耗时',
   del_flag  TINYINT(1) default 0 COMMENT '删除状态,0:正常,1:已删除',
   create_dt DATETIME COMMENT '创建日期',
   create_by BIGINT(11) COMMENT '创建人',
   update_dt DATETIME COMMENT '更新日期',
   update_by BIGINT(11) COMMENT '更新人'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT '系统日志';

alter table sys_log add constraint pk_sys_log primary key(pk_id) ;
