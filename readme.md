
#  🧪 Playwright Test Automation Framework (Java + Cucumber + ExtentReports)


A modern, scalable, and reusable test automation framework built with **Java**, **Playwright**, **Cucumber**, and **ExtentReports**, supporting both UI and API testing.

---

## ⚙️ Features

- ✅ **Playwright for UI Testing**
- 🔌 **REST Assured for API Testing**
- 🧪 **Cucumber BDD with Gherkin Syntax**
- 🧾 **ExtentReports** with screenshots after **every step**
- 📦 **Scalable TestContext** for parallel-safe execution
- 🛠️ **Reusable API methods** (GET, POST, PUT, DELETE)
- 📁 **Tag-based execution**: `@ui`, `@api`, or both

## 🧰 Tech Stack

| Tool           | Purpose                        |
|----------------|--------------------------------|
| Java           | Programming language           |
| Playwright     | UI automation engine           |
| REST Assured   | API testing                    |
| Cucumber       | BDD test framework             |
| ExtentReports  | Rich HTML test reporting       |
| Maven          | Dependency management          |

---

## 🚀 Getting Started

### Prerequisites

- JDK 8 or higher (JDK 11+ recommended)
- Maven 3.6+
- Internet connection to download dependencies

### 1️⃣ Clone the repo

```bash
  https://github.com/String-Gaurav/playwrite-java-framework
```

### 2️⃣ Install dependencies
```bash
  mvn clean install
```

### 3️⃣ Update config
```bash
  browser=chrome
  apiBaseUrl=https://jsonplaceholder.typicode.com
```

## 🏃 Running Tests

### UI Test

```bash
  mvn test -Dcucumber.filter.tags="@ui"
```

### API Test

```bash
  mvn test -Dcucumber.filter.tags="@api"
```

## 🏃 Reporting

```bash
target/extent-report/ExtentReport.html
```

### Includes:

- Step-by-step logs
- Screenshots after every step in UI tests
- Pass/fail status