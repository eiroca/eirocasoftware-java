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
package net.eiroca.crmadapter.process;

import net.eiroca.crmadapter.datamodel.ServiceIncident;
import net.eiroca.crmadapter.httpinterface.client.BEInterfaceClient;

public class AdapterBackEndImpl extends AdapterImpl implements IAdapterBackEnd {

  IAdapterFrontEnd frontend = Adapter.getFrontEnd();
  BEInterfaceClient httpClient = new BEInterfaceClient();

  AdapterBackEndImpl() {
  }

  @Override
  public int Requester_ServiceRequest(final ServiceIncident si) {
    // TODO Impelentazione
    assertCheck(check_ServiceIncident(si));
    int resCode;
    resCode = Provider_Entilement(si);
    if (resCode == ResultCode.OK) {
      resCode = Provider_AcceptProblem(si);
      if (resCode == ResultCode.OK) {
        resCode = frontend.AF_open(si);
      }
    }
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int Requester_QueryIncident(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    // Ignora?
    return ResultCode.OK;
  }

  @Override
  public int Requester_ProvideAdminInformation(final ServiceIncident si) {
    // TODO Impelentazione
    assertCheck(check_ServiceIncident(si));
    // Ignora?
    return ResultCode.OK;
  }

  @Override
  public int Requester_ProblemSubmittal(final ServiceIncident si) {
    // TODO Impelentazione
    assertCheck(check_ServiceIncident(si));
    // Ignora?
    return ResultCode.OK;
  }

  @Override
  public int Requester_ProvideProblemInformation(final ServiceIncident si) {
    // TODO Impelentazione
    assertCheck(check_ServiceIncident(si));
    // Ignora?
    return ResultCode.OK;
  }

  @Override
  public int Requester_RejectResolution(final ServiceIncident si) {
    // TODO Impelentazione
    assertCheck(check_ServiceIncident(si));
    int resCode;
    resCode = frontend.AF_wrongclose(si);
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int Requester_ConfirmClose(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    int resCode;
    resCode = frontend.AF_stop(si);
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int Requester_RequestClosure(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    int resCode;
    resCode = frontend.AF_abort(si);
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int Provider_QueryIncident(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    int resCode;
    resCode = httpClient.sendQueryIncident(si);
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int Provider_Entilement(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    int resCode;
    resCode = httpClient.sendEntitlement(si);
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int Provider_AcceptProblem(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    int resCode;
    resCode = httpClient.sendAcceptProblem(si);
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int Provider_ProvideProblemInformation(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    // Ignora?
    return ResultCode.OK;
  }

  // TODO Impelentazione
  @Override
  public int Provider_RequestProblemInformation(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    // Ignora?
    return ResultCode.OK;
  }

  // TODO Impelentazione
  @Override
  public int Provider_ProblemResolution(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    int resCode;
    resCode = httpClient.sendProblemResolution(si);
    return resCode;
  }

}
