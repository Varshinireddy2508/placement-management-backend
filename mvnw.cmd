@echo off
setlocal

rem Check if mvn is globally available
where mvn >nul 2>nul
if %ERRORLEVEL% equ 0 (
    mvn %*
    exit /b %ERRORLEVEL%
)

rem Fall back to user's cached Maven if present
set CACHED_MVN="%USERPROFILE%\.m2\wrapper\dists\apache-maven-3.9.12-bin\5nmfsn99br87k5d4ajlekdq10k\apache-maven-3.9.12\bin\mvn.cmd"
if exist %CACHED_MVN% (
    call %CACHED_MVN% %*
    exit /b %ERRORLEVEL%
)

echo [ERROR] Maven not found in PATH or standard wrapper location.
echo Please install Apache Maven or run using your IDE (IntelliJ / Eclipse / VS Code).
exit /b 1
