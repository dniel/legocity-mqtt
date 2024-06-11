#!/bin/sh
set -e

# The module to start.
APP_JAR="application/application.jar"

echo " --- RUNNING $(basename "$0") $(date -u "+%Y-%m-%d %H:%M:%S Z") --- "
set -x

# Print some debug info about the JVM and Heap
exec su-exec "$USER:$GROUP" "$JAVA_HOME/bin/java" \
  -XX:MaxRAMPercentage=80 \
  -XX:+PrintFlagsFinal \
  -version | grep Heap

exec su-exec "$USER:$GROUP" "$JAVA_HOME/bin/java" \
  -XX:MaxRAMPercentage=80 \
  -Djava.util.concurrent.ForkJoinPool.common.parallelism=4 \
  -jar "$APP_JAR"