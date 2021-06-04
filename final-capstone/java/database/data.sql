/* TEST DATA FILE */

BEGIN TRANSACTION;

/* NON DEFAULT USERS All PASSWORDS ARE SET TO "password" */

INSERT INTO users (username, password_hash, role)
VALUES ('austin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN'),
('morgan', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN'),
('david', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
('seth', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

COMMIT;


BEGIN TRANSACTION;
/* PROFILES */

INSERT INTO profiles (user_id, profile_name, description, profile_image) 
VALUES (1, 'R2-D2', 
'Beep Beep Be Deep Boop!', 
'http://res.cloudinary.com/tegram2021/image/upload/v1622735645/lpkzbw7jbvwihvvq143k.jpg');

INSERT INTO profiles (user_id, profile_name, description, profile_image) 
VALUES (2, 'HAL 9000', 
'I know Ive made some very poor decisions recently, but I can give you my complete assurance that my work will be back to normal. Ive still got the greatest enthusiasm and confidence in the mission. And I want to help you.', 
'http://res.cloudinary.com/tegram2021/image/upload/v1622735448/qrduon2aioox59muf3hq.png');

INSERT INTO profiles (user_id, profile_name, description, profile_image) 
VALUES (3, 'AUSTIN D', 
'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book', 
'http://res.cloudinary.com/tegram2021/image/upload/v1622735352/wvnzv09x2w7tahzfb883.png');

INSERT INTO profiles (user_id, profile_name, description, profile_image) 
VALUES (4, 'MORGAN B', 
'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book', 
'http://res.cloudinary.com/tegram2021/image/upload/v1622734957/vozzs95de8ltzqg5wofp.jpg');

INSERT INTO profiles (user_id, profile_name, description, profile_image) 
VALUES (5, 'DAVID L', 
'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book', 
'http://res.cloudinary.com/tegram2021/image/upload/v1622735263/d5lcibbsshcd6wmypidu.jpg');

INSERT INTO profiles (user_id, profile_name, description, profile_image) 
VALUES (6, 'SETH G',
'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book', 
'http://res.cloudinary.com/tegram2021/image/upload/v1622735076/dos7i5as3l3qk61iit0s.png');

/* POSTS */

INSERT INTO posts (user_id, date_posted, caption)
VALUES (4,'2019-03-04T11:30:00', 'Great!'),(2,'2019-04-09T10:40:00', 'Wow!'), (1,'2021-06-01T12:29:00', 'Boop Dee Boop!'), (5,'2021-05-29T12:40:00', 'OWLS!'),
(3,'2021-06-01T12:40:00', 'Pretty!'), (1,'2021-06-01T12:41:00', 'Beep boop!');

INSERT INTO images (post_id, image_url)
VALUES (1, 'https://i2.wp.com/thehorrorsyndicate.com/wp-content/uploads/2018/05/toxie.jpg?resize=678%2C381'),
(1,'https://3.bp.blogspot.com/-1iuUkyzpEyU/Th6Sox0V8yI/AAAAAAAAB4k/5sw0iyR8JBw/s640/toxic+french+poster.jpg'),
(2, 'https://res.cloudinary.com/tegram2021/image/upload/v1622662801/e3mb90ja4aded0pn48sf.jpg'),
(3, 'http://res.cloudinary.com/tegram2021/image/upload/v1622662811/qcluhe54lvg5ceifnajo.jpg'),
(4, 'http://res.cloudinary.com/tegram2021/image/upload/v1622575269/bocsuf8b3xiuessssn7n.jpg'),
(4, 'http://res.cloudinary.com/tegram2021/image/upload/v1622575269/tkuqjtvps8k6jrdxrbkl.jpg'),
(5, 'http://res.cloudinary.com/tegram2021/image/upload/v1622662791/d71pkxghkngpysqqyxna.jpg'),
(6, 'https://capturelandscapes.com/wp-content/uploads/2017/03/DSC2441-Panorama.jpeg'),
(6, 'https://photographycourse.net/wp-content/uploads/2014/11/Landscape-Photography-steps.jpg'),
(6, 'https://i1.wp.com/airlinkalaska.com/wp-content/uploads/safe-1-1-scaled.jpg?resize=1080%2C675&ssl=1');

/* Likes */
INSERT INTO liked (user_id_liked, post_id_liked)
VALUES (3, 1), (4, 1), (5, 1), (6,1), (6,2), (6,3);

/* follows */
INSERT INTO following (user_id_follower, user_id_following)
VALUES (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (2, 1), (2, 4), (2, 5), (3, 4), (4, 1), (3, 1), (3, 5), (5, 1), (5, 3), (5, 4), (3, 6) ;

INSERT INTO favorites (user_id_favorite, post_id_favorite)
VALUES (3,2);

INSERT INTO comments (user_id, post_id, datetime_posted, comment_text)
VALUES (4, 2, '2021-03-04T11:30:00', 'Nice'),
(5, 2, '2020-04-09T10:40:00', 'Awesome'),
(6, 1, '2021-06-01T12:40:00', 'Cool');


COMMIT TRANSACTION;