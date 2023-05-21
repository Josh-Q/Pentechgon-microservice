--CREATE TABLE `logged_in_user` (
--  `id` varchar(255) NOT NULL,
--  `session_id` CHAR(36) NOT NULL,
--  `csrf_id` VARCHAR(255)
--);
--
--CREATE TABLE `user_info` (
--  `id` int PRIMARY KEY AUTO_INCREMENT,
--  `name` varchar(255),
--  `password` VARCHAR(255),
--  `has_rolled_today` boolean DEFAULT false
--);
--
--CREATE TABLE `daily_jackpot_rolls` (
--  `id` int PRIMARY KEY AUTO_INCREMENT,
--  `user_id` int,
--  `created_at` datetime DEFAULT CURRENT_TIMESTAMP
--);
--
--CREATE TABLE `jackpot_roll_values` (
--  `id` int PRIMARY KEY AUTO_INCREMENT,
--  `daily_jackpot_rolls_id` int DEFAULT 0,
--  `value` int
--);
--
--CREATE TABLE `savings_history` (
--  `id` int PRIMARY KEY AUTO_INCREMENT,
--  `user_id` int DEFAULT 0,
--  `max_streak_savings` int DEFAULT 0,
--  `current_streak_savings` int DEFAULT 0,
--  `max_streak_days` int DEFAULT 0,
--  `current_streak_days` int DEFAULT 0,
--  `total_savings` int DEFAULT 0
--);
--
--ALTER TABLE `daily_jackpot_rolls` ADD FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`);
--
--ALTER TABLE `jackpot_roll_values` ADD FOREIGN KEY (`daily_jackpot_rolls_id`) REFERENCES `daily_jackpot_rolls` (`id`);
--
--ALTER TABLE `savings_history` ADD FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`);