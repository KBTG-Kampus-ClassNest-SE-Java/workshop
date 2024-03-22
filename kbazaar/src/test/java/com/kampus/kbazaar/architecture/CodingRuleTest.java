package com.kampus.kbazaar.architecture;

import static com.tngtech.archunit.lang.conditions.ArchConditions.beAnnotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;

import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(
        packages = "com.kampus.kbazaar",
        importOptions = ImportOption.DoNotIncludeTests.class)
public class CodingRuleTest {

    @ArchTest
    private final ArchRule no_field_injection =
            noFields()
                    .should(annotations())
                    .as("no classes should use field injection")
                    .because("assumed we're preferring constructor injection");

    private static ArchCondition<JavaField> annotations() {
        ArchCondition<JavaField> annotatedWithSpringAutowired =
                beAnnotatedWith("org.springframework.beans.factory.annotation.Autowired");
        ArchCondition<JavaField> annotatedWithGuiceInject =
                beAnnotatedWith("com.google.inject.Inject");
        ArchCondition<JavaField> annotatedWithJakartaInject =
                beAnnotatedWith("javax.inject.Inject");
        ArchCondition<JavaField> annotatedWithJakartaResource =
                beAnnotatedWith("javax.annotation.Resource");
        return annotatedWithSpringAutowired
                .or(annotatedWithGuiceInject)
                .or(annotatedWithJakartaInject)
                .or(annotatedWithJakartaResource)
                .as("be annotated with an injection annotation");
    }
}
