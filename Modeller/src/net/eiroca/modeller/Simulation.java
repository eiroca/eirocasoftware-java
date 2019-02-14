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

public class Simulation {

  private Scenario scenario;
  private Model model;

  public Simulation() {
    //
  }

  public void run() {
    final SimulationContext context = new SimulationContext(this);
    scenario.setup(context);
    model.beginSimulation(context);
    Event e;
    while (true) {
      e = context.nextEvent();
      if (e == null) {
        break;
      }
      model.processEvent(e);
    }
    model.endSimulation();
    scenario.cleanup(context);
    context.sendMessage("That's all...");
    System.out.println(context.getMessages());
  }

  public void setModel(final Model m) {
    model = m;
  }

  public void setScenario(final Scenario s) {
    scenario = s;
  }

}
