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

package org.sdnplatform.linkdiscovery.web;


import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.sdnplatform.linkdiscovery.ILinkDiscoveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoPortFast extends ServerResource {
    protected static Logger log = LoggerFactory.getLogger(AutoPortFast.class);

    @Get("json")
    public String retrieve() {
        ILinkDiscoveryService linkDiscovery;
        linkDiscovery = (ILinkDiscoveryService)getContext().getAttributes().
                get(ILinkDiscoveryService.class.getCanonicalName());

        String param = ((String)getRequestAttributes().get("state")).toLowerCase();
        if (param.equals("enable") || param.equals("true")) {
            linkDiscovery.setAutoPortFastFeature(true);
        } else if (param.equals("disable") || param.equals("false")) {
            linkDiscovery.setAutoPortFastFeature(false);
        }
        setStatus(Status.SUCCESS_OK, "OK");
        if (linkDiscovery.isAutoPortFastFeature())
            return "enabled";
        else return "disabled";
    }
}
