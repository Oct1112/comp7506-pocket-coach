# Pocket Coach 接口文档（Home / Learn）

## 1. 文档说明

本文档定义当前 `Pocket Coach` 前端在 `Home` 与 `Learn` 页面所需对接的接口。

当前约定：

- 接口返回格式以前端页面展示需求为准
- `Daily Book Picks` 的封面图不依赖后端图片 URL，由前端本地资源处理
- `deepDiveUrl` 不依赖后端，前端按已知播客做本地保底跳转

## 2. Base URL

本地开发环境示例：

```text
http://10.0.2.2:3000/
```

所有接口统一以 `/api/...` 开头。

---

## 3. Home 页接口

### 3.1 获取 Today's Lesson

**GET** `/api/home/todays-lesson`

#### 功能说明
返回首页顶部 `Today's Lesson` 模块所需内容。

#### 请求参数
无

#### 响应示例

```json
{
  "lesson": {
    "podcastId": "pod_ben_horowitz_hard_things",
    "title": "Mastering the Hard Things: Ben Horowitz on Leadership, AI, and the Abyss",
    "description": "Discover why real leadership is about making the decisions nobody likes and how to build the psychological muscle to survive the 'CEO abyss.'",
    "deepDiveUrl": "https://youtu.be/KPxTekxQjzc?si=_OYTtoz351tRXyM3",
    "ctaLabel": "Deep Dive"
  }
}
```

#### 字段说明

| 字段 | 类型 | 必填 | 说明 |
|---|---|---:|---|
| lesson.podcastId | string | 是 | 播客唯一标识 |
| lesson.title | string | 是 | 首页标题 |
| lesson.description | string | 是 | 首页简介 |
| lesson.deepDiveUrl | string | 否 | 点击 `Deep Dive` 后跳转链接 |
| lesson.ctaLabel | string | 否 | 按钮文案，默认可为 `Deep Dive` |

#### 说明
- `deepDiveUrl` 建议后端返回
- 如果后端暂时未返回该字段，前端会对已知播客使用本地保底链接

---

### 3.2 获取 5 Min Read

**GET** `/api/home/5min-read`

#### 功能说明
返回首页 `5 Min Read` 模块所需内容，包括三条核心要点与完整正文。

#### 请求参数
无

#### 响应示例

```json
{
  "summary": {
    "podcast": {
      "podcastId": "pod_ben_horowitz_hard_things",
      "title": "Mastering the Hard Things: Ben Horowitz on Leadership, AI, and the Abyss",
      "guest": "Ben Horowitz",
      "sourceLabel": "Mastering the Hard Things: Ben Horowitz on Leadership, AI, and the Abyss"
    },
    "title": "Mastering the Hard Things: Ben Horowitz on Leadership, AI, and the Abyss",
    "keyTakeaways": [
      "Real leadership only adds value when you make decisions that most people don't like.",
      "Success isn't one big win, but a chain of small, hard, correct choices.",
      "Great CEOs don't tutor executives; they find world-class talent to lead them."
    ],
    "fullSummary": "Imagine you're standing at the edge of a dark abyss and everyone is looking at you to pick a direction. That's what it's like being a CEO. ...",
    "buttonLabel": "Read Summary"
  }
}
```

#### 字段说明

| 字段 | 类型 | 必填 | 说明 |
|---|---|---:|---|
| summary.podcast.podcastId | string | 是 | 播客唯一标识 |
| summary.podcast.title | string | 是 | 播客标题 |
| summary.podcast.guest | string | 是 | 嘉宾名称 |
| summary.podcast.sourceLabel | string | 是 | 来源展示字段，当前可直接返回播客标题 |
| summary.title | string | 是 | `5 Min Read` 模块标题 |
| summary.keyTakeaways | string[] | 是 | 三条核心要点 |
| summary.fullSummary | string | 是 | 完整正文，由数据库字段读取并返回 |
| summary.buttonLabel | string | 否 | 按钮文案，默认可为 `Read Summary` |

#### 数据来源说明
- `fullSummary` 为正式业务字段，不依赖 `documents/` 下的蒸馏文本文件
- 后端数据库建议存储字段名：`fiveMinReadSummary`
- 接口返回时映射为：`fullSummary`

---

### 3.3 获取 Daily Book Picks

**GET** `/api/home/daily-books`

#### 功能说明
返回首页 `Daily Book Picks` 所需书籍列表。

#### 请求参数
无

#### 响应示例

```json
{
  "books": [
    {
      "id": "book_001",
      "title": "The Weirdest People in the World",
      "author": "Joseph Henrich",
      "reason": "It explains how unique cultural rules allowed for the creation of science and large-scale companies."
    },
    {
      "id": "book_002",
      "title": "Writing My Wrongs",
      "author": "Shaka Senghor",
      "reason": "A powerful story of personal transformation and building trust from zero, even in harsh conditions."
    },
    {
      "id": "book_003",
      "title": "How to Be Free",
      "author": "Shaka Senghor",
      "reason": "Offers specific techniques for dealing with extreme psychological pressure, which is vital for entrepreneurs."
    }
  ]
}
```

#### 字段说明

| 字段 | 类型 | 必填 | 说明 |
|---|---|---:|---|
| books[].id | string | 是 | 图书唯一标识 |
| books[].title | string | 是 | 书名 |
| books[].author | string | 是 | 作者 |
| books[].reason | string | 否 | 推荐理由 |

#### 说明
- 当前 `Daily Book Picks` 封面图不由后端返回
- 前端固定使用本地三张 JPG 资源进行展示

---

## 4. Learn 页接口

### 4.1 获取知识卡片列表

**GET** `/api/cards`

#### 功能说明
返回 `Learn` 页知识卡片列表，支持按标签与播客筛选。

#### Query 参数

| 参数 | 类型 | 必填 | 说明 |
|---|---|---:|---|
| tag | string | 否 | 标签筛选，例如 `Growth` / `Strategy` / `AI` |
| podcastId | string | 否 | 按播客 ID 筛选 |

#### 请求示例

```text
GET /api/cards?tag=Strategy&podcastId=pod_ben_horowitz_hard_things
```

#### 响应示例

```json
{
  "cards": [
    {
      "id": "card_001",
      "podcastId": "pod_ben_horowitz_hard_things",
      "typeLabel": "Key Insight",
      "keyInsight": "Real leadership only adds value through decisions that most people don't like.",
      "explanation": "Think of a leader like a navigator on a ship in a storm. If the path is clear and the sun is out, everyone knows where to go, and the navigator is just a passenger.",
      "quote": "If everybody agrees with the decision, then you didn't add any value because they would've done that without you.",
      "actionItem": "Identify one decision you've been putting off because it might be unpopular, and schedule a 15-minute meeting today to address it.",
      "tags": ["Leadership", "Strategy"],
      "glossary": [],
      "sourceLabel": "Mastering the Hard Things: Ben Horowitz on Leadership, AI, and the Abyss",
      "isSaved": false
    }
  ]
}
```

#### 字段说明

| 字段 | 类型 | 必填 | 说明 |
|---|---|---:|---|
| cards[].id | string | 是 | 卡片唯一标识 |
| cards[].podcastId | string | 是 | 所属播客 ID |
| cards[].typeLabel | string | 否 | 卡片类型标签，默认可为 `Key Insight` |
| cards[].keyInsight | string | 是 | 核心观点 |
| cards[].explanation | string | 是 | 解释说明 |
| cards[].quote | string | 否 | 原话引用 |
| cards[].actionItem | string | 否 | 可执行建议 |
| cards[].tags | string[] | 是 | 标签列表 |
| cards[].glossary | GlossaryItem[] | 是 | 术语解释列表 |
| cards[].sourceLabel | string | 是 | 来源展示字段，当前可直接返回播客标题 |
| cards[].isSaved | boolean | 否 | 当前用户是否已收藏 |

#### GlossaryItem 结构

```json
{
  "term": "Sunk Cost",
  "definition": "Money or effort already spent that you can't get back, which shouldn't affect your future decisions."
}
```

---

### 4.2 获取单张知识卡片详情

**GET** `/api/cards/:id`

#### 功能说明
返回单张知识卡片的完整内容。

#### Path 参数

| 参数 | 类型 | 必填 | 说明 |
|---|---|---:|---|
| id | string | 是 | 卡片 ID |

#### 请求示例

```text
GET /api/cards/card_001
```

#### 响应示例

```json
{
  "card": {
    "id": "card_001",
    "podcastId": "pod_ben_horowitz_hard_things",
    "typeLabel": "Key Insight",
    "keyInsight": "Real leadership only adds value through decisions that most people don't like.",
    "explanation": "Think of a leader like a navigator on a ship in a storm. If the path is clear and the sun is out, everyone knows where to go, and the navigator is just a passenger.",
    "quote": "If everybody agrees with the decision, then you didn't add any value because they would've done that without you.",
    "actionItem": "Identify one decision you've been putting off because it might be unpopular, and schedule a 15-minute meeting today to address it.",
    "tags": ["Leadership", "Strategy"],
    "glossary": [],
    "sourceLabel": "Mastering the Hard Things: Ben Horowitz on Leadership, AI, and the Abyss",
    "isSaved": false
  }
}
```

---

### 4.3 收藏知识卡片

**POST** `/api/cards/:id/save`

#### 功能说明
将指定卡片加入用户收藏。

#### Path 参数

| 参数 | 类型 | 必填 | 说明 |
|---|---|---:|---|
| id | string | 是 | 卡片 ID |

#### 请求体
无

#### 响应示例

```json
{
  "success": true,
  "message": "Card saved successfully."
}
```

#### 字段说明

| 字段 | 类型 | 必填 | 说明 |
|---|---|---:|---|
| success | boolean | 是 | 是否保存成功 |
| message | string | 是 | 返回说明信息 |

#### 备注
- 当前用户身份方案可先按 demo user 处理
- 后续如接登录系统，可再补充 JWT 鉴权方案

---

## 5. 前后端对齐说明

### 5.1 `sourceLabel`
- 当前版本为后端必返字段
- 当前最简单做法：直接返回对应播客标题

### 5.2 `deepDiveUrl`
- 后端建议返回
- 若缺失，前端会对已知播客使用本地保底链接

### 5.3 `Daily Book Picks` 封面
- 当前不属于 API 返回字段
- 前端固定使用本地资源图展示

### 5.4 空值策略建议
建议后端保持以下策略，减少前端解析与展示异常：

- `quote` 无值时返回空字符串或 `null`
- `actionItem` 无值时返回空字符串或 `null`
- `glossary` 无值时返回空数组 `[]`
- `tags` 无值时返回空数组 `[]`

---

## 6. 当前接口清单

| 页面 | 方法 | 路径 | 说明 |
|---|---|---|---|
| Home | GET | `/api/home/todays-lesson` | 获取 Today's Lesson |
| Home | GET | `/api/home/5min-read` | 获取 5 Min Read |
| Home | GET | `/api/home/daily-books` | 获取 Daily Book Picks |
| Learn | GET | `/api/cards` | 获取知识卡片列表 |
| Learn | GET | `/api/cards/:id` | 获取单张卡片详情 |
| Learn | POST | `/api/cards/:id/save` | 收藏卡片 |
