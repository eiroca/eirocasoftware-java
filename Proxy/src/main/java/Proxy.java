
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
import proxy.ProxyServer;

public class Proxy {

  public static void main(final String args[]) {
    final String targetHost = args.length > 0 ? args[0] : null;
    final Integer targetPort = args.length > 1 ? new Integer(args[1]) : null;
    final Integer localPort = args.length > 2 ? new Integer(args[2]) : targetPort;
    final boolean dump = args.length > 3 ? true: false;
    if (targetHost == null || targetPort == null || localPort == null) {
      System.err.println("Syntax targethost targetport localport");
    }
    else {
      final ProxyServer proxy = new ProxyServer(targetHost, targetPort, localPort, dump);
      proxy.start();
    }
  }

}
