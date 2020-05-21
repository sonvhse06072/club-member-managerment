package com.solid.cmm;

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
            .importPackages("com.solid.cmm");

        noClasses()
            .that()
            .resideInAnyPackage("com.solid.cmm.service..")
            .or()
            .resideInAnyPackage("com.solid.cmm.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.solid.cmm.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
