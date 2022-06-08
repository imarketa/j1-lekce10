package cz.czechitas.lekce10.formbuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

/**
 * @author Filip Jirsák
 */
public class ActionBuilder {
  public static Action create(String text, Runnable handler) {
    return create(text, null, handler);
  }

  public static Action create(String text, Icon icon, Runnable handler) {
    int mnemonicIndex = text.indexOf("&");
    Integer mnemonicKey = null;
    if (mnemonicIndex >= 0) {
      Objects.checkIndex(mnemonicIndex, text.length() - 1);
      text = text.substring(0, mnemonicIndex) + text.substring(mnemonicIndex + 1);
      mnemonicKey = (int) text.charAt(mnemonicIndex + 1);
    }

    AbstractAction action = new AbstractAction(text, icon) {
      @Override
      public void actionPerformed(ActionEvent e) {
        handler.run();
      }
    };

    if (mnemonicKey != null) {
      action.putValue(Action.MNEMONIC_KEY, mnemonicKey);
    }
    return action;
  }
}
