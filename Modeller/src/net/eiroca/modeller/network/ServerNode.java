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

import net.eiroca.modeller.Model;

public class ServerNode extends NetworkNode {

  public int size1 = 0 * 1024;
  public int size2 = 0 * 1024;

  public ServerNode(final Model model) {
    super(model);
    delay = 0.01 * NetworkNode.SECK;
    latency = 5;
  }

  public void processHTTPTransaction(final HTTPTransaction transaction) {
    transaction.res = new HTTPResponse();
    transaction.res.staus = 200;
    if (transaction.req.path.startsWith("/images")) {
      transaction.size = size2;
      transaction.res.mimeType = "image/jpg";
      transaction.elapsed = calcElapsed(size2);
    }
    else {
      transaction.size = size1;
      transaction.res.mimeType = "text/html";
      transaction.res.childs = new HTTPRequest[5];
      final String url = "http://" + transaction.req.host + "/images/";
      transaction.res.childs[0] = new HTTPRequest(url + "1");
      transaction.res.childs[1] = new HTTPRequest(url + "2");
      transaction.res.childs[2] = new HTTPRequest(url + "3");
      transaction.res.childs[3] = new HTTPRequest(url + "4");
      transaction.res.childs[4] = new HTTPRequest(url + "5");
      transaction.elapsed = calcElapsed(size1);
    }
  }

}
