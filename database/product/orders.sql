CREATE TABLE `orders` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    `order_no` varchar(32) NOT NULL COMMENT '订单编号（如：ORD20260828001）',
    `user_id` bigint NOT NULL COMMENT '下单用户ID',
    `status` tinyint DEFAULT 0 COMMENT '状态：0待付款 1待发货 2已发货 3已收货 4已取消 5已删除',
    `amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
    `receiver_name` varchar(64) DEFAULT '' COMMENT '收货人',
    `receiver_phone` varchar(20) DEFAULT '' COMMENT '收货手机号',
    `receiver_address` varchar(255) DEFAULT '' COMMENT '收货地址',
    `remark` varchar(255) DEFAULT '' COMMENT '买家备注',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted` tinyint DEFAULT 0,

    UNIQUE KEY `uk_order_no` (`order_no`),
    INDEX idx_user_id (`user_id`),
    INDEX idx_status (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';