# **📘 Collector Monitoring System – (Spring Boot + Next.js + MongoDB)**

A full-stack monitoring system that ingests API logs, tracks rate-limit hits, shows dashboard analytics, and manages rate-limit overrides.

---

## **🚀 Features**

### **Backend (Spring Boot + Kotlin)**

* JWT-based authentication
* API log ingestion
* Bulk ingestion support
* Rate-limit hit storage
* Log search API
* Dashboard stats API
* Alerts listing
* Rate-limit override (GET/SET/DISABLE)
* Dual MongoDB database support (primary + secondary)

### **Frontend (Next.js 14 + Tailwind)**

* Login screen
* Dashboard with charts & stats
* API log search panel
* Alerts list
* Rate-limit manager
* Token stored in localStorage
* Logout handling

---

## **🛠️ Tech Stack**

### **Backend**

* Spring Boot 3
* Kotlin
* MongoDB
* Spring Security + JWT

### **Frontend**

* Next.js 14
* React
* Tailwind CSS

---

## **📂 Project Structure**

```
collector-service/       → Spring Boot Backend
collector-frontend/      → Next.js Frontend
```

---

# **⚙️ Backend Setup**

### **1. Configure MongoDB in `application.properties`**

```properties
spring.data.mongodb.primary.uri=mongodb://localhost:27017/collector
spring.data.mongodb.secondary.uri=mongodb://localhost:27017/collector_alerts

jwt.secret=your_secret_here
jwt.expiration=86400000
```

### **2. Run Backend**

```bash
./gradlew bootRun
```

Backend runs at:

```
http://localhost:8080
```

---

# **📡 Backend API Endpoints**

### ✅ **Authentication**

```
POST /auth/login
```

### ✅ **Dashboard APIs**

```
GET /dashboard/stats
GET /dashboard/alerts
POST /dashboard/logs/search
```

### ✅ **Ingest APIs**

```
POST /ingest/log
POST /ingest/logs
POST /ingest/rate-limit-hit
```

### ✅ **Rate-Limit Override APIs**

```
GET /rate-limit/{serviceName}
POST /rate-limit/{serviceName}/{limit}
DELETE /rate-limit/{serviceName}
```

---

# **🌐 Frontend Setup**

### **1. Install dependencies**

```bash
npm install
```

### **2. Create `.env.local`**

```
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
```

### **3. Run Next.js app**

```bash
npm run dev
```

Frontend runs at:

```
http://localhost:3000
```

---

# **🔐 Authentication Flow**

1. User logs in → receives JWT
2. Token stored in `localStorage`
3. Every API call automatically includes:

```
Authorization: Bearer <token>
```

4. If no token → user is redirected to `/login`

---

# **📊 Dashboard Modules**

| Module                 | Description                                        |
| ---------------------- | -------------------------------------------------- |
| **Stats**              | Requests/min, error rate %, top endpoints, latency |
| **Log Search**         | Search logs by service, path, status code          |
| **Alerts**             | Shows alert history from secondary DB              |
| **Rate-Limit Manager** | Get/Set/Disable overrides                          |

---

# **📜 Assignment Requirements**

| Requirement           | Status |
| --------------------- | ------ |
| Ingest logs           | ✅      |
| Ingest rate-limit hit | ✅      |
| Dashboard stats       | ✅      |
| Alerts listing        | ✅      |
| Search logs           | ✅      |
| Rate-limit override   | ✅      |
| JWT login             | ✅      |
| Next.js UI            | ✅      |

---

# **🧪 Testing (Postman)**

Import and test:

* `/auth/login`
* `/dashboard/stats`
* `/dashboard/logs/search`
* `/ingest/log`
* `/rate-limit/...`

---

# **👤 Author**

**Anurag Choudhary**
Full-Stack Developer (Spring Boot + Next.js)

