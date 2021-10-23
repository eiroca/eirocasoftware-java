package proxy;

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
import java.net.ServerSocket;
import java.net.Socket;

/** proxyserver listens on given lport, forwards traffic to tport on thost * */
public class ProxyServer implements Runnable {

  public boolean debug = true;
  public boolean dump = false;

  protected String targetHost;
  protected int targetPort;
  protected int localPort;
  protected Thread thread;

  public ProxyServer(final String targetHost, final int port) {
    this(targetHost, port, port, false);
  }

  public ProxyServer(final String targetHost, final int targetPort, final int localPort, final boolean dump) {
    this.targetHost = targetHost;
    this.targetPort = targetPort;
    this.localPort = localPort;
    this.dump = dump;
  }

  public void start() {
    if (debug) {
      System.out.println("Listening port: " + localPort);
    }
    thread = new Thread(this);
    thread.start();
  }

  protected void gotconn(final Socket sconn) throws Exception {
    if (debug) {
      System.out.println("Connection accepted from "+sconn.getRemoteSocketAddress());
    }
    final ProxyConn pc = new ProxyConn(sconn, targetHost, targetPort, dump);
    pc.debug = debug;
    pc.go();
  }

  @Override
  public void run() {
    try (ServerSocket server = new ServerSocket(localPort);) {
      while (true) {
        final Socket sconn = server.accept();
        gotconn(sconn);
      }
    }
    catch (final Throwable e) {
      if (debug) {
        System.err.println("Exception: " + e.toString());
      }
    }
  }

}
