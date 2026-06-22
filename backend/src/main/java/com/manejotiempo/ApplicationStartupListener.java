package com.manejotiempo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener {

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {
        System.out.println("\n");
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║        🎉 Aplicación iniciada correctamente 🎉            ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.println("║                                                           ║");
        System.out.println("║   Accede a la aplicación en:                              ║");
        System.out.println("║   👉 http://localhost:8081                                ║");
        System.out.println("║                                                           ║");
        System.out.println("║   API REST:                                               ║");
        System.out.println("║   👉 http://localhost:8081/api                            ║");
        System.out.println("║                                                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println("\n");
    }
}
