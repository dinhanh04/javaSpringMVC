-- Insert default roles if they don't exist
INSERT IGNORE INTO roles (id, name, description) VALUES 
(1, 'USER', 'Người dùng thông thường'),
(2, 'ADMIN', 'Quản trị viên hệ thống');