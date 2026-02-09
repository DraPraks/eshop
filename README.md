
# Reflection

## 1) After writing the unit test

**How do you feel?**

Writing unit tests speeds up quality assurance time. It encourages thinking about edge cases and error handling early. It also provides a safety net for future changes.

**How many unit tests should be made in a class?**

There is no fixed number. A good rule is to prefer many small, focused tests over a few large ones. Each test should validate one behavior.

**How to make sure unit tests are enough? (Code coverage)**

Code coverage measures how much of the code is executed by tests. It helps reveal untested areas, but it does not guarantee correctness. A practical approach:
**Does 100% coverage mean no bugs?**

No. 100% coverage only means that all tests were executed. The tests may not validate all intended behavior.

## 2) Reflection on adding another functional test suite

If we create another functional test class that reuses the same setup and instance variables (driver setup, wait, base URL, etc.), the code may become duplicated and harder to maintain.

This reduces code quality:

- Duplicated code violates DRY and increases maintenance cost.
- Boilerplate hides test intent, making tests less readable.

**Improvements**

- **Extract a base test class** (e.g., `BaseFunctionalTest`) that handles `@BeforeEach`, `@AfterEach`, and `baseUrl()`.