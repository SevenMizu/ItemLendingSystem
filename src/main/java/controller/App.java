package controller;

import model.DataHandler;
import model.DayCounter;
import view.ViewContract;
import view.ViewItem;
import view.ViewMember;
import view.ViewMenu;

/**
 * Responsible for staring the application.
 */
public class App {
  DayCounter time = new DayCounter();
  DataHandler data = new DataHandler(time);
  ViewMenu view = new ViewMenu(time);
  ViewMember memberView = new ViewMember(time);
  ViewItem itemView = new ViewItem();
  ViewContract contractView = new ViewContract(); 
  UserInterface ui = new UserInterface(view, memberView, itemView, contractView, time, data);

  public void startApp() {
    data.insetData();
    view.startDisplay();
  }

  /**
   * Starting the app. 
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    App app = new App();
    app.startApp();
    UserInterface userInterface = app.ui;
    userInterface.mainMenu();
  }
}
