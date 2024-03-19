package com.kampus.kshop.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.kampus.kshop", importOptions = ImportOption.DoNotIncludeTests.class)
public class NamingTest {

    @ArchTest
    static ArchRule controller_should_be_in_a_controller_package =
            classes()
                    .that().haveSimpleNameContaining("Controller")
                    .should().resideInAPackage("..controller..");

    @ArchTest
    static ArchRule rest_controller_naming_should_ending_with_controller =
            classes()
                    .that().areAnnotatedWith(RestController.class)
                    .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    static ArchRule repository_naming_should_ending_repository =
            classes()
                    .that().areAnnotatedWith(Repository.class)
                    .should().haveSimpleNameEndingWith("Repository");

    @ArchTest
    static ArchRule service_naming_should_ending_service =
            classes()
                    .that().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("Service");
}
