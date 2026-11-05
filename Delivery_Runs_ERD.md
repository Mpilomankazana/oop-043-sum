# Delivery Runs ERD

Paste the Mermaid block below into your GitLab wiki page titled **Delivery Runs ERD**
(the wiki editor renders ```` ```mermaid ```` blocks), or redraw it in the
**Insert or edit diagram** editor (Entity Relation shapes) as the brief requires.
Every table, column, type, key and relationship label you need is listed here.

```mermaid
erDiagram
    HUBS ||--o{ DRIVERS : "one hub has many drivers"
    HUBS ||--o{ VEHICLES : "one hub has many vehicles"
    HUBS ||--o{ DELIVERY_RUNS : "one hub sends out many runs"
    DRIVERS ||--o{ DELIVERY_RUNS : "one driver does many runs"
    VEHICLES ||--o{ DELIVERY_RUNS : "one vehicle is used on many runs"
    DELIVERY_RUNS ||--o{ RUN_PARCELS : "one run carries many parcels"
    PARCELS ||--o{ RUN_PARCELS : "one parcel goes on many runs"

    HUBS {
        integer id PK
        text hub_code UK "NOT NULL, UNIQUE"
        text name "NOT NULL"
        text city "NOT NULL"
        text street_address "NOT NULL"
    }
    DRIVERS {
        integer id PK
        integer hub_id FK "NOT NULL"
        text employee_no UK "NOT NULL, UNIQUE"
        text full_name "NOT NULL"
        text mobile_no "NOT NULL"
        text licence_code "NOT NULL"
    }
    VEHICLES {
        integer id PK
        integer hub_id FK "NOT NULL"
        text registration_no UK "NOT NULL, UNIQUE"
        text make "NOT NULL"
        text model "NOT NULL"
        real max_load_kg "NOT NULL, CHECK max_load_kg > 0"
    }
    PARCELS {
        integer id PK
        text tracking_no UK "NOT NULL, UNIQUE"
        real weight_kg "NOT NULL"
        text recipient_name "NOT NULL"
        text delivery_address "NOT NULL"
    }
    DELIVERY_RUNS {
        integer id PK
        integer hub_id FK "NOT NULL, hub the run leaves from"
        integer driver_id FK "NOT NULL"
        integer vehicle_id FK "NOT NULL"
        text run_date "NOT NULL"
        text departure_time "NOT NULL"
        text return_time "nullable, unknown until driver is back"
    }
    RUN_PARCELS {
        integer id PK
        integer run_id FK "NOT NULL"
        integer parcel_id FK "NOT NULL"
        integer stop_number "NOT NULL"
        text outcome "nullable until the stop is done, CHECK in 4 values"
    }
```

## Multi-column constraints (show these on the ERD as notes)

| Table | Constraint | Business rule |
|---|---|---|
| `delivery_runs` | `UNIQUE (driver_id, run_date)` | a driver does at most one run per date |
| `delivery_runs` | `UNIQUE (vehicle_id, run_date)` | a vehicle is used for at most one run per date |
| `run_parcels` | `UNIQUE (run_id, parcel_id)` | a parcel is never loaded twice onto the same run |
| `run_parcels` | `UNIQUE (run_id, stop_number)` | no two parcels on a run share a stop number |
| `run_parcels` | `CHECK (outcome IN ('DELIVERED','NO_ONE_HOME','WRONG_ADDRESS','REFUSED'))` | outcome is one of the four values, or empty (NULL) |
| `vehicles` | `CHECK (max_load_kg > 0)` | maximum load is greater than zero |

## Crow's foot reading

Every relationship is **exactly one (`||`)** on the parent side and **zero or more (`o{`)**
on the child side. "Zero or more" is used on the many side because a newly opened hub may have
no drivers/vehicles yet, and a driver, vehicle, run or parcel can exist before it is attached to a
run. Foreign keys sit on the "many" side. `run_parcels` is the junction table that resolves the
many-to-many relationship between `delivery_runs` and `parcels`, and carries `stop_number` and `outcome`.
