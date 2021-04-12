package br.com.yagovcb.transacoes;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchTest {

    @Test
    @DisplayName("Service e Repository não podem depender da camada Web")
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("br.com.yagovcb");

        noClasses()
                .that()
                .resideInAnyPackage("..services..")
                .or()
                .resideInAnyPackage("..repository..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..br.com.yagovcb.web..")
                .because("Service e Repository não podem depender da camada Web")
                .check(importedClasses);
    }
}

