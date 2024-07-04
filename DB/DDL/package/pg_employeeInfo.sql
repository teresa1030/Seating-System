CREATE PROCEDURE GetAllEmployees()
BEGIN
SELECT emp_id,name,email,seat_id  FROM employeeInfo;
END;

CREATE PROCEDURE UpdateSeat(IN p_userId INT, IN p_seatId INT)
BEGIN
UPDATE employeeInfo
SET seat_id = p_seatId
WHERE emp_id = p_userId;
END;

CREATE PROCEDURE GetEmployeeById(IN p_userId INT)
BEGIN
SELECT emp_id,name,email,seat_id  FROM employeeInfo
WHERE emp_id = p_userId;
END;

CREATE PROCEDURE CreateEmployee( IN p_userId INT, IN p_name VARCHAR(32), IN p_email VARCHAR(32), IN p_seatId INT)
BEGIN
INSERT INTO employeeInfo (emp_id, name, email, seat_id)
VALUES (p_userId, p_name, p_email, p_seatId);
END;