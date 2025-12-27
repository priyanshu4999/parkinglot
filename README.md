


# Smart Parking Lot – Low Level Design (Backend)

## Overview

This project implements the **low-level design and backend logic** for a **Smart Parking Lot system**.  
It focuses on **correct domain modeling, clean architecture, and testable design**, without UI or persistence.

The system manages:
- Vehicle entry and exit
- Parking spot allocation by vehicle size
- Parking ticket generation
- Fee calculation
- Real-time availability updates (observer-based)

This project is intentionally **backend-only** and **single-threaded** at the current stage.

---
## UML
![Parking Lot UML](docs/uml.png)
## Objectives

- Design a clean **low-level architecture** for a parking lot
- Separate **domain logic** from **framework concerns**
- Use **SOLID principles** and common design patterns
- Keep the system extensible for future concurrency and persistence

---

## Functional Requirements (Implemented)

### 1. Parking Spot Allocation
- Vehicles are allocated spots based on size:
    - `BIKE` → `SMALL`
    - `CAR` → `MEDIUM`
    - `BUS` → `LARGE`
- Allocation uses a pluggable **strategy pattern**

### 2. Check-In / Check-Out
- Entry time is recorded when a vehicle parks
- Exit releases the occupied spot

### 3. Parking Fee Calculation
- Fee is calculated at exit
- Current implementation uses a **flat fee**
- Fee logic is abstracted behind an interface

### 4. Real-Time Availability Update
- Spot availability changes trigger notifications
- Implemented using **Observer pattern**
- Displays/subscribers receive availability payloads

---



## Architecture Overview





## Entities

- `Vehicle`
- `Spot`
- `Floor`
- `ParkingLot`
- `Ticket`
- `NotificationPayload`

### Storage

- Parking spots are stored as:
  ```java
  Map<SpotType, List<Spot>>
````


---

## Design Patterns Used

* **Strategy** – parking spot allocation
* **Observer** – availability notifications
* **Factory (implicit)** – ticket creation
* **Dependency Injection** – Spring container

---

