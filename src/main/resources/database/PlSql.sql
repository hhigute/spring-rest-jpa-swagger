use DbInvestment;


CREATE OR ALTER FUNCTION dbo.FN_IOF(@days INT)
RETURNS DECIMAL(5,2)
AS BEGIN
    DECLARE @ret DECIMAL(5,2)
	
	SELECT @ret = ISNULL(PercentFee, 0) 
	FROM dbo.IofRference 
	WHERE NrDay = @days;


    RETURN @ret
	
	/*SELECT dbo.FN_IOF(5);*/
	
END;
go




CREATE OR ALTER FUNCTION dbo.FN_IR(@days INT)
RETURNS DECIMAL(5,2)
AS BEGIN
    DECLARE @ret DECIMAL(5,2)
	
	SELECT @ret =  ISNULL(PercentFee, 0)
	FROM dbo.IRReference 
	WHERE @days BETWEEN StartDay AND FinishDay;


    RETURN @ret
	
	/*SELECT dbo.FN_IR(5);*/
	
END;
go

create or alter procedure dbo.spSelectRiskLevel 
as
	-- 	exec dbo.spSelectRiskLevel 
	set nocount on;  
	select * from dbo.RiskLevel order by id;

go


create or alter procedure dbo.spInsertRiskLevel 
													@paramId int,
													@paramDescription nvarchar(20)
as
	-- exec dbo.spInsertRiskLevel @paramId = 4, @paramDescription = 'teste';
	set nocount on;  
	insert into dbo.RiskLevel(Id, Description) values (@paramId, @paramDescription);
go




create or alter procedure dbo.spUpdateRiskLevel 
													@paramId int,
													@paramDescription nvarchar(20)
as
	-- exec dbo.spUpdateRiskLevel @paramId = 4, @paramDescription = 'teste';
	set nocount on;  
	update dbo.RiskLevel set Description = @paramDescription where Id = @paramId;
go

