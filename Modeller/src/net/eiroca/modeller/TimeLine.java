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
package net.eiroca.modeller;

import java.util.Iterator;
import java.util.PriorityQueue;

public class TimeLine {

  // Collezione di eventi disposti su un asse temporale
  protected long now;
  protected PriorityQueue<Event> newEvents = new PriorityQueue<>();
  protected PriorityQueue<Event> oldEvents = new PriorityQueue<>();

  public TimeLine() {
    clear();
  }

  public void addEvent(final Event e) throws TimeLineException {
    if (e.when < now) { throw new TimeLineException("Invalid event time"); }
    newEvents.add(e);
  }

  public void clear() {
    now = 0;
    newEvents.clear();
    oldEvents.clear();
  }

  public Event nextEvent() {
    final Event e = newEvents.poll();
    if (e != null) {
      final long when = e.when;
      if (now < when) {
        now = when;
      }
      oldEvents.add(e);
    }
    return e;
  }

  public Iterator<Event> processedEvents() {
    return oldEvents.iterator();
  }

}
