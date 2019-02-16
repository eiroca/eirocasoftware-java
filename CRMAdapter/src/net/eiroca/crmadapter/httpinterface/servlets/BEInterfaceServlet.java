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
package net.eiroca.crmadapter.httpinterface.servlets;

import net.eiroca.crmadapter.httpinterface.servlets.action.RequesterConfirmClose;
import net.eiroca.crmadapter.httpinterface.servlets.action.RequesterProblemSubmittal;
import net.eiroca.crmadapter.httpinterface.servlets.action.RequesterProvideAdminInformation;
import net.eiroca.crmadapter.httpinterface.servlets.action.RequesterProvideProblemInformation;
import net.eiroca.crmadapter.httpinterface.servlets.action.RequesterQueryIncident;
import net.eiroca.crmadapter.httpinterface.servlets.action.RequesterRejectResolution;
import net.eiroca.crmadapter.httpinterface.servlets.action.RequesterRequestClosure;
import net.eiroca.crmadapter.httpinterface.servlets.action.RequesterServiceRequest;

public class BEInterfaceServlet extends InterfaceServlet {

  private static final long serialVersionUID = -1923573026169957964L;

  static {
    InterfaceServlet.registerAction(new RequesterConfirmClose());
    InterfaceServlet.registerAction(new RequesterProblemSubmittal());
    InterfaceServlet.registerAction(new RequesterProvideAdminInformation());
    InterfaceServlet.registerAction(new RequesterProvideProblemInformation());
    InterfaceServlet.registerAction(new RequesterQueryIncident());
    InterfaceServlet.registerAction(new RequesterRejectResolution());
    InterfaceServlet.registerAction(new RequesterRequestClosure());
    InterfaceServlet.registerAction(new RequesterServiceRequest());
  }

}
