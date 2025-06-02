
# Coindesk API 專案
這個專案是一個 Spring Boot 應用程式，它展示了如何整合外部 API (Coindesk) 並與 H2 資料庫進行互動。

### 📌 功能概述
一個使用 Spring Boot 架構的 RESTful API 專案，支援：
- 呼叫 coindesk API，解析其下行內容並進行資料轉換，實作新的API。 </br>
API 路徑：https://kengp3.github.io/blog/coindesk.json 

- 幣別資料表 CRUD 等維護功能的 API。</br>
A. 更新時間（時間格式範例：1990/01/01 00:00:00）。 </br>
B. 幣別相關資訊（幣別，幣別中文名稱，以及匯率）。

### 📦 技術棧
- Spring Boot 3.x
- Java 8
- Maven
- H2 資料庫
- Spring Data JPA
- RESTful API
