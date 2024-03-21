INSERT INTO users (username, email, password) VALUES ('TechNinja', 'techninja@example.com', 'password123') ON CONFLICT DO NOTHING;
INSERT INTO users (username, email, password) VALUES ('CodeMaster', 'codemaster@example.com', 'securepassword') ON CONFLICT DO NOTHING;
INSERT INTO users (username, email, password) VALUES ('DataGuru', 'dataguru@example.com', 'strongpassword') ON CONFLICT DO NOTHING;
INSERT INTO users (username, email, password) VALUES ('CyberSavvy', 'cybersavvy@example.com', 'password2022') ON CONFLICT DO NOTHING;
INSERT INTO users (username, email, password) VALUES ('GeekChic', 'geekchic@example.com', 'geeky123') ON CONFLICT DO NOTHING;
