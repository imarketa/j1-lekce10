package cz.czechitas.lekce10.formbuilder;

import java.awt.*;

/**
 * @author Filip Jirsák
 */
public interface WithModel<B> {
  FormBuilderWithContainer<B> container(Container container);
}
