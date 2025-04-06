-- MySQL dump 10.13  Distrib 8.4.2, for Win64 (x86_64)
--
-- Host: localhost    Database: spring
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sg_article`
--

DROP TABLE IF EXISTS `sg_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sg_article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '文章内容',
  `summary` varchar(1024) DEFAULT NULL COMMENT '文章摘要',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类id',
  `thumbnail` varchar(256) DEFAULT NULL COMMENT '缩略图',
  `is_top` char(1) DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` char(1) DEFAULT '1' COMMENT '状态（0已发布，1草稿）',
  `view_count` bigint DEFAULT '0' COMMENT '访问量',
  `is_comment` char(1) DEFAULT '1' COMMENT '是否允许评论 1是，0否',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_article`
--

LOCK TABLES `sg_article` WRITE;
/*!40000 ALTER TABLE `sg_article` DISABLE KEYS */;
INSERT INTO `sg_article` VALUES (1,'走进科普SpringBoot','# SpringBoot你怎么看\n\n由chatGPT生成以下内容，仅做测试\n![请联系编写者](https://n.sinaimg.cn/blog/700/w1000h500/20220325/c84d-a6a4ed629d39ec51d911d70498ccbdbd.jpg)\n\n\nSpringBoot是一个用于快速构建基于Spring框架的应用程序的开发框架。它简化了Spring应用程序的配置和部署过程，让开发者能够更加专注于业务逻辑的实现。特点如下\n\n\n## 简化配置\n\nSpringBoot提供了自动配置的特性，通过约定大于配置的原则，减少了开发者在配置文件中进行大量繁琐的配置。开发者只需要关注自己的业务逻辑，而不用花费过多时间在配置上。\n\n> 内嵌服务器\n\nSpringBoot默认集成了多种内嵌服务器，如Tomcat、Jetty等，开发者无需手动部署应用程序到外部服务器，只需简单地打包成可执行的JAR文件，即可运行应用程序。\n\n> 自动装配\n\nSpringBoot可以根据项目的依赖自动装配相应的组件和配置，大大减少了手动配置的工作量。开发者只需在项目的依赖管理文件中添加相应的依赖，SpringBoot就会自动根据依赖进行配置。\n\n> 简化开发\n\nSpringBoot提供了丰富的开发工具和插件，能够帮助开发者更高效地进行开发。例如，SpringBootDevTools可以实现热部署，修改代码后无需重启应用程序即可生效。\n\n> 使用示例\n\n下面是一个简单的SpringBoot应用程序示例：\n~~~~java\nimport org.springframework.boot.SpringApplication;\nimport org.springframework.boot.autoconfigure.SpringBootApplication;\nimport org.springframework.web.bind.annotation.GetMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@SpringBootApplication@RestControllerpublic class MyApplication {\n      \n    public static void main(String[] args) {\n        SpringApplication.run(MyApplication.class, args);\n    }\n\n    @GetMapping(\"/\")\n    public String hello() {\n        return \"Hello, World!\";\n    }\n}\n~~~~','测试文章',1,'https://n.sinaimg.cn/blog/700/w1000h500/20220325/c84d-a6a4ed629d39ec51d911d70498ccbdbd.jpg','0','0',4,'0',1,'2023-08-11 07:14:55',-1,'2025-04-05 16:22:20',0),(2,'MySQL知识','# mysql介绍\n\n由chatGPT生成以下内容，仅做测试\n\n![请联系编写者](https://n.sinaimg.cn/blog/700/w1000h500/20220325/c84d-a6a4ed629d39ec51d911d70498ccbdbd.jpg)\n\n在当今数据驱动的世界中，学习和掌握数据库管理系统是非常重要的。MySQL作为最流行的开源关系型数据库管理系统之一，为许多应用程序和网站提供了可靠的数据存储和管理。下面是学习MySQL的一些日常步骤和要点，帮助你快速入门和提升技能。\n\n> 1. 安装和配置MySQL\n\n首先，你需要下载并安装MySQL数据库。根据你的操作系统，选择适合的MySQL版本，并按照安装向导进行安装。安装完成后，确保正确配置MySQL以确保正常运行。\n\n> 2. 学习SQL语言\n\nSQL是结构化查询语言，用于与数据库进行交互。学习SQL语言是学习MySQL的关键。了解基本的SQL语法，包括创建表、插入数据、查询数据、更新数据和删除数据等操作。通过练习和实践，逐渐掌握SQL语言的使用。\n\n> 3. 数据库设计和规范化\n\n学习MySQL还需要了解数据库设计和规范化的概念。掌握数据库设计原则和规范化技术，以确保数据库结构的合理性和数据的完整性。了解主键、外键、索引等概念，并学会在数据库中应用它们。\n\n> 4. 数据库管理和维护\n\n学习MySQL还包括数据库管理和维护的技能。了解如何备份和恢复数据库，如何优化查询性能，如何监控和管理数据库服务器等。学习并掌握MySQL提供的管理工具和命令，以便有效地管理和维护数据库。\n\n> 5. 学习高级特性和扩展\n\n除了基本的SQL语言和数据库管理技能，学习MySQL的过程中还可以深入了解一些高级特性和扩展。例如，学习存储过程、触发器和视图等高级功能，以及如何使用MySQL与编程语言进行集成。\n\n> 6. 参考文档和资源\n\n学习MySQL的过程中，及时查阅官方文档和其他相关资源是非常重要的。MySQL官方文档提供了详细的说明和示例，可以帮助你理解和掌握MySQL的各种功能和用法。此外，还可以参考在线教程、书籍和社区论坛等资源，与其他MySQL学习者交流和分享经验。\n\n学习MySQL需要时间和实践，但随着不断的学习和实践，你将逐渐掌握MySQL的技能，并能够灵活运用它来处理各种数据管理任务。希望以上步骤和要点能帮助你在学习MySQL的道路上取得成功！','测试文章',2,'https://n.sinaimg.cn/blog/700/w1000h500/20220325/c84d-a6a4ed629d39ec51d911d70498ccbdbd.jpg','1','0',190,'0',1,'2023-08-11 07:32:41',-1,'2025-04-05 16:22:20',0),(3,'科普知识','# 科普知识\n\n由chatGPT生成以下内容，仅做测试\n\n![请联系编写者](https://n.sinaimg.cn/blog/700/w1000h500/20220325/c84d-a6a4ed629d39ec51d911d70498ccbdbd.jpg)\n\n> 养成和训练人体记忆力的重要性\n\n人体的记忆力是我们日常生活中非常重要的一项能力。它不仅有助于我们记住重要的信息和经历，还能够帮助我们提高学习效率和解决问题的能力。然而，很多人可能发现自己的记忆力并不是很好，经常会忘记一些重要的事情。\n\n> 良好的生活习惯\n\n良好的生活习惯对于记忆力的提升非常重要。保持充足的睡眠和合理的饮食可以帮助大脑更好地运转，提高记忆力。此外，定期进行体育锻炼也能够促进血液循环，增加大脑的供氧量，有助于记忆力的提升。\n\n> 记忆训练\n\n我们可以通过一些特定的记忆训练来提高记忆力。例如，使用记忆宫殿法可以帮助我们记住一系列的事物。这种方法将要记忆的事物与一个已知的地点或者房间联系起来，通过想象在这个地点或者房间中进行一次虚拟的旅行，将事物与地点进行关联，可以帮助我们更好地记住它们。\n\n> 阅读和学习\n\n阅读可以帮助我们锻炼大脑的思维能力和逻辑思维能力，而学习新知识可以激发大脑的学习能力，提高记忆力。\n\n> 记忆技巧\n\n我们还可以通过一些记忆技巧来提高记忆力。例如，使用关键词、图像和联想等方法可以帮助我们更好地记忆信息。另外，将要记忆的信息进行分类整理，可以帮助我们更好地记忆和回忆。\n\n养成和训练人体记忆力是一个需要长期坚持和努力的过程。通过良好的生活习惯、记忆训练、阅读学习和记忆技巧的综合应用，我们可以提高自己的记忆力，更好地应对日常生活和工作中的各种挑战','测试文章',5,'https://n.sinaimg.cn/blog/700/w1000h500/20220325/c84d-a6a4ed629d39ec51d911d70498ccbdbd.jpg','1','0',8,'0',1,'2023-08-11 08:28:06',-1,'2025-04-05 16:22:20',0),(6,'test','test修改','test',5,'https://test-for-tree.oss-cn-beijing.aliyuncs.com/95df1ecb.jpg','1','0',0,'1',1,'2025-04-05 15:23:07',1,'2025-04-05 20:28:45',1),(7,'深入理解 Java Web 中 HttpServletRes','**一、Web 容器的核心作用**\n在 Java Web 体系中，像 Tomcat、Jetty 这样的 Web 容器，扮演着请求处理和响应分发的核心角色。当客户端，如浏览器，向服务器发起 HTTP 请求时，请求首先抵达 Web 容器。\n\nWeb 容器迅速创建HttpServletRequest对象，该对象负责封装请求的所有相关信息，包括请求头、请求参数等；与此同时，Web 容器还会创建HttpServletResponse对象，用于处理即将返回给客户端的响应。\n在创建这两个对象时，Web 容器会巧妙地维护它们之间的关联关系，同时通过网络连接信息，如客户端的 IP 地址和端口号，精准识别请求的来源。\n\n**二、响应设置与发送过程**\n当开发人员在代码中操作HttpServletResponse对象，进行响应设置时，一系列关键步骤会有序展开。开发人员通过HttpServletResponse对象设置状态码、内容类型，并写入响应内容。比如使用response.setStatus(200)设置 HTTP 状态码为 200，表示请求成功处理；通过response.setContentType(\"application/json\")指定响应内容为 JSON 格式；使用response.getWriter().print(string)将响应内容写入输出流。\n一旦完成这些设置，Web 容器便会依据其内部的复杂机制以及之前维护的连接信息，将填充好的响应发送回发起请求的客户端。\n\n**三、实际应用场景示例**\n以浏览器向服务器发起请求为例，当用户在浏览器地址栏输入 URL 并回车后，浏览器会向服务器发送 HTTP 请求。Web 容器接收到请求，在处理过程中，会调用对应的 Servlet 或控制器方法。\n\n在这些方法中，开发人员对HttpServletResponse对象进行操作，完成响应内容的构建和设置。处理完成后，Web 容器负责将响应数据通过网络连接发送回浏览器。浏览器接收到响应后，根据响应内容进行显示或其他处理，如解析 JSON 数据并更新页面。\n\n**四、总结**\n在 Java Web 开发中，HttpServletResponse本身无需显式指定发送的目标地址。Web 容器凭借其强大的功能和对请求来源信息的追踪，能够准确无误地将响应发送回客户端。理解这一机制，有助于开发人员优化 Web 应用的性能，提升用户体验，并解决可能出现的各种问题。',NULL,5,'','1','0',0,'0',1,'2025-04-05 16:44:39',1,'2025-04-05 16:44:39',0);
/*!40000 ALTER TABLE `sg_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_article_tag`
--

DROP TABLE IF EXISTS `sg_article_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sg_article_tag` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `tag_id` bigint NOT NULL DEFAULT '0' COMMENT '标签id',
  PRIMARY KEY (`article_id`,`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章标签关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_article_tag`
--

LOCK TABLES `sg_article_tag` WRITE;
/*!40000 ALTER TABLE `sg_article_tag` DISABLE KEYS */;
INSERT INTO `sg_article_tag` VALUES (1,3),(2,3),(3,3),(6,3),(7,2);
/*!40000 ALTER TABLE `sg_article_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_category`
--

DROP TABLE IF EXISTS `sg_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sg_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '分类名',
  `pid` bigint DEFAULT '-1' COMMENT '父分类id，如果没有父分类为-1',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `status` char(1) DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_category`
--

LOCK TABLES `sg_category` WRITE;
/*!40000 ALTER TABLE `sg_category` DISABLE KEYS */;
INSERT INTO `sg_category` VALUES (1,'SpringBoot',-1,'我是描述','0',1,'2023-08-11 06:45:55',1,'2023-08-11 06:46:42',0),(2,'MySQL',-1,'我是描述','0',1,'2023-08-11 06:46:36',1,'2023-08-11 06:46:36',0),(3,'Web',-1,'我是描述','0',1,'2023-08-11 06:46:54',1,'2023-08-11 06:46:54',0),(4,'MyBatis',-1,'我是描述','0',1,'2023-08-11 06:47:17',1,'2023-08-11 06:47:17',0),(5,'day',-1,'我是描述','0',1,'2023-08-11 06:48:04',1,'2025-04-05 16:30:34',0),(6,'分类002',-1,'我是描述','0',1,'2023-08-11 06:48:18',1,'2025-04-05 20:35:45',1),(7,'分类003',-1,'我是描述','0',1,'2023-08-11 06:48:31',1,'2025-04-05 20:35:45',1),(8,'分类004',-1,'我是描述','0',1,'2023-08-11 06:48:49',1,'2025-04-05 16:29:06',1),(9,'2134',-1,'12','0',1,'2025-04-05 16:26:13',1,'2025-04-05 16:29:02',1);
/*!40000 ALTER TABLE `sg_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_comment`
--

DROP TABLE IF EXISTS `sg_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sg_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` char(1) DEFAULT '0' COMMENT '评论类型（0代表文章评论，1代表友链评论）',
  `article_id` bigint DEFAULT NULL COMMENT '文章id',
  `root_id` bigint DEFAULT '-1' COMMENT '根评论id',
  `content` varchar(512) DEFAULT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint DEFAULT '-1' COMMENT '所回复的目标评论的userid',
  `to_comment_id` bigint DEFAULT '-1' COMMENT '回复目标评论id',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_comment`
--

LOCK TABLES `sg_comment` WRITE;
/*!40000 ALTER TABLE `sg_comment` DISABLE KEYS */;
INSERT INTO `sg_comment` VALUES (1,'0',2,-1,'测试时间',-1,-1,2,'2023-10-26 08:45:13',2,'2023-10-26 08:45:13',0),(2,'0',2,1,'测试时间',2,1,2,'2023-10-26 16:46:35',2,'2023-10-26 16:46:35',0),(3,'0',3,-1,'第二次测试时间',-1,-1,2,'2023-10-26 16:46:53',2,'2023-10-26 16:46:53',0),(4,'0',2,-1,'1',-1,-1,2,'2023-11-27 14:29:00',2,'2023-11-27 14:29:00',0),(5,'0',2,4,'1',2,4,2,'2023-11-27 14:29:07',2,'2023-11-27 14:29:07',0),(7,'0',2,-1,'测试',-1,-1,1,'2025-03-19 22:52:31',1,'2025-03-19 22:52:31',0),(8,'0',2,7,'测试回复',1,7,1,'2025-03-19 22:52:52',1,'2025-03-19 22:52:52',0),(9,'1',1,-1,'测试',-1,-1,1,'2025-03-20 16:57:04',1,'2025-03-20 16:57:04',0);
/*!40000 ALTER TABLE `sg_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_link`
--

DROP TABLE IF EXISTS `sg_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sg_link` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `logo` varchar(256) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL COMMENT '网站地址',
  `status` char(1) DEFAULT '2' COMMENT '审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='友链';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_link`
--

LOCK TABLES `sg_link` WRITE;
/*!40000 ALTER TABLE `sg_link` DISABLE KEYS */;
INSERT INTO `sg_link` VALUES (1,'百度','https://img0.baidu.com/it/u=4029604882,246120315&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=285','国内搜索引擎','https://baidu.com','0',1,'2023-08-11 06:50:11',1,'2023-08-11 06:51:37',0),(2,'bilibili','https://img2.baidu.com/it/u=3444794441,3761663944&fm=253&fmt=auto&app=120&f=JPEG?w=669&h=604','某去二次元化同性交友平台','https://www.bilibili.com','0',1,'2023-08-11 06:53:46',1,'2023-08-11 06:59:50',0),(3,'GitHub','https://img1.baidu.com/it/u=2626381969,3909549426&fm=253&fmt=auto&app=138&f=JPEG?w=588&h=401','代码托管平台','https://github.com','0',1,'2023-08-11 07:01:54',1,'2023-08-11 07:01:54',0),(4,'阿里云','https://img1.baidu.com/it/u=1459264756,3043818066&fm=253&fmt=auto&app=120&f=PNG?w=649&h=500','服务器云服务--能否給點打折','https://www.aliyun.com','0',1,'2023-08-11 07:04:01',1,'2025-04-05 22:57:18',0),(5,'啦啦啦','dsaf','adf','asdf','1',1,'2025-04-05 22:57:33',1,'2025-04-05 22:57:43',1);
/*!40000 ALTER TABLE `sg_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sg_tag`
--

DROP TABLE IF EXISTS `sg_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sg_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '标签名',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sg_tag`
--

LOCK TABLES `sg_tag` WRITE;
/*!40000 ALTER TABLE `sg_tag` DISABLE KEYS */;
INSERT INTO `sg_tag` VALUES (1,'日志',1,'2023-08-11 07:05:51',1,'2023-08-11 07:05:51',0,'我是描述'),(2,'开发',1,'2023-08-11 07:06:08',1,'2023-08-11 07:06:08',0,'我是描述'),(3,'日常',1,'2023-08-11 07:07:54',1,'2023-08-11 07:07:54',0,'我是描述'),(4,'管理',1,'2023-08-11 07:08:04',1,'2023-08-11 07:08:04',0,'我是描述'),(5,'办公',1,'2023-08-11 07:08:18',1,'2023-08-11 07:08:18',0,'我是描述'),(6,'笔记',1,'2023-08-11 07:08:26',1,'2023-08-11 07:08:26',0,'我是描述'),(7,'学校',1,'2023-08-11 07:09:06',1,'2023-08-11 07:09:06',0,'我是描述'),(8,'美食',1,'2023-08-11 07:09:20',1,'2023-08-11 07:09:20',0,'我是描述'),(9,'学习',1,'2023-08-11 07:10:09',1,'2023-08-11 07:10:09',0,'我是描述'),(10,'风景',1,'2023-08-11 07:10:32',1,'2023-08-11 07:10:32',0,'我是描述'),(11,'你好',1,'2025-03-26 19:27:30',NULL,'2025-04-05 00:33:25',1,'asd'),(12,'12',1,'2025-04-05 01:32:55',NULL,'2025-04-05 01:33:18',0,'启动');
/*!40000 ALTER TABLE `sg_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2032 DEFAULT CHARSET=utf8mb3 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,1,'M','0','0','','system',0,'2021-11-12 10:46:19',0,NULL,'系统管理目录','0'),(100,'用户管理',1,1,'user','system/user/index',1,'C','0','0','system:user:list','user',0,'2021-11-12 10:46:19',1,'2023-08-10 06:17:35','用户管理菜单','0'),(101,'角色管理',1,2,'role','system/role/index',1,'C','0','0','system:role:list','peoples',0,'2021-11-12 10:46:19',0,NULL,'角色管理菜单','0'),(102,'菜单管理',1,3,'menu','system/menu/index',1,'C','0','0','system:menu:list','tree-table',0,'2021-11-12 10:46:19',0,NULL,'菜单管理菜单','0'),(1001,'用户查询',100,1,'','',1,'F','0','0','system:user:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1002,'用户新增',100,2,'','',1,'F','0','0','system:user:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1003,'用户修改',100,3,'','',1,'F','0','0','system:user:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1004,'用户删除',100,4,'','',1,'F','0','0','system:user:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1005,'用户导出',100,5,'','',1,'F','0','0','system:user:export','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1006,'用户导入',100,6,'','',1,'F','0','0','system:user:import','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1007,'重置密码',100,7,'','',1,'F','0','0','system:user:resetPwd','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1008,'角色查询',101,1,'','',1,'F','0','0','system:role:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1009,'角色新增',101,2,'','',1,'F','0','0','system:role:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1010,'角色修改',101,3,'','',1,'F','0','0','system:role:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1011,'角色删除',101,4,'','',1,'F','0','0','system:role:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1012,'角色导出',101,5,'','',1,'F','0','0','system:role:export','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1013,'菜单查询',102,1,'','',1,'F','0','0','system:menu:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1014,'菜单新增',102,2,'','',1,'F','0','0','system:menu:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1015,'菜单修改',102,3,'','',1,'F','0','0','system:menu:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1016,'菜单删除',102,4,'','',1,'F','0','0','system:menu:remove','#',0,'2021-11-12 10:46:19',1,'2025-04-05 20:51:13','','0'),(2017,'内容管理',0,4,'content',NULL,1,'M','0','0',NULL,'table',NULL,'2022-01-08 02:44:38',1,'2022-07-31 12:34:23','','0'),(2018,'分类管理',2017,1,'category','content/category/index',1,'C','0','0','content:category:list','example',NULL,'2022-01-08 02:51:45',NULL,'2022-01-08 02:51:45','','0'),(2019,'文章管理',2017,0,'article','content/article/index',1,'C','0','0','content:article:list','build',NULL,'2022-01-08 02:53:10',NULL,'2022-01-08 02:53:10','','0'),(2021,'标签管理',2017,6,'tag','content/tag/index',1,'C','0','0','content:tag:index','button',NULL,'2022-01-08 02:55:37',NULL,'2022-01-08 02:55:50','','0'),(2022,'友链管理',2017,4,'link','content/link/index',1,'C','0','0','content:link:list','404',NULL,'2022-01-08 02:56:50',NULL,'2022-01-08 02:56:50','','0'),(2023,'写博文',0,0,'write','content/article/write/index',1,'C','0','0','content:article:writer','build',NULL,'2022-01-08 03:39:58',1,'2022-07-31 22:07:05','','0'),(2024,'友链新增',2022,0,'',NULL,1,'F','0','0','content:link:add','#',NULL,'2022-01-16 07:59:17',NULL,'2022-01-16 07:59:17','','0'),(2025,'友链修改',2022,1,'',NULL,1,'F','0','0','content:link:edit','#',NULL,'2022-01-16 07:59:44',NULL,'2022-01-16 07:59:44','','0'),(2026,'友链删除',2022,1,'',NULL,1,'F','0','0','content:link:remove','#',NULL,'2022-01-16 08:00:05',NULL,'2022-01-16 08:00:05','','0'),(2027,'友链查询',2022,2,'',NULL,1,'F','0','0','content:link:query','#',NULL,'2022-01-16 08:04:09',NULL,'2022-01-16 08:04:09','','0'),(2028,'导出分类',2018,1,'',NULL,1,'F','0','0','content:category:export','#',NULL,'2022-01-21 07:06:59',NULL,'2022-01-21 07:06:59','','0'),(2030,'1',0,0,'h',NULL,1,'M','0','0',NULL,'#',1,'2025-04-05 20:52:20',1,'2025-04-05 20:52:51','','1'),(2031,'2',2030,2,'2',NULL,1,'M','0','0',NULL,'#',1,'2025-04-05 20:52:40',1,'2025-04-05 20:52:48','','1');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,'0','0',0,'2023-07-12 07:46:19',0,NULL,'超级管理员'),(2,'普通角色','common',2,'0','0',0,'2023-08-10 19:46:19',4,'2025-04-05 22:13:06','普通角色'),(3,'开发者','system:build:test',3,'0','0',1,'2023-08-10 08:12:03',1,'2023-08-10 09:05:47','开发者'),(4,'原神','3',3,'0','1',4,'2025-04-05 22:10:37',4,'2025-04-05 22:11:58',NULL),(5,'1','1',0,'0','1',4,'2025-04-05 22:12:26',4,'2025-04-05 22:12:29',NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (0,0),(2,2017),(2,2018),(2,2019),(2,2021),(2,2022),(2,2023),(2,2024),(2,2025),(2,2026),(2,2027),(2,2028),(4,1),(4,100),(4,101),(4,1001),(4,1002),(4,1003),(4,1004),(4,1005),(4,1006),(4,1007),(4,1008),(4,1009),(4,1010),(4,1011),(4,1012),(4,2023),(5,2023);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `type` char(1) DEFAULT '0' COMMENT '用户类型：0代表普通用户，1代表管理员',
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `create_by` bigint DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'tree','二叉树','$2a$10$VcIamfDZIvkRP1JJZKYAHOZpsb4Z3LZptJACS9wur9mZoOpTMpsAO','1','0','228675@qq.com','18888888888','1','https://test-for-tree.oss-cn-beijing.aliyuncs.com/e6a6e4dc.png',NULL,'2023-07-21 13:07:35',1,'2023-07-22 13:08:04',0),(2,'HFuser','测试用户','$2a$10$Cjxu8UwfmUYvgzy7VJexke3suuKNM9bwy8ENHj4UEzBmMZX5p.OBm','0','0','123@huanfqc.cn','12093473451','0','https://img1.baidu.com/it/u=1333417867,4012964063&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500',NULL,'2023-07-13 13:07:28',NULL,'2023-12-25 13:55:04',0),(4,'lcc','lcc是大作家','$2a$10$2fN2VV7oL0JMLu/XfqLAAuDJJt3A2N4HVlzkIwNDQp1rZHca0Pv1G','0','0','3091701066@qq.com','','0','https://test-for-tree.oss-cn-beijing.aliyuncs.com/e9fefc96.png',NULL,NULL,1,'2025-04-05 22:49:33',0),(5,'HYL','傻兒子','$2a$10$8MjFBaj2uhxSVurcGQHYHepuyK4fH2lZeEJPeyevJYOyX9Dr73AK2','0','0',NULL,NULL,'2',NULL,1,'2025-04-05 22:49:12',1,'2025-04-05 22:49:12',0),(6,'1123','1','$2a$10$OyaHuqpjqDz87Lohm8KrlOVYU.8kSKnd8qNYoiRwvfd1p83czYEMa','0','0',NULL,NULL,NULL,NULL,1,'2025-04-05 22:49:52',1,'2025-04-05 22:50:03',1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(2,2),(4,2),(5,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_book`
--

DROP TABLE IF EXISTS `t_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_book` (
  `book_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `book_name` varchar(20) DEFAULT NULL COMMENT '图书名称',
  `price` int DEFAULT NULL COMMENT '价格',
  `stock` int unsigned DEFAULT NULL COMMENT '库存（无符号）',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book`
--

LOCK TABLES `t_book` WRITE;
/*!40000 ALTER TABLE `t_book` DISABLE KEYS */;
INSERT INTO `t_book` VALUES (1,'斗破苍穹',80,98),(2,'罗大陸',50,100);
/*!40000 ALTER TABLE `t_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_emp`
--

DROP TABLE IF EXISTS `t_emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_emp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_emp`
--

LOCK TABLES `t_emp` WRITE;
/*!40000 ALTER TABLE `t_emp` DISABLE KEYS */;
INSERT INTO `t_emp` VALUES (1,'nai',10,'1'),(2,'long',10,'2'),(3,'niuzi',30,'2'),(5,'????????',30,'2'),(6,'��������',30,'2'),(7,'��������',30,'2');
/*!40000 ALTER TABLE `t_emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `balance` int unsigned DEFAULT NULL COMMENT '余额（无符号）',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin',340);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-05 23:08:02
