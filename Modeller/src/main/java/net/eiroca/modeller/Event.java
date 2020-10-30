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

import java.util.Date;
import java.util.EventListener;
import javax.swing.event.EventListenerList;

public class Event implements Comparable<Event>, Cloneable {

  public static final int EVENT_RAISED = 0;
  public static final int EVENT_PROCESSED = 1;

  private static int IDgen = 0;

  public int ID;
  public long when;

  public EventListenerList listener = new EventListenerList();

  public Event() {
    when = 0;
  }

  public Event(final Date when) {
    this.when = when.getTime();
  }

  public void addListener(final EventHandler node) {
    listener.add(EventHandler.class, node);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public int compareTo(final Event o) {
    final Event e = o;
    if (when < e.when) { return -1; }
    if (when > e.when) { return 1; }
    return ID - e.ID;
  }

  public void fire(final int eventID) {
    final EventListener[] l = listener.getListeners(ModelNode.class);
    for (final EventListener element : l) {
      ((EventHandler)element).processEvent(eventID, this);
    }
  }

  public void processed() {
    fire(Event.EVENT_PROCESSED);
  }

  public void raised() {
    fire(Event.EVENT_RAISED);
  }

  public void removeListener(final EventHandler node) {
    listener.remove(EventHandler.class, node);
  }

  protected synchronized void setID() {
    ID = Event.IDgen;
    Event.IDgen++;
  }

  public boolean unlistened() {
    return (listener.getListenerCount() == 0);
  }

}
