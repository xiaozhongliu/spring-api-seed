#!/bin/bash

java -jar $app_artifact_id-$app_version.jar --spring.profiles.active=prod \
-server \
-Xms128m \
-Xmx512m \
-Xmn128m \
-XX:PermSize=64m \
-XX:MaxPermSize=128m \
-XX:+UseParNewGC \
-XX:+UseConcMarkSweepGC \
-XX:+UseCMSCompactAtFullCollection \
-XX:CMSFullGCsBeforeCompaction=1 \
-XX:+CMSClassUnloadingEnabled \
-XX:CMSInitiatingOccupancyFraction=75 \
-XX:+UseCompressedOops \
-XX:+PrintGCDetails \
-XX:+PrintGCTimeStamps \
-XX:+PrintHeapAtGC \
-Duser.timezone=GMT+08 \
-Xloggc:gc.log
>> start.log
