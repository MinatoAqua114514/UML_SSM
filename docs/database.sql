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

-- 民宿信息表
CREATE TABLE listings
(
    id          INT AUTO_INCREMENT PRIMARY KEY,      -- 民宿ID
    district_id INT,                                 -- 区ID，外键
    title       VARCHAR(100) NOT NULL,               -- 民宿标题
    description TEXT,                                -- 民宿描述
    price       INT,                                 -- 价格
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    location    VARCHAR(50),                         -- 地理位置
    FOREIGN KEY (district_id) REFERENCES districts (id) ON DELETE CASCADE
);

-- 省
CREATE TABLE provinces
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

-- 市
CREATE TABLE cities
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    province_id INT,
    FOREIGN KEY (province_id) REFERENCES provinces (id)
);

-- 区
CREATE TABLE districts
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL,
    city_id INT,
    FOREIGN KEY (city_id) REFERENCES cities (id)
);

-- 评分表
CREATE TABLE mark
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    user_id    INT NOT NULL,
    listing_id INT NOT NULL,
    score      INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (listing_id) REFERENCES listings (id)
);

-- 评价表
CREATE TABLE evaluate
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    user_id     INT  NOT NULL,
    listing_id  INT  NOT NULL,
    mark_id     INT  NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (listing_id) REFERENCES listings (id),
    FOREIGN KEY (mark_id) REFERENCES mark (id)
);