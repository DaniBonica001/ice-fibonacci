#!/bin/bash
export PASSWORD="swarch"
path="ArturoDiaz-DanielaBonilla-CallBack_Client"
exec 3<"host.txt"
while read -u 3 line; do
    ip=$(echo "$line" | awk '{print $1}')
    name=$(echo "$line" | awk '{print $2}')
    echo "host swarch@"$name""
    sed -i "s/^CallbackReceiver.Endpoints = tcp -h hgrid3 -p 10008/CallbackReceiver.Endpoints=default -h $ip -p 10008/" client/src/main/resources/config.client
    gradle -p client build
    sshpass -p ${PASSWORD} ssh -o StrictHostKeyChecking=no swarch@"$ip" "if [ -d $path ]; then echo 'Folder exists'; else mkdir $path; fi"
    sshpass -p ${PASSWORD} scp -o StrictHostKeyChecking=no ./client/build/libs/client.jar swarch@"$ip":./$path
    sed -i "s/CallbackReceiver.Endpoints=default -h $ip -p 10008/CallbackReceiver.Endpoints = tcp -h hgrid3 -p 10008/" client/src/main/resources/config.client
done