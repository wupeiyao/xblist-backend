CREATE DATABASE IF NOT EXISTS todo;
USE todo;

CREATE TABLE USER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(8) UNIQUE NOT NULL,
    email VARCHAR(255) NOT  NULL,
    phone VARCHAR(50) UNIQUE ,
    sex VARCHAR(10),
    PASSWORD VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(255),
    birthday DATETIME,
    address VARCHAR(255),
    signature TEXT,
    university VARCHAR(255),
    secondary_school VARCHAR(255),
    elementary_school VARCHAR(255),
    company VARCHAR(255),
    introduce TEXT,
    major VARCHAR(255),
    role VARCHAR(50)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;



INSERT INTO USER (
    username,
    email,
    phone,
    sex,
    PASSWORD,
    birthday,
    role
) VALUES (
    'admin',
    '2034037932@qq.com',
    '1234567890',
    'Male',
    'E10ADC3949BA59ABBE56E057F20F883E', -- 注意：实际存储时应该存储密码的哈希值
    '2024-01-01 00:00:00',
    'admin'
);

CREATE TABLE task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    task_content TEXT NOT NULL,
    task_title VARCHAR(255) NOT NULL,
    category_id INT,
    finish_time DATETIME,
    STATUS INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_important TINYINT(1) DEFAULT 0
);

CREATE TABLE `task_label` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `user_id` BIGINT(20) NOT NULL COMMENT '用户id',
  `task_id` BIGINT(20) DEFAULT NULL COMMENT '任务id',
  `name` VARCHAR(255) NOT NULL COMMENT '标签名称',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `run` TINYINT(1) DEFAULT '1' COMMENT '是否还在使用（0：已删除，1：正常）',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

CREATE TABLE `group_x` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    description TEXT,
    category_id BIGINT,
    STATUS INT DEFAULT 0, -- 假设默认状态为0，具体值根据实际情况调整
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 假设创建时间默认为当前时间
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 更新时间默认为当前时间，并在每次更新记录时自动更新
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE Task_group (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    task_id BIGINT NOT NULL,
    group_id BIGINT NOT NULL,
    task_name VARCHAR(255) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE Category (
    id BIGINT NOT NULL AUTO_INCREMENT,
    `cname` VARCHAR(255) NOT NULL,
    create_time DATETIME NOT NULL,
    update_time DATETIME NOT NULL,
    PRIMARY KEY (id)
);
