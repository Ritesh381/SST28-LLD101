# Immutable Tickets — Solution

## Problem
The ticket object (`IncidentTicket`) was mutable.
That means after creating a ticket, any part of code could change it using setters.

This created 3 big issues:
- Audit problems: old logs and current object state could differ.
- Data safety problems: external code could change internal `tags` list.
- Validation problems: checks were spread across places, so rules were easy to miss.

## Goal
Make ticket objects safe and predictable by:
- creating them once,
- validating everything in one place,
- and never mutating them after creation.

## Solution Overview
### 1) Made `IncidentTicket` immutable
In [src/com/example/tickets/IncidentTicket.java](src/com/example/tickets/IncidentTicket.java):
- all fields are `private final`
- setters were removed
- object construction is done through a `Builder`
- `tags` is stored as an unmodifiable defensive copy

Result: once a ticket is created, its state cannot be changed.

### 2) Added fluent Builder pattern
In the same file:
- `IncidentTicket.builder()` starts creation
- required values: `id`, `reporterEmail`, `title`
- optional values: `description`, `priority`, `tags`, `assigneeEmail`, `customerVisible`, `slaMinutes`, `source`

Also added copy-based update support:
- `toBuilder()`
- `Builder.from(existingTicket)`

Result: easy object creation and safe “update by copy” flow.

### 3) Centralized validation in one place
Validation is now triggered in `Builder.build()` using helpers from [src/com/example/tickets/Validation.java](src/com/example/tickets/Validation.java).

Rules enforced:
- `id`: non-blank, max 20, only `A-Z`, `0-9`, `-`
- `reporterEmail`: valid email
- `title`: non-blank, max 80
- `priority`: one of `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`
- `slaMinutes`: if present, between 5 and 7200
- `assigneeEmail`: if present, valid email

Result: all validation happens in one predictable place.

### 4) Refactored service to avoid mutation
In [src/com/example/tickets/TicketService.java](src/com/example/tickets/TicketService.java):
- `createTicket(...)` builds and returns a complete ticket
- `assign(...)` returns a **new** ticket instance
- `escalateToCritical(...)` returns a **new** ticket instance

Result: service methods are now immutable-friendly and audit-safe.

### 5) Updated demo to show immutability
In [src/TryIt.java](src/TryIt.java):
- create ticket
- assign/escalate by creating new objects
- show original ticket remains unchanged
- attempt to mutate `tags` from outside and show it is blocked

Result: behavior clearly demonstrates immutability in action.

## Why this design is better
- Safer: no accidental post-creation changes.
- Easier to reason about: object state is fixed.
- Cleaner validation: all rules in one build step.
- Better for audit and logging: historical object snapshots remain trustworthy.

## How to run
From [src](src):

```bash
javac com/example/tickets/*.java TryIt.java
java TryIt
```

You should see:
- original ticket unchanged after updates,
- and external tags mutation blocked.
