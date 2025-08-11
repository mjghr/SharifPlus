## SharifPlus

SharifPlus is a simple console-based ordering system that simulates a combined Restaurant and CoffeeShop. It supports two roles: customer and admin. Customers can browse menus and place orders; admins can view/cancel orders and manage ingredient stock. Availability of menu items is driven by storage levels of their required ingredients.

### What this app is
- **Domain**: A tiny point-of-sale simulation for a restaurant and a coffee shop under one roof.
- **Core idea**: Items can only be ordered if their ingredients exist in storage. Storage is seeded from JSON and quantities are randomized per run.
- **Roles**:
  - **Customer**: register/login, order from Restaurant or CoffeeShop, view menus and ingredients, see their past orders.
  - **Admin**: list all orders, cancel orders by id, inspect storage, increase/decrease inventory (globally or per ingredient).
- **Persistence/logging**:
  - Reads initial users and storage ingredient names from JSON files in `src/resources/`.
  - Appends actions/logs to `log.txt`.
  - Note: user creation currently isn’t persisted back to JSON (write is present but commented), and orders are in-memory for the session.

### Features at a glance
- **Interactive CLI**: Entry via `Main` → `CLI`, colored prompts via `Utils/TextFormatter`.
- **Menus**:
  - Restaurant: food, appetizer, drinks (hot/cold) via `Controller/RestaurantController` + `Model/Restaurant`.
  - CoffeeShop: drinks and desserts via `Controller/CoffeeShopController` + `Model/CoffeeShop`.
- **Availability engine**: `StoreController.setAvailability(...)` marks items unavailable if any required ingredient is out of stock.
- **Ordering**: `OrderController.addOrder(...)` creates an `Order` (UUID id), associates it to the current user, and logs the action.
- **Admin tools**: view/cancel orders; inspect and mutate storage (`StorageController`).
- **Data layer**: `Utils/JsonManager` uses Gson to read JSON. Logs are appended to `log.txt` with timestamps.

### Project structure
- `src/Main.java`: starts the CLI.
- `src/CLI.java`: interactive loop for login/registration and user flows.
- `src/Controller/`: controllers for users, orders, storage, and stores (restaurant/coffeeshop).
- `src/Model/`: domain models (`User`, `Order`, `Storage`, `Restaurant`, `CoffeeShop`) and product types under `Model/Product/...`.
- `src/Utils/`: utilities (`JsonManager`, `TextFormatter`, enums like `DrinkType`).
- `src/resources/`: seed data
  - `users.json` (optional list of users)
  - `storageIngredients.json` (list of ingredient names)
  - `storage.json` (unused placeholder)
  - `log.txt` (runtime logs; created/updated in project root as `log.txt`)
- `lib/`: bundled jars (Gson used; Jackson jars present but not used by current code).

### Dependencies
- Runtime: **Gson** (included in `lib/gson-2.8.6.jar`).
- Bundled but not used directly: Jackson core/annotations/databind.

### How to run
Prerequisites: Java 8+.

On Linux/macOS (bash):
```bash
# From repo root
javac -cp lib/gson-2.8.6.jar -d out $(find src -name '*.java')
java -cp out:lib/gson-2.8.6.jar Main
```

On Windows (CMD):
```bat
REM From repo root
for /r %f in (src\*.java) do @echo %f >> sources.txt
javac -cp lib\gson-2.8.6.jar -d out @sources.txt
java -cp out;lib\gson-2.8.6.jar Main
```

### Usage tips
- Type `back` to go up a level, `logout` to sign out.
- Customer menu options: 1) Restaurant, 2) CoffeeShop, 3) View menu+ingredients, 4) View your orders.
- Admin menu options: 1) View/Cancel orders, 2) View/Modify storage.
- Some multi-word items need normalized names internally. The app handles common cases (e.g., "hot chocolate" → `hot-chocolate`).

### Known limitations / notes
- User registration is not persisted back to `src/resources/users.json` (the write is currently disabled in `UserController`).
- Orders are not persisted; they live in-memory for the session.
- Storage levels are randomized on startup from the ingredient names in `src/resources/storageIngredients.json`.
- `Model/Restaurant` has a likely typo mapping: `"steak"` points to `HotChocolate` instead of a Steak product.
- `Utils/JsonManager` contains some informal console messages that could be cleaned for production.

### License
No license file was provided. Add one if you intend to distribute.
