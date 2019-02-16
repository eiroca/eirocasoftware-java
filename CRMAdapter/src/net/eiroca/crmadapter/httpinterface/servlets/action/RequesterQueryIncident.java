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

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.eiroca.crmadapter.datamodel.ServiceIncident;
import net.eiroca.crmadapter.process.ResultCode;

public class RequesterQueryIncident extends BackEndAction {

  public RequesterQueryIncident() {
    super("query_incident");
  }

  @Override
  public int execute(final HttpServletRequest request, final StringBuffer output) throws ServletException, IOException {
    //TODO Implementazione
    final String serviceIncidentID = readServiceIncidentID(request);
    int resCode = backend.check_ServiceIncidentID(serviceIncidentID);
    if (resCode == ResultCode.OK) {
      final ServiceIncident si = loadServiceIncident(request);
      resCode = backend.Requester_QueryIncident(si);
    }
    return resCode;
  }

}
