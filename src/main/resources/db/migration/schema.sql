SET FOREIGN_KEY_CHECKS = 0; -- Disable foreign key constraints

-- Drop tables in the desired order to avoid dependency conflicts
DROP TABLE IF EXISTS logged_in_user;
DROP TABLE IF EXISTS savings_history;
DROP TABLE IF EXISTS jackpot_roll_values;
DROP TABLE IF EXISTS daily_jackpot_rolls;
DROP TABLE IF EXISTS user_info;

-- Add more DROP TABLE statements for all the tables in your database

SET FOREIGN_KEY_CHECKS = 1; -- Enable foreign key constraints