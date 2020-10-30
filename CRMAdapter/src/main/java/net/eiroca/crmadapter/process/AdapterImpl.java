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

public class AdapterImpl {

  public AdapterImpl() {
  }

  public int check_ServiceIncidentID(final String serviceIncidentID) {
    if (serviceIncidentID == null) { return ResultCode.KO_SERVICEINCIDENTID; }
    return ResultCode.OK;
  }

  public int check_ServiceIncident(final ServiceIncident si) {
    if (si == null) { return ResultCode.KO_SERVICEINCIDENT; }
    return ResultCode.OK;
  }

  protected void assertCheck(final int value) {
    if (value != ResultCode.OK) { throw new IllegalArgumentException("ERR=" + ResultCode.decodeError(value)); }
  }

}
