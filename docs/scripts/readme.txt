进入脚本所在目录:eg,F:\360yunpan\studyData\proData\ump\docs\scripts
执行sqlplus
sqlplus webapp/webapp@ump
sql>@dbinit.sql



@与@@的区别是什么？

@等于start命令，用来运行一个sql脚本文件。

@命令调用当前目录下的，或指定全路径，或可以通过SQLPATH环境变量搜寻到的脚本文件。该命令使用是一般要指定要执行的文件的全路径，否则从缺省路径(可用SQLPATH变量指定)下读取指定的文件。

@@用在sql脚本文件中，用来说明用@@执行的sql脚本文件与@@所在的文件在同一目录下，而不用指定要执行sql脚本文件的全路径，也不是从SQLPATH环境变量指定的路径中寻找sql脚本文件，该命令一般用在脚本文件中。