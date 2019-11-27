#!/bin/bash
sqlplus -s scott/tiger << EOF
set feedback off;
set pagesize 0;
select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') from dual;
EOF