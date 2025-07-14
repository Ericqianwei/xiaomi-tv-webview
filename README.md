# 小米电视WebView应用程序

## 项目概述
这是一个专为小米电视MIUI TV 1.3.51系统设计的Android应用程序，支持Android 4.4.2及以上版本。该应用能够全屏显示指定的网页：`http://118.31.72.29:8100/#/de-link/BdGpvdNr`

## 功能特性
- ✅ 全屏网页浏览体验
- ✅ 适配小米电视遥控器操作
- ✅ 支持Android 4.4.2系统
- ✅ 自动处理网页加载和错误
- ✅ 电视专用UI优化
- ✅ 支持网页内导航

## 技术规格
- **最低系统版本**: Android 4.4.2 (API Level 19)
- **目标系统版本**: Android 9.0 (API Level 28)
- **应用包名**: com.minimax.tvwebview
- **屏幕方向**: 强制横屏
- **网络权限**: 需要互联网访问权限

## 项目结构
```
tv_webview_app/
├── app/
│   ├── build.gradle                 # 应用构建配置
│   ├── proguard-rules.pro          # 代码混淆规则
│   └── src/main/
│       ├── AndroidManifest.xml     # 应用清单文件
│       ├── java/com/minimax/tvwebview/
│       │   └── MainActivity.java   # 主活动代码
│       └── res/
│           ├── drawable/
│           │   └── ic_launcher.xml  # 应用图标
│           ├── layout/
│           │   └── activity_main.xml # 主界面布局
│           ├── values/
│           │   ├── strings.xml      # 字符串资源
│           │   └── styles.xml       # 样式定义
├── build.gradle                    # 项目构建配置
├── settings.gradle                 # 项目设置
├── gradle.properties              # Gradle属性
├── gradlew                        # Gradle包装器脚本
└── gradle/wrapper/
    └── gradle-wrapper.properties  # Gradle包装器配置
```

## 构建步骤

### 方法一：使用Android Studio（推荐）
1. **安装Android Studio**: 从官网下载并安装最新版本的Android Studio
2. **导入项目**: 打开Android Studio，选择"Open an existing Android Studio project"，选择本项目文件夹
3. **SDK配置**: 确保安装了API Level 19 (Android 4.4.2) 和 API Level 28 (Android 9.0)
4. **构建APK**: 
   - 方式1: 菜单栏 → Build → Build Bundle(s) / APK(s) → Build APK(s)
   - 方式2: 在Terminal中运行 `./gradlew assembleRelease`
5. **获取APK**: 构建完成后，APK文件位于 `app/build/outputs/apk/release/app-release.apk`

### 方法二：命令行构建
前提条件：
- 安装Java JDK 8或以上版本
- 安装Android SDK
- 设置ANDROID_HOME环境变量

构建命令：
```bash
# 进入项目目录
cd tv_webview_app

# 赋予gradlew执行权限（Linux/Mac）
chmod +x gradlew

# 构建发布版APK
./gradlew assembleRelease

# 或构建调试版APK
./gradlew assembleDebug
```

## 安装说明

### 小米电视安装步骤
1. **开启调试模式**:
   - 进入 设置 → 系统 → 关于
   - 连续点击"版本号"7次开启开发者模式
   - 返回设置，进入 开发者选项
   - 开启"USB调试"和"未知来源应用安装"

2. **传输APK文件**:
   - 使用U盘将APK文件复制到小米电视
   - 或通过网络文件管理器下载APK

3. **安装应用**:
   - 使用电视的文件管理器找到APK文件
   - 点击安装并确认权限

4. **运行应用**:
   - 安装完成后，在应用列表中找到"电视网页浏览器"
   - 点击启动即可全屏浏览指定网页

## 使用指南

### 基本操作
- **启动**: 应用启动后自动加载指定网页
- **导航**: 使用遥控器方向键浏览网页内容
- **返回**: 按遥控器返回键可以后退网页或退出应用
- **退出**: 在首页按返回键退出应用

### 遥控器支持
- **方向键**: 网页滚动和元素导航
- **确认键**: 点击网页链接或按钮
- **返回键**: 网页后退或退出应用
- **菜单键**: 可能触发网页上下文菜单（如果网页支持）

## 故障排除

### 常见问题
1. **网页加载失败**:
   - 检查网络连接
   - 确认目标网址可访问
   - 重启应用重试

2. **应用无法安装**:
   - 确认已开启"未知来源"安装
   - 检查APK文件是否损坏
   - 确认电视系统版本兼容性

3. **全屏显示问题**:
   - 应用会自动适配电视屏幕
   - 如有显示异常，重启应用

4. **遥控器操作异常**:
   - 确认遥控器正常工作
   - 尝试重新配对遥控器

### 网络要求
- 需要稳定的网络连接
- 建议使用有线网络以获得最佳体验
- 确保网络可以访问目标网址

## 技术说明

### 核心功能实现
- **WebView组件**: 使用Android原生WebView显示网页
- **全屏模式**: 隐藏状态栏和导航栏，提供沉浸式体验
- **电视适配**: 专门针对电视遥控器和大屏幕优化
- **错误处理**: 自动处理网络错误和加载失败情况

### 安全性
- 仅加载指定的安全网址
- 不收集用户隐私数据
- 不需要敏感权限

## 开发信息
- **开发者**: MiniMax Agent
- **版本**: 1.0
- **兼容性**: Android 4.4.2 - Android 9.0+
- **包大小**: 约2-3MB

## 许可证
本项目仅供学习和个人使用。

---

如有任何问题或需要技术支持，请联系开发团队。
