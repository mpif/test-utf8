@echo off
cmd /V:ON

set cp=

for %f in (*.jar) do set cp=!cp!;%f

echo %cp%

