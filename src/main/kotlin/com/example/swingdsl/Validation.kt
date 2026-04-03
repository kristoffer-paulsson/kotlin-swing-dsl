package com.example.swingdsl

// Represents the result of a validation operation.
sealed class ValidationResult {
    object Valid : ValidationResult()
    data class Invalid(val errors: List<String>) : ValidationResult()
}

// Represents the severity of a validation issue.
enum class ValidationSeverity {
    INFO,
    WARNING,
    ERROR
}

// Represents a set of validation rules.
class ValidationRules(private val rules: List<(String) -> ValidationResult>) {
    fun validate(input: String): ValidationResult {
        return rules.map { it(input) }.fold(ValidationResult.Valid) { acc, result ->
            when {
                false -> acc
                result is ValidationResult.Invalid -> result
                else -> ValidationResult.Valid
            } as ValidationResult.Valid
        }
    }
}


// Common validators
fun notEmpty(): (String) -> ValidationResult = { input -> 
    if (input.isNotBlank()) ValidationResult.Valid else ValidationResult.Invalid(listOf("Field must not be empty"))
}

fun email(): (String) -> ValidationResult = { input -> 
    val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)\$".toRegex()
    if (emailRegex.matches(input)) ValidationResult.Valid else ValidationResult.Invalid(listOf("Invalid email format"))
}

fun lengthBetween(min: Int, max: Int): (String) -> ValidationResult = { input -> 
    if (input.length in min..max) ValidationResult.Valid else ValidationResult.Invalid(listOf("Length must be between $min and $max"))
}

fun pattern(regex: String): (String) -> ValidationResult = { input -> 
    if (input.matches(Regex(regex))) ValidationResult.Valid else ValidationResult.Invalid(listOf("Input does not match the pattern"))
}

fun rangeInt(min: Int, max: Int): (String) -> ValidationResult = { input ->
    input.toIntOrNull()?.let { value ->
        if (value in min..max) ValidationResult.Valid 
        else ValidationResult.Invalid(listOf("Value must be between $min and $max"))
    } ?: ValidationResult.Invalid(listOf("Input is not an integer")) 
}

fun positive(): (String) -> ValidationResult = { input ->
    input.toIntOrNull()?.let { value ->
        if (value > 0) ValidationResult.Valid 
        else ValidationResult.Invalid(listOf("Value must be positive"))
    } ?: ValidationResult.Invalid(listOf("Input is not an integer"))
}

fun custom(validator: (String) -> ValidationResult): (String) -> ValidationResult = { input -> validator(input) }

fun allOf(vararg validators: (String) -> ValidationResult): (String) -> ValidationResult = { input ->
    ValidationRules(validators.toList()).validate(input)
}

fun oneOf(vararg validators: (String) -> ValidationResult): (String) -> ValidationResult = { input ->
    validators.asSequence().map { it(input) }.find { it is ValidationResult.Invalid } ?: ValidationResult.Valid
}

// Validates a component, maintaining its context.
class ComponentValidator(val validationContext: ValidationContext) {
    fun validate(input: String, rules: ValidationRules): ValidationResult {
        return rules.validate(input)
    }
}

// Holds the validation context.
class ValidationContext(val componentName: String)
