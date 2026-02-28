# 📏 Quantity Measurement App

## 🧾 Overview
The Quantity Measurement App is a Test-Driven Development (TDD) based project that demonstrates how to build scalable and maintainable software through incremental development.

The application focuses on comparing and converting length measurements across different units while following:

- Test-Driven Development (TDD)
- Clean Code Principles
- DRY (Don’t Repeat Yourself)
- Professional Git Workflow (feature branches + PR)

The project evolves step-by-step through Use Cases (UCs), with each UC introducing a small feature and refining the design to keep the system extensible and clean.


## 🧪 Development Methodology

This project follows the TDD cycle:

🔴 Write a failing test  
🟢 Write minimal code to pass  
🔵 Refactor safely  

This ensures:
- Code safety
- Maintainability
- Scalability

## 🌳 Git Workflow

- `main` → Stable production code  
- `dev` → Integration branch  
- `feature/UCx-*` → Individual feature branches  

Each Use Case was:
1. Developed in a feature branch  
2. Tested locally  
3. Pushed with a Pull Request  
4. Reviewed and merged into `dev`  

## 📌 Key Features

- Compare lengths across different units
- Convert between units
- Easily extendable unit system
- Fully covered with unit tests

---


## 📂 Project Structure

```
src/
 ├── main/java/
 └── test/java/
```

---

📚 USE CASE IMPLEMENTATION
## 🟢 UC1 — Feet Equality
🎯 Goal

Compare two Feet measurements for equality.

🧪 Tests Written

We validated the equals contract:

Same value → equal

Different value → not equal

Null comparison → false

Different object type → false

Same reference → true

💻 Implementation

Created Feet class with:

value field

equals() method

🧠 Learning Outcome

Understanding equality contract

First step of TDD

---

## 🟢 UC2 — Inches Equality

### 🎯 Goal
Add support for the **Inches** unit alongside **Feet**.

### 🧪 Tests Written
- Inches = Inches equality tests  
- Repeated the same equality logic used for Feet  

### 💻 Implementation
- Created an `Inches` class similar to the existing `Feet` class  
- Implemented equality comparison  

### ⚠️ Problem Observed
- Significant code duplication  
- `Feet` and `Inches` contained identical logic  
- Violated the **DRY (Don’t Repeat Yourself)** principle  

### 🧠 Learning Outcome
Recognized the need for refactoring.  
This use case highlighted how duplication signals a design flaw and pushed the system toward a more generic, extensible solution.

---

## 🔵 UC3 — Refactor to Generic Length Class

### 🎯 Goal
Eliminate duplication by introducing a generic measurement model.

---

### 🛠 Refactoring Done

Removed:
- ❌ `Feet` class
- ❌ `Inches` class

Introduced:
- ✅ `Length` class
- ✅ `LengthUnit` enum

---

### 🧠 Core Design Change

Replaced multiple concrete classes:

- Feet  
- Inches  

With a single generic model:

```
Length(value, LengthUnit)
```

### 📐 Base Unit Concept

All units are internally converted to **INCHES** (base unit).

- 1 Foot = 12 Inches  
- 1 Inch = 1 Inch  

Added method:
```
convertToBaseUnit()
```

### 🧪 Tests Covered

- ✔ Feet = Feet  
- ✔ Inches = Inches  
- ✔ 1 Foot = 12 Inches  
- ✔ Symmetry  
- ✔ Transitive equality  
- ✔ `equals()` contract validation  

### 🧠 Learning Outcome

- Safe refactoring using tests  
- Generic design thinking  
- Strong domain modeling  
- DRY principle implementation  

---

# Quantity Measurement – UC4

## Supported Units

* Feet
* Inch
* Yard
* Centimeter

## Features

* Takes input from user
* Converts units internally
* Checks if two values are equal
* Supports cross-unit comparison

## Example

1 Feet = 12 Inch
3 Feet = 1 Yard
2.54 Centimeter = 1 Inch
---
# UC5 – Unit-to-Unit Conversion

UC5 adds direct conversion between length units using a common base unit.

## Supported Units

FEET, INCHES, YARDS, CENTIMETERS

## Features

* Static `convert(value, source, target)` method
* Instance `convertTo()` method
* Base unit normalization
* Input validation (null, NaN, infinite)
* Immutable value object

## Formula

```
result = value × (source.factor / target.factor)
```

## Example

* 1 FEET → INCHES = 12
* 3 YARDS → FEET = 9
* 36 INCHES → YARDS = 1

---
# UC6 – Addition of Two Length Units

UC6 extends UC5 by adding support for **addition of two length measurements** (same category).

## Supported Units

FEET, INCHES, YARDS, CENTIMETERS

## Features

* Add two `Length` objects
* Automatic unit conversion before addition
* Result returned in unit of first operand
* Uses base unit normalization (FEET)
* Immutable design (returns new object)
* Input validation for null, NaN, infinite values

## Logic

1. Convert both lengths to base unit (FEET)
2. Add values
3. Convert sum back to first operand’s unit
4. Return new `Length` object

## Example

* 1 FEET + 2 FEET = 3 FEET
* 1 FEET + 12 INCHES = 2 FEET
* 12 INCHES + 1 FEET = 24 INCHES
* 1 YARD + 3 FEET = 2 YARDS

  ---

  # Quantity Measurement – UC7

## Description

This project performs **length addition and unit conversion** using OOP principles in Java.

## Supported Units

* FEET
* INCHES
* YARDS
* CENTIMETERS

## Features

* Add two quantities
* Automatic unit conversion
* Result in chosen target unit
* Equality comparison using base unit (FEET)
* Immutable design
* Input validation

## Example

```
1 FEET + 12 INCHES

Output:
2.0 feet
24.0 inches
0.6666666666666666 yards
```

## Design

* Base unit: FEET
* Uses conversion factors
* EPSILON-based equality comparison

  ---
  # UC8 – Standalone LengthUnit Refactoring

## Description

UC8 refactors the design by extracting `LengthUnit` into a standalone enum and assigning it full responsibility for unit conversions.

## Key Improvements

* `LengthUnit` handles all conversion logic
* `Quantity` handles comparison and arithmetic
* Follows Single Responsibility Principle (SRP)
* Removes circular dependency risk
* Improves scalability for future units (Weight, Volume, etc.)
* Fully backward compatible with UC1–UC7

## Supported Units

* FEET
* INCHES
* YARDS
* CENTIMETERS

## Example Output

```
Convert 1 FEET to INCHES:
12.0 inches

Add 1 FEET + 12 INCHES (in FEET):
2.0 feet

Add 1 FEET + 12 INCHES (in YARDS):
0.6666666666666666 yards

36 INCHES equals 1 YARDS?
true
```

## Architecture

* Base Unit: FEET
* Conversion delegated to `LengthUnit`
* Equality uses epsilon comparison
* Immutable design

---
# UC9 – Weight Measurement

## Description
UC9 extends the Quantity Measurement Application to support **Weight Measurements** alongside Length.

Supported Units:
- **KILOGRAM (kg)** – Base unit
- **GRAM (g)** – 1 g = 0.001 kg
- **POUND (lb)** – 1 lb = 0.453592 kg

## Features
- Equality comparison (cross-unit supported)
- Unit conversion
- Addition (implicit & explicit target unit)
- Immutable design
- Type-safe (Weight ≠ Length)
- Uses epsilon (1e-6) for floating-point precision

## Conclusion
UC9 validates scalable architecture by supporting multiple measurement categories without affecting existing length functionality.

---

# UC10 – Generic Quantity

## Description
UC10 refactors UC9 into a single generic class:

`Quantity<U extends IMeasurable>`

This removes duplication and makes the system scalable and maintainable.

---

## Improvements
- Single generic `Quantity` class
- Common `IMeasurable` interface
- Supports multiple categories (Length, Weight)
- No duplicate logic (DRY)
- Type-safe and immutable

---

## Features
- Equality comparison
- Unit conversion
- Addition (with optional target unit)
- Prevents cross-category comparison

---

# UC11 – Volume Measurement (Litre, Millilitre, Gallon)

## Description
UC11 extends the Generic Quantity system (UC10) to support **Volume measurements** using the existing:

---

## Supported Volume Units

- **LITRE (L)** – Base Unit  
- **MILLILITRE (mL)** → 1 L = 1000 mL  
- **GALLON (gal)** → 1 gal ≈ 3.78541 L  

---

## Features

- Equality comparison (cross-unit supported)
- Unit conversion
- Addition (implicit & explicit target unit)
- Cross-category comparison prevented
- Immutable design
- Fully backward compatible (UC1–UC10)

  ---
  
# UC12 – Subtraction & Division on Quantity

## Description
UC12 extends the generic `Quantity<U extends IMeasurable>` system by adding:

- Subtraction → returns new `Quantity<U>`
- Division → returns dimensionless `double`

No architectural changes required.

## Features
- Cross-unit arithmetic (same category)
- Explicit & implicit target unit
- Cross-category prevention
- Division by zero handling
- Immutability preserved
- Works for Length, Weight, Volume
