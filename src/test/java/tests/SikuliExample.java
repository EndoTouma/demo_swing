package tests;

import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.IOException;

public class SikuliExample {
    public static void main(String[] args) throws InterruptedException, IOException, FindFailed {

        // Шаг 1: Запускаем .exe файл
        System.out.println("[INFO] Запускаем .exe файл...");
        new ProcessBuilder("src/test/resources/application/DemoSwing.exe").start();
        Thread.sleep(5000);
        System.out.println("[INFO] Приложение запущено.");

        // Шаг 2: Инициализируем экран и добавляем путь к изображениям
        Screen screen = new Screen();
        ImagePath.add("src/test/resources/sikuli-images/");

        // Шаг 3: Загружаем изображения элементов
        Pattern ClickMeButton = new Pattern("ClickMeButton.png");
        Pattern TextInputField = new Pattern("TextInputField.png");
        Pattern ClickCloseButton = new Pattern("CloseButton");

        // Шаг 4: Нажимаем кнопку
        System.out.println("[INFO] Ищем кнопку...");
        screen.wait(ClickMeButton, 5);
        screen.click(ClickMeButton);
        System.out.println("[INFO] Кнопка нажата.");

        // Шаг 5: Вводим текст в текстовое поле
        System.out.println("[INFO] Ищем текстовое поле...");
        screen.wait(TextInputField, 5);
        screen.paste(TextInputField, "Тест");
        System.out.println("[INFO] Текст введён.");

        // Шаг 6: Закрываем приложение после выполнения теста
        System.out.println("[INFO] Ищем кнопку закрытия приложения...");
        screen.wait(ClickCloseButton, 5);
        screen.click(ClickCloseButton);
        System.out.println("[INFO] Приложение закрыто.");

    }
}
