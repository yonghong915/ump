----------------------------------------------
-- Export file for user WEBAPP@ORCL         --
-- Created by fangyh on 2016/4/24, 13:32:10 --
----------------------------------------------
spool ump_db.log
create or replace procedure proc_dropifexists(   
    p_table in varchar2   
) is   
    v_count number(10);   
begin   
   select count(*)   
   into v_count   
   from user_tables   
   where table_name = upper(p_table);   
  
   if v_count > 0 then   
      execute immediate 'drop table ' || p_table ||' purge';   
   end if;   
end proc_dropifexists;
/
spool off