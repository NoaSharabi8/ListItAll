# 📋 ListItAll

**ListItAll** is a modular Android project built for an advanced app development course.  
The project demonstrates how to create **two separate applications** using a shared module (`common`) that manages item lists.

---

## 📱 Project Overview

The project includes:

- 🛒 **App 1: Shopping List** – allows users to add grocery items with category, quantity, and mark them as purchased.  
- ✅ **App 2: Task Manager** – allows users to add tasks with category, priority, and mark them as completed.

Both apps share:

- A common `Item` data model  
- A shared `Fragment` that displays a customizable list of items  
- A shared `SPManager` for saving data using `SharedPreferences`  
- A shared `BaseActivity` with utility functions

---

## 🏗️ Project Structure

```
ListItAll/
├── shopList/                # Shopping list app
├── toDoList/                # Task manager app
├── common/              # Shared module
│   ├── Item.java
│   ├── ItemListFragment.java
│   ├── SPManager.java
│   └── BaseActivity.java
```

---

## ✨ Key Features

- 🔄 Shared UI logic across multiple apps  
- 🧠 Modular architecture for reuse and separation of concerns  
- 💾 Data is saved locally using `SharedPreferences`  
- 🎨 Dynamic UI: the same fragment can behave differently depending on the host app

---

## 🚀 How to Run

1. Open the project in Android Studio  
2. Sync Gradle  
3. Choose either `shoplist` or `todolist` as your run target  
4. Run on emulator or device

---

## 📚 Technologies Used

- Java  
- Android SDK  
- SharedPreferences  
- Fragments & Activities  
- Modular Gradle setup

---

📌 *Maintained by:*  Noa Sharabi 
