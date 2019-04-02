# springboot-blog
基于springboot的简单个人博客

前端就是很简单的纯HTML,CSS,Jquery.

后端使用了springboot，mybatis，mysql

想看效果只需在properties文件中配置好数据库和密码
<br>
<br>
<br>
<br>
建表语句

CREATE TABLE `POSTS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `content` text,
  `createTime` datetime DEFAULT NULL,
  `views` int(8) DEFAULT NULL,
  `stars` int(8) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
