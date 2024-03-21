package com.kampus.kbazaar.architecture;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(
        packages = "com.kampus.kbazaar",
        importOptions = ImportOption.DoNotIncludeTests.class)
public class CodingRuleTest {

    @ArchTest private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
}
