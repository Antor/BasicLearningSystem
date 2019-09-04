CREATE TABLE `users` (
  `id` int PRIMARY KEY,
  `username` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255),
  `last_name` varchar(255)
);

CREATE TABLE `roles` (
  `id` varchar(255) PRIMARY KEY
);

CREATE TABLE `users_roles` (
  `user_id` int,
  `role_id` varchar(255),
  PRIMARY KEY (`user_id`, `role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
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
  `content` varchar(255), -- text in markdown format for basic implementation
  `unit_id` int,
  FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`)
);

CREATE TABLE `users_completed_lessons` (
  `user_id` int,
  `lesson_id` int,
  PRIMARY KEY (`user_id`, `lesson_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`id`)
);
