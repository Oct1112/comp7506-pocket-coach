# Pocket Coach Frontend

`Pocket Coach` 前端为 Android Studio Kotlin 原生项目，当前代码位于 `frontend/` 目录。

## 项目说明
该前端项目用于展示播客学习相关的移动端界面与交互，当前主要包含以下页面：

- `Home`
- `Learn`
- `Ask`
- `Library`

当前主要技术方案：

- Kotlin
- Android View XML
- ViewBinding
- RecyclerView
- Retrofit
- 本地 Mock 数据渲染

## 目录结构

```text
.
├─ README.md
└─ frontend/
   ├─ app/
   ├─ gradle/
   ├─ build.gradle.kts
   ├─ settings.gradle.kts
   ├─ gradlew
   ├─ gradlew.bat
   └─ README.md
```

## 环境要求

- Android Studio
- JDK 11
- Android SDK
- Gradle Wrapper（项目已自带）

## 本地运行

### 方式一：使用 Android Studio

1. 打开 Android Studio
2. 选择 `Open`
3. 打开仓库中的 `frontend/` 目录
4. 等待 Gradle 同步完成
5. 选择模拟器或真机后运行项目

### 方式二：命令行构建

在仓库根目录执行：

```powershell
cd frontend
.\gradlew.bat assembleDebug
```

构建成功后，调试包默认位于：

```text
frontend/app/build/outputs/apk/debug/
```

## 数据与接口说明

当前页面默认使用本地 Mock 数据进行展示。

如果需要切换到真实接口，请修改前端项目中的接口配置与 Mock 开关。相关代码位于：

- `frontend/app/src/main/java/com/hkucs/pocketcoach/network/ApiConfig.kt`
- `frontend/app/src/main/java/com/hkucs/pocketcoach/data/MockDataSource.kt`

## 常用命令

```powershell
cd frontend
.\gradlew.bat assembleDebug
.\gradlew.bat installDebug
.\gradlew.bat clean
```
