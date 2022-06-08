package cz.czechitas.lekce10.formbuilder;

/**
 * @author Filip Jirsák
 */
public interface WithInput<B> {
  FormBuilderWithContainer<B> add();

  FormBuilderWithContainer<B> add(Object constraints);
}
