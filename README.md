# Quantity-Measurement-App

##  Overview
This repository captures the step-by-step development journey of the **Quantity Measurement Application** built during the training program.

---

##  Branch Strategy
The project follows a structured branching model where each feature is implemented independently.

| Branch | Description |
|--------|------------|
| main | Contains final stable code and documentation |
| dev | Integration branch for all features |
| feature/UC1-FeetEquality | Basic equality check for feet |
| feature/UC2-InchSupport | Equality with feet and inches |
| feature/UC3-QuantityModel | Generic quantity class |
| feature/UC4-NewUnits | Added more length units |
| feature/UC5-ConversionLogic | Unit conversion logic |
| feature/UC6-Addition | Addition of quantities |
| feature/UC7-TargetConversion | Addition with result unit |
| feature/UC8-UnitRefactor | Logic moved into enums |
| feature/UC9-WeightModule | Weight units support |
| feature/UC10-GenericDesign | Generic quantity implementation |
| feature/UC11-VolumeModule | Volume support |
| feature/UC12-Arithmetic | Subtraction and division |
| feature/UC13-CodeOptimization | Removed redundancy (DRY) |
| feature/UC14-TemperatureModule | Temperature handling |
| feature/UC15-LayeredArchitecture | N-Tier structure |
| feature/UC16-DatabaseIntegration | JDBC persistence |
| feature/UC17-SpringBoot | Migrated to Spring Boot |

---

##  Project Structure

QuantityMeasurementApp/
│
├── main/
│ └── README.md
│
├── dev/
│
├── feature/
│ ├── UC1-FeetEquality/
│ ├── UC2-InchSupport/
│ ├── UC3-QuantityModel/
│ ├── UC4-NewUnits/
│ ├── UC5-ConversionLogic/
│ ├── UC6-Addition/
│ ├── UC7-TargetConversion/
│ ├── UC8-UnitRefactor/
│ ├── UC9-WeightModule/
│ ├── UC10-GenericDesign/
│ ├── UC11-VolumeModule/
│ ├── UC12-Arithmetic/
│ ├── UC13-CodeOptimization/
│ ├── UC14-TemperatureModule/
│ ├── UC15-LayeredArchitecture/
│ ├── UC16-DatabaseIntegration/
│ └── UC17-SpringBoot/


---

##  Use Case Progression

### 🔹 UC1 – Basic Feet Comparison
- Compare two values in feet  
- Implement equality logic  
- Write unit test cases  

### 🔹 UC2 – Feet & Inches Support
- Introduce inches  
- Compare based on value, not reference  
- Identify duplication issues  

### 🔹 UC3 – Unified Quantity Model
- Create reusable `Quantity` class  
- Use enum for units  
- Follow DRY principle  

### 🔹 UC4 – Extend Unit Support
- Add yard and centimeter  
- No change in core design  
- Improve extensibility  

### 🔹 UC5 – Conversion System
- Convert between units  
- Use base unit strategy  
- Handle precision properly  

### 🔹 UC6 – Quantity Addition
- Add two quantities  
- Convert before operation  
- Maintain immutability  

### 🔹 UC7 – Result in Target Unit
- Return result in desired unit  
- Improve flexibility  

### 🔹 UC8 – Refactor Unit Handling
- Move logic into enum  
- Reduce coupling  

### 🔹 UC9 – Weight Integration
- Add kilogram, gram, pound  
- Reuse existing structure  

### 🔹 UC10 – Generic Quantity Design
- Use generics (`Quantity<U>`)  
- Introduce unit interface  
- Improve scalability  

### 🔹 UC11 – Volume Support
- Add litre, millilitre, gallon  
- Follow Open-Closed Principle  

### 🔹 UC12 – Arithmetic Enhancements
- Add subtraction & division  
- Return accurate results  

### 🔹 UC13 – Code Optimization
- Eliminate repeated logic  
- Introduce helper methods  

### 🔹 UC14 – Temperature Handling
- Add Celsius & Fahrenheit  
- Restrict invalid operations  

### 🔹 UC15 – Layered Architecture
- Controller, Service, Repository  
- Improve maintainability  

### 🔹 UC16 – Database Integration
- JDBC persistence  
- Prepared statements  

### 🔹 UC17 – Spring Boot Migration
- Convert to Spring Boot  
- Build REST APIs  
- Use JPA  

---

##  Key Learnings
- Object-Oriented Programming (OOP)  
- Test-Driven Development (TDD)  
- Clean Code & SOLID Principles  
- Generic Programming  
- N-Tier Architecture  
- REST API Development  
- Database Integration (JDBC & JPA)  

---

##  Final Outcome
A production-ready application that:
- Supports multiple measurement types  
- Performs arithmetic operations across units  
- Uses layered architecture  
- Provides REST APIs (Spring Boot)  
- Stores data in a database  

---

##  Author
**Harsh Gupta**
