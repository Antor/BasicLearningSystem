CREATE USER 'basic_learning_system'@'localhost' IDENTIFIED BY 'basic_learning_system_password';

GRANT ALL PRIVILEGES ON `BasicLearningSystem` . * TO 'basic_learning_system'@'localhost';
FLUSH PRIVILEGES;