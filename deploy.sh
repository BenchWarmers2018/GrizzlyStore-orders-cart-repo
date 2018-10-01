#!/usr/bin/env bash
echo 'Deployment in progress'
pwd && cd target

if (( $(ps -ef | grep -v grep | grep grizzlystore-order | wc -l) > 0))
	then
		echo "User service is running...attempting to stop service!"
		sudo systemctl stop grizzlystore-order.service || true
fi

if [ -f grizzlystore-order ] ; then
    sudo rm /etc/init.d/grizzlystore-order || true
fi

cp *.jar /opt/GrizzlyStoreMicroServices/grizzlystore-order.jar
sudo systemctl start grizzlystore-order.service