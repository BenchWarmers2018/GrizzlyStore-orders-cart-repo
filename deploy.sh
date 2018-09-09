#!/usr/bin/env bash

echo 'Deployment in process'
pwd && cd target
sudo systemctl stop grizzly-order.service || true
sudo rm /etc/init.d/grizzly-order || true
sudo ln -s grizzlystore-order-0.0.1-SNAPSHOT.jar /etc/init.d/grizzly-order
sudo systemctl start grizzly-order.service