DROP PROCEDURE IF EXISTS GetAllSeats;
CREATE PROCEDURE GetAllSeats()
BEGIN
    SELECT floor_seat_seq,floor_no,seat_no FROM seatInfo;
END ;

CREATE PROCEDURE GetAllEmployees()
BEGIN
SELECT emp_id,name,email,seat_id  FROM employeeInfo;
END
