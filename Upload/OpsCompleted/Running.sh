#!/bin/sh
echo "Bash version ${BASH_VERSION}..."
for ((n=1;n<=12;n++))
do
  sleep 1
  echo "Slept for $n secs" >> jobFile.out
done
