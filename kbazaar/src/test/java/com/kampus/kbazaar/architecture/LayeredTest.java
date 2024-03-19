package com.kampus.kbazaar.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.kampus.kbazaar", importOptions = ImportOption.DoNotIncludeTests.class)
public class LayeredTest {

    static final ArchRule layeredArchitecture =
            layeredArchitecture()
                    .consideringAllDependencies()
                    .layer("Controller").definedBy("..controller..")
                    .layer("Service").definedBy("..service..")
                    .layer("Repository").definedBy("..repository..")
                    .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                    .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

    static final ArchRule controller_packages =
            classes().that().areAnnotatedWith(RestController.class)
                    .should().resideInAPackage("..controller..");

    static final ArchRule service_packages =
            classes().that()
                    .areAnnotatedWith(Service.class)
                    .should().resideInAPackage("..service..");

    static final ArchRule repository_packages =
            classes().that()
                    .areAnnotatedWith(Repository.class)
                    .should().resideInAPackage("..repository..");

}