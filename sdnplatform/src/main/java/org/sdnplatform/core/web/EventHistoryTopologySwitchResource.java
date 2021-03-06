/*
 * Copyright (c) 2013 Big Switch Networks, Inc.
 *
 * Licensed under the Eclipse Public License, Version 1.0 (the
 * "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 *      http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.sdnplatform.core.web;


import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.sdnplatform.linkdiscovery.ILinkDiscoveryService;
import org.sdnplatform.linkdiscovery.internal.EventHistoryTopologySwitch;
import org.sdnplatform.linkdiscovery.internal.LinkDiscoveryManager;
import org.sdnplatform.util.EventHistory;

/**
 * @author subrata
 *
 */
public class EventHistoryTopologySwitchResource extends ServerResource {

    @Get("json")
    public EventHistory<EventHistoryTopologySwitch> handleEvHistReq() {

        // Get the event history count. Last <count> events would be returned
        String evHistCount = (String)getRequestAttributes().get("count");
        int    count = EventHistory.EV_HISTORY_DEFAULT_SIZE;
        try {
            count = Integer.parseInt(evHistCount);
        }
        catch(NumberFormatException nFE) {
            // Invalid input for event count - use default value
        }

        LinkDiscoveryManager topoManager =
           (LinkDiscoveryManager)getContext().getAttributes().
               get(ILinkDiscoveryService.class.getCanonicalName());

        return new EventHistory<EventHistoryTopologySwitch>(
                                topoManager.evHistTopologySwitch, count);
    }
}
