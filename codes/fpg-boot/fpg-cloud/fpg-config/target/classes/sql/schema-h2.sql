DROP TABLE IF EXISTS properties;
 CREATE TABLE properties (
   id INT(11) NOT NULL,
   application VARCHAR(50) NOT NULL COMMENT '应用名称',
   profile VARCHAR(50) NOT NULL COMMENT '应用环境',
   label VARCHAR(50) NOT NULL COMMENT '应用模块',
   akey VARCHAR(50) NOT NULL,
   avalue VARCHAR(500) NOT NULL,
   PRIMARY KEY (id)
 ) ;


DROP TABLE IF EXISTS common_config_properties;
CREATE TABLE common_config_properties(
   id BIGINT(20) NOT NULL,
   application_name VARCHAR(255) NOT NULL COMMENT '应用名称',
   config_profile VARCHAR(255) NOT NULL COMMENT '应用环境',
   config_label VARCHAR(255) NOT NULL COMMENT '应用模块',
   config_key VARCHAR(50) NOT NULL,
   config_value VARCHAR(500) NOT NULL,
   PRIMARY KEY (id)
 ) ;
