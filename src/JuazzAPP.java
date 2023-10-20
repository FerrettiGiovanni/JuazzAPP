import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class JuazzAPP {

    public static void main(String[] args) throws Exception {
        Process p = null; // Dichiarare la variabile p all'esterno del blocco try-catch

        // Verifica se WhatsApp Web è già aperto nel browser
        if (!isWhatsAppWebOpen()) {
            // Aprire il browser con processo esterno solo se non è già aperto
            p = Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe https://web.whatsapp.com/");

            // Aspettare che il browser si apra
            Thread.sleep(5000);
        }

        // Il resto del tuo codice per l'automazione
        Robot robot = new Robot();
        
        // Cliccare su un punto specifico dello schermo
        int x = 200; // Coordinata x del punto
        int y = 185; // Coordinata y del punto
        robot.mouseMove(x, y); // Sposta il mouse al punto desiderato
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Clicca il pulsante sinistro del mouse
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // Rilascia il pulsante sinistro del mouse

        // Aspettare che la finestra di chat sia completamente caricata
        Thread.sleep(5000);
        
        String name = "Nome Contatto";
        robot.setAutoDelay(40);
        typeString(robot, name);
        
        // Cliccare sul contatto desiderato
        robot.delay(1000); 
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Aspettare che la finestra di chat sia completamente caricata
        Thread.sleep(5000);

        // Scrivere il messaggio nella chat
        String message = "PROVA INVIO";
        robot.setAutoDelay(40);
        typeString(robot, message);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Chiudere il browser 
        if (p != null) {
            p.destroy();
        }
    }

    private static void typeString(Robot robot, String text) {
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

    private static boolean isWhatsAppWebOpen() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("tasklist");
            Process process = processBuilder.start();

            // Leggi l'output del comando tasklist
            java.util.Scanner sc = new java.util.Scanner(process.getInputStream());
            while (sc.hasNext()) {
                String taskListEntry = sc.nextLine();
                if (taskListEntry.contains("chrome.exe") && taskListEntry.contains("https://web.whatsapp.com/")) {
                    // Trovato un processo del browser con WhatsApp Web aperto
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Nessun processo del browser con WhatsApp Web aperto
        return false;
    }
}



