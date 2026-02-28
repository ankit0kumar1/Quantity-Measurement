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

