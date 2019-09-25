CREATE DATABASE `BasicLearningSystem`;

USE `BasicLearningSystem`;

CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `token` varchar(255),
  `first_name` varchar(255),
  `last_name` varchar(255),
  `user_role` varchar(255)
);

CREATE TABLE `courses` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255),
  `description` varchar(255),
  `active` boolean
);

-- User need to register/enrol on course before start learning
CREATE TABLE `users_courses_enrolment` (
  `user_id` int,
  `course_id` int,
  PRIMARY KEY (`user_id`, `course_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
);

CREATE TABLE `units` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255),
  `description` varchar(255),
  `ordinal` int,
  `course_id` int,
  FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
);

CREATE TABLE `lessons` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255),
  `description` varchar(255),
  `ordinal` int,
  `unit_id` int,
  FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`)
);

CREATE TABLE `lesson_items` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255),
  `content` varchar(255),
  `ordinal` int,
  `lesson_id` int,
  FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`id`)
);

CREATE TABLE `users_completed_lesson_items` (
  `user_id` int,
  `lesson_item_id` int,
  PRIMARY KEY (`user_id`, `lesson_item_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`lesson_item_id`) REFERENCES `lesson_items` (`id`)
);




