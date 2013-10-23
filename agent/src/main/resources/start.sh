#!/bin/sh
nohup java -cp .:lib/* com.athena.peacock.agent.Starter > /dev/null 2>&1 &
sleep 1
tail -f log/athena-peacock-agent.log
