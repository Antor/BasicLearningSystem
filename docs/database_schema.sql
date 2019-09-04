CREATE DATABASE `BasicLearningSystem`;

USE `BasicLearningSystem`;

CREATE TABLE `users` (
  `id` int PRIMARY KEY,
  `username` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `user_role` varchar(255)
);

CREATE TABLE `courses` (
  `id` int PRIMARY KEY,
  `title` varchar(255),
  `description` varchar(255)
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
  `id` int PRIMARY KEY,
  `title` varchar(255),
  `description` varchar(255),
  `course_id` int,
  FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
);

CREATE TABLE `lessons` (
  `id` int PRIMARY KEY,
  `title` varchar(255),
  `description` varchar(255),
  `unit_id` int,
  FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`)
);

CREATE TABLE `lesson_items` (
  `id` int PRIMARY KEY,
  `content` varchar(255),
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




