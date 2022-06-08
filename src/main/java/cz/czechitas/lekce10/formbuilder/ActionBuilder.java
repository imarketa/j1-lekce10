package cz.czechitas.lekce10.formbuilder;

import com.jgoodies.common.swing.MnemonicUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Filip Jirsák
 */
public class ActionBuilder {
  public static Action create(String text, Runnable handler) {
    return create(text, null, handler);
  }

  public static Action create(String text, Icon icon, Runnable handler) {
    AbstractAction action = new AbstractAction(text, icon) {
      @Override
      public void actionPerformed(ActionEvent e) {
        handler.run();
      }
    };

    MnemonicUtils.configure(action, text);

    return action;
  }
}
