-- Passwords are hashed using BCrypt algorithm https://bcrypt-generator.com/
-- Passwords for all users are: #Intel1#
--
-- Script generates database for my sbnz project
-- It generates:
--	- 3 roles
--	- 69 users
--		- 3 administrators
--		- 6 teachers
--		- 60 students
--	- 1 schools
--	- 3 classes
--		- 20 students per class
--
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------- Inserting roles --------------------------------------------------------------------------------------------
insert into roles (id, name) values (1, 'ROLE_ADMIN');
insert into roles (id, name) values (2, 'ROLE_TEACHER');
insert into roles (id, name) values (3, 'ROLE_STUDENT');
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------- Altering role_id_seq -----------------------------------------------------------------------------------------
alter sequence role_id_seq restart with 4;
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------- Inserting admins -------------------------------------------------------------------------------------------
insert into admins (id, name, surname, birth_date, email, username, password, archived, role_id) values (1, 'Svetlana', 'Brkić', '1980-5-21', 'adminemail1@gmail.com', 'adminusername1', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 1);
insert into admins (id, name, surname, birth_date, email, username, password, archived, role_id) values (2, 'Gordana', 'Vukomanović', '1980-5-23', 'adminemail2@gmail.com', 'adminusername2', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 1);
insert into admins (id, name, surname, birth_date, email, username, password, archived, role_id) values (3, 'Lana', 'Jelić', '1980-2-26', 'adminemail3@gmail.com', 'adminusername3', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 1);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------ Inserting teachers ------------------------------------------------------------------------------------------
insert into teachers (id, name, surname, birth_date, email, username, password, archived, role_id) values (4, 'Đurđica', 'Krstić', '1985-6-19', 'teacheremail1@gmail.com', 'teacherusername1', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 2);
insert into teachers (id, name, surname, birth_date, email, username, password, archived, role_id) values (5, 'Nemanja', 'Vasić', '1985-7-3', 'teacheremail2@gmail.com', 'teacherusername2', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 2);
insert into teachers (id, name, surname, birth_date, email, username, password, archived, role_id) values (6, 'Dejan', 'Tadić', '1985-2-24', 'teacheremail3@gmail.com', 'teacherusername3', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 2);
insert into teachers (id, name, surname, birth_date, email, username, password, archived, role_id) values (7, 'Zorka', 'Rakočević', '1985-10-7', 'teacheremail4@gmail.com', 'teacherusername4', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 2);
insert into teachers (id, name, surname, birth_date, email, username, password, archived, role_id) values (8, 'Tijana', 'Vasić', '1985-5-17', 'teacheremail5@gmail.com', 'teacherusername5', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 2);
insert into teachers (id, name, surname, birth_date, email, username, password, archived, role_id) values (9, 'Dejana', 'Todorović', '1985-3-27', 'teacheremail6@gmail.com', 'teacherusername6', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 2);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------ Inserting students ------------------------------------------------------------------------------------------
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (10, 'Berislav', 'Krstić', '2007-6-15', 'studentemail1@gmail.com', 'studentusername1', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (11, 'Bogdan', 'Janketić', '2007-10-24', 'studentemail2@gmail.com', 'studentusername2', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (12, 'Marko', 'Krstić', '2007-10-12', 'studentemail3@gmail.com', 'studentusername3', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (13, 'Tijana', 'Dapčević', '2007-4-18', 'studentemail4@gmail.com', 'studentusername4', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (14, 'David', 'Petrović', '2007-11-18', 'studentemail5@gmail.com', 'studentusername5', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (15, 'Cvetko', 'Stefanović', '2007-9-2', 'studentemail6@gmail.com', 'studentusername6', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (16, 'David', 'Filipović', '2007-8-17', 'studentemail7@gmail.com', 'studentusername7', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (17, 'Davor', 'Vukomanović', '2007-8-24', 'studentemail8@gmail.com', 'studentusername8', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (18, 'Petra', 'Đorđević', '2007-11-21', 'studentemail9@gmail.com', 'studentusername9', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (19, 'Ljubisav', 'Popović', '2007-9-25', 'studentemail10@gmail.com', 'studentusername10', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (20, 'Marko', 'Vlahović', '2007-3-23', 'studentemail11@gmail.com', 'studentusername11', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (21, 'Života', 'Rakočević', '2007-4-21', 'studentemail12@gmail.com', 'studentusername12', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (22, 'Maja', 'Kostić', '2007-8-26', 'studentemail13@gmail.com', 'studentusername13', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (23, 'Davor', 'Velimirović', '2007-9-27', 'studentemail14@gmail.com', 'studentusername14', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (24, 'Nenad', 'Velimirović', '2007-8-18', 'studentemail15@gmail.com', 'studentusername15', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (25, 'Gordan', 'Todorović', '2007-1-13', 'studentemail16@gmail.com', 'studentusername16', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (26, 'Nenad', 'Tadić', '2007-6-8', 'studentemail17@gmail.com', 'studentusername17', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (27, 'Mileta', 'Marinković', '2007-9-21', 'studentemail18@gmail.com', 'studentusername18', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (28, 'Jana', 'Georgijević', '2007-8-16', 'studentemail19@gmail.com', 'studentusername19', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (29, 'Zorka', 'Vukomanović', '2007-7-6', 'studentemail20@gmail.com', 'studentusername20', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (30, 'Radomir', 'Filipović', '2007-9-11', 'studentemail21@gmail.com', 'studentusername21', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (31, 'Cvetko', 'Todorović', '2007-3-6', 'studentemail22@gmail.com', 'studentusername22', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (32, 'Svetlana', 'Kostić', '2007-3-5', 'studentemail23@gmail.com', 'studentusername23', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (33, 'Zorka', 'Pejić', '2007-9-15', 'studentemail24@gmail.com', 'studentusername24', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (34, 'Miljan', 'Vasić', '2007-6-8', 'studentemail25@gmail.com', 'studentusername25', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (35, 'Milica', 'Stefanović', '2007-4-23', 'studentemail26@gmail.com', 'studentusername26', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (36, 'Draginja', 'Velimirović', '2007-7-4', 'studentemail27@gmail.com', 'studentusername27', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (37, 'Milka', 'Zorić', '2007-4-17', 'studentemail28@gmail.com', 'studentusername28', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (38, 'Ana', 'Antić', '2007-1-11', 'studentemail29@gmail.com', 'studentusername29', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (39, 'Nenad', 'Todorović', '2007-4-12', 'studentemail30@gmail.com', 'studentusername30', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (40, 'Draginja', 'Popović', '2007-2-21', 'studentemail31@gmail.com', 'studentusername31', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (41, 'Smiljana', 'Jelić', '2007-6-14', 'studentemail32@gmail.com', 'studentusername32', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (42, 'Nemanja', 'Vukašinović', '2007-4-26', 'studentemail33@gmail.com', 'studentusername33', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (43, 'Milka', 'Jelić', '2007-8-1', 'studentemail34@gmail.com', 'studentusername34', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (44, 'Berislav', 'Petrović', '2007-7-21', 'studentemail35@gmail.com', 'studentusername35', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (45, 'Ljubisav', 'Đorđević', '2007-6-13', 'studentemail36@gmail.com', 'studentusername36', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (46, 'Života', 'Jelić', '2007-11-14', 'studentemail37@gmail.com', 'studentusername37', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (47, 'Branislav', 'Vasić', '2007-9-12', 'studentemail38@gmail.com', 'studentusername38', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (48, 'Stanimir', 'Vukašinović', '2007-4-13', 'studentemail39@gmail.com', 'studentusername39', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (49, 'Đurđica', 'Pejić', '2007-4-15', 'studentemail40@gmail.com', 'studentusername40', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (50, 'Gordan', 'Jelić', '2007-11-3', 'studentemail41@gmail.com', 'studentusername41', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (51, 'Filip', 'Dejanič', '2007-4-6', 'studentemail42@gmail.com', 'studentusername42', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (52, 'Danijela', 'Borisov', '2007-3-16', 'studentemail43@gmail.com', 'studentusername43', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (53, 'Filip', 'Stefanović', '2007-11-3', 'studentemail44@gmail.com', 'studentusername44', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (54, 'Cvetko', 'Popović', '2007-6-17', 'studentemail45@gmail.com', 'studentusername45', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (55, 'Đurđica', 'Đorđević', '2007-7-27', 'studentemail46@gmail.com', 'studentusername46', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (56, 'Miljan', 'Zebić', '2007-10-26', 'studentemail47@gmail.com', 'studentusername47', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (57, 'Svetlana', 'Borisov', '2007-10-26', 'studentemail48@gmail.com', 'studentusername48', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (58, 'Stanimir', 'Todorović', '2007-8-10', 'studentemail49@gmail.com', 'studentusername49', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (59, 'Stanimir', 'Krstić', '2007-6-21', 'studentemail50@gmail.com', 'studentusername50', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (60, 'Nataša', 'Janketić', '2007-11-20', 'studentemail51@gmail.com', 'studentusername51', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (61, 'Milica', 'Janketić', '2007-7-19', 'studentemail52@gmail.com', 'studentusername52', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (62, 'Đurđica', 'Krstić', '2007-4-25', 'studentemail53@gmail.com', 'studentusername53', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (63, 'Nenad', 'Rakočević', '2007-4-24', 'studentemail54@gmail.com', 'studentusername54', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (64, 'Ljiljana', 'Marinković', '2007-3-12', 'studentemail55@gmail.com', 'studentusername55', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (65, 'Filip', 'Kostić', '2007-10-19', 'studentemail56@gmail.com', 'studentusername56', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (66, 'Petra', 'Rakočević', '2007-8-15', 'studentemail57@gmail.com', 'studentusername57', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (67, 'Života', 'Vlahović', '2007-5-27', 'studentemail58@gmail.com', 'studentusername58', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (68, 'Danijela', 'Vasić', '2007-7-10', 'studentemail59@gmail.com', 'studentusername59', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, email, username, password, archived, role_id, nivo_sklonosti) values (69, 'Nataša', 'Pejić', '2007-2-23', 'studentemail60@gmail.com', 'studentusername60', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------- Altering user_id_seq -----------------------------------------------------------------------------------------
alter sequence user_id_seq restart with 70;
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------ Inserting classes -------------------------------------------------------------------------------------------
insert into odeljenja (id, naziv, staresina_id, archived) values (1, 'IIO-1', 4, false);
insert into odeljenja (id, naziv, staresina_id, archived) values (2, 'IIO-2', 5, false);
insert into odeljenja (id, naziv, staresina_id, archived) values (3, 'IIO-3', 6, false);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------- Altering odeljenje_id_seq ---------------------------------------------------------------------------------------
alter sequence odeljenje_id_seq restart with 4;
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------ Inserting schools -------------------------------------------------------------------------------------------
insert into schools (id, name) values (1, 'Gimnazija "Svetozar Marković"');
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------- Altering school_id_seq ----------------------------------------------------------------------------------------
alter sequence school_id_seq restart with 2;
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------- Inserting classes_students --------------------------------------------------------------------------------------
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 16);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 63);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 51);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 23);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 22);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 42);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 49);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 30);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 48);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 62);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 61);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 44);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 50);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 53);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 55);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 31);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 12);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 47);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 19);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 15);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 45);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 17);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 33);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 57);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 60);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 13);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 40);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 29);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 34);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 21);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 39);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 28);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 25);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 35);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 66);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 43);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 26);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 38);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 58);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 59);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 54);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 52);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 67);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 64);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 18);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 24);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 37);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 56);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 27);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 10);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 65);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 46);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 11);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 41);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 20);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 68);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 32);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 36);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 69);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 14);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------- Inserting schools_teachers --------------------------------------------------------------------------------------
insert into schools_teachers (school_id, teachers_id) values (1, 4);
insert into schools_teachers (school_id, teachers_id) values (1, 5);
insert into schools_teachers (school_id, teachers_id) values (1, 6);
insert into schools_teachers (school_id, teachers_id) values (1, 7);
insert into schools_teachers (school_id, teachers_id) values (1, 8);
insert into schools_teachers (school_id, teachers_id) values (1, 9);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------- Inserting schools_classes ---------------------------------------------------------------------------------------
insert into schools_odeljenja (school_id, odeljenja_id) values (1, 1);
insert into schools_odeljenja (school_id, odeljenja_id) values (1, 2);
insert into schools_odeljenja (school_id, odeljenja_id) values (1, 3);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

