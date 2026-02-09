# Eshop Test Guide

## Run the test suite

Windows (PowerShell):

- Run all tests:
  - `./gradlew.bat test`

Test reports are generated in [build/reports/tests/test/index.html](build/reports/tests/test/index.html).

## How to add more functional tests

This project uses Spring Boot + JUnit 5 + Selenium. Use the same pattern as in [src/test/java/id/ac/ui/cs/advprog/eshop/CreateProductFunctionalTest.java](src/test/java/id/ac/ui/cs/advprog/eshop/CreateProductFunctionalTest.java).

### Minimal template

1. Create a new test class under [src/test/java/id/ac/ui/cs/advprog/eshop](src/test/java/id/ac/ui/cs/advprog/eshop) with the name `SomethingFunctionalTest`.
2. Use `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)` and `@LocalServerPort`.
3. In `@BeforeEach`, create a headless `ChromeDriver` and a `WebDriverWait`.
4. In your test, navigate to a page, interact with elements, and assert the result.
5. In `@AfterEach`, call `driver.quit()`.

### Example pattern

- Base URL:
  - `http://localhost:<port>/products/create`
- Element IDs from templates:
  - Create page: `idInput`, `nameInput`, `quantityInput` (see [src/main/resources/templates/createProduct.html](src/main/resources/templates/createProduct.html))
  - List page content is rendered in [src/main/resources/templates/ListProduct.html](src/main/resources/templates/ListProduct.html)

### Tips

- Always wait for elements before interacting:
  - `wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idInput")))`
- Prefer deterministic data for assertions (e.g., `UUID` for IDs).
- Keep assertions simple: verify expected text appears in the list page.

## Run a single test class

- `./gradlew.bat test --tests "id.ac.ui.cs.advprog.eshop.CreateProductFunctionalTest"`
