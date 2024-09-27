SET NAMES utf8mb4;

create database if not exists uml_db;

use uml_db;

-- 创建用户信息表
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

-- 省
CREATE TABLE provinces
(
    id   INT PRIMARY KEY AUTO_INCREMENT, -- 主键，自增省ID
    name VARCHAR(50) NOT NULL            -- 省的昵称
);

-- 市
CREATE TABLE cities
(
    id          INT PRIMARY KEY AUTO_INCREMENT, -- 主键，自增市ID
    name        VARCHAR(50) NOT NULL,           -- 市的昵称
    province_id INT         NOT NULL,           -- 外键，省ID
    FOREIGN KEY (province_id) REFERENCES provinces (id)
);

-- 区
CREATE TABLE districts
(
    id      INT PRIMARY KEY AUTO_INCREMENT, -- 主键，自增区ID
    name    VARCHAR(50) NOT NULL,           -- 区的昵称
    city_id INT         NOT NULL,           -- 外键，市ID
    FOREIGN KEY (city_id) REFERENCES cities (id)
);

-- 民宿信息表
CREATE TABLE listings
(
    id          INT AUTO_INCREMENT PRIMARY KEY,      -- 主键，自增民宿ID
    district_id INT          NOT NULL,               -- 外键，区ID
    title       VARCHAR(100) NOT NULL,               -- 民宿标题
    description TEXT      DEFAULT NULL,              -- 民宿描述
    price       INT       DEFAULT NULL,              -- 民宿价格
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    FOREIGN KEY (district_id) REFERENCES districts (id) ON DELETE CASCADE
);

-- 评分表
CREATE TABLE mark
(
    id         INT PRIMARY KEY AUTO_INCREMENT, -- 主键，自增评分ID
    user_id    INT     NOT NULL,               -- 外键，用户ID
    listing_id INT     NOT NULL,               -- 外键，民宿ID
    score      TINYINT NOT NULL,               -- 评分分数
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (listing_id) REFERENCES listings (id) ON DELETE CASCADE
);

-- 评价表
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

-- 添加索引
-- 用户表：为用户名和邮箱添加索引，因为这些字段可能会被用于登录查询
CREATE INDEX idx_users_username ON users (username);
CREATE INDEX idx_users_email ON users (email);

-- 民宿信息表：为标题添加索引，可能会根据标题搜索民宿
CREATE INDEX idx_listings_title ON listings (title);

-- 省、市和区表：为名称添加索引，可能会根据名称搜索
CREATE INDEX idx_province_name ON provinces (name);
CREATE INDEX idx_cities_name ON cities (name);
CREATE INDEX idx_districts_name ON districts (name);
-- 查找父级地址
-- select name from provinces where id = (select province_id from cities where id = (select city_id from districts where id = 1));