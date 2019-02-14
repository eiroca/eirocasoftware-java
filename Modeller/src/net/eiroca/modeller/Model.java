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

public class Model {

  protected SimulationContext context;
  public ModelNode modelRootNode;

  public Model() {
    //
  }

  public void addEvent(final Event e, final boolean inTimeLine) {
    if (inTimeLine) {
      try {
        final TimeLine tl = context.getTimeLine();
        tl.addEvent(e);
      }
      catch (final TimeLineException e1) {
        // TODO Gestire eventi nel passato spannati dai nodi
      }
    }
    else {
      processEvent(e);
    }
  }

  public void beginSimulation(final SimulationContext context) {
    this.context = context;
  }

  public void endSimulation() {
    //
  }

  public void processEvent(final Event e) {
    if (e.unlistened() && (modelRootNode != null)) {
      e.addListener(modelRootNode);
    }
    e.raised();
  }

  public void sendMessage(final String msg) {
    context.sendMessage(msg);
  }

}
