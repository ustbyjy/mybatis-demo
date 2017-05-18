CREATE TABLE blog (
  id          INT(11) PRIMARY KEY AUTO_INCREMENT
  COMMENT 'ID',
  u_id        INT(11)      NOT NULL
  COMMENT '用户ID',
  title       VARCHAR(20)  NOT NULL
  COMMENT '标题',
  content     VARCHAR(255) NOT NULL
  COMMENT '内容',
  create_time DATETIME     NOT NULL
  COMMENT '创建时间'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO blog (u_id, title, content, create_time) VALUES (1, '标题1', '内容内容1', '2016-12-03 17:04:00');
INSERT INTO blog (u_id, title, content, create_time) VALUES (1, '标题2', '内容内容2', '2016-12-03 17:04:00');
INSERT INTO blog (u_id, title, content, create_time) VALUES (1, '标题3', '内容内容3', '2016-12-03 17:04:00');

CREATE TABLE user (
  id              INT(11) PRIMARY KEY AUTO_INCREMENT
  COMMENT 'ID',
  username        VARCHAR(20) NOT NULL
  COMMENT '用户名',
  hashed_password VARCHAR(50) NOT NULL
  COMMENT '密码'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO user (username, hashed_password) VALUES ('张三', 'slhdafuwe5f7dy023');

CREATE TABLE address (
  u_id     INT(11)     NOT NULL
  COMMENT '用户ID',
  province VARCHAR(20) NOT NULL
  COMMENT '省份',
  city     VARCHAR(20) NOT NULL
  COMMENT '城市',
  detail   VARCHAR(50) NOT NULL
  COMMENT '详细地址'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO address (u_id, province, city, detail) VALUES (1, '内蒙古', '呼和浩特', '托克托县城关信用社家属楼');


CREATE TABLE `employee` (
  `id`        INT(11)     NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(50) NOT NULL,
  `email`     VARCHAR(50) NOT NULL,
  `gender`    VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;