CREATE TABLE `plant` (
    -- ========== 1. 主键 ==========
    `id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '植物ID（同时也是模型训练的类别ID）',

    -- ========== 2. 基础信息 ==========
    `name` varchar(64) NOT NULL COMMENT '中文正式名称（如：绿萝）',
    `alias` varchar(128) DEFAULT '' COMMENT '别名（如：黄金葛、魔鬼藤）',
    `scientific_name` varchar(128) DEFAULT '' COMMENT '学名/拉丁名（如：Epipremnum aureum）',
    `family` varchar(64) DEFAULT '' COMMENT '科（如：天南星科）',
    `genus` varchar(64) DEFAULT '' COMMENT '属（如：麒麟叶属）',
    `category` varchar(64) DEFAULT '' COMMENT '观赏分类（如：观叶植物/观花植物/多肉植物/香草植物）',
    `image` varchar(255) DEFAULT '' COMMENT '植物展示图片URL',

    -- ========== 3. 养护参数（8个维度） ==========
    `difficulty` tinyint DEFAULT 1 COMMENT '养护难度：1简单 2中等 3困难',
    `light` varchar(64) DEFAULT '' COMMENT '光照需求（如：散射光/充足阳光/耐阴）',
    `water` varchar(64) DEFAULT '' COMMENT '浇水频率（如：见干见湿/干透浇透/保持湿润）',
    `temperature` varchar(64) DEFAULT '' COMMENT '适宜温度（如：15-28℃）',
    `soil` varchar(128) DEFAULT '' COMMENT '土壤要求（如：疏松肥沃的沙质壤土）',
    `fertilizer` varchar(128) DEFAULT '' COMMENT '肥料建议（如：生长期每月施一次稀薄液肥）',
    `flowerpot` varchar(128) DEFAULT '' COMMENT '花盆建议（如：选择透气性好的陶盆）',
    `ventilation` varchar(128) DEFAULT '' COMMENT '通风要求（如：需良好通风，避免闷热）',

    -- ========== 4. 进阶养护 ==========
    `pest` text COMMENT '病虫害防治（如：常见蚜虫、红蜘蛛，可用...）',
    `propagation` varchar(128) DEFAULT '' COMMENT '繁殖方式（如：扦插/分株/播种）',
    `toxic` tinyint DEFAULT 0 COMMENT '对宠物是否有毒：0无毒 1有毒 2未知',

    -- ========== 5. 展示内容 ==========
    `description` varchar(500) DEFAULT '' COMMENT '简介/养护卖点（列表页展示，80-200字）',
    `detail` longtext COMMENT '详细养护知识（RAG知识库语料，800-3000字）',

    -- ========== 6. 审计 ==========
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` tinyint DEFAULT 0 COMMENT '软删除：0未删除 1已删除',

    -- 索引（提升查询性能）
    INDEX idx_name (`name`),
    INDEX idx_difficulty (`difficulty`),
    INDEX idx_family (`family`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='植物百科表';

ALTER TABLE `plant`
ADD COLUMN `water_cycle_days` int DEFAULT NULL COMMENT '浇水间隔天数',
ADD COLUMN `fertilize_cycle_days` int DEFAULT NULL COMMENT '施肥间隔天数',
ADD COLUMN `pest_cycle_days` int DEFAULT NULL COMMENT '病虫害防治间隔天数（松土/除虫/喷药等）';