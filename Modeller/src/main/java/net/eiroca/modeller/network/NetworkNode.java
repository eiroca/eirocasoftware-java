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
package net.eiroca.modeller.network;

import net.eiroca.modeller.Event;
import net.eiroca.modeller.Model;
import net.eiroca.modeller.ModelNode;

public class NetworkNode extends ModelNode {

  public static final double SECK = 1000.0 / (1024.0);
  public static final double SECMbit = 8000.0 / (1024.0 * 1024.0);

  public int latency = 1;
  public double delay = 0.01 * NetworkNode.SECMbit;
  private NetworkNode server;
  protected boolean autoForward = false;

  public NetworkNode(final Model model) {
    super(model);
  }

  protected long calcElapsed(final int size) {
    return (long)(latency + (size * delay));
  }

  public void connectTo(final NetworkNode server) {
    this.server = server;
  }

  public void dispatch(final Event e) {
    if (server != null) {
      server.processEvent(0, e);
    }
  }

  @Override
  public void processEvent(final int eventID, final Event e) {
    if (eventID == Event.EVENT_RAISED) {
      processEventStart(e);
    }
    else if (eventID == Event.EVENT_PROCESSED) {
      processEventStop(e);
    }
  }

  public void processEventStart(final Event e) {
    if (e instanceof Transaction) {
      final Transaction t = (Transaction)e;
      t.setElapsed(t.elapsed + calcElapsed(t.size));
    }
    e.processed();
  }

  public void processEventStop(final Event e) {
    //
  }

}
