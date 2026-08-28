CREATE TABLE `goods` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
    `name` varchar(100) NOT NULL COMMENT '商品名称',
    `price` decimal(10,2) NOT NULL COMMENT '价格（元）',
    `stock` int DEFAULT 0 COMMENT '库存',
    `image` varchar(255) DEFAULT '' COMMENT '商品图片',
    `category` varchar(32) DEFAULT '其他' COMMENT '分类',
    `status` tinyint DEFAULT 1 COMMENT '状态：0下架 1上架',
    `description` varchar(255) DEFAULT '' COMMENT '商品描述',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted` tinyint DEFAULT 0,

    INDEX idx_status (`status`),
    INDEX idx_category (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';