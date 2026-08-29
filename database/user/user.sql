CREATE TABLE `user` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID（主键）',
    `no` varchar(32) NOT NULL COMMENT '用户账号（注册时随机生成，用于登录）',
    `password` varchar(255) NOT NULL COMMENT '密码（BCrypt加密存储）',
    `phone` varchar(11) NOT NULL COMMENT '手机号（可用于登录）',
    `nickname` varchar(64) DEFAULT '' COMMENT '昵称（注册后自动生成为：花友+账号）',
    `avatar` varchar(255) DEFAULT '' COMMENT '头像URL',
    `role` tinyint DEFAULT 2 COMMENT '角色：0超级管理员 1管理员 2普通用户',
    `status` tinyint DEFAULT 1 COMMENT '账号状态：1正常 2冻结',
    `deleted` tinyint DEFAULT 0 COMMENT '软删除：0未删除 1已删除',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    UNIQUE KEY `uk_no` (`no`),
    UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

ALTER TABLE `user`
ADD COLUMN `gender` tinyint DEFAULT 0 COMMENT '性别：0未知 1男 2女' AFTER `avatar`,
ADD COLUMN `birthday` date DEFAULT NULL COMMENT '出生日期（系统自动更新年龄）' AFTER `gender`;