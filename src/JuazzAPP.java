import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;

public class JuazzAPP {

    public static void main(String[] args) throws Exception {
        Process p = null; 

        p = Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe https://web.whatsapp.com/");
        Thread.sleep(7000);
     

        AutomationHandler automationHandler = new AutomationHandler();
        automationHandler.performWhatsAppAutomation();

        if (p != null) {
            p.destroy();
        }
    }
}


