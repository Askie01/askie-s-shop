package context.listeners;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final Dotenv dotenv = Dotenv.load();
        System.setProperty("LOG_PATH", dotenv.get("LOG_PATH"));
        System.out.println("Ścieżka logów została ustawiona na: " + System.getProperty("LOG_PATH"));
    }
}
