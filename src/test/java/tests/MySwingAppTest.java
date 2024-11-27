package tests;

import com.example.MySwingApp;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

public class MySwingAppTest {
    private JFrame frame;
    private Robot robot;

    @BeforeEach
    public void setUp() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            MySwingApp app = new MySwingApp();
            app.setVisible(true);
            frame = app;
        });

        robot = BasicRobot.robotWithNewAwtHierarchy();

        assertThat(frame.isShowing()).isTrue();
    }

    @AfterEach
    public void tearDown() {
        if (robot != null) {
            robot.cleanUp();
        }
        if (frame != null) {
            frame.dispose();
        }
    }

    @Test
    public void testButton() {
        JButton button = (JButton) findComponentByName(frame.getContentPane(), "myButton");
        assertThat(button).isNotNull();

        JButtonFixture buttonFixture = new JButtonFixture(robot, button);
        buttonFixture.requireVisible().click();
    }

    @Test
    public void testTextField() {
        JTextField textField = (JTextField) findComponentByName(frame.getContentPane(), "myTextField");
        assertThat(textField).isNotNull();

        JTextComponentFixture textFieldFixture = new JTextComponentFixture(robot, textField);
        textFieldFixture.requireVisible().enterText("Тест");
        textFieldFixture.requireText("Тест");
    }

    private Component findComponentByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (name.equals(component.getName())) {
                return component;
            }
            if (component instanceof Container) {
                Component found = findComponentByName((Container) component, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
