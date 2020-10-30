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
import net.eiroca.crmadapter.httpinterface.HTTPMapping;
import net.eiroca.crmadapter.process.ResultCode;

public class FrontEndFail extends FrontEndAction {

  public FrontEndFail() {
    super("fail");
  }

  @Override
  public int execute(final HttpServletRequest request, final StringBuffer output) throws ServletException, IOException {
    //TODO Implementazione
    //TODO Gestione Errori
    final String serviceIncidentID = readServiceIncidentID(request);
    int resCode = frontend.check_ServiceIncidentID(serviceIncidentID);
    if (resCode == ResultCode.OK) {
      final ServiceIncident si = loadServiceIncident(serviceIncidentID);
      resCode = frontend.check_ServiceIncident(si);
      if (resCode == ResultCode.OK) {
        //TODO copiare i valori passati dalla richiesta
        si.setData(request.getParameter(HTTPMapping.PRM_SERVICEINCIDENTDATA));
        si.setState(1);
        resCode = frontend.FA_fail(si);
      }
    }
    return resCode;
  }

}
