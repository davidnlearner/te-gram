BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, posts, comments, images, following, liked, favorites, profiles;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) unique NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE posts (
        post_id serial,
        user_id int NOT NULL,
        date_posted VARCHAR(25) NOT NULL,
        caption varchar (150),
        CONSTRAINT PK_post PRIMARY KEY (post_id),
        CONSTRAINT FK_user_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE comments (
        comment_id serial,
        user_id int NOT NULL,
        post_id int NOT NULL,
        datetime_posted VARCHAR(25) NOT NULL,
        comment_text varchar (150),
        CONSTRAINT PK_comment PRIMARY KEY (comment_id),
        CONSTRAINT FK_post_id FOREIGN KEY (post_id) REFERENCES posts (post_id)
);

CREATE TABLE images (
        image_id serial,
        post_id int NOT NULL,
        image_url varchar (200),
        CONSTRAINT PK_image PRIMARY KEY (image_id),
        CONSTRAINT FK_image_post_id FOREIGN KEY (post_id) REFERENCES posts (post_id)
);

CREATE TABLE following (
        user_id_follower int NOT NULL,
        user_id_following int NOT NULL,
        CONSTRAINT FK_follower FOREIGN KEY (user_id_follower) REFERENCES users (user_id),
        CONSTRAINT FK_following FOREIGN KEY (user_id_following) REFERENCES users (user_id)
);

CREATE TABLE favorites (
        user_id_favorite int NOT NULL,
        post_id_favorite int NOT NULL,
        CONSTRAINT FK_user_favorite FOREIGN KEY (user_id_favorite) REFERENCES users (user_id),
        CONSTRAINT FK_post_favorite FOREIGN KEY (post_id_favorite) REFERENCES posts (post_id)
);

CREATE TABLE liked (
        user_id_liked int NOT NULL,
        post_id_liked int NOT NULL,
        CONSTRAINT FK_user_liked FOREIGN KEY (user_id_liked) REFERENCES users (user_id),
        CONSTRAINT FK_post_liked FOREIGN KEY (post_id_liked) REFERENCES posts (post_id)
);

CREATE TABLE profiles (
        user_id int NOT NULL,
        profile_name varchar (50) NOT NULL,
        description varchar (350) NOT NULL,
        profile_image varchar(200) NOT NULL,
        CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);



INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;
