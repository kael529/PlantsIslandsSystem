CREATE TABLE `post_favorite` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `post_id` bigint NOT NULL COMMENT '帖子ID',
    `user_id` bigint NOT NULL COMMENT '收藏用户ID',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',

    UNIQUE KEY `uk_post_user` (`post_id`, `user_id`),
    -- 查“我的收藏”时用
    INDEX idx_user_id (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子收藏记录表';