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

public class FEInterfaceClient {

  public static final String FRONTEND_URL = "http://localhost/frontend";

  private static final String getFrontEndURL() {
    return FEInterfaceClient.FRONTEND_URL;
  }

  public FEInterfaceClient() {
  }

  public int sendOpen(final ServiceIncident si) {
    return HTTPUtils.sendPost(FEInterfaceClient.getFrontEndURL(), "open", si);
  }

  public int sendUpdate(final ServiceIncident si) {
    return HTTPUtils.sendPost(FEInterfaceClient.getFrontEndURL(), "update", si);
  }

  public int sendAbort(final ServiceIncident si) {
    return HTTPUtils.sendPost(FEInterfaceClient.getFrontEndURL(), "abort", si);
  }

  public int sendStop(final ServiceIncident si) {
    return HTTPUtils.sendPost(FEInterfaceClient.getFrontEndURL(), "stop", si);
  }

  public int sendWrongClose(final ServiceIncident si) {
    return HTTPUtils.sendPost(FEInterfaceClient.getFrontEndURL(), "close", si);
  }

}
