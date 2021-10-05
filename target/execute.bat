@echo off
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_181
set Path=%JAVA_HOME%\bin;%Path%
TITLE = Application (IMEDIA-NOTIFY-PROCESS-1.0)
java -Xms32m -Xmx512m -jar -Xmx512m IMD_NOTIFY_API_2-0.0.1.jar