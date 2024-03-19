package com.kampus.kbazaar.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.kampus.kbazaar", importOptions = ImportOption.DoNotIncludeTests.class)
public class DependencyRuleTest {

    @ArchTest
    static final ArchRule service_packages =
            classes().that()
                    .areAnnotatedWith(Service.class)
                    .should().onlyBeAccessed().byClassesThat()
                    .areAnnotatedWith(RestController.class)
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule repository_packages =
            classes().that()
                    .areAnnotatedWith(Repository.class)
                    .should().onlyBeAccessed().byClassesThat()
                    .areAnnotatedWith(Service.class)
                    .allowEmptyShould(true);
}