call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openBrowser
echo Cannot run runcrud.bat file
goto fail

goto openBrowser

:openBrowser
start /d "C:\Program Files\Mozilla Firefox" firefox.EXE http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors