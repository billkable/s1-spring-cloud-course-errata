set REPORTS_DIR=%CD%/reports
set SIMULATION_DIR=%CD%/simulations

gatling -sf %SIMULATION_DIR% -rf %REPORTS_DIR% -rd CreateTimesheets