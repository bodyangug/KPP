package first;

import javax.swing.*;
import java.awt.*;

public class Graphics extends JFrame {

    private final String unavailable = "Адрес недоступен";
    private JEditorPane editor;  // наш редактор
    private JTextField url;  // текстовое поле с адресом

    public Graphics() {
        super("Пример с JEditorPane");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создаие пользовательского интерфейса
        createGUI();
        // Вывод окна на экран
        setSize(500, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Graphics();
    }

    /**
     * Процедура создания интерфейса
     */
    private void createGUI() {
        // Панель с адресной строкой
        JPanel pnlURL = new JPanel();
        pnlURL.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlURL.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Поле URL адреса
        url = new JTextField(35);
        // Слушатель окончания ввода
        pnlURL.add(new JLabel("Class:"));
        pnlURL.add(url);
        // Создание редактора
        editor = new JEditorPane();

        JButton btnNewButtonPlot = new JButton("Find");
        btnNewButtonPlot.getBounds().x = 1;
        btnNewButtonPlot.getBounds().y = 1;
        btnNewButtonPlot.setSize(10, 10);
        btnNewButtonPlot.addActionListener(e -> {
            String result = "";
            try {
                Class<?> myClass = Class.forName(url.getText());
                result += ClassChecker.checkModifiers(myClass);
                result += ClassChecker.checkSuperclass(myClass);
                result += ClassChecker.checkInterfaces(myClass);
                result += ClassChecker.checkFields(myClass);
                result += ClassChecker.checkConstructors(myClass);
                result += ClassChecker.checkMethods(myClass);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            editor.setText(result);
        });
        getContentPane().add(btnNewButtonPlot, BorderLayout.AFTER_LAST_LINE);
        // Поддержка ссылок
        // Размещение в форме
        getContentPane().add(pnlURL, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(editor));
    }

}
