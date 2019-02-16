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
package net.eiroca.crmadapter.poller;

public class Poller extends Thread {

  public int delay = 1000;

  public Poller() {
  }

  public boolean fetch() {
    return false;
  }

  public void process() {
  }

  public void delay() throws InterruptedException {
    Thread.sleep(delay);
  }

  @Override
  public void run() {
    while (true) {
      final boolean next = fetch();
      if (next) {
        process();
      }
      else {
        try {
          delay();
        }
        catch (final InterruptedException ex) {
          break;
        }
      }
    }
  }

}
