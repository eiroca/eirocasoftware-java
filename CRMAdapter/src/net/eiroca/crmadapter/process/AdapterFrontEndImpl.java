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
import net.eiroca.crmadapter.datamodel.StorageHelper;
import net.eiroca.crmadapter.httpinterface.client.FEInterfaceClient;

public class AdapterFrontEndImpl extends AdapterImpl implements IAdapterFrontEnd {

  IAdapterBackEnd backend = Adapter.getBackEnd();
  FEInterfaceClient httpClient = new FEInterfaceClient();

  AdapterFrontEndImpl() {
  }

  // TODO Impelentazione
  @Override
  public int AF_open(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    final int resCode = httpClient.sendOpen(si);
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int AF_update(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    final int resCode = httpClient.sendUpdate(si);
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int AF_abort(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    final int resCode = httpClient.sendAbort(si);
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int AF_stop(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    final int resCode = httpClient.sendStop(si);
    return resCode;
  }

  // TODO Impelentazione
  @Override
  public int AF_wrongclose(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    final int resCode = httpClient.sendWrongClose(si);
    return resCode;
  }

  /**
   *
   * @param si
   * @return
   */
  @Override
  public int FA_fail(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    //TODO Impelentazione
    si.setState(1);
    StorageHelper.store(si);
    return ResultCode.OK;
  }

  /**
   *
   * @param si
   * @return
   */
  @Override
  public int FA_close(final ServiceIncident si) {
    assertCheck(check_ServiceIncident(si));
    // TODO Impelentazione
    final IAdapterBackEnd backend = Adapter.getBackEnd();
    final int resCode = backend.Provider_ProblemResolution(si);
    return resCode;
  }

  /**
   * Recover di un ServiceIncident
   *
   * @param serviceIncidentID
   * @return
   */
  @Override
  public int FA_recover(final String serviceIncidentID) {
    assertCheck(check_ServiceIncidentID(serviceIncidentID));
    // TODO Impelentazione
    final ServiceIncident si = new ServiceIncident();
    si.setServiceIncidentID(serviceIncidentID);
    StorageHelper.store(si);
    final IAdapterBackEnd backend = Adapter.getBackEnd();
    final int resCode = backend.Provider_QueryIncident(si);
    return resCode;
  }

}
