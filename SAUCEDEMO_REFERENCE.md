# Swag Labs Demo - Reference Guide

## Website Overview
**URL:** https://www.saucedemo.com/  
**Type:** Free, Open-Source QA Testing Website  
**Purpose:** Specifically designed for practicing test automation

## Test Credentials (Pre-configured)

### Available Test Users:
| Username | Password | Behavior |
|----------|----------|----------|
| `standard_user` | `secret_sauce` | Standard user with full functionality |
| `locked_out_user` | `secret_sauce` | User account locked after login attempt |
| `problem_user` | `secret_sauce` | User with visual/UI issues |
| `performance_glitch_user` | `secret_sauce` | Simulates slow loading (2-3 sec delay) |
| `error_user` | `secret_sauce` | User that triggers backend errors |
| `visual_user` | `secret_sauce` | Shows visual differences in UI |

## Application Features

### 1. **Login Page** (`/`)
- Username and password input fields
- Login button
- Error messages for failed authentication
- Supports different user types with varying behaviors

### 2. **Product Inventory** (`/inventory.html`)
- Displays 6 products with images, names, descriptions, and prices
- Products: Sauce Labs Backpack, Bike Light, Bolt T-Shirt, Fleece Jacket, Onesie, T-Shirt
- **Sorting Options:**
  - Name (A to Z)
  - Name (Z to A)
  - Price (Low to High)
  - Price (High to Low)
- Add to Cart button for each product
- Continue Shopping link to inventory

### 3. **Shopping Cart** (`/cart.html`)
- View all items added to cart
- Remove button for each item
- Quantity display
- Subtotal, Tax, and Total calculations
- Proceed to Checkout button
- Continue Shopping link

### 4. **Checkout** (`/checkout-step-one.html` → `/checkout-step-two.html` → `/checkout-complete.html`)
- **Step One:** First Name, Last Name, Postal Code input
- **Step Two:** Review order summary
- **Completion:** Order confirmation with order details

### 5. **Footer & Header**
- Header: Swag Labs logo, cart icon with item count, menu button
- Footer: Twitter, Facebook, LinkedIn links
- Consistent navigation across all pages

## Test Scenarios Covered by Generated Test Cases

### 1. **Authentication Testing**
- ✓ Valid login (standard_user)
- ✓ Invalid credentials
- ✓ Locked out user behavior
- ✓ Error user behavior
- ✓ Logout functionality

### 2. **Product Catalog Testing**
- ✓ Product display and pagination
- ✓ Product sorting (by name, price)
- ✓ Product image loading
- ✓ Add to cart from inventory
- ✓ Product details accuracy

### 3. **Shopping Cart Testing**
- ✓ Add products to cart
- ✓ Remove products from cart
- ✓ Cart quantity updates
- ✓ Price calculations
- ✓ Cart persistence
- ✓ Empty cart handling

### 4. **Checkout Testing**
- ✓ Checkout initiation
- ✓ Required field validation
- ✓ Order review
- ✓ Order confirmation
- ✓ Successful order completion
- ✓ Back navigation from checkout

### 5. **Performance Testing**
- ✓ Standard user load time
- ✓ Performance glitch user (slow loading)
- ✓ Page transition speed
- ✓ Image loading time

### 6. **Visual/UI Testing**
- ✓ Visual consistency
- ✓ Button alignment and clickability
- ✓ Responsive design
- ✓ Visual differences for visual_user
- ✓ Layout consistency across pages

## Why Use Swag Labs for QA Training?

1. **Free & Accessible:** No registration, always available
2. **Real-World Scenarios:** Covers typical e-commerce flows
3. **Multiple Test Scenarios:** Different user types create varied test conditions
4. **Performance Testing:** Built-in performance variations
5. **Visual Testing:** Includes visual regression scenarios
6. **Stable & Reliable:** Consistently available for testing
7. **Perfect for Demos:** Great example application for showcasing automation capabilities

## Using with SmartTestGen

### Generate Test Cases from Swag Labs:

```bash
# Use the provided requirements file
gradle run --args="examples/saucedemo-requirements.md generated-tests-saucedemo"

# Output: 35 test cases from 7 user stories
```

### Manual Test Execution:

```java
// Example: Test with Selenium WebDriver
@Test
public void testUserLoginWithValidCredentials() {
    driver.get("https://www.saucedemo.com/");
    
    WebElement usernameField = driver.findElement(By.id("user-name"));
    usernameField.sendKeys("standard_user");
    
    WebElement passwordField = driver.findElement(By.id("password"));
    passwordField.sendKeys("secret_sauce");
    
    WebElement loginButton = driver.findElement(By.id("login-button"));
    loginButton.click();
    
    // Assert: User is on inventory page
    Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
}
```

## Generated Test Metrics

- **Total User Stories:** 7
- **Total Test Cases Generated:** 35 (5 per story)
- **Test Categories:**
  - Positive Tests: 40%
  - Negative Tests: 40%
  - Edge Cases: 20%

## Next Steps

1. **Customize Test Cases:** Update generated tests with actual assertions
2. **Integrate WebDriver:** Add Selenium/Playwright for browser automation
3. **CI/CD Integration:** Add to your CI/CD pipeline
4. **Reporting:** Configure test reporting and metrics
5. **Real API Testing:** Extend tests for backend API validation

---

**Created with SmartTestGen** - AI-Powered Test Case Generation  
**Website:** https://www.saucedemo.com/
