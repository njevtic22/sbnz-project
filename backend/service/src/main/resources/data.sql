-- Passwords are hashed using BCrypt algorithm https://bcrypt-generator.com/
-- Passwords for all users are: #Intel1#
--
-- Script generates database for my sbnz project
-- It generates:
--	- 3 roles
--	- 66 users
--		- 3 administrators
--		- 3 teachers
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
insert into admins (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id) values (1, 'Nenad', 'Pešić', '1980-5-15', '1505980369183', 'adminemail1@gmail.com', 'adminusername1', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 1);
insert into admins (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id) values (2, 'Radomir', 'Jelić', '1980-8-21', '2108980457693', 'adminemail2@gmail.com', 'adminusername2', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 1);
insert into admins (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id) values (3, 'Petra', 'Brkić', '1980-3-15', '1503980359356', 'adminemail3@gmail.com', 'adminusername3', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 1);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------ Inserting teachers ------------------------------------------------------------------------------------------
insert into teachers (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id) values (4, 'Lana', 'Georgijević', '1985-5-18', '1805985765986', 'teacheremail1@gmail.com', 'teacherusername1', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 2);
insert into teachers (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id) values (5, 'Lana', 'Dejanič', '1985-5-20', '2005985591793', 'teacheremail2@gmail.com', 'teacherusername2', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 2);
insert into teachers (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id) values (6, 'Života', 'Vasić', '1985-3-6', '0603985753648', 'teacheremail3@gmail.com', 'teacherusername3', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 2);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------ Inserting students ------------------------------------------------------------------------------------------
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (7, 'Todor', 'Vasić', '2007-8-23', '2308007528939', 'studentemail1@gmail.com', 'studentusername1', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (8, 'Smiljana', 'Blagojević', '2007-5-1', '0105007769997', 'studentemail2@gmail.com', 'studentusername2', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (9, 'Gordana', 'Vlahović', '2007-4-17', '1704007434669', 'studentemail3@gmail.com', 'studentusername3', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (10, 'Danijela', 'Vukašinović', '2007-8-27', '2708007528329', 'studentemail4@gmail.com', 'studentusername4', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (11, 'Marko', 'Velimirović', '2007-10-27', '2710007797691', 'studentemail5@gmail.com', 'studentusername5', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (12, 'Maja', 'Popović', '2007-3-24', '2403007379679', 'studentemail6@gmail.com', 'studentusername6', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (13, 'Zdravko', 'Zorić', '2007-2-11', '1102007973288', 'studentemail7@gmail.com', 'studentusername7', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (14, 'Đurđica', 'Vukomanović', '2007-9-2', '0209007244827', 'studentemail8@gmail.com', 'studentusername8', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (15, 'Tijana', 'Stefanović', '2007-5-25', '2505007428619', 'studentemail9@gmail.com', 'studentusername9', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (16, 'Radomir', 'Georgijević', '2007-6-22', '2206007157958', 'studentemail10@gmail.com', 'studentusername10', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (17, 'Ana', 'Nastasić', '2007-2-8', '0802007826722', 'studentemail11@gmail.com', 'studentusername11', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (18, 'Svetlana', 'Janketić', '2007-9-1', '0109007263429', 'studentemail12@gmail.com', 'studentusername12', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (19, 'Nenad', 'Tadić', '2007-8-23', '2308007247185', 'studentemail13@gmail.com', 'studentusername13', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (20, 'Davor', 'Rakočević', '2007-3-17', '1703007853426', 'studentemail14@gmail.com', 'studentusername14', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (21, 'Tijana', 'Popović', '2007-10-25', '2510007969813', 'studentemail15@gmail.com', 'studentusername15', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (22, 'Nenad', 'Petrović', '2007-6-7', '0706007798639', 'studentemail16@gmail.com', 'studentusername16', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (23, 'Draginja', 'Pešić', '2007-3-4', '0403007265895', 'studentemail17@gmail.com', 'studentusername17', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (24, 'Svetlana', 'Rakočević', '2007-11-3', '0311007922444', 'studentemail18@gmail.com', 'studentusername18', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (25, 'Tijana', 'Pejić', '2007-6-27', '2706007226623', 'studentemail19@gmail.com', 'studentusername19', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (26, 'Berislav', 'Vasić', '2007-7-4', '0407007699623', 'studentemail20@gmail.com', 'studentusername20', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (27, 'Mileta', 'Pešić', '2007-2-17', '1702007497217', 'studentemail21@gmail.com', 'studentusername21', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (28, 'Mileta', 'Blagojević', '2007-3-12', '1203007987779', 'studentemail22@gmail.com', 'studentusername22', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (29, 'Ana', 'Lazarević', '2007-6-12', '1206007975765', 'studentemail23@gmail.com', 'studentusername23', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (30, 'Milica', 'Pejić', '2007-4-25', '2504007197744', 'studentemail24@gmail.com', 'studentusername24', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (31, 'Bogdan', 'Zebić', '2007-6-15', '1506007999849', 'studentemail25@gmail.com', 'studentusername25', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (32, 'Branislav', 'Dapčević', '2007-4-14', '1404007612628', 'studentemail26@gmail.com', 'studentusername26', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (33, 'Tijana', 'Rajković', '2007-3-6', '0603007922372', 'studentemail27@gmail.com', 'studentusername27', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (34, 'Miljan', 'Nastasić', '2007-3-5', '0503007979493', 'studentemail28@gmail.com', 'studentusername28', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (35, 'Marko', 'Popović', '2007-5-25', '2505007668126', 'studentemail29@gmail.com', 'studentusername29', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (36, 'Gordan', 'Stefanović', '2007-8-3', '0308007665898', 'studentemail30@gmail.com', 'studentusername30', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (37, 'Cvetko', 'Krstić', '2007-7-21', '2107007629637', 'studentemail31@gmail.com', 'studentusername31', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (38, 'Dejana', 'Kostić', '2007-3-17', '1703007759369', 'studentemail32@gmail.com', 'studentusername32', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (39, 'Lana', 'Velimirović', '2007-9-24', '2409007481644', 'studentemail33@gmail.com', 'studentusername33', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (40, 'Bogdan', 'Nastasić', '2007-2-7', '0702007894743', 'studentemail34@gmail.com', 'studentusername34', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (41, 'Zorka', 'Rajković', '2007-3-21', '2103007259267', 'studentemail35@gmail.com', 'studentusername35', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (42, 'Zorka', 'Tadić', '2007-8-27', '2708007461463', 'studentemail36@gmail.com', 'studentusername36', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (43, 'Adrijana', 'Georgijević', '2007-11-20', '2011007456861', 'studentemail37@gmail.com', 'studentusername37', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (44, 'Ljiljana', 'Popović', '2007-6-25', '2506007763872', 'studentemail38@gmail.com', 'studentusername38', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (45, 'Gordan', 'Marinković', '2007-3-23', '2303007986318', 'studentemail39@gmail.com', 'studentusername39', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (46, 'Nataša', 'Rajković', '2007-5-7', '0705007521615', 'studentemail40@gmail.com', 'studentusername40', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (47, 'Filip', 'Janketić', '2007-2-9', '0902007313489', 'studentemail41@gmail.com', 'studentusername41', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (48, 'Milka', 'Dejanič', '2007-4-6', '0604007116681', 'studentemail42@gmail.com', 'studentusername42', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (49, 'Miljan', 'Krstić', '2007-7-11', '1107007547192', 'studentemail43@gmail.com', 'studentusername43', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (50, 'Bogdan', 'Janković', '2007-2-8', '0802007679655', 'studentemail44@gmail.com', 'studentusername44', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (51, 'Dejana', 'Todorović', '2007-11-27', '2711007669336', 'studentemail45@gmail.com', 'studentusername45', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (52, 'Tijana', 'Rakočević', '2007-4-21', '2104007349581', 'studentemail46@gmail.com', 'studentusername46', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (53, 'Zdravko', 'Zebić', '2007-8-22', '2208007469547', 'studentemail47@gmail.com', 'studentusername47', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (54, 'Miljan', 'Janković', '2007-10-14', '1410007419523', 'studentemail48@gmail.com', 'studentusername48', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (55, 'Draginja', 'Todorović', '2007-2-26', '2602007799164', 'studentemail49@gmail.com', 'studentusername49', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (56, 'Nataša', 'Dapčević', '2007-2-19', '1902007977934', 'studentemail50@gmail.com', 'studentusername50', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (57, 'Miljan', 'Georgijević', '2007-2-24', '2402007567499', 'studentemail51@gmail.com', 'studentusername51', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (58, 'Branislav', 'Marinković', '2007-2-6', '0602007955569', 'studentemail52@gmail.com', 'studentusername52', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (59, 'Gordana', 'Krstić', '2007-11-4', '0411007719333', 'studentemail53@gmail.com', 'studentusername53', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (60, 'Danijela', 'Zorić', '2007-7-24', '2407007895853', 'studentemail54@gmail.com', 'studentusername54', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (61, 'Zdravko', 'Dapčević', '2007-10-17', '1710007395489', 'studentemail55@gmail.com', 'studentusername55', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (62, 'Milka', 'Vasić', '2007-4-17', '1704007938629', 'studentemail56@gmail.com', 'studentusername56', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (63, 'Cvetko', 'Lazarević', '2007-7-7', '0707007594958', 'studentemail57@gmail.com', 'studentusername57', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (64, 'Jana', 'Zorić', '2007-7-10', '1007007489947', 'studentemail58@gmail.com', 'studentusername58', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (65, 'Nenad', 'Filipović', '2007-10-17', '1710007271489', 'studentemail59@gmail.com', 'studentusername59', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
insert into students (id, name, surname, birth_date, jmbg, email, username, password, archived, role_id, nivo_sklonosti) values (66, 'Zorka', 'Vasić', '2007-6-20', '2006007579741', 'studentemail60@gmail.com', 'studentusername60', '$2a$10$JCYrt8QGHg4suBXWiRgjKu93h5DCq3yFDXMDsTY/Itkgeu3h3pCE6', false, 3, 'NEMA');
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------- Altering user_id_seq -----------------------------------------------------------------------------------------
alter sequence user_id_seq restart with 67;
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------ Inserting classes -------------------------------------------------------------------------------------------
insert into odeljenja (id, naziv, staresina_id) values (1, '||O-1', 4);
insert into odeljenja (id, naziv, staresina_id) values (2, '||O-2', 5);
insert into odeljenja (id, naziv, staresina_id) values (3, '||O-3', 6);
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
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 13);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 28);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 20);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 14);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 16);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 42);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 46);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 50);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 41);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 64);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 24);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 34);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 9);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 36);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 19);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 47);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 53);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 30);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 17);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (1, 48);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 65);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 61);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 38);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 21);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 56);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 18);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 23);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 45);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 11);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 54);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 57);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 52);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 26);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 27);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 25);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 66);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 29);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 51);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 49);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (2, 8);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 44);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 60);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 37);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 63);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 7);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 15);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 39);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 31);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 55);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 35);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 12);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 62);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 22);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 33);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 43);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 40);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 10);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 32);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 58);
insert into odeljenja_ucenici (odeljenje_id, ucenici_id) values (3, 59);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------- Inserting schools_teachers --------------------------------------------------------------------------------------
insert into schools_teachers (school_id, teachers_id) values (1, 4);
insert into schools_teachers (school_id, teachers_id) values (1, 5);
insert into schools_teachers (school_id, teachers_id) values (1, 6);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------- Inserting schools_classes ---------------------------------------------------------------------------------------
insert into schools_odeljenja (school_id, odeljenja_id) values (1, 1);
insert into schools_odeljenja (school_id, odeljenja_id) values (1, 2);
insert into schools_odeljenja (school_id, odeljenja_id) values (1, 3);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
