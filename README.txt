The data file used with this project must be in the form below:
(DATE: YYYY-MM-DD)*SPACE*(TIME: HH:MM:SS)*SPACE*(HUMIDITY: IN PERCENT)*SPACE*(TEMP: IN F)*SPACE*(PRESSURE: IN Pa)*SPACE*(ALTITUDE)*SPACE*(SEALEVEL PRESSURE: IN Pa)*SPACE*(DEW POINT IN F)



		EXAPMLE:-----|
			     v

 _______________________________________________________________________
|        YEAR      TIME    HUM  TEMP   PRESS    ALT   SEAPRESS   DewPt  |
|       2015-05-23 11:35:36 40.39 73.74 95293.00 515.27 95286.00 54.7   |
|_______________________________________________________________________|

WHAT THE CLASES DO=========================================

DB_Controller-------------------
Intracts directly with the MYSQL Database: create, insert, make requests, etc...


Stats---------------------------
Preforms various calculations:
	Overall High/Low: Temp/Humidity/Pressure/Dew Point
	Daily High/Low:  Temp/Humidity/Pressure/Dew Point
	Overall average: Temp/Humidity/Pressure/Dew Point
	daily average: Temp/Humidity/Pressure/Dew Point
provides methods for bulk insertion into the 'DailAvgs' and 'DailyHiLow' database tables

File Parser---------------------
Parses the data in the datafile into a form that can be inserted into the 'weatherdata' table in the db

PiProjDbProg--------------------
firstly; that's short for "Pi Project Database Program"
this is the main class that runs the project


NOTES ON THE DATABASE===================================
The databses is a MYSQL database containing 3 tables
DB name: weather
table names: 
	weather data: main table 
	dailyavgs: contains the average Temp/Humidity/Pressure/Dewpoint for a given day
	dailyHiLow: contains the High and low values for Temp/Humidity/Pressure/Dewpoint for a given day















OBLIGITORY DISCLAIMER: PLEASE DO NOT USE ANY PART OF THIS PROJECT WITHOUT ME KNOWING: JUST ASK okay?

