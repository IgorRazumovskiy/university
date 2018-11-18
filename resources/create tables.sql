CREATE TABLE "faculty"
(
    "id" serial NOT NULL,
    "name" character varying(30) NOT NULL,
    CONSTRAINT faculty_pk PRIMARY KEY ("id")
);

CREATE TABLE "chair"
(
    "id" serial NOT NULL,
    "name" character varying(30) NOT NULL,
    "faculty_id" bigint NOT NULL,
    CONSTRAINT chair_pk PRIMARY KEY ("id"),
    CONSTRAINT "chair_fk0" FOREIGN KEY ("faculty_id") 
        REFERENCES "faculty"("id") ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE "classroom" (
    "id" serial NOT NULL,
    "number" character varying(30) NOT NULL,
    "capacity" bigint NOT NULL,
    "faculty_id" bigint NOT NULL,
    CONSTRAINT classroom_pk PRIMARY KEY ("id"),
    CONSTRAINT "classroom_fk0" FOREIGN KEY ("faculty_id")
        REFERENCES "faculty"("id") ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE "group"
(
    "id" serial NOT NULL,
    "name" character varying(30) NOT NULL,
    "faculty_id" bigint NOT NULL,
    CONSTRAINT group_pk PRIMARY KEY ("id"),
    CONSTRAINT "group_fk0" FOREIGN KEY ("faculty_id")
        REFERENCES "faculty"("id") ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE "student"
(
    "id" serial NOT NULL,
    "name" character varying(50) NOT NULL,
    "group_id" bigint NOT NULL,
    CONSTRAINT student_pk PRIMARY KEY ("id"),
    CONSTRAINT "student_fk0" FOREIGN KEY ("group_id")
        REFERENCES "group"("id") ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE "course"
(
    "id" serial NOT NULL,
    "name" character varying(30) NOT NULL,
    "chair_id" bigint NOT NULL,
    CONSTRAINT course_pk PRIMARY KEY ("id"),
    CONSTRAINT "course_fk0" FOREIGN KEY ("chair_id")
        REFERENCES "chair"("id") ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE "teacher"
(
    "id" serial NOT NULL,
    "name" character varying(50) NOT NULL,
    "chair_id" bigint NOT NULL,
    CONSTRAINT teacher_pk PRIMARY KEY ("id"),
    CONSTRAINT "teacher_fk0" FOREIGN KEY ("chair_id")
        REFERENCES "chair"("id") ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE "course_teacher"
(
    "course_id" bigint NOT NULL,
    "teacher_id" bigint NOT NULL,
    CONSTRAINT "course_teacher_fk0" FOREIGN KEY ("course_id")
        REFERENCES "course"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT "course_teacher_fk1" FOREIGN KEY ("teacher_id")
        REFERENCES "teacher"("id") ON UPDATE CASCADE ON DELETE CASCADE    
);

CREATE TABLE "lesson"
(
    "id" serial NOT NULL,
    "teacher_id" bigint NOT NULL,
    "group_id" bigint NOT NULL,
    "classroom_id" bigint NOT NULL,
    "course_id" bigint NOT NULL,
    "dateTime" bigint NOT NULL,
    CONSTRAINT lesson_pk PRIMARY KEY ("id"),
    CONSTRAINT "lesson_fk0" FOREIGN KEY ("teacher_id")
        REFERENCES "teacher"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT "lesson_fk1" FOREIGN KEY ("group_id")
        REFERENCES "group"("id") ON UPDATE CASCADE ON DELETE CASCADE,    
    CONSTRAINT "lesson_fk2" FOREIGN KEY ("classroom_id")
        REFERENCES "classroom"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT "lesson_fk3" FOREIGN KEY ("course_id")
        REFERENCES "course"("id") ON UPDATE CASCADE ON DELETE CASCADE
);