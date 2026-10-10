# Week 9 — Abstraction & Interface Coding Assignment

1. Movie Ticket Counter
2. Parcel Shipping Desk
3. College Fee Counter
4. City Cab Fare Meter
5. Home Appliance Energy Report

Each program uses an abstract base class for shared rules and interfaces to represent optional abilities such as insurability, bus usage, night service, and saver mode.

## Notes on the design
- Shared fees and minimum-fare rules live in common base classes.
- Optional capabilities (insurance, bus use, night service, saver mode) are represented by interfaces so unrelated subclasses do not need to implement unsupported operations.
