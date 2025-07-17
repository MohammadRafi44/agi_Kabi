# 🧪 Test Automation Suite Powered by Al Ghurair

## 📘 Project Description
A Java-based test automation framework for functional, end-to-end UI, API, and Mobile testing.

---

## 📚 Table of Contents
- [Project Overview](#project-overview)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Running Tests](#running-tests)
- [Reporting](#reporting)
- [📱 Mobile Tests - Configuration Guide](#-mobile-tests---configuration-guide)

---

## 🔍 Project Overview
- Java-based project for test automation.
- Supports functional and end-to-end testing across Web, API, and Mobile layers.
- Framework uses Selenium with TestNG following the Page Object Model (POM) design.
- Integrates Extent Reports, PDF Reports, and CSV Reports.
- Supports UI test execution video recording.

---

## 🚀 Getting Started
To get started with this test automation project, follow the steps below.

---

## 🧱 Prerequisites

### Common Tools for Web & APIs
- Java JDK 21
- Maven (latest)
- IntelliJ IDE (latest)

---

## ⚙️ Installation

1. Place `al-ghurair-core-framework` `.m2` package into the local `.m2` folder.

2. Checkout Test Automation Project from Version Management System (Azure Repo).

3. Perform Maven Update and ensure no dependency errors exist.

---

## 🧩 Chrome Driver

📍 **Location:**  
The ChromeDriver executable is embedded within the framework at:\drivers

---

## ▶️ Usage

1. Configure `TestConfig.properties` with browser type, version, run manager file name, and other necessary settings.

2. Update `RunManager` workbook under `SuiteDetails` sheet and mark test cases as **Yes** for execution.

---

## 🧩 Project Structure
The framework is structured using the **Page Object Model** (POM) pattern to ensure scalability and maintainability.

---

## 🧪 Running Tests

To execute tests via IntelliJ IDE:

- Locate `testng.xml` at the root of the project.
- Right-click → Run as → TestNG Suite
- *(Install TestNG plugin if not visible.)*

---

## 📊 Reporting

- Post execution, reports will be available under the `reports` folder in project root at: \report 
- Open in any browser to view
- Reports available in below formats
  - HTML 
  - PDF 
  - CSV 
- Test Excecution of Video Recordings will be available at: \report\2025-07-17-02-47-48
   - Here 2025-07-17-02-47-48 is timestamp of the run

---

## 📊 Test Execution Log

🗂️ The detailed execution log is stored at:\report\TestExecution.log

---

## 📱 Mobile Tests - Configuration Guide

### ✅ Prerequisites
- Windows OS
- Java JDK 21
- Node.js (LTS version)
- Real Android device with USB debugging enabled

### 🔧 Installation Steps

1. **Install Java JDK**  
   [Download JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)  
   Set environment variables:  
   ```bash
   setx JAVA_HOME "C:\Program Files\Java\jdk-21"
   setx PATH "%JAVA_HOME%\bin;%PATH%"
   ```

2. **Install Node.js**  
   [Download Node.js](https://nodejs.org/)  
   Verify installation:
   ```bash
   node -v
   npm -v
   ```

3. **Install Appium CLI**  
   ```bash
   npm install -g appium
   appium --version
   ```

4. **Install Appium Doctor**  
   ```bash
   npm install -g appium-doctor
   appium-doctor
   ```

5. **Install Android SDK Command Line Tools**  
   [Download CLI Tools](https://developer.android.com/studio#cmdline-tools)  
   Extract and set environment variables:
   ```bash
   setx ANDROID_HOME "C:\sdk"
   setx PATH "%PATH%;%ANDROID_HOME%\cmdline-tools\tools\bin"
   setx PATH "%PATH%;%ANDROID_HOME%\platform-tools"
   ```

6. **Install Required SDK Components**  
   ```bash
   sdkmanager "platform-tools" "platforms;android-30" "build-tools;30.0.3"
   ```

7. **Start Appium Server**  
   ```bash
   appium
   ```

8. **Connect Real Android Device**  
   Enable USB debugging. Then verify:
   ```bash
   adb devices
   ```

9. **Update TestConfig for Mobile**  
   Set the following in `TestConfig.properties`:
   ```properties
   settings.mobile.app.package=com.orangehrm.opensource
   settings.mobile.app.activity=com.orangehrm.opensource.MainActivity
   ```

---

### 🟢 Final Check

- ✅ Device shows up with `adb devices`
- ✅ Appium server running on `http://127.0.0.1:4723`
- ✅ Environment variables for Java, Node.js, and Android SDK set
- ✅ You're ready to run Appium tests!
