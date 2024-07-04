DROP PROCEDURE IF EXISTS GetAllSeats;
CREATE PROCEDURE GetAllSeats()
BEGIN
SELECT floor_seat_seq,floor_no,seat_no FROM seatInfo;
END ;

CREATE PROCEDURE GetSeatById(IN p_seatId INT)
BEGIN
SELECT floor_seat_seq,floor_no,seat_no  FROM seatInfo
WHERE floor_seat_seq = p_seatId;
END;

DROP PROCEDURE IF EXISTS CreateSeat;
CREATE PROCEDURE CreateSeat( IN p_floorNo INT, IN p_seatNo INT)
BEGIN
INSERT INTO seatInfo(floor_no, seat_no)
VALUES (p_floorNo, p_seatNo);
SELECT LAST_INSERT_ID() AS floor_seat_seq;
END;
