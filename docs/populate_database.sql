USE `BasicLearningSystem`;

-- Create users
-- password hash alg: bcrypt(10 rounds)

insert into users (id, email, password, first_name, last_name, user_role)
values (1, 'john.smith@example.com', '123456', 'John', 'Smith', 'admin');

insert into users (id, email, password, first_name, last_name, user_role)
values (2, 'john.smith2@example.com', '123456', 'John22', 'Smith22', 'student');

-- Create courses

insert into courses (id, title, description, active)
values (1, 'Course 1', 'Description for Course 1', true);

insert into courses (id, title, description, active)
values (2, 'Course 2', 'Description for Course 2', true);

insert into courses (id, title, description, active)
values (3, 'Course 3', 'Description for Course 3', false);

-- Create units for course 1

insert into units (id, title, description, ordinal, course_id)
values (1, 'Unit 1', 'Description for Unit 1', 1, 1);

insert into units (id, title, description, ordinal, course_id)
values (2, 'Unit 2', 'Description for Unit 2', 2, 1);

insert into units (id, title, description, ordinal, course_id)
values (3, 'Unit 3', 'Description for Unit 3', 3, 1);

-- Create lessons for unit 1

insert into lessons (id, title, description, ordinal, unit_id)
values (1, 'Lesson 1', 'Description for Lesson 1', 1, 1);

insert into lessons (id, title, description, ordinal, unit_id)
values (2, 'Lesson 2', 'Description for Lesson 2', 2, 1);

insert into lessons (id, title, description, ordinal, unit_id)
values (3, 'Lesson 3', 'Description for Lesson 3', 3, 1);

-- Create lesson items for lesson 1

insert into lesson_items (id, title, content, ordinal, lesson_id)
values (1, 'Lesson Item 1', 'Lesson Item 1 Content', 1, 1);

insert into lesson_items (id, title, content, ordinal, lesson_id)
values (2, 'Lesson Item 2', 'Lesson Item 2 Content', 2, 1);

insert into lesson_items (id, title, content, ordinal, lesson_id)
values (3, 'Lesson Item 3', 'Lesson Item 3 Content', 3, 1);

-- Assign users to courses

insert into users_courses_enrolment (user_id, course_id)
values (1, 1);

insert into users_courses_enrolment (user_id, course_id)
values (1, 2);

-- Complete lesson items

insert into users_completed_lesson_items (user_id, lesson_item_id)
values (1, 1);

insert into users_completed_lesson_items (user_id, lesson_item_id)
values (1, 3);





