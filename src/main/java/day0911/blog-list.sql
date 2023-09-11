create table `blog-list`
(
    id                 int auto_increment
        primary key,
    author             varchar(32)   null,
    title              varchar(128)  null,
    `publish-time`     datetime      null,
    `last-update-time` datetime      null,
    content            text          null,
    view               int default 0 null,
    constraint `blog-list_pk`
        unique (id)
);

INSERT INTO db1.`blog-list` (author, title, `publish-time`, `last-update-time`, content, view)
VALUES ('admin', 'jsp结合了html与java', '2023-09-11 11:28:47', '2023-09-11 11:28:53',
'##### 1.jsp的两种输出方式：<% %>标签 与<%= %>标签
![img_1.png](img/img_1.png)
##### 2.jsp文件转化成java1与class文件可以在以下路径找到
![img_2.png](img/img_2.png)
到达指定路径后继续往下 例如：
\\work\\Catalina\\localhost\\zszdmzmm_web2_war\\org\\apache\\jsp\\jsp
##### 3.jsp本质上是servlet,其转化方式如下。
![img.png](img/img.png)
##### 4.<%! %>标签：将state和behavior写在_jspService()方法之外
![img_3.png](img/img_3.png)
可以看到：红框中的代码都在_jspService()方法之外。
##### 5.通过循环在页面上添加元素
切记java与html的代码各司其职：
![img_4.png](img/img_4.png)', DEFAULT);

INSERT INTO db1.`blog-list` (author, title, `publish-time`, `last-update-time`, content, view)
VALUES ('admin', 'druid.properties', '2023-09-11 11:34:32', '2023-09-11 11:34:36', 'url=jdbc:mysql://localhost:3306/db1?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false
driverClassName=com.mysql.cj.jdbc.Driver
username=root
password=0521

#
initialSize=3

#
maxActive=10

#
minIdle=2', DEFAULT);

INSERT INTO db1.`blog-list` (author, title, `publish-time`, `last-update-time`, content, view)
VALUES ('admin', 'web.xml', '2023-09-11 11:36:07', '2023-09-11 11:35:56', '<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
  <context-param>
    <param-name>connector</param-name>
    <param-value>mybatis</param-value>
  </context-param>
  <filter>
    <filter-name>FirstFilter</filter-name>
    <filter-class>day0831.FirstFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>FirstFilter</filter-name>
    <url-pattern>/FilterOrderServlet</url-pattern>
  </filter-mapping>
  <filter-mapping>
    <filter-name>SecondFilter</filter-name>
    <url-pattern>/FilterOrderServlet</url-pattern>
  </filter-mapping>
</web-app>
', DEFAULT);