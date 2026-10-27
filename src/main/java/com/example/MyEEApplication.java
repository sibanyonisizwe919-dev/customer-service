package com.example;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/customer-service")
public class MyEEApplication extends Application {
    // GlassFish auto-scans all REST endpoints in the project when left empty
}
