CREATE TABLE `order_item` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '订单项ID',
    `order_id` bigint NOT NULL COMMENT '所属订单ID',
    `goods_id` bigint NOT NULL COMMENT '商品ID',
    `goods_name` varchar(100) NOT NULL COMMENT '商品名称（快照）',
    `price` decimal(10,2) NOT NULL COMMENT '购买时单价（快照）',
    `quantity` int NOT NULL COMMENT '购买数量',
    `total_price` decimal(10,2) GENERATED ALWAYS AS (price * quantity) STORED COMMENT '小计（自动计算）',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_order_id (`order_id`),
    INDEX idx_goods_id (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单项表';