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

        // Creazione di un oggetto per gestire l'automazione
        AutomationHandler automationHandler = new AutomationHandler();
        automationHandler.performWhatsAppAutomation();

        // Chiudere il browser 
        if (p != null) {
            p.destroy();
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



