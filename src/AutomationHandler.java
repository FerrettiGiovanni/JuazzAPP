import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class AutomationHandler {
    private Robot robot;

    public AutomationHandler() {
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void performWhatsAppAutomation() {
        openWhatsAppWeb();
        clickAtLocation(200, 185);
        waitForChatToLoad(2000);
        typeString("Nome Contatto");
        pressEnter();
        waitForChatToLoad(5000);
        typeString("Inserire il messaggio");
        pressEnter();
        
    }

    private void openWhatsAppWeb() {
        // Codice per aprire WhatsApp Web
    	// ancora non implementato
    }

    private void clickAtLocation(int x, int y) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void waitForChatToLoad(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void typeString(String text) {
        Random random = new Random();

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

            // Aggiunge una pausa casuale tra 3ms e 30ms
            int randomDelay = random.nextInt(28) + 3; // Genera un numero casuale tra 3 e 30
            waitForChatToLoad(randomDelay);
        }
    }

    private void pressEnter() {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
