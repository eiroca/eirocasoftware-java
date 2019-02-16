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
package net.eiroca.crmadapter.httpinterface.servlets.action;

import javax.servlet.http.HttpServletRequest;
import net.eiroca.crmadapter.datamodel.ServiceIncident;
import net.eiroca.crmadapter.datamodel.StorageHelper;
import net.eiroca.crmadapter.httpinterface.HTTPMapping;
import net.eiroca.crmadapter.process.Adapter;
import net.eiroca.crmadapter.process.IAdapterFrontEnd;

public abstract class FrontEndAction implements IAction {

  public String actionName;
  IAdapterFrontEnd frontend = Adapter.getFrontEnd();

  public FrontEndAction(final String actionName) {
    this.actionName = actionName;
  }

  @Override
  public String getName() {
    return actionName;
  }

  public String readServiceIncidentID(final HttpServletRequest request) {
    //TODO Da parametrizzare
    final String serviceIncidentID = request.getParameter(HTTPMapping.PRM_SERVICEINCIDENTID);
    return serviceIncidentID;
  }

  public ServiceIncident loadServiceIncident(final String serviceIncidentID) {
    //TODO Completare
    if (serviceIncidentID == null) { return null; }
    final ServiceIncident si = StorageHelper.load(serviceIncidentID);
    return si;
  }

}
