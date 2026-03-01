package com.felipe.spring_boot_template;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTests {

    @Test
    void verifyModularStructure() {
        ApplicationModules modules = ApplicationModules.of(SpringBootTemplateApplication.class);
        modules.verify();
    }

    @Test
    void printModules() {
        ApplicationModules modules = ApplicationModules.of(SpringBootTemplateApplication.class);
        modules.forEach(System.out::println);
    }
}
