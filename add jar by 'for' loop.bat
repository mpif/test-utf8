@echo off

set CURRENTDIR=%CD%

set CLASSPATH=.

cmd /V:ON 

for /F %%a in ('dir lib\*.jar lib\*.zip /b') do set CLASSPATH=!CLASSPATH!;%CURRENTDIR%\lib\%%a

echo CLASSPATH=%CLASSPATH%


set cp=%CD%/lib/*.jar

echo cp=%cp%