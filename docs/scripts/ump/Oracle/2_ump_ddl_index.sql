--------------------------
---
---create index
--------------------------
spool ump_db.log append
create index sysuser_username_idx on sys_user(user_name) tablespace mydata_index;
create index bizaddress_userid_idx on biz_address(user_id) tablespace mydata_index;


spool off