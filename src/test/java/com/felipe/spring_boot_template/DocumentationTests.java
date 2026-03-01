package com.felipe.spring_boot_template;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.docs.Documenter.DiagramOptions;
import org.springframework.modulith.docs.Documenter.Options;

/**
 * Generates UML and C4 architecture diagrams using Spring Modulith Docs.
 *
 * <p>
 * Output files are written to:
 * 
 * <pre>
 * target/spring-modulith-docs/
 * </pre>
 *
 * <ul>
 * <li>spring-modulith-docs.puml → UML general (all modules)</li>
 * <li>{module-name}.puml → UML per module</li>
 * <li>components.puml → C4 Component diagram</li>
 * </ul>
 */
class DocumentationTests {

        /** Shared ApplicationModules instance to avoid scanning twice. */
        private final ApplicationModules modules = ApplicationModules.of(SpringBootTemplateApplication.class);

        // ─────────────────────────────────────────────────────────────────────────
        // 1. Architecture verification
        // ─────────────────────────────────────────────────────────────────────────

        /**
         * Verifies that no module dependency rules are violated before generating docs.
         * Fails fast if the architecture is inconsistent.
         */
        @Test
        void verifyModularStructureBeforeDocumenting() {
                modules.verify();
        }

        // ─────────────────────────────────────────────────────────────────────────
        // 2. UML – diagram for ALL modules together
        // ─────────────────────────────────────────────────────────────────────────

        /**
         * Generates {@code target/spring-modulith-docs/spring-modulith-docs.puml}
         * showing every module and the relationships between them.
         *
         * <p>
         * Open the {@code .puml} file in IntelliJ with the <em>PlantUML
         * Integration</em>
         * plugin to see the rendered diagram instantly.
         */
        @Test
        void writeDocumentationSnippets() {
                new Documenter(modules)
                                .writeModulesAsPlantUml(
                                                DiagramOptions.defaults()
                                                                .withStyle(DiagramOptions.DiagramStyle.UML))
                                .writeIndividualModulesAsPlantUml(
                                                DiagramOptions.defaults()
                                                                .withStyle(DiagramOptions.DiagramStyle.UML));
        }

        // ─────────────────────────────────────────────────────────────────────────
        // 3. UML – individual diagram per module
        // ─────────────────────────────────────────────────────────────────────────

        /**
         * Generates one {@code .puml} file per module inside
         * {@code target/spring-modulith-docs/}.
         * Each file is named after its module (e.g. {@code users.puml},
         * {@code auth.puml}).
         *
         * <p>
         * Useful to share focused diagrams with stakeholders responsible for a
         * single bounded context.
         */
        @Test
        void writeIndividualModuleDiagrams() {
                new Documenter(modules)
                                .writeIndividualModulesAsPlantUml(
                                                DiagramOptions.defaults()
                                                                .withStyle(DiagramOptions.DiagramStyle.UML));
        }

        // ─────────────────────────────────────────────────────────────────────────
        // 4. C4 Component diagram
        // ─────────────────────────────────────────────────────────────────────────

        /**
         * Generates {@code target/spring-modulith-docs/components.puml} using the C4
         * Component style (compatible with the <em>C4-PlantUML</em> notation).
         *
         * <p>
         * The C4 view highlights the components and their inter-dependencies in a
         * cleaner, higher-level notation than the standard UML class diagram.
         */
        @Test
        void writeC4ComponentDiagram() {
                new Documenter(modules)
                                .writeModulesAsPlantUml(
                                                DiagramOptions.defaults()
                                                                .withStyle(DiagramOptions.DiagramStyle.C4));
        }

        // ─────────────────────────────────────────────────────────────────────────
        // 5. All diagrams in one shot (convenience method)
        // ─────────────────────────────────────────────────────────────────────────

        /**
         * Convenience test that generates every diagram in a single run:
         * <ol>
         * <li>UML overview of all modules</li>
         * <li>UML diagram per individual module</li>
         * <li>C4 Component overview</li>
         * </ol>
         *
         * <p>
         * Run this test to refresh all documentation artifacts at once.
         */
        @Test
        void writeAllDiagrams() {
                Documenter documenter = new Documenter(modules,
                                Options.defaults().withOutputFolder("target/spring-modulith-docs"));

                // UML – all modules
                documenter.writeModulesAsPlantUml(
                                DiagramOptions.defaults()
                                                .withStyle(DiagramOptions.DiagramStyle.UML));

                // UML – per module
                documenter.writeIndividualModulesAsPlantUml(
                                DiagramOptions.defaults()
                                                .withStyle(DiagramOptions.DiagramStyle.UML));

                // C4 – all modules
                documenter.writeModulesAsPlantUml(
                                DiagramOptions.defaults()
                                                .withStyle(DiagramOptions.DiagramStyle.C4));
        }
}
