package annotations.gui;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class GUIGenerator {

  private GUIGenerator() {}

  public static void generateGUI(Object formObject) {
    Class<?> cls = formObject.getClass();

    // Проверка на наличия GUIForm аннотации
    if (!cls.isAnnotationPresent(GUIForm.class)) {
      return;
    }

    GUIForm formAnnotation = cls.getAnnotation(GUIForm.class);

    // Создаем основное окно
    JFrame frame = new JFrame(formAnnotation.title());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(formAnnotation.width(), formAnnotation.height());
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

    // Итерируемся по полям класса
    for (Field field : cls.getDeclaredFields()) {
      if (field.isAnnotationPresent(GUITextField.class)) {
        GUITextField textFieldAnnotation = field.getAnnotation(GUITextField.class);

        // Создаем панель с лейблом и текстовым полем
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel label = new JLabel(textFieldAnnotation.label());
        JTextField textField = new JTextField();
        textField.setColumns(20);
        panel.add(label);
        panel.add(textField);
        frame.getContentPane().add(panel);
      }
    }

    frame.pack();
    frame.setVisible(true);
  }
}
