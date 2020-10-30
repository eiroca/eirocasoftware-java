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

public interface IAdapterFrontEnd {

  /**
   * Nuova chiamata o chiamata chiusa
   */
  public static final int STATE_CLOSED = 0;

  /**
   * Chiamata in carico al front-end
   */
  public static final int STATE_PROGRESS = 1;

  /**
   * Chiamata formalmente chiusa al front-end
   */
  public static final int STATE_CLOSING = 2;

  /**
   * Chiamata chiusa al front-end ma rifiutata dal back-end
   */
  public static final int STATE_INVALID = 3;

  /**
   * Chiamata forzata a chiusa dal back-end
   */
  public static final int STATE_ABORTED = 4;

  /**
   * Chiamata forzata a chiusa dal front-end
   */
  public static final int STATE_FAILED = 5;

  /**
   * Apertura di una nuova chiamata (STATE_UNKNOWN -> STATE_PROGRESS)
   */
  public int AF_open(ServiceIncident si);

  /**
   * Aggiornamento di una chiamata (STATE_PROGRESS -> STATE_PROGRESS)
   */
  public int AF_update(ServiceIncident si);

  /**
   * Chiusura forzata dal backend di una chiamata (STATE_PROGRESS -> STATE_ABORTED)
   */
  public int AF_abort(ServiceIncident si);

  /**
   * Notifica finale chiusura di una chiamata (STATE_ABORTED -> STATE_CLOSED) (STATE_CLOSING ->
   * STATE_CLOSED) (STATE_FAILED -> STATE_CLOSED)
   */
  public int AF_stop(ServiceIncident si);

  /**
   * Rifiuto della chiusura del front-end di una chiamata (STATE_CLOSING -> STATE_INVALID)
   */
  public int AF_wrongclose(ServiceIncident si);

  /**
   * Chiusura forzata dal backend di una chiamata (STATE_PROGRESS -> STATE_FAILED)
   */
  public int FA_fail(ServiceIncident si);

  /**
   * Chiusura dal front-end di una chiamata (STATE_PROGRESS -> STATE_CLOSING)
   */
  public int FA_close(ServiceIncident si);

  /**
   * Tentativo di recuperare una transazione che risulta "persa" nell'adapter (? -> STATE_PROGRESS)
   */
  public int FA_recover(String serviceIncidentID);

  public int check_ServiceIncidentID(String serviceIncidentID);

  public int check_ServiceIncident(ServiceIncident si);
}
