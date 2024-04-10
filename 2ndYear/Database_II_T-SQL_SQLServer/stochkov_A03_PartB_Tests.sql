--/*
--The following stored procedures insert dummy data
--and use test cases to test the stored procedures
--ListReservationsStarting, ListReservationsEnding & ListReservationsActive.
--*/

Use HVKW24_st;
GO
CREATE OR ALTER PROCEDURE TestListReservationsStarting
(@NumPassed INT OUTPUT)
AS BEGIN
SET @NumPassed = 0
DECLARE @TestStartTable TABLE (TestId INT, TestScenario VARCHAR(50), StartDate DATE);
INSERT INTO @TestStartTable VALUES 
	(1, 'No reservations start on that day.', CONVERT(DATE,'24-03-23',2)),
	(2, '1 reservation starts on that day', CONVERT(DATE,'24-03-24',2)),
	(3, '3 reservations start on that day', CONVERT(DATE,'24-04-08',2));
DECLARE 
	@Count INT, 
	@Scenario VARCHAR(50), 
	@Start DATE,
	@ReservationId INT,
	@StartDate DATE,
	@EndDate DATE;
SET @Count = 1;
PRINT UPPER('Tests for ListReservationsStarting:') + CHAR(10)
WHILE (@Count <= 3)
	BEGIN
		DECLARE	@CursorStart CURSOR, @PF VARCHAR(10);
		SET @Start = (SELECT StartDate FROM @TestStartTable WHERE TestId = @Count);
		SET @Scenario = (SELECT TestScenario FROM @TestStartTable WHERE TestId = @Count);
		SET @PF = 'Pass';
		EXECUTE ListReservationsStarting @StartDate = @Start, @StartingCursor = @CursorStart OUTPUT;
		PRINT 'Test Case ' + CONVERT(VARCHAR, @Count) + ': ' + @Scenario;
		FETCH NEXT FROM @CursorStart INTO @ReservationId, @StartDate, @EndDate;
		WHILE @@FETCH_STATUS = 0
			BEGIN
				PRINT 'Reservation from ' + CONVERT(VARCHAR, @StartDate) + ' to ' + CONVERT(VARCHAR, @EndDate);
				IF (@StartDate != @Start)
					SET @PF = 'Fail';
				FETCH NEXT FROM @CursorStart INTO @ReservationId, @StartDate, @EndDate;
			END;
		CLOSE @CursorStart;
		DEALLOCATE @CursorStart;
		IF (@PF LIKE 'Pass')
			SET @NumPassed = @NumPassed + 1;
		PRINT UPPER(@PF) + CHAR(10);
		SET @Count = @Count + 1;
	END;
END;
GO

CREATE OR ALTER PROCEDURE TestListReservationsEnding
(@NumPassed INT OUTPUT)
AS BEGIN
SET @NumPassed = 0;
DECLARE @TestEndTable TABLE (TestId INT, TestScenario VARCHAR(50), EndDate DATE);
INSERT INTO @TestEndTable VALUES 
	(1, 'No reservations end on that day.', CONVERT(DATE,'24-04-01',2)),
	(2, '1 reservation ends on that day', CONVERT(DATE,'24-04-12',2)),
	(3, '5 reservations end on that day', CONVERT(DATE,'24-03-14',2));
DECLARE 
	@Count INT, 
	@Scenario VARCHAR(50), 
	@End DATE,
	@ReservationId INT,
	@StartDate DATE,
	@EndDate DATE;
SET @Count = 1;
PRINT UPPER('Tests for ListReservationsEnding:') + CHAR(10)
WHILE (@Count <= 3)
	BEGIN
		DECLARE	@CursorEnd CURSOR, @PF VARCHAR(10);
		SET @End = (SELECT EndDate FROM @TestEndTable WHERE TestId = @Count);
		SET @Scenario = (SELECT TestScenario FROM @TestEndTable WHERE TestId = @Count);
		SET @PF = 'Pass';
		EXECUTE ListReservationsEnding @EndDate = @End, @EndingCursor = @CursorEnd OUTPUT;
		PRINT 'Test Case ' + CONVERT(VARCHAR, @Count) + ': ' + @Scenario;
		FETCH NEXT FROM @CursorEnd INTO @ReservationId, @StartDate, @EndDate;
		WHILE @@FETCH_STATUS = 0
			BEGIN
				PRINT 'Reservation from ' + CONVERT(VARCHAR, @StartDate) + ' to ' + CONVERT(VARCHAR, @EndDate);
				IF (@EndDate != @End)
					SET @PF = 'Fail';
				FETCH NEXT FROM @CursorEnd INTO @ReservationId, @StartDate, @EndDate;
			END;
		CLOSE @CursorEnd;
		DEALLOCATE @CursorEnd;
		PRINT UPPER(@PF) + CHAR(10);
		IF (@PF LIKE 'Pass')
			SET @NumPassed = @NumPassed + 1;
		SET @Count = @Count + 1;
	END;
END;
GO

CREATE OR ALTER PROCEDURE TestListReservationsActive 
(@NumPassed INT OUTPUT)
AS BEGIN
SET @NumPassed = 0;
DECLARE @TestActiveTable TABLE (TestId INT, TestScenario VARCHAR(50), CustNum INT, ExpectedName VARCHAR(25));
INSERT INTO @TestActiveTable VALUES 
	(1, 'No CustomerNumber provided', NULL, NULL),
	(2, 'CustomerNumber = 2 - No reservations active', 2, NULL),
	(3, 'CustomerNumber = 3 - 1 reservation active', 3, 'Wong'),
	(4, 'CustomerNumber = 4 - 2 reservations active', 4, 'Coate');
DECLARE 
	@Count INT, 
	@Scenario VARCHAR(50), 
	@CustId INT,
	@ReservationId INT,
	@FirstName VARCHAR(25),
	@LastName VARCHAR(25),
	@PetId INT,
	@PetName VARCHAR(25),
	@StartDate DATE,
	@EndDate DATE;
SET @Count = 1;
PRINT UPPER('Tests for ListReservationsActive:') + CHAR(10)
WHILE (@Count <= 4)
	BEGIN
		DECLARE	@CursorActive CURSOR, @PF VARCHAR(10), @ExpectedLast VARCHAR(25);
		SET @CustId = (SELECT CustNum FROM @TestActiveTable WHERE TestId = @Count);
		SET @Scenario = (SELECT TestScenario FROM @TestActiveTable WHERE TestId = @Count);
		SET @ExpectedLast = (SELECT ExpectedName FROM @TestActiveTable WHERE TestId = @Count);
		SET @PF = 'Pass';
		EXECUTE ListReservationsActive @CustomerNumber = @CustId, @ActiveCursor = @CursorActive OUTPUT;
		PRINT 'Test Case ' + CONVERT(VARCHAR, @Count) + ': ' + @Scenario;
		FETCH NEXT FROM @CursorActive INTO @ReservationId, @FirstName, @LastName, @PetId, @PetName, @StartDate, @EndDate;
		WHILE @@FETCH_STATUS = 0
			BEGIN
				PRINT 'Reservation from ' + CONVERT(VARCHAR, @StartDate) + ' to ' + CONVERT(VARCHAR, @EndDate);
				IF (@ExpectedLast != @LastName)
					SET @PF = 'Fail';
				FETCH NEXT FROM @CursorActive INTO @ReservationId, @FirstName, @LastName, @PetId, @PetName, @StartDate, @EndDate;
			END;
		CLOSE @CursorActive;
		DEALLOCATE @CursorActive;
		PRINT UPPER(@PF) + CHAR(10);
		IF (@PF LIKE 'Pass')
			SET @NumPassed = @NumPassed + 1;
		SET @Count = @Count + 1;
	END;
END;

GO

CREATE OR ALTER PROCEDURE TestAll
(@PassFail BIT OUTPUT)
AS BEGIN
BEGIN TRANSACTION

	-- INSERT TEST DATA

	INSERT INTO dbo.Reservation (StartDate, EndDate, Status) VALUES (CONVERT(DATE,'24-03-24',2), CONVERT(DATE,'24-04-08',2), 3);
	INSERT INTO dbo.Reservation (StartDate, EndDate, Status) VALUES (CONVERT(DATE,'24-04-08',2), CONVERT(DATE,'24-04-09',2), 2);
	INSERT INTO dbo.Reservation (StartDate, EndDate, Status) VALUES (CONVERT(DATE,'24-04-08',2), CONVERT(DATE,'24-04-12',2), 2);
	INSERT INTO dbo.Reservation (StartDate, EndDate, Status) VALUES (CONVERT(DATE,'24-04-08',2), CONVERT(DATE,'24-05-08',2), 2);

	INSERT INTO dbo.Reservation (StartDate, EndDate, Status) VALUES (CONVERT(DATE,'24-02-22',2), CONVERT(DATE,'24-03-14',2), 5);
	INSERT INTO dbo.Reservation (StartDate, EndDate, Status) VALUES (CONVERT(DATE,'24-03-01',2), CONVERT(DATE,'24-03-14',2), 5);
	INSERT INTO dbo.Reservation (StartDate, EndDate, Status) VALUES (CONVERT(DATE,'24-03-05',2), CONVERT(DATE,'24-03-14',2), 5);
	INSERT INTO dbo.Reservation (StartDate, EndDate, Status) VALUES (CONVERT(DATE,'24-03-10',2), CONVERT(DATE,'24-03-14',2), 5);
	INSERT INTO dbo.Reservation (StartDate, EndDate, Status) VALUES (CONVERT(DATE,'24-03-13',2), CONVERT(DATE,'24-03-14',2), 5);

	DECLARE 
		@StartingPassed INT,
		@EndingPassed INT,
		@ActivePassed INT,
		@TotalPassed INT;

	DECLARE @TestSummary TABLE (Type VARCHAR(15), TestsRun INT, TestsPassed INT, TestsFailed INT);
	
	-- EXECUTE STORED PROCS Start & End 

	EXECUTE TestListReservationsStarting @NumPassed = @StartingPassed OUTPUT;
	EXECUTE TestListReservationsEnding @NumPassed = @EndingPassed OUTPUT;

ROLLBACK;

BEGIN TRANSACTION

	SET IDENTITY_INSERT dbo.Reservation ON;
	INSERT INTO dbo.Reservation (ReservationId, StartDate, EndDate, Status) VALUES (1, CONVERT(DATE,'24-04-01',2), CONVERT(DATE,'24-07-01',2), 2);
	INSERT INTO dbo.Reservation (ReservationId, StartDate, EndDate, Status) VALUES (2, CONVERT(DATE,'24-04-03',2), CONVERT(DATE,'24-08-05',2), 2);
	INSERT INTO dbo.Reservation (ReservationId, StartDate, EndDate, Status) VALUES (3, CONVERT(DATE,'24-03-25',2), CONVERT(DATE,'24-07-25',2), 2);
	SET IDENTITY_INSERT dbo.Reservation OFF;

	SET IDENTITY_INSERT dbo.PetReservation ON;
	INSERT INTO dbo.PetReservation (PetReservationId, PetId, ReservationId) VALUES (1, 13, 1);
	INSERT INTO dbo.PetReservation (PetReservationId, PetId, ReservationId) VALUES (2, 7, 2);
	INSERT INTO dbo.PetReservation (PetReservationId, PetId, ReservationId) VALUES (3, 7, 3);
	SET IDENTITY_INSERT dbo.PetReservation OFF;

	-- EXECUTE STORED PROC Active

	EXECUTE TestListReservationsActive @NumPassed = @ActivePassed OUTPUT;

ROLLBACK;

SET @TotalPassed = @StartingPassed + @EndingPassed + @ActivePassed;
INSERT INTO @TestSummary VALUES ('Quantity', 10, @TotalPassed, 10 - @TotalPassed);
INSERT INTO @TestSummary VALUES ('Percentage', 100, @TotalPassed * 10, 100 - (@TotalPassed * 10));
SELECT * FROM @TestSummary;

DBCC CHECKIDENT ('[Reservation]', RESEED, 0);
END;

GO
DECLARE @Result BIT;
EXECUTE TestAll @PassFail = @Result;
GO