DROP TABLE IF EXISTS DEVELOPER;
DROP TABLE IF EXISTS STORY;
DROP TABLE IF EXISTS BUG;

CREATE TABLE DEVELOPER (
       ID INT NOT NULL AUTO_INCREMENT
     , NAME VARCHAR(60) NOT NULL
     , PRIMARY KEY (ID)
);

CREATE TABLE STORY (
       ID INT NOT NULL AUTO_INCREMENT
     , DEVELOPER INT NOT NULL
     , TITLE VARCHAR(60) NOT NULL
     , DESCRIPTION VARCHAR(600) NOT NULL
     , CREATION_DATE DATE
     , FOREIGN KEY (DEVELOPER) REFERENCES DEVELOPER (ID)
     , STORY_STATUS VARCHAR(60) NOT NULL
     , POINTS INT NOT NULL
);

CREATE TABLE BUG (
       ID INT NOT NULL AUTO_INCREMENT
     , DEVELOPER INT NOT NULL
     , TITLE VARCHAR(60) NOT NULL
     , DESCRIPTION VARCHAR(600) NOT NULL
     , CREATION_DATE DATE
     , FOREIGN KEY (DEVELOPER) REFERENCES DEVELOPER (ID)
);
