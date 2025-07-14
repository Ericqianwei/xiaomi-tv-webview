#!/bin/bash

# 小米电视WebView应用构建脚本
# 作者: MiniMax Agent

echo "=== 小米电视WebView应用构建脚本 ==="
echo ""

# 检查Java环境
echo "检查Java环境..."
if command -v java &> /dev/null; then
    java -version
    echo "✅ Java环境正常"
else
    echo "❌ 未找到Java环境，请先安装JDK 8或以上版本"
    echo "建议安装Oracle JDK 8或OpenJDK 8"
    exit 1
fi

echo ""

# 检查Android SDK
echo "检查Android SDK环境..."
if [ -z "$ANDROID_HOME" ]; then
    echo "❌ 未设置ANDROID_HOME环境变量"
    echo "请安装Android SDK并设置ANDROID_HOME环境变量"
    exit 1
else
    echo "✅ ANDROID_HOME: $ANDROID_HOME"
fi

echo ""

# 检查项目文件
echo "检查项目文件完整性..."
required_files=(
    "app/src/main/AndroidManifest.xml"
    "app/src/main/java/com/minimax/tvwebview/MainActivity.java"
    "app/src/main/res/layout/activity_main.xml"
    "app/build.gradle"
    "build.gradle"
    "settings.gradle"
)

all_files_exist=true
for file in "${required_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ $file - 文件缺失"
        all_files_exist=false
    fi
done

if [ "$all_files_exist" = false ]; then
    echo "❌ 项目文件不完整，请检查项目结构"
    exit 1
fi

echo ""
echo "🚀 开始构建APK..."
echo ""

# 赋予gradlew执行权限
chmod +x gradlew

# 清理之前的构建
echo "清理之前的构建..."
./gradlew clean

echo ""

# 构建发布版APK
echo "构建发布版APK..."
./gradlew assembleRelease

# 检查构建结果
if [ -f "app/build/outputs/apk/release/app-release.apk" ]; then
    echo ""
    echo "🎉 构建成功！"
    echo "APK文件位置: app/build/outputs/apk/release/app-release.apk"
    echo ""
    
    # 显示APK信息
    apk_size=$(du -h "app/build/outputs/apk/release/app-release.apk" | cut -f1)
    echo "📱 APK信息:"
    echo "   文件大小: $apk_size"
    echo "   包名: com.minimax.tvwebview"
    echo "   版本: 1.0"
    echo "   最低Android版本: 4.4.2"
    echo ""
    
    echo "📋 安装说明:"
    echo "1. 将APK文件传输到小米电视（通过U盘或网络）"
    echo "2. 在电视上开启"未知来源"应用安装权限"
    echo "3. 使用文件管理器安装APK"
    echo "4. 启动应用即可全屏浏览指定网页"
    
else
    echo ""
    echo "❌ 构建失败，请检查错误信息并重试"
    exit 1
fi

echo ""
echo "=== 构建完成 ==="
