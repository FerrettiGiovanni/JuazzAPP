import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AutomationHandler {

    public void performWhatsAppAutomation() throws Exception {
        // Creazione di un oggetto Robot per l'automazione
        Robot robot = new Robot();

        // Automazione dei passaggi di WhatsApp
        clickOnScreen(robot, 200, 185);
        Thread.sleep(5000);

        String contactName = "Nome Contatto";
        typeString(robot, contactName);
        pressEnter(robot);

        Thread.sleep(5000);

        String message = "PROVA INVIO";
        typeString(robot, message);
        pressEnter(robot);
    }

    private void clickOnScreen(Robot robot, int x, int y) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void typeString(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (Character.isUpperCase(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            if (Character.isUpperCase(c)) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
    }

    private void pressEnter(Robot robot) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
