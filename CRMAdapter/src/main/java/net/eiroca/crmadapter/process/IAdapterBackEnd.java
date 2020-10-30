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

public interface IAdapterBackEnd {

  public int Requester_ServiceRequest(ServiceIncident si);

  public int Requester_QueryIncident(ServiceIncident si);

  public int Requester_ProvideAdminInformation(ServiceIncident si);

  public int Requester_ProblemSubmittal(ServiceIncident si);

  public int Requester_ProvideProblemInformation(ServiceIncident si);

  public int Requester_RejectResolution(ServiceIncident si);

  public int Requester_ConfirmClose(ServiceIncident si);

  public int Requester_RequestClosure(ServiceIncident si);

  public int Provider_QueryIncident(ServiceIncident si);

  public int Provider_Entilement(ServiceIncident si);

  public int Provider_AcceptProblem(ServiceIncident si);

  public int Provider_ProvideProblemInformation(ServiceIncident si);

  public int Provider_RequestProblemInformation(ServiceIncident si);

  public int Provider_ProblemResolution(ServiceIncident si);

  public int check_ServiceIncidentID(String serviceIncidentID);
}
