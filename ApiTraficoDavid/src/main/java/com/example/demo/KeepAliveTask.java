package com.example.demo;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableScheduling
public class KeepAliveTask {

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 600000) // Cada 10 minutos
    public void keepAlive() {
        try {
            String url = "https://ApiTraficoOpendataEuskadi.onrender.com/health";
            restTemplate.getForObject(url, String.class);
            System.out.println("✅ Sigue activa request enviada");
        } catch (Exception e) {
            System.err.println("❌ Error en no sigue activa: " + e.getMessage());
        }
    }
}