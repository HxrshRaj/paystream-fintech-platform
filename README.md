# 💳 PayStream – Distributed FinTech Platform

## 🧭 Overview

PayStream is a high-concurrency, event-driven backend platform designed to simulate enterprise-grade payment processing systems.

The system is built around **strict financial correctness guarantees**:
- No duplicate transactions
- Strong ACID compliance across services
- Deterministic failure handling

Instead of optimizing for simplicity, the architecture prioritizes **consistency, fault isolation, and throughput under load**.

---

## 🏗️ High-Level Architecture

The system follows a **microservices + event streaming architecture**, where services are decoupled through Kafka rather than synchronous REST chaining.

### Key Architectural Decisions

- **Event-Driven Communication (Kafka)**
  - Eliminates tight coupling between services
  - Enables replay for recovery and auditing
  - Supports horizontal scaling under high throughput

- **Service Isolation**
  - Payment Service → transaction intake
  - Ledger Service → financial recording
  - Settlement Service → final state reconciliation
  - Notification Service → async updates

- **State Management**
  - PostgreSQL used for ACID guarantees
  - Redis used for fast-access operational data (idempotency keys)

---

### Flow

```
Client
  ↓
API Gateway
  ↓
Payment Service
  ↓
PostgreSQL (Transaction Record)
  ↓
Kafka (Event Published)
  ↓
Consumers:
  → Ledger Service
  → Settlement Service
  → Notification Service
```

---

## ⚙️ Core Features

### 1. Asynchronous Processing

- Payment requests are processed via Kafka events
- Services consume independently → no blocking chains
- Improves throughput and resilience under load

---

### 2. ACID Compliance via Two-Phase Commit Simulation

- Ensures **transaction integrity across distributed services**
- Uses coordinated state transitions:
  - INITIATED → PROCESSING → COMPLETED / FAILED
- Prevents partial financial updates

---

### 3. Idempotency Enforcement

- Each request includes an **Idempotency-Key**
- Stored in Redis
- Prevents duplicate charges in retry scenarios

---

### 4. Fault Tolerance

- Kafka ensures message durability
- Consumers can replay events on failure
- System avoids cascading failures via async boundaries

---

## 🧰 Tech Stack

| Layer            | Technology              | Reasoning |
|------------------|------------------------|----------|
| Backend          | Java 17 + Spring Boot 3 | Strong ecosystem, production-grade concurrency |
| Messaging        | Apache Kafka           | Event streaming + decoupling |
| Database         | PostgreSQL             | ACID guarantees |
| Cache            | Redis                  | Low-latency idempotency checks |
| Frontend         | React.js               | UI simulation layer |
| Containerization | Docker, Docker Compose | Reproducible environments |

---

## 🚀 Local Development Setup

### Prerequisites

- Docker
- Docker Compose

---

### Start All Services

```bash
docker-compose up -d
```

---

### Verify Services

- Kafka → running
- PostgreSQL → running
- Redis → running
- Payment service → exposed via API

---

### Sample Request

```http
POST /api/payments

Headers:
Idempotency-Key: unique_key
Authorization: Bearer <jwt>
```

---

## ⚠️ Known Limitations / Future Scope

- No distributed tracing (OpenTelemetry pending)
- No circuit breaker implementation (Resilience4j planned)
- Exactly-once Kafka semantics not fully leveraged
- No fraud detection layer (planned extension)

---

## 🧾 Consultant Disclaimer

This repository focuses on distributed system design patterns for financial systems. Proprietary settlement logic, fraud detection rules, and compliance workflows are excluded due to enterprise IP constraints. Detailed architecture extensions can be shared upon request.

---

*Note: Full UI source code is currently migrating to this monorepo.*
