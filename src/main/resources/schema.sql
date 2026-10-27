-- ========================================================
-- Placement Management System Database Schema (MySQL 8.0+)
-- ========================================================

CREATE DATABASE IF NOT EXISTS placement_db;
USE placement_db;

-- 1. Students Table
CREATE TABLE IF NOT EXISTS students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    roll_number VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(100) NOT NULL,
    cgpa DOUBLE NOT NULL,
    graduation_year INT NOT NULL,
    phone VARCHAR(20),
    skills VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. Company Drives Table
CREATE TABLE IF NOT EXISTS company_drives (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(150) NOT NULL,
    job_role VARCHAR(150) NOT NULL,
    package_lpa DOUBLE NOT NULL,
    location VARCHAR(150),
    min_cgpa DOUBLE NOT NULL,
    eligible_departments VARCHAR(255),
    drive_date DATE,
    status VARCHAR(30) NOT NULL DEFAULT 'OPEN',
    description TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. Applications Table
CREATE TABLE IF NOT EXISTS applications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    company_drive_id BIGINT NOT NULL,
    applied_at DATETIME NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'APPLIED',
    remarks VARCHAR(255),
    CONSTRAINT uk_student_drive UNIQUE (student_id, company_drive_id),
    CONSTRAINT fk_app_student FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    CONSTRAINT fk_app_drive FOREIGN KEY (company_drive_id) REFERENCES company_drives(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
