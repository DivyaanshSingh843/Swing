-- Step 1: Create Database
CREATE DATABASE StudentDB;
GO

-- Step 2: Use Database
USE StudentDB;
GO

-- Step 3: Create Students Table
CREATE TABLE Students (
    RollNo VARCHAR(20) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Course VARCHAR(50) NOT NULL,
    Phone VARCHAR(15) NOT NULL,
    Email VARCHAR(100) NOT NULL
);
GO

-- Step 4: Insert Sample Data (Optional - for testing)
INSERT INTO Students (RollNo, Name, Course, Phone, Email) VALUES
('R001', 'Rahul Kumar', 'BCA', '9876543210', 'rahul@email.com'),
('R002', 'Priya Sharma', 'B.Sc CS', '9876543211', 'priya@email.com'),
('R003', 'Amit Singh', 'MCA', '9876543212', 'amit@email.com');
GO

-- Step 5: Verify Table
SELECT * FROM Students;
GO