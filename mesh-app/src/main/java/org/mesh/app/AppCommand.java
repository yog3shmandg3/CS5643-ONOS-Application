/*
 * Copyright 2019-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mesh.app;

import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Completion;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.onosproject.cli.AbstractShellCommand;
import org.onosproject.cli.net.HostIdCompleter;
import org.onosproject.net.*;
import org.onosproject.net.device.DeviceService;
import org.onosproject.net.host.HostService;
import org.onosproject.net.intent.Intent;
import org.onosproject.net.intent.IntentService;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Sample Apache Karaf CLI command
 */
@Service
@Command(scope = "onos", name = "mesh-list-hosts",
         description = "Sample Apache Karaf CLI command")
public class AppCommand extends AbstractShellCommand {

    //Reading Device ID as command line argument
    @Argument(index = 0, name = "uri", description = "Device ID", required = true, multiValued = false)
    @Completion(HostIdCompleter.class)
    private String uri = null;

    @Override
    protected void doExecute() {
        HostService hostService = get(HostService.class);
        DeviceService deviceService = get(DeviceService.class);

        //Traslates the Set of hosts in a List of hosts
        //List<Host> hosts = newArrayList(hostService.getHosts());

        //Fetch all the list of devices
        List<Device> devices = newArrayList(deviceService.getDevices());

        //Validate the input Device ID
        int flag = 0;
        for(int i=0; i < devices.size(); i++){
            if(uri.equals(devices.get(i).id().toString()))
                flag = 1;
        }

        if(flag == 0) {
            print("The device with Device ID doesn't exist");
        }
        else {
            //Fetch the list of all hosts connected to input Device ID
            List<Host> hosts = newArrayList(hostService.getConnectedHosts(DeviceId.deviceId(uri)));

            print("-------------------------------");
            print("--- List Of Connected Hosts to Device %s ---", uri);
            print("-------------------------------");

            //Print out all the hosts connected to input Device ID
            for (int i = 0; i < hosts.size(); i++) {
                print("Host ID: %s - IP addresses: %s",
                        hosts.get(i).id(),
                        hosts.get(i).ipAddresses());
            }
        }
    }
}
