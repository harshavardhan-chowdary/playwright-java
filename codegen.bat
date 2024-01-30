@echo off
setlocal

REM Check if website parameter is provided
if "%1"=="" (
    echo Usage: run_code_gen.bat ^<WEBSITE^>
    exit /b 1
)

REM Set the website parameter
set WEBSITE=%1

REM Run Maven command
mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="codegen %WEBSITE%"

endlocal
