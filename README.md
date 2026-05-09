# 🧪 QMS Automation — Supply Chain OS (New Deviation Page)

Selenium + Java + TestNG automation suite for the **QMS New Deviation / New Event** page of the Supply Chain OS platform.

---

## 📁 Project Structure

```
selenium-qms-automation/
├── pom.xml
├── src/test/java/com/qms/
│   ├── pages/
│   │   ├── LoginPage.java            ← Page Object for Login
│   │   └── NewDeviationPage.java     ← Page Object for New Deviation form
│   ├── tests/
│   │   ├── BaseTest.java             ← Setup / teardown / report lifecycle
│   │   ├── LoginTest.java            ← 9 Login test cases
│   │   └── NewDeviationPageTest.java ← 36 Deviation page test cases
│   └── utils/
│       ├── ConfigReader.java         ← Reads config.properties
│       ├── DriverManager.java        ← ThreadLocal WebDriver init
│       ├── ExtentReportManager.java  ← HTML report generation
│       ├── ScreenshotUtil.java       ← Screenshot on failure
│       └── WaitUtil.java             ← Explicit wait helpers
├── src/test/resources/
│   ├── config.properties             ← URL / credentials / browser config
│   └── testng.xml                    ← TestNG suite definition
└── reports/                          ← Auto-generated HTML reports + screenshots
```

---

## ✅ Test Coverage

| Category              | Test IDs                     | Count |
|-----------------------|------------------------------|-------|
| Login Tests           | TC_LOGIN_001–009             | 9     |
| Functional Tests      | TC_DEV_001–010               | 10    |
| UI / Field Presence   | TC_UI_001–005                | 5     |
| Validation Tests      | TC_VAL_001–006               | 6     |
| Positive Scenarios    | TC_POS_001–004               | 4     |
| Negative Scenarios    | TC_NEG_001–005               | 5     |
| Boundary Tests        | TC_BND_001–003               | 3     |
| Navigation Tests      | TC_NAV_001–003               | 3     |
| **Total**             |                              | **45**|

---

## 🚀 Prerequisites

| Requirement     | Version  |
|-----------------|----------|
| Java (JDK)      | 11+      |
| Maven           | 3.6+     |
| Google Chrome   | Latest   |
| Internet access | Required (to reach the test server) |

> **WebDriverManager** automatically downloads the matching ChromeDriver — no manual setup needed.

---

## ⚙️ Configuration

Edit `src/test/resources/config.properties`:

```properties
base.url=http://216.48.184.249:5289
login.url=http://216.48.184.249:5289/login
deviation.page.url=http://216.48.184.249:5289/quality/records/new?template_id=ef20c1ca-208e-4162-b6bf-f9aa7cfb7464

username=testing@aivoa.net
password=password123

browser=chrome       # chrome | firefox | edge
headless=false       # true for CI/CD (no GUI)
implicit.wait=10
explicit.wait=20
```

---

## ▶️ How to Run

### Run All Tests
```bash
mvn clean test
```

### Run Only Login Tests
```bash
mvn clean test -Dtest=LoginTest
```

### Run Only Deviation Page Tests
```bash
mvn clean test -Dtest=NewDeviationPageTest
```

### Run in Headless Mode (no browser window)
```bash
mvn clean test -Dheadless=true
```

---

## 📊 Test Reports

After execution, an **HTML report** is auto-generated in the `reports/` folder:

```
reports/QMS_TestReport_YYYYMMDD_HHmmss.html
```

Open it in any browser to see:
- ✅ Pass / ❌ Fail / ⏭ Skip per test
- Screenshots on failure
- System info (browser, environment, module)

---

## 🏭 Real-World Test Examples (Life Sciences Context)

The test cases use realistic QMS data:

- **API Manufacturing** — Temperature excursion in reactor, batch quarantine
- **Raw Materials** — Moisture content out-of-spec, supplier lot rejection
- **Batch Numbers** — `API-2024-0501-B`, `RM-STARCH-2024-003`

---

## 🤖 AI Tools Used

Test cases and locator strategies were refined using **Claude AI** based on:
- Page structure and DOM analysis
- QMS domain knowledge
- Boundary and negative scenario generation

---

## 👤 Author

QA Automation Engineer — Round 1 Assignment Submission
