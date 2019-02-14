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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;

/** proxyconn is glue between two Sockets wrapped in dataconn, to pass traffic between them. * */
public class ProxyConn implements Runnable {

  public boolean debug = false;
  private final boolean dump = false;
  protected PrintWriter dumpFile;
  public String logfspec = "?";

  protected Thread t;
  protected DataConn c1;
  protected DataConn c2;

  public ProxyConn(final Socket s1, final Socket s2, final boolean dump) {
    this(new DataConn(s1), new DataConn(s2), dump);
  }

  public ProxyConn(final Socket s1, final String thost, final int tport, final boolean dump) throws IOException {
    this(new DataConn(s1), new DataConn(thost, tport), dump);
  }

  public ProxyConn(final DataConn _c1, final DataConn _c2, final boolean dump) {
    c1 = _c2;
    c2 = _c2;
    if (dump) {
      try {
        final InetAddress rhost = c1.getSocket().getInetAddress();
        final String rhostname = rhost.getHostName();
        logfspec = rhostname + "-" + UUID.randomUUID().toString() + ".log";
        final FileOutputStream outraw = new FileOutputStream(logfspec);
        dumpFile = new PrintWriter(outraw);
        dumpFile.println("// CLIENT: " + c1.getSocket().getInetAddress());
        dumpFile.println("// TARGET: " + c2.getSocket().getInetAddress());
      }
      catch (final Throwable T) {
        System.err.println("Can't open log file: " + logfspec);
        System.err.println("proxylogserver ERR: " + T.getMessage());
      }
    }
    else {
      dumpFile = null;
    }

  }

  /* dopolling is called in run() loop. return false to close proxy connection * */
  protected boolean doPolling() {
    try {
      byte[] d;
      d = c1.read();
      if (d != null) {
        if (dump) {
          log(true, d);
        }
        c2.write(d);
      }
      d = c2.read();
      if (d != null) {
        if (dump) {
          log(false, d);
        }
        c1.write(d);
      }
      if (c1.error || c2.error) { return false; }
    }
    catch (final Throwable T) {
      exception(T);
      return false;
    }
    return true;
  }

  protected void exception(final Throwable T) {
    try {
      System.err.println("EXCEPTION: " + T.getMessage());
      if (dump) {
        dumpFile.println("EXCEPTION: " + T.getMessage());
      }
    }
    catch (final Throwable T1) {
      //
    }
  }

  public void go() {
    c1.debug = debug;
    c1.debugname = "c1";
    c2.debug = debug;
    c2.debugname = "c2";
    t = new Thread(this);
    t.start();
  }

  @Override
  public void run() {
    while (doPolling()) {
      //
    }
    try {
      if (dumpFile != null) {
        dumpFile.close();
      }
    }
    catch (final Throwable T) {
      //
    }
  }

  @Override
  protected void finalize() {
    try {
      if (dumpFile != null) {
        dumpFile.close();
      }
    }
    catch (final Throwable T) {
      //
    }
  }

  protected void log(final boolean fromc1, final byte[] d) {
    try {
      if (fromc1) {
        dumpFile.print("c(\"");
      }
      else {
        dumpFile.print("s(\"");
      }
      dumpFile.print(ProxyConn.printableBytes(d));
      dumpFile.println("\");");
      dumpFile.flush();
    }
    catch (final Throwable T) {
      exception(T);
    }
  }

  private static final String printableBytes(final byte[] bytes) {
    if (bytes == null) { return "*NONE*"; }
    final StringBuilder s = new StringBuilder(bytes.length);
    int i;
    for (i = 0; i < bytes.length;) {
      int b = bytes[i];
      if (b < 0) {
        b = 256 + b; // byte is signed type!
      }
      if ((b < ' ') || (b > 0x7f)) {
        final int d1 = (b >> 6) & 7;
        b = b & 0x3f;
        final int d2 = (b >> 3) & 7;
        final int d3 = b & 7;
        s.append("\\" + d1);
        s.append(d2);
        s.append(d3);
      }
      else if ('\\' == (char)b) {
        s.append("\\\\");
      }
      else {
        s.append((char)b);
      }
      i++;
      if (0 == (i - (40 * (i / 40)))) {
        s.append("\"+\n   \"");
      }
    }
    return s.toString();
  }

}
