USE LibraryDB;
GO

-- =====================================================
-- STEP 1: DELETE ALL EXISTING DATA
-- =====================================================

DELETE FROM IssueRecords;
DELETE FROM Members;
DELETE FROM Books;
DELETE FROM Users;

GO

-- =====================================================
-- STEP 2: RESET IDENTITY COUNTERS
-- =====================================================

DBCC CHECKIDENT ('IssueRecords', RESEED, 0);
DBCC CHECKIDENT ('Members', RESEED, 0);
DBCC CHECKIDENT ('Books', RESEED, 0);
DBCC CHECKIDENT ('Users', RESEED, 0);

GO

-- =====================================================
-- STEP 3: INSERT FRESH DATA
-- =====================================================

-- Insert Admin User
INSERT INTO Users (username, password, full_name, role) VALUES
('admin', 'admin123', 'Administrator', 'ADMIN');

-- Insert Librarians
INSERT INTO Users (username, password, full_name, role) VALUES
('librarian1', 'lib123', 'Priya Sharma', 'LIBRARIAN'),
('librarian2', 'lib123', 'Rajesh Kumar', 'LIBRARIAN');

GO

-- =====================================================
-- INSERT BOOKS (Sequential IDs: 1-30)
-- =====================================================

-- Technology Books (1-5)
INSERT INTO Books (isbn, title, author, publisher, category, quantity, available_quantity, rack_no) VALUES
('978-0134685991', 'Effective Java', 'Joshua Bloch', 'Addison-Wesley', 'Technology', 5, 5, 'A-10'),
('978-0132350884', 'Clean Code', 'Robert C. Martin', 'Prentice Hall', 'Technology', 4, 4, 'A-11'),
('978-0201633610', 'Design Patterns', 'Gang of Four', 'Addison-Wesley', 'Technology', 3, 3, 'A-12'),
('978-0596009205', 'Head First Design Patterns', 'Eric Freeman', 'OReilly Media', 'Technology', 6, 6, 'A-13'),
('978-0137081073', 'The Clean Coder', 'Robert C. Martin', 'Prentice Hall', 'Technology', 3, 3, 'A-14');

-- Fiction Books (6-10)
INSERT INTO Books (isbn, title, author, publisher, category, quantity, available_quantity, rack_no) VALUES
('978-0143039433', 'The White Tiger', 'Aravind Adiga', 'Penguin', 'Fiction', 8, 8, 'B-20'),
('978-0143420415', 'The God of Small Things', 'Arundhati Roy', 'Penguin', 'Fiction', 6, 6, 'B-21'),
('978-0143031031', 'Train to Pakistan', 'Khushwant Singh', 'Penguin', 'Fiction', 5, 5, 'B-22'),
('978-0143065487', 'The Namesake', 'Jhumpa Lahiri', 'Houghton Mifflin', 'Fiction', 7, 7, 'B-23'),
('978-0140274707', '2 States', 'Chetan Bhagat', 'Rupa Publications', 'Fiction', 10, 10, 'B-24');

-- Science Books (11-14)
INSERT INTO Books (isbn, title, author, publisher, category, quantity, available_quantity, rack_no) VALUES
('978-0553380163', 'A Brief History of Time', 'Stephen Hawking', 'Bantam', 'Science', 4, 4, 'C-30'),
('978-0385537209', 'The Grand Design', 'Stephen Hawking', 'Bantam', 'Science', 3, 3, 'C-31'),
('978-0143127796', 'Sapiens', 'Yuval Noah Harari', 'Penguin', 'Science', 6, 6, 'C-32'),
('978-1784701994', 'Homo Deus', 'Yuval Noah Harari', 'Harvill Secker', 'Science', 5, 5, 'C-33');

-- Self-Help Books (15-18)
INSERT INTO Books (isbn, title, author, publisher, category, quantity, available_quantity, rack_no) VALUES
('978-1501139154', 'The 7 Habits of Highly Effective People', 'Stephen Covey', 'Simon & Schuster', 'Self-Help', 5, 5, 'D-40'),
('978-0062457714', 'The Subtle Art of Not Giving', 'Mark Manson', 'HarperOne', 'Self-Help', 8, 8, 'D-41'),
('978-0062316097', 'Atomic Habits', 'James Clear', 'Avery', 'Self-Help', 7, 7, 'D-42'),
('978-1501110368', 'How to Win Friends', 'Dale Carnegie', 'Simon & Schuster', 'Self-Help', 6, 6, 'D-43');

-- History Books (19-21)
INSERT INTO Books (isbn, title, author, publisher, category, quantity, available_quantity, rack_no) VALUES
('978-0143031116', 'India After Gandhi', 'Ramachandra Guha', 'Penguin', 'History', 4, 4, 'E-50'),
('978-0143424208', 'The Discovery of India', 'Jawaharlal Nehru', 'Penguin', 'History', 5, 5, 'E-51'),
('978-0143031185', 'Freedom at Midnight', 'Larry Collins', 'Vikas Publishing', 'History', 3, 3, 'E-52');

-- Biography Books (22-25)
INSERT INTO Books (isbn, title, author, publisher, category, quantity, available_quantity, rack_no) VALUES
('978-1501139239', 'Steve Jobs', 'Walter Isaacson', 'Simon & Schuster', 'Biography', 5, 5, 'F-60'),
('978-0143424031', 'Wings of Fire', 'APJ Abdul Kalam', 'Universities Press', 'Biography', 8, 8, 'F-61'),
('978-0143064558', 'Long Walk to Freedom', 'Nelson Mandela', 'Little Brown', 'Biography', 4, 4, 'F-62'),
('978-1476735719', 'Elon Musk', 'Ashlee Vance', 'Ecco', 'Biography', 6, 6, 'F-63');

-- Academic Books (26-29)
INSERT INTO Books (isbn, title, author, publisher, category, quantity, available_quantity, rack_no) VALUES
('978-0073523323', 'Database System Concepts', 'Silberschatz', 'McGraw-Hill', 'Academic', 10, 10, 'G-70'),
('978-0133594140', 'Operating System Concepts', 'Silberschatz', 'Wiley', 'Academic', 8, 8, 'G-71'),
('978-0262033848', 'Introduction to Algorithms', 'Cormen', 'MIT Press', 'Academic', 7, 7, 'G-72'),
('978-0131103627', 'The C Programming Language', 'Kernighan & Ritchie', 'Prentice Hall', 'Academic', 12, 12, 'G-73');

-- Children Books (30-32)
INSERT INTO Books (isbn, title, author, publisher, category, quantity, available_quantity, rack_no) VALUES
('978-0439139595', 'Harry Potter and the Sorcerer Stone', 'J.K. Rowling', 'Scholastic', 'Children', 15, 15, 'H-80'),
('978-0545162074', 'The Hunger Games', 'Suzanne Collins', 'Scholastic', 'Children', 10, 10, 'H-81'),
('978-0141354934', 'The Diary of a Young Girl', 'Anne Frank', 'Penguin', 'Children', 6, 6, 'H-82');

GO

-- =====================================================
-- INSERT MEMBERS (Sequential IDs: 1-12)
-- =====================================================

INSERT INTO Members (member_no, full_name, email, phone, address, status) VALUES
('MEM001', 'Rahul Sharma', 'rahul.sharma@email.com', '9876543210', '123, MG Road, Delhi', 'ACTIVE'),
('MEM002', 'Priya Singh', 'priya.singh@email.com', '9876543211', '456, Park Street, Mumbai', 'ACTIVE'),
('MEM003', 'Amit Kumar', 'amit.kumar@email.com', '9876543212', '789, Brigade Road, Bangalore', 'ACTIVE'),
('MEM004', 'Sneha Patel', 'sneha.patel@email.com', '9876543213', '321, Anna Salai, Chennai', 'ACTIVE'),
('MEM005', 'Vikram Reddy', 'vikram.reddy@email.com', '9876543214', '654, Banjara Hills, Hyderabad', 'ACTIVE'),
('MEM006', 'Anjali Verma', 'anjali.verma@email.com', '9876543215', '987, Residency Road, Pune', 'ACTIVE'),
('MEM007', 'Ravi Gupta', 'ravi.gupta@email.com', '9876543216', '147, Civil Lines, Jaipur', 'ACTIVE'),
('MEM008', 'Neha Kapoor', 'neha.kapoor@email.com', '9876543217', '258, Sector 17, Chandigarh', 'ACTIVE'),
('MEM009', 'Karan Malhotra', 'karan.malhotra@email.com', '9876543218', '369, Mall Road, Shimla', 'ACTIVE'),
('MEM010', 'Pooja Joshi', 'pooja.joshi@email.com', '9876543219', '741, MG Road, Kochi', 'ACTIVE'),
('MEM011', 'Suresh Nair', 'suresh.nair@email.com', '9876543220', '852, Beach Road, Goa', 'INACTIVE'),
('MEM012', 'Divya Iyer', 'divya.iyer@email.com', '9876543221', '963, Lake View, Udaipur', 'ACTIVE');

GO

-- =====================================================
-- INSERT ISSUE RECORDS (Sequential IDs: 1-10)
-- =====================================================

-- Current Issues (Recently issued - IDs 1-4)
INSERT INTO IssueRecords (book_id, member_id, issue_date, due_date, status, issued_by) VALUES
(1, 1, DATEADD(day, -5, CAST(GETDATE() AS DATE)), DATEADD(day, 9, CAST(GETDATE() AS DATE)), 'ISSUED', 1),
(2, 2, DATEADD(day, -3, CAST(GETDATE() AS DATE)), DATEADD(day, 11, CAST(GETDATE() AS DATE)), 'ISSUED', 1),
(6, 3, DATEADD(day, -7, CAST(GETDATE() AS DATE)), DATEADD(day, 7, CAST(GETDATE() AS DATE)), 'ISSUED', 1),
(10, 4, DATEADD(day, -2, CAST(GETDATE() AS DATE)), DATEADD(day, 12, CAST(GETDATE() AS DATE)), 'ISSUED', 1);

-- Update available quantity for issued books
UPDATE Books SET available_quantity = 4 WHERE book_id = 1;
UPDATE Books SET available_quantity = 3 WHERE book_id = 2;
UPDATE Books SET available_quantity = 7 WHERE book_id = 6;
UPDATE Books SET available_quantity = 9 WHERE book_id = 10;

-- Overdue Issues (IDs 5-6)
INSERT INTO IssueRecords (book_id, member_id, issue_date, due_date, status, issued_by) VALUES
(11, 5, DATEADD(day, -25, CAST(GETDATE() AS DATE)), DATEADD(day, -11, CAST(GETDATE() AS DATE)), 'ISSUED', 1),
(12, 6, DATEADD(day, -30, CAST(GETDATE() AS DATE)), DATEADD(day, -16, CAST(GETDATE() AS DATE)), 'ISSUED', 1);

-- Update available quantity for overdue books
UPDATE Books SET available_quantity = 3 WHERE book_id = 11;
UPDATE Books SET available_quantity = 2 WHERE book_id = 12;

-- Returned Records (IDs 7-10)
INSERT INTO IssueRecords (book_id, member_id, issue_date, due_date, return_date, fine_amount, status, issued_by) VALUES
(3, 7, DATEADD(day, -40, CAST(GETDATE() AS DATE)), DATEADD(day, -26, CAST(GETDATE() AS DATE)), DATEADD(day, -28, CAST(GETDATE() AS DATE)), 10.00, 'RETURNED', 1),
(4, 8, DATEADD(day, -35, CAST(GETDATE() AS DATE)), DATEADD(day, -21, CAST(GETDATE() AS DATE)), DATEADD(day, -20, CAST(GETDATE() AS DATE)), 0.00, 'RETURNED', 1),
(7, 9, DATEADD(day, -50, CAST(GETDATE() AS DATE)), DATEADD(day, -36, CAST(GETDATE() AS DATE)), DATEADD(day, -30, CAST(GETDATE() AS DATE)), 30.00, 'RETURNED', 1),
(8, 10, DATEADD(day, -45, CAST(GETDATE() AS DATE)), DATEADD(day, -31, CAST(GETDATE() AS DATE)), DATEADD(day, -31, CAST(GETDATE() AS DATE)), 0.00, 'RETURNED', 1);

GO

-- =====================================================
-- VERIFICATION
-- =====================================================

PRINT '================================================';
PRINT 'DATA INSERTION COMPLETE!';
PRINT '================================================';
PRINT '';

SELECT 'Books' as Table_Name, COUNT(*) as Total_Records, 
       MIN(book_id) as First_ID, MAX(book_id) as Last_ID FROM Books;

SELECT 'Members' as Table_Name, COUNT(*) as Total_Records, 
       MIN(member_id) as First_ID, MAX(member_id) as Last_ID FROM Members;

SELECT 'IssueRecords' as Table_Name, COUNT(*) as Total_Records, 
       MIN(issue_id) as First_ID, MAX(issue_id) as Last_ID FROM IssueRecords;

SELECT 'Users' as Table_Name, COUNT(*) as Total_Records FROM Users;

PRINT '';
PRINT 'Books: IDs 1-32 (Sequential)';
PRINT 'Members: IDs 1-12 (Sequential)';
PRINT 'Issue Records: IDs 1-10 (Sequential)';
PRINT 'Users: 3 (admin, librarian1, librarian2)';

GO