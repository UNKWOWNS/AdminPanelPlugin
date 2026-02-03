import os
import subprocess
import shutil

# Paths
SRC_DIR = "src"
BUILD_DIR = "build"
OUTPUT_JAR = "AdminPanel.jar"
RESOURCE_DIR = os.path.join(SRC_DIR, "main", "resources")

# Clean previous build
if os.path.exists(BUILD_DIR):
    shutil.rmtree(BUILD_DIR)
os.makedirs(BUILD_DIR)

# Find all Java files
java_files = []
for root, dirs, files in os.walk(SRC_DIR):
    for file in files:
        if file.endswith(".java"):
            java_files.append(os.path.join(root, file))

if not java_files:
    print("No Java files found in src folder.")
    exit(1)

# Compile Java files
print("Compiling Java files...")
javac_cmd = ["javac", "-d", BUILD_DIR] + java_files
result = subprocess.run(javac_cmd)

if result.returncode != 0:
    print("Compilation failed.")
    exit(1)

# Copy resources
if os.path.exists(RESOURCE_DIR):
    for root, dirs, files in os.walk(RESOURCE_DIR):
        for file in files:
            rel_dir = os.path.relpath(root, RESOURCE_DIR)
            dest_dir = os.path.join(BUILD_DIR, rel_dir)
            os.makedirs(dest_dir, exist_ok=True)
            shutil.copy(os.path.join(root, file), os.path.join(dest_dir, file))

# Create JAR
print("Creating JAR file...")
jar_cmd = ["jar", "cf", OUTPUT_JAR, "-C", BUILD_DIR, "."]
result = subprocess.run(jar_cmd)

if result.returncode == 0:
    print(f"✅ Plugin built successfully: {OUTPUT_JAR}")
else:
    print("Failed to create JAR.")
