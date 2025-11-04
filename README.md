# 🧑‍💼 JDBC Employee Management

A Java project that demonstrates **Oracle JDBC connectivity** and **object-oriented programming (OOP)** concepts to manage employee data.  
This application simulates basic employee record operations such as adding, updating, retrieving, and deleting staff information from a database.

---

## ⚙️ Technologies Used
- **Java SE**
- **Oracle Database (JDBC)**
- **Object-Oriented Programming (OOP)**
- **Exception Handling**

---

## 📂 Project Files
| File | Description |
|------|--------------|
| **StaffSimulator.java** | Defines staff attributes and simulation logic. |
| **Sem2_Assignment4_tester.java** | Main class that executes and tests database operations. |
| **MyOracleInfo.java** | Contains JDBC connection details *(make sure to remove or mask credentials before uploading).* |

---

## 🧠 Concepts Demonstrated
- Establishing database connections with **JDBC**
- Using **PreparedStatement** and **ResultSet**
- **Encapsulation** and **Class Design** in Java
- Exception Handling and Resource Management

---

## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/Mus-1/JDBC-Employee-Management.git
   cd JDBC-Employee-Management
   ```

   
2. Compile the Java files:

```bash
javac *.java
```

3. Run the main class:

```bash
java Sem2_Assignment4_tester
```

Ensure you have:

Oracle JDBC driver in your classpath

Database credentials configured correctly inside MyOracleInfo.java
