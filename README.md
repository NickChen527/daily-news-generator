# daily-news-generator
根據各國每日新聞透過 AI 產生報導摘要

## 1. 專案概述 (Project Overview)
此專案是一個自動化的 AI 新聞聚合與摘要平台。系統根據使用者訂閱的特定領域（如：財經、科技、教育等），每日定時透過 AI 彙整前五大頭條新聞，並發送精簡的摘要報告與原始出處連結至使用者信箱。

---

## 2. 技術架構 (Technical Stack)

### 2.1 前端 (Frontend)
* **框架：** Vue.js
* **語言：** HTML5, CSS3, JavaScript (ES6+)

### 2.2 後端 (Backend)
* **語言與框架：** Java 17+, Spring Boot
* **模組化設計：** Package-by-Feature (依功能拆分套件，利於未來微服務擴充)
* **身分驗證：** JWT (JSON Web Token)
* **安全處理：** BCrypt 密碼雜湊加密

### 2.3 資料庫與整合 (DB & Integrations)
* **資料庫：** MySQL
* **新聞來源：** NewsAPI
* **第三方登入：** Google OAuth 2.0
* **郵件服務：** Spring Boot Starter Mail (JavaMailSender)
* **AI 引擎：** Gemini / Claude API

---

## 3. 功能性需求 (Functional Requirements)

### 3.1 使用者帳戶與認證
* **登入方式：** 支援傳統 Email 註冊與 Google 第三方快速登入。
* **權限控管：** 使用 JWT 進行 API 存取驗證，確保使用者僅能修改自己的訂閱偏好。

### 3.2 訂閱偏好設定
* **領域選擇：** 使用者可在 Web 介面勾選感興趣的新聞類別（如：Business, Technology, Science, Health, Education 等）。
* **即時存取：** 偏好設定需即時同步至 MySQL 資料庫。

### 3.3 新聞抓取與 AI 處理 (核心邏輯)
* **資料來源：** 系統每日對接 NewsAPI，獲取各分類之熱門新聞。
* **篩選機制：** 針對每個分類僅選取 **Top 5** 則頭條新聞。
* **AI 摘要邏輯：** 1.  將 5 則新聞的內容彙整傳送給 AI。
    2.  產出一份約 150-300 字的綜合趨勢總結。
    3.  保留 5 則新聞的原始標題與 URL 連結。

### 3.4 自動化發送任務
* **發送時間：** 每日早上 **09:00 AM (台北時間)** 準時發送。
* **報告結構：**
    1.  每日報告總結 (根據所選領域的前五大頭條做總結)。
    2.  文章出處連結 (5 則新聞的原始網址)。

---

## 4. 非功能性需求 (Non-Functional Requirements)

* **安全性 (Security)：** * 資料庫密碼嚴禁明文存儲。
    * JWT 需設置合理的過期時間。
* **可維護性 (Maintainability)：** 後端代碼需嚴格遵守模組化封裝，降低不同功能間的耦合度。
* **效能 (Performance)：** 每日更新頻率為一次，系統應設計快取或暫存機制，避免重複對 NewsAPI 與 AI 進行不必要的請求。
* **擴充性 (Scalability)：** 初期使用者預估為 100 人，架構設計需保留未來拆分為 Microservices 的彈性。

---

## 5. 資料實體模型 (Data Entities)

* **User：** `id`, `email`, `password_hash`, `google_id`, `created_at`
* **Category：** `id`, `name` (新聞分類)
* **UserSubscription：** `user_id`, `category_id` (多對多關聯)
* **DailyReportArchive：** `id`, `category_id`, `summary_text`, `source_links`, `report_date` (報告暫存區)

---

## 6. 開發里程碑 (Milestones)
1.  **M1：** 資料庫設計與 Spring Boot 基礎環境建置。
2.  **M2：** 串接 Google Login 與 JWT 驗證機制。
3.  **M3：** 完成 NewsAPI 抓取與 AI 總結接口串接。
4.  **M4：** 實作每日 09:00 排程發信功能。
5.  **M5：** Vue.js 前端介面開發與系統整合測試。