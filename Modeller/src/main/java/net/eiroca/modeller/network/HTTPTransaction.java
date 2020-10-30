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

public class HTTPTransaction extends Transaction {

  public HTTPRequest req;
  public HTTPResponse res;

  public HTTPTransaction(final HTTPRequest req) {
    this.req = req;
  }

  public HTTPTransaction(final String URL) {
    this(URL, "Mozilla");
  }

  public HTTPTransaction(final String URL, final String userAgent) {
    req = new HTTPRequest(URL, userAgent);
  }

}
