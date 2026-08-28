CREATE TABLE `comment` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '评论ID',
    `post_id` bigint NOT NULL COMMENT '所属帖子ID',
    `user_id` bigint NOT NULL COMMENT '评论用户ID',
    `parent_id` bigint DEFAULT NULL COMMENT '父评论ID（NULL表示一级评论）',
    `content` varchar(500) NOT NULL COMMENT '评论内容',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` tinyint DEFAULT 0 COMMENT '软删除',

    INDEX idx_post_id (`post_id`),
    INDEX idx_user_id (`user_id`),
    INDEX idx_parent_id (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';