CREATE TABLE `post` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '帖子ID',
    `user_id` bigint NOT NULL COMMENT '发帖用户ID',
    `title` varchar(100) NOT NULL COMMENT '帖子标题',
    `content` text NOT NULL COMMENT '帖子内容',
    `category` varchar(32) DEFAULT '其他' COMMENT '分类（养花心得/求助/晒图/其他）',
    `images` varchar(500) DEFAULT '' COMMENT '图片URL列表（多个用逗号分隔）',
    `status` tinyint DEFAULT 0 COMMENT '状态：0待审核 1已发布 2已驳回 3已删除',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` tinyint DEFAULT 0 COMMENT '软删除',

    INDEX idx_user_id (`user_id`),
    INDEX idx_status (`status`),
    INDEX idx_create_time (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子表';