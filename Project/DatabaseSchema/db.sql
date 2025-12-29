-- Database banao
CREATE DATABASE LibraryDB;
GO

USE LibraryDB;
GO

-- Users table
CREATE TABLE Users (
    user_id INT PRIMARY KEY IDENTITY(1,1),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100),
    role VARCHAR(20) DEFAULT 'LIBRARIAN',
    created_at DATETIME DEFAULT GETDATE()
);

-- Books table
CREATE TABLE Books (
    book_id INT PRIMARY KEY IDENTITY(1,1),
    isbn VARCHAR(20) UNIQUE,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100),
    publisher VARCHAR(100),
    category VARCHAR(50),
    quantity INT DEFAULT 0,
    available_quantity INT DEFAULT 0,
    rack_no VARCHAR(20),
    added_date DATE DEFAULT GETDATE()
);

-- Members table
CREATE TABLE Members (
    member_id INT PRIMARY KEY IDENTITY(1,1),
    member_no VARCHAR(20) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(15),
    address VARCHAR(200),
    registration_date DATE DEFAULT GETDATE(),
    status VARCHAR(20) DEFAULT 'ACTIVE'
);

-- Issue Records table
CREATE TABLE IssueRecords (
    issue_id INT PRIMARY KEY IDENTITY(1,1),
    book_id INT FOREIGN KEY REFERENCES Books(book_id),
    member_id INT FOREIGN KEY REFERENCES Members(member_id),
    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    fine_amount DECIMAL(10,2) DEFAULT 0,
    status VARCHAR(20) DEFAULT 'ISSUED',
    issued_by INT FOREIGN KEY REFERENCES Users(user_id)
);

-- Default admin user
INSERT INTO Users (username, password, full_name, role) 
VALUES ('admin', 'admin123', 'Administrator', 'ADMIN');
GO