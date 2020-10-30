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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.eiroca.crmadapter.httpinterface.HTTPMapping;
import net.eiroca.crmadapter.httpinterface.servlets.action.IAction;
import net.eiroca.crmadapter.process.ResultCode;

public class InterfaceServlet extends HttpServlet {

  private static final long serialVersionUID = -3512858352505036965L;
  private static final HashMap<String, IAction> actionMapping = new HashMap<>();

  @Override
  public void init() throws ServletException {
  }

  @Override
  public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response);
  }

  @Override
  public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response);
  }

  public void doProcess(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    final String action_key = request.getParameter(HTTPMapping.PRM_ACTION).toLowerCase();
    final IAction action = InterfaceServlet.actionMapping.get(action_key);
    int resCode = ResultCode.KO;
    final StringBuffer output = new StringBuffer(1024);
    if (action != null) {
      resCode = action.execute(request, output);
    }
    writeResponse(response, resCode, output.toString());
  }

  public void writeResponse(final HttpServletResponse response, final int resCode, final String output) throws IOException {
    response.setContentType("text/plain");
    final PrintWriter pw = response.getWriter();
    try {
      if (resCode == ResultCode.OK) {
        pw.print(output);
      }
      else {
        final String errMSg = ResultCode.decodeError(resCode);
        response.sendError(499 + resCode, errMSg);
        pw.print(errMSg);
      }
    }
    finally {
      pw.close();
    }
  }

  public void destroy(final HttpServletRequest request, final HttpServletResponse response) {
  }

  protected static void registerAction(final IAction action) {
    InterfaceServlet.actionMapping.put(action.getName(), action);
  }

}
