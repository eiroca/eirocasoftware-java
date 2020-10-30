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

public class ClientNode extends NetworkNode {

  public int span_delayfirst = 1;
  public int span_delay = 1;
  public int span_maxpar = 8;

  public ClientNode(final Model model) {
    super(model);
    delay = 0.05 * NetworkNode.SECK;
    latency = 5;
  }

  @Override
  public void processEventStart(final Event e) {
    if (e instanceof HTTPTransaction) {
      //
    }
  }

  @Override
  public void processEventStop(final Event e) {
    //
  }

  public void processHTTPTransaction(final HTTPTransaction t) {
    t.req.userAgent = "Mozillino";
    dispatch(t);
    final HTTPResponse res = t.res;
    if ((res != null) && (res.childs != null)) {
      long when = t.when + t.elapsed + span_delayfirst;
      int cnt = 0;
      for (final HTTPRequest req : res.childs) {
        if (cnt >= span_maxpar) {
          cnt = 0;
          when = when + span_delay;
        }
        cnt++;
        final HTTPTransaction tra = new HTTPTransaction(req);
        tra.when = when;
        // t.addChild(tra);
        model.addEvent(tra, true);
      }
    }
  }

}
