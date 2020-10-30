/**
 *
 * Copyright (C) 2001-2019 eIrOcA (eNrIcO Croce & sImOnA Burzio) - AGPL >= 3.0
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 *
 **/
package net.eiroca.crmadapter.httpinterface.client;

import net.eiroca.crmadapter.datamodel.ServiceIncident;

public class BEInterfaceClient {

  public static final String BACKEND_URL = "http://localhost/backend";

  private static final String getBackEndURL() {
    return BEInterfaceClient.BACKEND_URL;
  }

  public BEInterfaceClient() {
  }

  public int sendQueryIncident(final ServiceIncident si) {
    return HTTPUtils.sendPost(BEInterfaceClient.getBackEndURL(), "query_incident", si);
  }

  public int sendEntitlement(final ServiceIncident si) {
    return HTTPUtils.sendPost(BEInterfaceClient.getBackEndURL(), "entitlement", si);
  }

  public int sendAcceptProblem(final ServiceIncident si) {
    return HTTPUtils.sendPost(BEInterfaceClient.getBackEndURL(), "accept_problem", si);
  }

  public int sendProblemResolution(final ServiceIncident si) {
    return HTTPUtils.sendPost(BEInterfaceClient.getBackEndURL(), "problem_resolution", si);
  }

}
