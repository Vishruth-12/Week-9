# Week 9 — Abstraction and Interfaces: Practice

## Coding problems
1. Garden Plot Area Report
2. Weekly Staff Pay
3. Library Late Fine Counter
4. Electricity Connection Billing
5. Travel Booking with a Common Fee

## Quiz answer key
1. C — Abstraction hides implementation details and exposes what an object does.
2. C — An abstract class cannot be instantiated directly.
3. B — The subclass must implement all inherited abstract methods or remain abstract.
4. C — An abstract method cannot be private because subclasses must be able to implement it.
5. C — Interface fields are implicitly public, static, and final.
6. C — A class may implement any number of interfaces.
7. D — Implemented interface methods must be public.
8. C — Interfaces avoid the conflicting instance-state inheritance that causes the classic diamond problem.
9. D — Conflicting default methods require the implementing class to override the method.
10. B — Abstract classes can define constructors that execute as part of creating a subclass object.

## Concept answers

### 1. Abstraction example
A food delivery app lets a customer select a restaurant, choose dishes, and pay. The app shows the menu, order status, and total, while hiding database queries, payment processing details, and dispatch algorithms. The customer uses the service without needing to know its internal implementation.

### 2. Abstraction vs encapsulation
Abstraction focuses on showing the essential operations while hiding implementation details. Encapsulation protects an object's state by keeping data private and controlling access through methods. A `BankAccount` can expose `deposit()` and `withdraw()` as its abstraction while keeping `balance` private as encapsulation.

### 3. Abstract-class constructors
An abstract class cannot be instantiated, but it can hold fields common to all subclasses. Its constructor initializes that shared state when a concrete subclass object is created. The subclass constructor invokes it directly or implicitly through `super()`.

### 4. Restrictions on abstract methods
A `private` method cannot be overridden by subclasses, so it cannot require an implementation there. A `static` method belongs to the class rather than an object and is not dynamically overridden. A `final` method cannot be overridden. Therefore, none of these modifiers can be combined with an abstract method.

### 5. Diamond problem
The diamond problem occurs when a class inherits the same member through two paths and it becomes ambiguous which inherited implementation or state should apply. Java limits classes to extending one class, avoiding multiple inheritance of class instance state. A class can implement multiple interfaces; conflicting default methods must be explicitly resolved by overriding them.