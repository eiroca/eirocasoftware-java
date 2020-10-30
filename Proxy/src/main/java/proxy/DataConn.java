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
package proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class DataConn implements AutoCloseable {

  public boolean debug = false;
  public String debugname = null;
  public boolean error = false;

  protected Socket socket;
  protected InputStream is;
  protected OutputStream os;

  public DataConn() {
  }

  public DataConn(final InetAddress host, final int port) throws IOException {
    this(new Socket(host, port));
  }

  public DataConn(final String hostName, final int port) throws IOException {
    this(new Socket(InetAddress.getByName(hostName), port));
  }

  public DataConn(final Socket socket) {
    connect(socket);
  }

  public void connect(final Socket socket) {
    this.socket = socket;
    connect();
  }

  @Override
  public void close() {
    if (socket == null) { return; }
    try {
      os = null;
      is = null;
      socket.close();
      socket = null;
    }
    catch (final Throwable t) {
      exception(t);
      error = true;
    }
  }

  protected void connect() {
    error = false;
    try {
      is = socket.getInputStream();
      os = socket.getOutputStream();
    }
    catch (final Throwable t) {
      exception(t);
      error = true;
    }
  }

  protected void log(final boolean isread, final byte[] d) {
    if (!debug) { return; }
    final StringBuilder sb = new StringBuilder();
    if (debugname != null) {
      sb.append(debugname).append(" ");
    }
    sb.append(isread ? "R: " : "W: ");
    sb.append(d.length).append(" bytes");
    System.err.println(sb.toString());
  }

  protected void exception(final Throwable t) {
    final String m = "EXCEPTION: " + t.getMessage();
    System.err.println(m);
    if (!(t instanceof SocketException)) {
      t.printStackTrace();
    }
  }

  protected final byte[] read() {
    if (error) { return null; }
    try {
      Thread.sleep(50);
    }
    catch (final InterruptedException IE) {
      return null;
    }
    try {
      final int iavail = is.available();
      if (iavail > 0) {
        final byte[] d = new byte[iavail];
        is.read(d);
        log(true, d);
        return d;
      }
    }
    catch (final Throwable t) {
      exception(t);
      error = true;
    }
    return null;
  }

  protected final void write(final byte[] d) {
    if (error) { return; }
    try {
      os.write(d);
      os.flush();
      log(false, d);
    }
    catch (final Throwable T) {
      exception(T);
      error = true;
    }
  }

  protected final void write(final String d) {
    write(d.getBytes());
  }

  public Socket getSocket() {
    return socket;
  }

}
