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

import java.net.MalformedURLException;
import java.net.URL;

public class HTTPRequest {

  private final String URL;
  public String host;
  public String path;
  public String userAgent;

  public HTTPRequest(final String aURL) {
    this(aURL, "Mozilla");
  }

  public HTTPRequest(final String aURL, final String userAgent) {
    URL = aURL;
    URL url;
    try {
      url = new URL(aURL);
      host = url.getHost();
      path = url.getPath();
      this.userAgent = userAgent;
    }
    catch (final MalformedURLException e) {
      e.printStackTrace();
    }
  }

  public String getURL() {
    return URL;
  }

}
