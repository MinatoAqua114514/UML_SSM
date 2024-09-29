SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database if not exists uml_db;

use uml_db;

-- 创建用户信息表
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id         INT AUTO_INCREMENT PRIMARY KEY,                                                             -- 主键，自增用户ID
    username   VARCHAR(50)  NOT NULL UNIQUE,                                                               -- 用户名，唯一
    email      VARCHAR(100) NOT NULL UNIQUE,                                                               -- 邮箱，唯一
    password   VARCHAR(255) NOT NULL,                                                                      -- 密码，确保使用加密存储
    status     ENUM ('active', 'inactive', 'banned') DEFAULT 'active',                                     -- 用户状态
    created_at TIMESTAMP                             DEFAULT CURRENT_TIMESTAMP,                            -- 用户账户的创建时间
    updated_at TIMESTAMP                             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 用户账户的最后更新时间（如用户信息的修改）
);

-- 插入用户示例信息
INSERT INTO users (username, email, password)
VALUES ('叶玉梅', 'renxia@example.com', '21631394510759945162');
INSERT INTO users (username, email, password)
VALUES ('吕成', 'tao47@example.net', '65220576661500527995');
INSERT INTO users (username, email, password)
VALUES ('苏桂荣', 'ncai@example.net', '02221870505374365059');
INSERT INTO users (username, email, password)
VALUES ('杨玉梅', 'chao11@example.com', '51497011910074490729');
INSERT INTO users (username, email, password)
VALUES ('孙桂花', 'dhou@example.net', '60745743496258832457');
INSERT INTO users (username, email, password)
VALUES ('高芳', 'ping97@example.org', '17143962119439377733');
INSERT INTO users (username, email, password)
VALUES ('叶佳', 'uxue@example.net', '92049583726877529986');
INSERT INTO users (username, email, password)
VALUES ('刘畅', 'jieshao@example.org', '59215864670117111616');
INSERT INTO users (username, email, password)
VALUES ('杨冬梅', 'pengyang@example.com', '11599734005238532304');
INSERT INTO users (username, email, password)
VALUES ('简旭', 'uxiong@example.net', '95315336842848465297');
INSERT INTO users (username, email, password)
VALUES ('孙秀兰', 'rhou@example.net', '81298315131772793701');
INSERT INTO users (username, email, password)
VALUES ('汪建华', 'ecai@example.net', '11634800542035710503');
INSERT INTO users (username, email, password)
VALUES ('刘红霞', 'yan40@example.net', '64320774488451073299');
INSERT INTO users (username, email, password)
VALUES ('成晨', 'minzhao@example.com', '45912793312234969832');
INSERT INTO users (username, email, password)
VALUES ('冯颖', 'xiaxia@example.org', '53133145372787706187');
INSERT INTO users (username, email, password)
VALUES ('张桂花', 'chao73@example.net', '96191138235070647124');
INSERT INTO users (username, email, password)
VALUES ('林浩', 'dengchao@example.net', '57799817691552040722');
INSERT INTO users (username, email, password)
VALUES ('吴柳', 'minlai@example.org', '82252931479635664810');
INSERT INTO users (username, email, password)
VALUES ('钱桂荣', 'li84@example.net', '55312815486957770282');
INSERT INTO users (username, email, password)
VALUES ('王秀兰', 'bma@example.net', '10454283177018380604');
INSERT INTO users (username, email, password)
VALUES ('陈飞', 'lixie@example.org', '32815601303382181295');
INSERT INTO users (username, email, password)
VALUES ('侯强', 'wantao@example.net', '36289116270134341123');
INSERT INTO users (username, email, password)
VALUES ('罗博', 'chaogu@example.com', '06252500746674603691');
INSERT INTO users (username, email, password)
VALUES ('林玲', 'mingjin@example.com', '47441364771356138492');
INSERT INTO users (username, email, password)
VALUES ('俞玉兰', 'heqiang@example.org', '20109958522066968333');
INSERT INTO users (username, email, password)
VALUES ('蒋洁', 'lei04@example.org', '75035789101774247973');
INSERT INTO users (username, email, password)
VALUES ('庞淑珍', 'gang65@example.net', '72747218498212094270');
INSERT INTO users (username, email, password)
VALUES ('曹文', 'qiangkang@example.org', '62355901609821953824');
INSERT INTO users (username, email, password)
VALUES ('岑桂英', 'weishao@example.org', '39570285964444502997');
INSERT INTO users (username, email, password)
VALUES ('何峰', 'jiepan@example.net', '28759445851661111751');
INSERT INTO users (username, email, password)
VALUES ('范秀兰', 'heyang@example.net', '83762288861144585897');
INSERT INTO users (username, email, password)
VALUES ('刘龙', 'vxiang@example.net', '59128086930826926082');
INSERT INTO users (username, email, password)
VALUES ('陈丽丽', 'songming@example.net', '88004678670622528885');
INSERT INTO users (username, email, password)
VALUES ('鲁慧', 'xiulan50@example.net', '53909151506700732044');
INSERT INTO users (username, email, password)
VALUES ('黄婷', 'changxiulan@example.com', '29216723634562713252');
INSERT INTO users (username, email, password)
VALUES ('郝波', 'jundong@example.com', '34669384778931354350');
INSERT INTO users (username, email, password)
VALUES ('廖峰', 'wanxiuying@example.com', '20061276756218564775');
INSERT INTO users (username, email, password)
VALUES ('袁凯', 'tao55@example.org', '31419697210191878907');
INSERT INTO users (username, email, password)
VALUES ('郑玉华', 'jun75@example.org', '67281588642982560658');
INSERT INTO users (username, email, password)
VALUES ('萧建', 'linwei@example.net', '15015770610738146530');

-- 省
DROP TABLE IF EXISTS provinces;
CREATE TABLE provinces
(
    id   INT PRIMARY KEY AUTO_INCREMENT, -- 主键，自增省ID
    name VARCHAR(50) NOT NULL            -- 省的昵称
);

-- 市
DROP TABLE IF EXISTS cities;
CREATE TABLE cities
(
    id          INT PRIMARY KEY AUTO_INCREMENT, -- 主键，自增市ID
    name        VARCHAR(50) NOT NULL,           -- 市的昵称
    province_id INT         NOT NULL,           -- 外键，省ID
    FOREIGN KEY (province_id) REFERENCES provinces (id)
);

-- 区
DROP TABLE IF EXISTS districts;
CREATE TABLE districts
(
    id      INT PRIMARY KEY AUTO_INCREMENT, -- 主键，自增区ID
    name    VARCHAR(50) NOT NULL,           -- 区的昵称
    city_id INT         NOT NULL,           -- 外键，市ID
    FOREIGN KEY (city_id) REFERENCES cities (id)
);

-- 插入省市区示例数据
INSERT INTO provinces (id, name) VALUES (1, '海南省');
INSERT INTO cities (id, name, province_id) VALUES (1, '海口市', 1);
INSERT INTO cities (id, name, province_id) VALUES (2, '三亚市', 1);
INSERT INTO cities (id, name, province_id) VALUES (3, '三沙市', 1);
INSERT INTO cities (id, name, province_id) VALUES (4, '五指山市', 1);
INSERT INTO districts (id, name, city_id) VALUES (1, '秀英区', 1);
INSERT INTO districts (id, name, city_id) VALUES (2, '龙华区', 1);
INSERT INTO districts (id, name, city_id) VALUES (3, '琼山区', 1);
INSERT INTO districts (id, name, city_id) VALUES (4, '美兰区', 1);
INSERT INTO districts (id, name, city_id) VALUES (5, '海棠区', 2);
INSERT INTO districts (id, name, city_id) VALUES (6, '吉阳区', 2);
INSERT INTO districts (id, name, city_id) VALUES (7, '天涯区', 2);
INSERT INTO districts (id, name, city_id) VALUES (8, '崖州区', 2);
INSERT INTO districts (id, name, city_id) VALUES (9, '西沙区', 3);
INSERT INTO districts (id, name, city_id) VALUES (10, '南沙区', 3);
INSERT INTO districts (id, name, city_id) VALUES (11, '通什镇', 4);
INSERT INTO districts (id, name, city_id) VALUES (12, '南圣镇', 4);
INSERT INTO districts (id, name, city_id) VALUES (13, '毛阳镇', 4);
INSERT INTO districts (id, name, city_id) VALUES (14, '番阳镇', 4);

-- 民宿信息表
DROP TABLE IF EXISTS listings;
CREATE TABLE listings
(
    id          INT AUTO_INCREMENT PRIMARY KEY,      -- 主键，自增民宿ID
    district_id INT          NOT NULL,               -- 外键，区ID
    title       VARCHAR(100) NOT NULL,               -- 民宿标题
    description TEXT      DEFAULT NULL,              -- 民宿描述
    price       INT          NOT NULL,               -- 民宿价格
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    FOREIGN KEY (district_id) REFERENCES districts (id) ON DELETE CASCADE
);

-- 插入民宿信息
INSERT INTO listings (district_id, title, description, price) VALUES
(1, '温馨小屋', '靠近市中心，交通便利，适合短期居住。', 300),
(5, '浪漫阁楼', '适合情侣，环境优雅，提供早餐服务。', 500),
(9, '海边别墅', '享受海景，放松心情，配备游泳池。', 800),
(12, '乡村民宿', '远离城市喧嚣，享受自然，适合家庭聚会。', 250);

-- 评分表
DROP TABLE IF EXISTS mark;
CREATE TABLE mark
(
    id         INT PRIMARY KEY AUTO_INCREMENT, -- 主键，自增评分ID
    user_id    INT     NOT NULL,               -- 外键，用户ID
    listing_id INT     NOT NULL,               -- 外键，民宿ID
    score      TINYINT NOT NULL,               -- 评分分数
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (listing_id) REFERENCES listings (id) ON DELETE CASCADE
);

-- 插入评分信息
INSERT INTO mark (user_id, listing_id, score) VALUES
(1, 1, 5),
(2, 1, 4),
(1, 2, 5),
(3, 3, 3),
(2, 4, 4);

-- 评价表
DROP TABLE IF EXISTS evaluate;
CREATE TABLE evaluate
(
    id         INT PRIMARY KEY AUTO_INCREMENT, -- 主键，自增评价ID
    user_id    INT  NOT NULL,                  -- 外键，用户ID
    listing_id INT  NOT NULL,                  -- 外键，民宿ID
    mark_id    INT  NOT NULL,                  -- 外键，评分ID
    content    TEXT NOT NULL,                  -- 评价内容
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (listing_id) REFERENCES listings (id) ON DELETE CASCADE,
    FOREIGN KEY (mark_id) REFERENCES mark (id) ON DELETE CASCADE
);

-- 插入评价信息
INSERT INTO evaluate (user_id, listing_id, mark_id, content) VALUES
(1, 1, 1, '非常满意，服务很好！'),
(2, 1, 2, '环境不错，但价格稍贵。'),
(1, 2, 3, '非常浪漫的地方，值得推荐！'),
(3, 3, 4, '一般般，期待改进。'),
(2, 4, 5, '很不错的体验，适合家庭出游。');

-- 添加索引
-- 用户表：为用户名和邮箱添加索引，因为这些字段可能会被用于登录查询
CREATE INDEX idx_users_username ON users (username);
CREATE INDEX idx_users_email ON users (email);

-- 民宿信息表：为标题和地区添加索引，可能会根据标题和地区搜索民宿
CREATE INDEX idx_listings_title ON listings (title);
CREATE INDEX idx_listings_district_id ON listings (district_id);

-- 省、市和区表：为名称添加索引，可能会根据名称搜索
CREATE INDEX idx_province_name ON provinces (name);
CREATE INDEX idx_cities_name ON cities (name);
CREATE INDEX idx_districts_name ON districts (name);
-- 查找父级地址
-- select name from provinces where id = (select province_id from cities where id = (select city_id from districts where id = 1));