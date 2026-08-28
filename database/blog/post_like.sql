CREATE TABLE `post_like` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `post_id` bigint NOT NULL COMMENT '帖子ID',
    `user_id` bigint NOT NULL COMMENT '点赞用户ID',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',

    UNIQUE KEY `uk_post_user` (`post_id`, `user_id`),
    INDEX idx_post_id (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子点赞记录表';