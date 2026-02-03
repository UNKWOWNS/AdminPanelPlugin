@echo off
echo ========================================
echo    BUILDING MINECRAFT PLUGIN
echo ========================================
echo.

REM Create folders
if not exist "build" mkdir build
if not exist "lib" mkdir lib

echo Step 1: Downloading Spigot API...
echo This might take a moment...
powershell -Command "Invoke-WebRequest -Uri 'https://hub.spigotmc.org/nexus/service/local/repositories/snapshots/content/org/spigotmc/spigot-api/1.21.1-R0.1-SNAPSHOT/spigot-api-1.21.1-R0.1-SNAPSHOT.jar' -OutFile 'lib\spigot-api.jar'"

if not exist "lib\spigot-api.jar" (
    echo ERROR: Could not download Spigot API
    echo Download it manually from:
    echo https://hub.spigotmc.org/nexus/service/local/repositories/snapshots/content/org/spigotmc/spigot-api/1.21.1-R0.1-SNAPSHOT/spigot-api-1.21.1-R0.1-SNAPSHOT.jar
    echo Save it to the 'lib' folder as 'spigot-api.jar'
    pause
    exit /b 1
)

echo Step 2: Compiling your plugin...
REM Compile each folder separately
javac -cp "lib\spigot-api.jar" -d build src\main\java\me\tailzz\adminpanel\*.java
javac -cp "lib\spigot-api.jar" -d build src\main\java\me\tailzz\adminpanel\commands\*.java
javac -cp "lib\spigot-api.jar" -d build src\main\java\me\tailzz\adminpanel\gui\*.java
javac -cp "lib\spigot-api.jar" -d build src\main\java\me\tailzz\adminpanel\listeners\*.java
javac -cp "lib\spigot-api.jar" -d build src\main\java\me\tailzz\adminpanel\vanish\*.java

if %errorlevel% neq 0 (
    echo.
    echo Checking if Java is installed...
    java -version
    javac -version
    echo.
    echo If you see errors above:
    echo 1. Download Java JDK 17 from: https://adoptium.net/
    echo 2. Install it
    echo 3. Restart your computer
    pause
    exit /b 1
)

echo Step 3: Copying resources...
if exist "src\main\resources" (
    xcopy /E /I src\main\resources build\ >nul
)

echo Step 4: Creating JAR file...
cd build
jar cf ..\AdminPanel.jar *
cd ..

echo.
echo ========================================
echo    SUCCESS!
echo    Plugin built: AdminPanel.jar
echo ========================================
echo.
echo Copy this file to:
echo [Your Server]\plugins\AdminPanel.jar
echo.
pause