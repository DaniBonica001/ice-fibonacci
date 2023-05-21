#!/bin/bash
export PASSWORD="swarch"
server_id="xhgrid1"
path="ArturoDiaz-DanielaBonilla-CallBack_Server"
sshpass -p ${PASSWORD} ssh -o StrictHostKeyChecking=no swarch@"$server_id" "if [ -d $path ]; then echo 'Folder exists'; else mkdir $path; fi"
echo "Send Jar Server"
gradle -p server build
sshpass -p ${PASSWORD} scp -o StrictHostKeyChecking=no ./server/build/libs/server.jar swarch@"$server_id":./$path