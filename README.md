# CS5643: ONOS Application
The repository holds the source code for ONOS SDN Controller Application done for CS5643 Software Defined Networking course.
1. List all the known hosts that are attached to a particular device.
2. Withdraw all the intents used by the host after its removal.
## Prerequisites
#### ONOS SDN Controller
ONOS is our OpenSource SDN Controller.
#### Mininet
Mininet is used to create emulated data plane composed of SDN switches based on OpenVSwitch.
#### IntelliJ
IntelliJ is the IDE used to edit ONOS Java code and create ONOS applications.
## Step by Step Instructions
Open terminal and start ONOS SDN Controller, 
```shell
$ cd onos
$ ok --clean
```
Create devices, hosts and communication links between them using Mininet emulator,
```shell
$ sudo python topology.py
```
Make sure you have Proxy ARP/NDP and Reactive Forwarding applications enabled in ONOS Controller.
Build and install the application in ONOS SDN Controller,
```shell
$ cd mesh-app
$ mvn clean install
$ onos-app localhost reinstall! /target/mesh-app-1.0-SNAPSHOT.oar
```
### List all known hosts attached to a particular device
Start ONOS command line interface,
```shell
$ onos localhost
onos> mesh-list-hosts of:1000000000000001
```
The above command will list out all the known hosts attached to the device of:1000000000000001. Change the value of command line argument to your device ID.
### Withdraw all the intents used by host upon its removal
Open REST Interface for ONOS,
```shell
Move to Hosts
Move to DELETE /hosts/{mac}/{vlan}
Input the mac and vlan of host to be deleted
```
Deletion of host would trigger the withdrawal of all the Intents that were used by that particular host, it can be seen in the log console of ONOS Controller.
