##快速开始指南

#快速体验
1. 具备运行环境：JDK1.6、Maven3.0、MySql。
2. 修改src\main\resources\application.properties文件中的数据库设置参数。
3. 根据修改参数创建对应MySql数据库。
4. 运行bin\resresh-db\refresh-db.bat脚本，导入表结构及演示数据
5. 运行bin\jetty.bat，启动服务器（第一次运行，需要下载依赖jar包，请耐心等待）。
6. 最高管理员，用户名：admin 密码：admin

#开发指南
1.打开Eclipse集成开发环境，配置JDK和Tomcat，然后依次操作：File --> Import--> Existing Projects in Workspace，然后选择下载openeap项目目录，导入即可。
2.安装m2eclipse插件，然后选择pom.xml，右键--> Run As --> Maven install，下载jar包
3.在tomcat\conf\server.xml中配置： <Context path="/openeap" docBase="E:\xxx\xxx\openeap\src\main\webapp" debug="0"/>，其中xxx为代码目录。

#使用log4jdbc-remix打印生成的SQL：
1.修改application.properties文件,取消下面代码注释，注释掉原有的jdbc.driver和jdbc.url
	jdbc.driver=net.sf.log4jdbc.DriverSpy
	jdbc.url=jdbc:log4jdbc:mysql://127.0.0.1:3306/openeap?useUnicode=true&characterEncoding=utf-8

2.修改log4j.properties文件，取消下面代码注释
	log4j.logger.jdbc.sqltiming=INFO
	
3.重启tomcat