package br.com.appregia;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.appregia");

        noClasses()
            .that()
            .resideInAnyPackage("br.com.appregia.service..")
            .or()
            .resideInAnyPackage("br.com.appregia.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.com.appregia.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
