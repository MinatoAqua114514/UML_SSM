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