package system;

import ui.MainMenu;
import ui.ClientLister;

public class AppOperator {
  int width;
  int height;
  MainMenu mm;
  ClientLister cl;

  public AppOperator(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void execOperator() {
    mm = new MainMenu(this, width, height);
    mm.execMainMenu();
  }

  public void runClientLister() {
    cl = new ClientLister(this, width, height);
    cl.show();
  }
}
