# Summative Assessment

## Learning Outcomes

| Learning Outcome | Section |
|---|---|
| Project Management / Agile | Section 1 |
| OOP / Greenfield Development | Section 2 |
| Brownfields Development / Testing | Section 3 |
| Build Pipelines / Scripting | Section 4 (Part A) |
| Relational Database Design | Section 4 (Part B) |
| Web Development | Section 5 |

---

## Duration

**Total time: 3 hours**

| Section | Recommended Time |
|---|---|
| Section 1 — Project Management | 25 minutes |
| Section 2 — OOP and Greenfield Development | 40 minutes |
| Section 3 — Brownfields Development and Testing | 25 minutes |
| Section 4 — Build, Release and Database Design | 55 minutes (Part A: 20, Part B: 35) |
| Section 5 — Web Development | 35 minutes |

---

## Scoring

| Section | Marks |
|---|---|
| Section 1 — Project Management | 15 |
| Section 2 — OOP and Greenfield Development | 20 |
| Section 3 — Brownfields Development and Testing | 14 |
| Section 4 — Build, Release and Database Design | 31 (Part A: 13, Part B: 18) |
| Section 5 — Web Development | 20 |
| **Total** | **100** |

To pass, your final score must be **60% or higher**.

---

## The Scenario

**SwiftParcel** is a South African courier company that collects parcels from senders, moves
them through regional hubs, and delivers them to recipients' doors. Sections 1 to 4 of
this assessment are about a part of SwiftParcel's software. Section 5 uses examples from the
Web Development curriculum.

This repository is **one Maven project**: `courier-service`, SwiftParcel's courier software.

| Package (under `src/main/java/za/co/wethinkcode/courier/`) | What it is | Used in |
|---|---|---|
| `dispatch/` | A brand-new parcel dispatch module that you build from scratch | Section 2 |
| `model/`, `service/` | The existing consignment rules, written by a previous team | Section 3 |
| `Main`, `Database`, `DatabaseSchema`, `pom.xml`, pipeline | The application's entry point, database and build | Sections 3 and 4 |

Q5.1 uses a separate starter module, `webdev/`, which is not part of the Maven project.

The project compiles from the start, before you change anything, so you can complete the
sections in any order. **Keep it compiling as you work.** A compile error anywhere stops
every test in the project from running.

Write all written answers in `answers.txt`, under the matching question heading. Do not
change the format of the file or create a new one. The one exception is the ERD in Q4.4,
which you draw in your project's GitLab wiki.

---

# Section 1 — Project Management

## Scenario

Salesforce.com — Agile at Scale

By 2006, Salesforce.com's research and development organisation had grown quickly, and its
way of delivering software had not kept up. Work moved through long phases: requirements were
gathered and signed off up front, then built, then handed over to be tested near the end.
Releases were getting later and smaller. The number of major releases a year had dropped to
about one, and the business could not predict what would be in a release or when it would
arrive.

In late 2006, the organisation moved every team at once to an Agile, Scrum-based way of
working. Work was planned and delivered in short time-boxed iterations, each team kept its own
prioritised backlog, and the product was kept in a releasable state. Within a year, the teams
were back on a regular, predictable release schedule and delivering more features than before.

You have been brought in as project manager for a new SwiftParcel team about to build a
similar product: **SwiftParcel Live Tracking**. Recipients should be able to enter a tracking
number and see where their parcel is. Drivers on the road should be able to record each
delivery attempt, including failed ones ("no one home", "wrong address", "refused"), so that
the recipient sees an accurate status.

Your first responsibility, before any code is written, is to set the team's way of working.

> **Reference:** Fry, C., and Greene, S. (2007). Large Scale Agile Transformation in an
> On-Demand World. *Agile 2007 (AGILE 2007)*, IEEE, pp. 136–142.

| Task | Marks | What earns marks |
|---|---|---|
| Q1.1 — Agile and user stories | 4 | Accurate definition of Agile, linked to Salesforce's problem (1), two correctly formed user stories (1), testable acceptance criteria for each (1), a justified backlog priority (1) |
| Q1.2 — Establishing a way of working | 6 | Relevant guardrails, each explained (what it is, when it happens, what problem it prevents), that together give the team a complete, repeatable process |
| Q1.3 — Stand-up vs retrospective | 5 | Accurate comparison of purpose, timing and output (2), three relevant questions for each meeting, each with what it is meant to find out (0.5 each, 3) |

---

### Q1.1 — Agile and User Stories *(4 marks)*

**Q1.1.1** Define Agile in your own words, and say in one or two sentences how it helped
Salesforce with releases that kept getting later and harder to predict. *(1 mark)*

**Q1.1.2** In an Agile team, work starts life as user stories in the product backlog. Write
**two user stories** for SwiftParcel Live Tracking, one for a **recipient** and one for a
**driver**, in the format:

> *As a ... I want ... so that ...*

Give each story **at least two acceptance criteria** that a tester could check. Then say which
of your two stories you would place higher in the product backlog, and justify your choice.
*(3 marks)*

---

### Q1.2 — Establishing a Way of Working *(6 marks)*

You have just joined the Live Tracking team, and nobody has agreed how the team will work
together yet. Developers are picking up whatever they like, nobody knows who is working on
what, and problems are only discovered when someone happens to mention them.

What basic guardrails would you put in place so that the team has a clear, repeatable process
of working? For each guardrail, explain:

- what it is,
- when or how often it happens, and
- what problem it prevents for this team.

Include how the team will see the progress of every piece of work, from the moment it is
requested to the moment it is finished.

---

### Q1.3 — Stand-up vs Retrospective *(5 marks)*

A new developer on the team says: *"The stand-up and the retrospective are the same thing —
we all meet and talk about how things are going."*

**Q1.3.1** Explain the difference between a daily stand-up and a sprint retrospective. Compare
their purpose, when and how often they happen, and what each one focuses on and produces.
*(2 marks)*

**Q1.3.2** You are running both meetings for the Live Tracking team. For **each** meeting, give
**three questions** you would ask the team, and for each question say what you are trying to
find out. Your questions should make it obvious which meeting they belong to. *(3 marks)*

---

# Section 2 — OOP and Greenfield Development

## Scenario

SwiftParcel is adding a new **parcel dispatch** module to `courier-service`, built from
scratch. Every parcel has a tracking number, a sender, a recipient address, a weight, and
whether it has been delivered. Parcels are sent at different service levels (Standard, Express
and Fragile), and each one prices and schedules delivery differently. Express and Fragile
parcels carry declared-value insurance; Standard parcels do not.

All of the work for this section is in the `dispatch` package.

| Task | Marks | What earns marks |
|---|---|---|
| Q2.1 — Implementation | 12 | `(dispatch tests passed / total dispatch tests) × 12` |
| Q2.2 — The pillars of OOP | 8 | Names and correctly defines each of the four pillars (1 each), explains how to recognise each one in a codebase with a correct example from the `dispatch` package (1 each) |

### Class Hierarchy

```
Parcel  (abstract)
├── StandardParcel
├── ExpressParcel   implements Insurable
└── FragileParcel   implements Insurable

Interface: Insurable      (provided — do not modify)
```

### Package Structure

```
src/
  main/java/za/co/wethinkcode/courier/dispatch/
    Insurable.java         (provided)
    Parcel.java
    StandardParcel.java
    ExpressParcel.java
    FragileParcel.java
    DispatchCentre.java
  test/java/za/co/wethinkcode/courier/dispatch/
    ParcelTest.java
    ParcelTypesTest.java
    InsurableTest.java
    DispatchCentreTest.java
    Types.java
```

The skeleton classes already contain their constructors and public methods, so the project
compiles. Each method body is a placeholder that throws `UnsupportedOperationException`.
**The design is yours to complete:** declare the fields, decide what is `abstract`, add the
`extends` and `implements` relationships, call `super(...)`, and replace every placeholder
with a real implementation, as described below. The tests check all of these.

> **Do NOT modify any test files or `Insurable.java`.**

Run only this section's tests with:

```bash
mvn test -Dtest='za.co.wethinkcode.courier.dispatch.*Test'
```

---

### Q2.1 — Implementation *(12 marks)*

Work through the steps **in order**. Each class builds on the ones before it.

#### Step 1 — Read `Insurable` (Interface — provided)

**File:** `dispatch/Insurable.java`

| Method | Details |
|---|---|
| `getDeclaredValue()` | Returns the value the sender declared for the contents, in Rands (`double`). |
| `insurancePremium()` | Returns the premium charged to insure the parcel, in Rands (`double`). |

---

#### Step 2 — Implement `Parcel` (Abstract)

**File:** `dispatch/Parcel.java`

`Parcel` is the root of the hierarchy. It holds the state every parcel shares, but it cannot
price or schedule a delivery on its own.

##### Fields

| Field | Type | Details |
|---|---|---|
| `trackingNumber` | `String` | Private and **immutable** |
| `senderName` | `String` | Private and **immutable** |
| `recipientAddress` | `String` | Private |
| `weightKg` | `double` | Private. Must always be greater than `0`. |
| `delivered` | `boolean` | Private. Starts as `false`. |

##### Constructor

Accepts `trackingNumber`, `senderName`, `recipientAddress`, `weightKg`. Throws
`IllegalArgumentException` if `weightKg` is zero or negative. Sets `delivered` to `false`.

##### Methods

| Method | Details |
|---|---|
| `getTrackingNumber()` | Returns the tracking number. No setter. |
| `getSenderName()` | Returns the sender's name. No setter. |
| `getRecipientAddress()` <br/> `updateRecipientAddress(String)` | Getter and updater. The updater throws `IllegalStateException` if the parcel has already been delivered. |
| `getWeightKg()` <br/> `updateWeightKg(double)` | Getter and updater. The updater throws `IllegalArgumentException` if the value is zero or negative. |
| `isDelivered()` <br/> `markDelivered()` | Returns whether the parcel has been delivered. `markDelivered()` sets it to `true`. |
| `toString()` | Returns a summary that includes the tracking number and the sender's name. |
| `deliveryFee()` | **Abstract.** Returns the delivery fee in Rands. |
| `deliveryDays()` | **Abstract.** Returns the number of working days delivery takes. |
| `serviceLevel()` | **Abstract.** Returns a short service level label. |

---

#### Step 3 — Implement `StandardParcel`, `ExpressParcel`, `FragileParcel`

All three extend `Parcel` and implement every abstract method. Use the `@Override` annotation
on every overridden method.

##### `StandardParcel`

| | Details |
|---|---|
| Extends | `Parcel` |
| Extra fields | None |
| Constructor | Accepts all `Parcel` parameters |
| `deliveryFee()` | `BASE_FEE + (weightKg × FEE_PER_KG)` |
| `deliveryDays()` | Returns `DELIVERY_DAYS` |
| `serviceLevel()` | Returns `"Standard"` |
| `toString()` | Includes `"Standard"` and the parent summary |

##### `ExpressParcel`

| | Details |
|---|---|
| Extends / implements | `Parcel`, `Insurable` |
| Extra fields | `declaredValue (double)` — private and **immutable**. |
| Constructor | Accepts all `Parcel` parameters, then `declaredValue`. Throws `IllegalArgumentException` if `declaredValue` is negative. |
| `deliveryFee()` | `BASE_FEE + (weightKg × FEE_PER_KG)` |
| `deliveryDays()` | Returns `DELIVERY_DAYS` |
| `serviceLevel()` | Returns `"Express"` |
| `insurancePremium()` | `declaredValue × INSURANCE_RATE` |
| `toString()` | Includes `"Express"` and the parent summary |

##### `FragileParcel`

| | Details |
|---|---|
| Extends / implements | `Parcel`, `Insurable` |
| Extra fields | `declaredValue (double)` — private and **immutable**. `handlingNotes (String)` — private and **immutable**; getter `getHandlingNotes()`. |
| Constructor | Accepts all `Parcel` parameters, then `declaredValue`, then `handlingNotes`. Throws `IllegalArgumentException` if `declaredValue` is negative. |
| `deliveryFee()` | `BASE_FEE + (weightKg × FEE_PER_KG)` |
| `deliveryDays()` | Returns `DELIVERY_DAYS` |
| `serviceLevel()` | Returns `"Fragile"` |
| `insurancePremium()` | `declaredValue × INSURANCE_RATE` |
| `toString()` | Includes `"Fragile"` and the handling notes |

> The constants (`BASE_FEE`, `FEE_PER_KG` and so on) are already declared in each skeleton
> file.
>
> **Hint:** inside a subclass, `weightKg` is private to `Parcel`, so use `getWeightKg()`.

---

#### Step 4 — Implement `DispatchCentre`

**File:** `dispatch/DispatchCentre.java`

`DispatchCentre` ties the system together: it holds every parcel at a centre and reports on
them.

##### Fields

| Field | Type | Details |
|---|---|---|
| `centreName` | `String` | Private and immutable. |
| `parcels` | `List<Parcel>` | Private. Every parcel at this centre. |

##### Constructor

Accepts `centreName`. Initialises `parcels` as an empty `ArrayList`.

##### Methods

| Method | Details |
|---|---|
| `getCentreName()` | Returns the centre name. |
| `addParcel(Parcel)` | Adds a parcel. Throws `IllegalArgumentException` if a parcel with the same tracking number is already present. |
| `findParcel(String trackingNumber)` | Returns the `Parcel`, or `null` if not found. |
| `getParcels()` | Returns `Collections.unmodifiableList(parcels)`. |
| `insurableParcels()` | Returns a **new** `List<Insurable>` holding every parcel that is `Insurable`. *(Hint: `instanceof`.)* |
| `totalDeliveryRevenue()` | Sums `deliveryFee()` across all parcels. Returns `0.0` if there are none. |
| `totalInsurancePremiums()` | Sums `insurancePremium()` across all insurable parcels. Returns `0.0` if there are none. |

---

### Q2.2 — The Pillars of OOP *(8 marks)*

Object-oriented programming rests on four key pillars.

For **each** of the four pillars:

1. Name it and define it in your own words.
2. Explain how you would identify it when reading a codebase: what keywords, structures or
   patterns tell you it is being used?
3. Point to one place in the `dispatch` package where it appears (name the class and the
   field, method or declaration) and explain briefly why that is an example of the pillar.

---

# Section 3 — Brownfields Development and Testing

## Scenario

The `model` and `service` packages hold SwiftParcel's existing rules service, written by a
team that has since moved on. `DeliveryRulesService` decides whether a consignment can be
loaded onto a driver's vehicle (`canLoadOntoVehicle`) and what should happen after a delivery
attempt (`decideNextStep`). It works, and `DeliveryRulesServiceTest` covers it, but both methods
are hard to read, and the business wants new delivery rules added next sprint.

Read `DeliveryRulesService` before you answer. **You do not change any code in this section.**
All four answers go in `answers.txt`.

| Task | Marks | What earns marks |
|---|---|---|
| Q3.1 — Greenfield vs brownfield | 2 | Accurate distinction between the two, using this repository's packages as examples (1), one challenge specific to each, with how it changes the way a developer works (1) |
| Q3.2 — Refactoring | 3 | Accurate definition (1), clear distinction from other kinds of change (1), a relevant example from `DeliveryRulesService` (1) |
| Q3.3 — Changing code safely | 5 | Names and explains a suitable development approach (2), describes each stage of its cycle correctly (2), applies it to the example rule (1) |
| Q3.4 — Types of testing | 4 | Each of the three checks correctly classified with a justification (1 each), explanation of what the third check confirms that the other two cannot (1) |

---

### Q3.1 — Greenfield vs Brownfield Development *(2 marks)*

This repository holds both kinds of work. In Section 2 you built the `dispatch` package from
scratch. In this section you work with `DeliveryRulesService`, which a previous team wrote and
which is already in use.

Explain the difference between **greenfield** and **brownfield** development, using these two
packages as your examples. Then give one challenge that is specific to each kind of
development, and say how it changes the way a developer works.

---

### Q3.2 — Refactoring *(3 marks)*

Your team lead asks you to refactor `DeliveryRulesService` before any new rules are added.

Explain what refactoring is, and how it differs from fixing a bug or adding a feature. Use one
of the two methods in `DeliveryRulesService` as an example of code that would benefit from it,
and say what you would want to improve.

---

### Q3.3 — Changing Code Safely *(5 marks)*

The team lead adds a condition: no driver or customer may notice any change in behaviour while
you restructure the code, and the new rules the team adds next sprint must be built in a way
that keeps that true for the next team as well.

The first new rule is: *a consignment heavier than 30 kg cannot be loaded unless the driver is
working with a second driver.*

Describe the development approach you would follow, both while you restructure the existing
code and when you add this new rule. Name the approach and explain why it keeps behaviour safe.
Then walk through each stage of the cycle it follows, saying what you would do at each stage
for the 30 kg rule and how you know when to move on to the next stage.

---

### Q3.4 — Types of Testing *(4 marks)*

SwiftParcel runs three checks before a release:

1. `DeliveryRulesServiceTest` — the existing tests for the rules service you read in Q3.2. They
   create `Consignment` and `Driver` objects in memory and call one method of
   `DeliveryRulesService` at a time.
2. `DatabaseSchemaTest` — opens a real SQLite connection through `Database`, runs
   `DatabaseSchema`, and then inspects the tables that were created in the database.
3. A release check run by the operations team. It launches the packaged jar and confirms that,
   for a list of scenarios agreed with the business ("a refused parcel is returned to sender",
   "a same-day parcel can go to any zone"), the service gives the outcome the business signed
   off on. The team has no knowledge of how the code is written.

Name the type of testing each check represents and justify each classification. Then explain
what the third check confirms that the first two cannot.

---

# Section 4 — Build, Release and Database Design

Part A is about how `courier-service` is built and released. Part B is about designing a
database from a business use case.

## Part A — Build and Release Management

### Scenario

`courier-service` is built with Maven, and its `Makefile` has the targets you can run locally:
`make compile`, `make test` and `make package`. The team is getting ready to release and needs a
GitLab CI pipeline.

| Task | Marks | What earns marks |
|---|---|---|
| Q4.1 — Releasing a snapshot | 4 | Correct explanation of `-SNAPSHOT` and why it must not reach customers (1), correct release version (1), justification that addresses each change (2) |
| Q4.2 — Complete the pipeline | 3 | The pipeline runs the required jobs in the required order (1), the built jar can be downloaded once the pipeline finishes (1), a correct explanation of why the order matters (1) |
| Q4.3 — Build tools and the `pom.xml` | 6 | Q4.3.1: accurate definition of a build tool with at least two jobs it automates (1.5), a correctly named build tool (0.5). Q4.3.2: what a `pom.xml` is and what it aims to achieve (1), two things this project's `pom.xml` defines, each with what Maven does with it (1). Q4.3.3: accurate definition of a dependency (1), what each part of the `junit-jupiter` declaration tells Maven and what Maven does with it (1) |

---

### Q4.1 — Releasing a Snapshot *(4 marks)*

Open `pom.xml`. The project's version is `1.4.0-SNAPSHOT`. The last version released to
customers was `1.3.2`.

**Q4.1.1** Explain what the `-SNAPSHOT` suffix means and why a snapshot build should never be
handed to customers.

**Q4.1.2** Since `1.3.2`, the following changes have been merged:

- A rounding defect in delivery fee quotes was fixed.
- A new public method, `estimateDeliveryDate(...)`, was added. No existing method changed.
- The deprecated public method `DispatchClient.sendLegacy()` was removed. A partner team's
  integration still calls it.

The team plans to release this as `1.4.0`. Is that the correct version? State the version you
would release and justify it with reference to **each** of the three changes.

---

### Q4.2 — Complete the Pipeline *(3 marks)*

`.gitlab-ci.yml` has been started for you. Complete it so that the pipeline:

1. **compiles** the code,
2. then **runs the test suite**,
3. then **packages** the application, and keeps the built jar so it can be downloaded from the
   pipeline page once the pipeline finishes.

Each job should run the matching `Makefile` target. Then, in `answers.txt`, explain why the
pipeline must run these steps in this order.

> You are allowed to use the [GitLab CI/CD YAML docs](https://docs.gitlab.com/ci/yaml/).

---

### Q4.3 — Build Tools and the `pom.xml` *(6 marks)*

**Q4.3.1** Explain what a **build tool** is. Describe at least two jobs it automates for a
development team, and what the team would have to do by hand, on every machine and in the
pipeline, without one. Then name **one** build tool. *(2 marks)*

**Q4.3.2** Every Maven project, including `courier-service`, has a `pom.xml` at its root.
Explain what a `pom.xml` file is and what it aims to achieve. Then point to **two** different
things this project's `pom.xml` defines, other than its dependencies, and explain what Maven
does with each of them. *(2 marks)*

**Q4.3.3** In the context of a `pom.xml`, what is a **dependency**? Use the `junit-jupiter`
dependency in this project's `pom.xml` as your example: explain what its `groupId`,
`artifactId`, `version` and `scope` each tell Maven, and what Maven does with that dependency
when you run `mvn test`. *(2 marks)*

---

## Part B — Database Design

### Scenario

At the moment, each SwiftParcel hub plans its day's deliveries on a whiteboard. The operations
team wants that planning stored in a database, and you have been asked to design it.

This is how the operations manager describes the business:

> 1. SwiftParcel runs several **hubs**. Every hub has a hub code that no other hub shares (for
>    example `JHB-NORTH`), a name, a city and a street address.
> 2. Every **driver** works out of exactly one hub. We record each driver's employee number (no
>    two drivers share one), full name, mobile number and licence code. A hub can have many
>    drivers, but a hub that has just opened may not have any yet.
> 3. Every **vehicle** belongs to exactly one hub. We record its registration number (no two
>    vehicles share one), its make and model, and the maximum load it can carry in kilograms,
>    which must be greater than zero. A hub can have many vehicles.
> 4. Every **parcel** has a tracking number that no other parcel shares, a weight in kilograms,
>    the recipient's name and the delivery address.
> 5. Every working day, each hub sends out **delivery runs**. A run is one driver taking one
>    vehicle out on one date. Drivers and vehicles are sometimes borrowed from another hub, so
>    every run must record which hub it leaves from. We record the time the run departs, and
>    the time it returns. We don't know the return time until the driver is back.
> 6. A driver can do at most one run on a given date, and a vehicle can be used for at most
>    one run on a given date.
> 7. A run carries many parcels. A parcel can go out on more than one run during its life: if
>    nobody is home, it is loaded again onto a later run. A parcel can never be loaded twice
>    onto the same run.
> 8. For every parcel on a run, we record its **stop number**, the position in which the driver
>    will deliver it. No two parcels on the same run can have the same stop number. Once the
>    driver has been to the stop, we also record the outcome (`DELIVERED`, `NO_ONE_HOME`,
>    `WRONG_ADDRESS` or `REFUSED`). Until then, there is no outcome.

> You are **not** writing any SQL or Java code in Part B. Use the SQLite types `integer`,
> `text` and `real`, and crow's foot notation for relationships.
>
> `resources/SQL-Cheat-Sheet.pdf` is provided as a reference for data types and constraints.

| Task | Marks | What earns marks |
|---|---|---|
| Q4.4 — Draw the ERD | 10 | Tables and columns (2), keys (3), the many-to-many relationship (3), cardinality and notation (2), as described below |
| Q4.5 — Turning business rules into constraints | 4 | Each rule enforced with the correct constraint on the correct table and column(s) (1 each) |
| Q4.6 — Design reasoning | 4 | Q4.6.1 (2), Q4.6.2 (2) |

---

### Q4.4 — Draw the ERD *(10 marks)*

Design the database for the business described above, and draw it as an entity relationship
diagram.

Your diagram must show:

- Every table you design.
- For every column: its name, its type, and whether it is `PK`, `FK`, `NOT NULL` or `UNIQUE`.
- Every relationship as a line with crow's foot notation on **both ends**, showing whether
  each side is "exactly one", "zero or one", "one or more" or "zero or more", and labelled
  with a short phrase (for example *"one hub has many drivers"*).

Draw your diagram in your project's **GitLab wiki**. No other tool is allowed:

1. On your GitLab project's landing page, click **Add Wiki** (or open the wiki if it already
   exists and create a new page).
2. Title the page **`Delivery Runs ERD`**.
3. In the editor toolbar, click the **Insert or edit diagram** icon (it looks like three small
   connected boxes, like a tiny org chart) and draw your diagram in the diagram editor that
   opens. The *Entity Relation* shape library has table shapes and crow's foot connectors.
4. Save the diagram, then save the wiki page.

| Marks | What earns them |
|---|---|
| Tables and columns (2) | Every entity in the business description has its own table, and every attribute the manager mentions is stored in the right table (1). Each column has a sensible type, and `NOT NULL` is used only where the business says a value must always be known, so the return time and the outcome can be empty (1). |
| Keys (3) | Every table has a primary key (1). Every one-to-many relationship has its foreign key on the "many" side (1). A run is linked to its driver, its vehicle and the hub it leaves from, each with its own foreign key (1). |
| The many-to-many relationship (3) | Runs and parcels are recognised as many-to-many (1) and resolved with a separate table that has a foreign key to each side (1). The stop number is stored in that table (1). |
| Cardinality and notation (2) | Crow's foot cardinality is correct at both ends of every relationship, including the optional ones (1). Every relationship is labelled (1). |

---

### Q4.5 — Turning Business Rules into Constraints *(4 marks)*

A diagram only protects the data if the database enforces the business rules. For each rule
below, in `answers.txt`, give:

- the table and column(s) involved,
- the constraint that enforces the rule in the database (for example `NOT NULL`, `UNIQUE`,
  `CHECK`, `PRIMARY KEY` or `FOREIGN KEY`), including any constraint that covers more than one
  column, and
- one sentence explaining what the database would do if someone tried to break the rule.

Show each constraint on your ERD as well.

- **(a)** A vehicle's maximum load must be greater than zero.
- **(b)** A driver can do at most one run on a given date.
- **(c)** A parcel can never be loaded twice onto the same run.
- **(d)** No two parcels on the same run can have the same stop number.

---

### Q4.6 — Design Reasoning *(4 marks)*

Answer both in `answers.txt`.

**Q4.6.1** A teammate suggests a simpler design. Instead of a separate table, they would add a
`run_id` column to the parcels table. Using rules 7 and 8, explain what information this design
would lose or be unable to store, and how your design avoids the problem. *(2 marks)*

**Q4.6.2** Every hub already has a hub code that is unique and never empty, so a teammate asks
why the hubs table needs a separate `id` column at all. Explain the difference between a
`PRIMARY KEY` and a `UNIQUE` constraint, and give one reason why the tables that refer to a
hub should reference its `id` rather than its hub code. *(2 marks)*

---

# Section 5 — Web Development: The Whole Stack

## Scenario

Show that you understand how the web works: how a browser loads and runs a page, how a web
application handles requests and remembers its users, and where a page's work can happen.

Q5.1 has starter code in the `webdev/` module. It is not part of the Maven project, so nothing
in it is compiled or run by `mvn` or the pipeline. Q5.2 and Q5.3 are written questions about concepts:
you do not read or write any code for them, but you must apply each idea to the situation
described.

| Task | Marks | What earns marks |
|---|---|---|
| Q5.1 — The browser side | 6 | Q5.1.1: the script works as described (2). Q5.1.2: an accurate, complete and correctly ordered walkthrough that explains why each step happens (4) |
| Q5.2 — How a web application remembers you | 8 | Accurate explanation of each concept, applied to the WeShare login: Q5.2.1 (2), Q5.2.2 (2), Q5.2.3 (3), Q5.2.4 (1) |
| Q5.3 — Server-side vs client-side page generation | 6 | Accurate explanation of each concept, applied to the bank's page: Q5.3.1 (2), Q5.3.2 (2), Q5.3.3 (1 per requirement, with a justification) |

---

### Q5.1 — The Browser Side *(6 marks)*

Here is the introduction's page, `webdev/hello-world/index.html`:

```html
<!DOCTYPE html>
<html>
    <head>
        <title>Hello, hello</title>
        <link rel="stylesheet" href="css/styles-2.css"/>
        <script src="js/update.js" defer></script>
    </head>
    <body>
        <h1>Hello, world</h1>
        <p>Using any of the following languages
            write a program that prints out <em>Hello, world</em>
            to the console</p>
        <ul id="languages">
            <li>Java</li>
            <li>Ruby</li>
            <li>Python</li>
        </ul>
        <p id="selection"></p>
    </body>
</html>
```

**Q5.1.1** Complete `webdev/hello-world/js/update.js` so that clicking a language in the list
shows *You chose* followed by that language in the empty paragraph. For example, clicking
"Python" shows "You chose Python". Do not change `index.html`. You can open `index.html` in a
browser to try it.

**Q5.1.2** Walk through what happens, from the browser's first HTTP request until clicking
"Python" shows "You chose Python" on the page.

---

### Q5.2 — How a Web Application Remembers You *(8 marks)*

A student opens WeShare, enters their email address on the login page, clicks Login, and lands
on a page listing their own expenses.

**Q5.2.1** When the student clicks Login, the browser sends the form to the server. Explain the
difference between an HTTP **GET** and an HTTP **POST** request, and say which one the login
form should use and why. *(2 marks)*

**Q5.2.2** HTTP is described as **stateless**. Explain what that means. Then explain how WeShare
can still know which student is asking for the expenses page, even though it is a separate
request from the login. *(2 marks)*

**Q5.2.3** WeShare is built with the **Model–View–Controller** pattern. Explain the job of each
part, and say what each one would be responsible for in this login-to-expenses journey.
*(3 marks)*

**Q5.2.4** After a successful login, the server tells the browser to go to the expenses page
instead of returning the expenses page straight away. Explain why web applications do this.
*(1 mark)*

---

### Q5.3 — Server-Side vs Client-Side Page Generation *(6 marks)*

A bank's web app shows a logged-in user their account balances when they visit
`/balances.html`. The page could be generated on the server or in the browser.

**Q5.3.1** Explain server-side page generation and client-side page generation for this page.
For each approach, say where the work happens and what the browser receives. *(2 marks)*

**Q5.3.2** In the client-side approach, the browser asks the server for the balances as data
rather than as a finished page. Explain what an **API endpoint** is, and why it usually
returns its data as JSON rather than HTML. *(2 marks)*

**Q5.3.3** The bank now has two new requirements for this page:

1. The balances must update every 30 seconds without the whole page reloading.
2. The balances must still be shown to customers whose browsers have JavaScript switched off.

For each requirement, say which approach meets it and justify your choice. Is there a way to
meet both? *(2 marks)*

---

### End of Assessment

---

## Repository Structure

```
.
├── README.md
├── answers.txt
├── pom.xml
├── Makefile
├── .gitlab-ci.yml
├── resources/
│   └── SQL-Cheat-Sheet.pdf
├── webdev/                  # Section 5 starter module — separate from the Maven project
│   └── hello-world/         # Q5.1 — index.html and css provided; you complete js/update.js
├── src/
│   ├── main/java/za/co/wethinkcode/courier/
│   │   ├── Main.java
│   │   ├── Database.java
│   │   ├── DatabaseSchema.java
│   │   ├── dispatch/            # Section 2 — Parcel hierarchy, Insurable, DispatchCentre
│   │   ├── model/               # Section 3 — Consignment, Driver, DeliveryAttempt, ServiceLevel, AttemptOutcome
│   │   └── service/             # Section 3 — DeliveryRulesService (read only)
│   └── test/java/za/co/wethinkcode/courier/
│       ├── DatabaseSchemaTest.java
│       ├── dispatch/            # Section 2 tests
│       └── service/DeliveryRulesServiceTest.java
```

## Useful Commands

```bash
# Compile the source code
mvn compile

# Run every test
mvn test

# Run only one section's tests
mvn test -Dtest='za.co.wethinkcode.courier.dispatch.*Test'   # Section 2
mvn test -Dtest=DeliveryRulesServiceTest                     # Section 3
mvn test -Dtest=DatabaseSchemaTest                           # Q3.4

# Package the application into a jar (does not run the tests)
make package

# Run the packaged jar
java -jar target/courier-service-jar-with-dependencies.jar
```

The `Makefile` wraps the same commands, and is what your GitLab CI pipeline should run:

```bash
make compile
make test
make package
```

---

**NOTE:** Before pushing your solution for grading, make sure it compiles by running
`mvn clean compile test-compile`. If it does not compile, no tests can run, and that will
cost you marks in every section.
