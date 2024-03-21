package com.kampus.kbazaar.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@AnalyzeClasses(
        packages = "com.kampus.kbazaar",
        importOptions = ImportOption.DoNotIncludeTests.class)
public class DependencyRuleTest {

    @ArchTest
    static final ArchRule service_should_access_by_controller =
            classes()
                    .that()
                    .areAnnotatedWith(Service.class)
                    .should()
                    .dependOnClassesThat()
                    .areAnnotatedWith(Repository.class)
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule repository_should_access_by_service =
            classes()
                    .that()
                    .areAnnotatedWith(Repository.class)
                    .should()
                    .onlyBeAccessed()
                    .byClassesThat()
                    .areAnnotatedWith(Service.class)
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule repository_should_not_access_services =
            noClasses()
                    .that()
                    .haveSimpleNameEndingWith("Repository")
                    .should()
                    .accessClassesThat()
                    .haveSimpleNameEndingWith("Service");

    @ArchTest
    static final ArchRule service_should_not_access_controller =
            noClasses()
                    .that()
                    .haveSimpleNameEndingWith("Service")
                    .should()
                    .accessClassesThat()
                    .haveSimpleNameEndingWith("Controller")
                    .allowEmptyShould(true);
}
