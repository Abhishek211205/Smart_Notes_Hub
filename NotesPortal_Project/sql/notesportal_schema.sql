-- Run this script in MySQL to create database and tables
CREATE DATABASE IF NOT EXISTS notesportal;
USE notesportal;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    role ENUM('student','teacher','admin') DEFAULT 'student'
);

CREATE TABLE IF NOT EXISTS notes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    subject VARCHAR(100),
    semester VARCHAR(20),
    filename VARCHAR(255),
    uploader_id INT,
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (uploader_id) REFERENCES users(id) ON DELETE SET NULL
);

-- sample data
INSERT INTO users (username, password, role) VALUES ('student1','1234','student'), ('teacher1','abcd','teacher');

INSERT INTO notes (title, subject, semester, filename, uploader_id) VALUES
('Intro to OS - Notes', 'Operating Systems', '4', 'sample_os_notes.pdf', 2);
