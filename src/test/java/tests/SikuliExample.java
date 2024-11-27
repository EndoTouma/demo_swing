package tests;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.sikuli.script.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

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
        Pattern ClickCloseButton = new Pattern("CloseButton.png");

        // Шаг 4: Проверяем текст кнопки
        System.out.println("[INFO] Проверяем текст кнопки...");
        Region buttonRegion = screen.wait(ClickMeButton, 5); // Ожидаем появления кнопки

        // Захват изображения региона
        BufferedImage buttonImage = screen.capture(buttonRegion).getImage();
        BufferedImage processedImage = preprocessImage(buttonImage);
        File outputFile = new File("src/test/resources/temp/ClickMeButton.png");
        ImageIO.write(processedImage, "png", outputFile);

        // Распознаём текст с помощью Tess4J
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); // Путь к файлам Tesseract
        tesseract.setLanguage("rus"); // Устанавливаем русский язык

        try {
            String recognizedText = tesseract.doOCR(outputFile);
            System.out.println("[INFO] Распознанный текст кнопки: " + recognizedText);

            if (recognizedText != null && recognizedText.trim().equalsIgnoreCase("Нажми меня")) {
                System.out.println("[INFO] Текст кнопки корректный.");
            } else {
                System.err.println("[ERROR] Некорректный текст кнопки: '" + recognizedText + "'");
            }
        } catch (TesseractException e) {
            e.printStackTrace();
            System.err.println("[ERROR] Ошибка распознавания текста.");
        }

        // Шаг 5: Нажимаем кнопку
        System.out.println("[INFO] Ищем кнопку...");
        screen.click(ClickMeButton);
        System.out.println("[INFO] Кнопка нажата.");

        // Шаг 6: Вводим текст в текстовое поле
        System.out.println("[INFO] Ищем текстовое поле...");
        screen.wait(TextInputField, 5);
        screen.paste(TextInputField, "Тест");
        System.out.println("[INFO] Текст введён.");

        // Шаг 7: Закрываем приложение после выполнения теста
        System.out.println("[INFO] Ищем кнопку закрытия приложения...");
        screen.wait(ClickCloseButton, 5);
        screen.click(ClickCloseButton);
        System.out.println("[INFO] Приложение закрыто.");
    }


    private static BufferedImage preprocessImage(BufferedImage original) {
        BufferedImage binaryImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics = binaryImage.createGraphics();
        graphics.drawImage(original, 0, 0, null);
        graphics.dispose();
        return binaryImage;
    }
}
