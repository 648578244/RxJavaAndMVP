# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\adt-bundle-windows-x86_64-20140624\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
 # 代码混淆压缩比，在0~7之间，默认为5,一般不下需要修改
 -optimizationpasses 5

 # 混淆时不使用大小写混合，混淆后的类名为小写
 # windows下的同学还是加入这个选项吧(windows大小写不敏感)
 -dontusemixedcaseclassnames

 # 指定不去忽略非公共的库的类
 # 默认跳过，有些情况下编写的代码与类库中的类在同一个包下，并且持有包中内容的引用，此时就需要加入此条声明
 -dontskipnonpubliclibraryclasses

 # 指定不去忽略非公共的库的类的成员
 -dontskipnonpubliclibraryclassmembers

 # 不做预检验，preverify是proguard的四个步骤之一
 # Android不需要preverify，去掉这一步可以加快混淆速度
 -dontpreverify

 # 有了verbose这句话，混淆后就会生成映射文件
 -verbose
 #apk 包内所有 class 的内部结构
 -dump class_files.txt
 #未混淆的类和成员
 -printseeds seeds.txt
 #列出从 apk 中删除的代码
 -printusage unused.txt
 #混淆前后的映射
 -printmapping mapping.txt

 # 指定混淆时采用的算法，后面的参数是一个过滤器
 # 这个过滤器是谷歌推荐的算法，一般不改变
 -optimizations !code/simplification/artithmetic,!field/*,!class/merging/*

 # 保护代码中的Annotation不被混淆
 # 这在JSON实体映射时非常重要，比如fastJson
 -keepattributes *Annotation*

 # 避免混淆泛型
 # 这在JSON实体映射时非常重要，比如fastJson
 -keepattributes Signature

 # 抛出异常时保留代码行号
 -keepattributes SourceFile,LineNumberTable

 #忽略警告
 -ignorewarning

 #==================================【项目配置】==================================

 # 保留所有的本地native方法不被混淆
 -keepclasseswithmembernames class * {
     native <methods>;
 }

 # 保留了继承自Activity、Application这些类的子类
 -keep public class * extends android.app.Fragment
 -keep public class * extends android.app.Activity
 -keep public class * extends android.app.Application
 -keep public class * extends android.app.Service
 -keep public class * extends android.content.BroadcastReceiver
 -keep public class * extends android.content.ContentProvider
 -keep public class * extends android.preference.Preference
 -keep public class * extends android.view.View
 -keep public class * extends android.database.sqlite.SQLiteOpenHelper{*;}
-keep public class * extends android.support.v4.**

 # 如果有引用android-support-v4.jar包，可以添加下面这行
 -keep public class com.null.test.ui.fragment.** {*;}

 #如果引用了v4或者v7包
 -dontwarn android.support.**

 # 保留Activity中的方法参数是view的方法，
 # 从而我们在layout里面编写onClick就不会影响
 -keepclassmembers class * extends android.app.Activity {
     public void * (android.view.View);
 }

 # 枚举类不能被混淆
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 # 保留自定义控件(继承自View)不能被混淆
 -keep public class * extends android.view.View {
     public <init>(android.content.Context);
     public <init>(android.content.Context, android.util.AttributeSet);
     public <init>(android.content.Context, android.util.AttributeSet, int);
     public void set*(***);
     *** get* ();
 }

 # 保留所有注解
 -keepattributes *Annotation*
 -keepattributes *JavascriptInterface*

 # 替换成自己的包名 保留对应的交互方法
# -keepclassmembers class com.weidaiwang.yewuyuan.model.webutil.WebViewJSBridge {
#   public *;
# }

 -keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
 }
 -keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String);
 }

 # 保留Parcelable序列化的类不能被混淆
 -keep class * implements android.os.Parcelable{
     public static final android.os.ParcelableCreator *;
 }

 # 保留Serializable 序列化的类不被混淆
 -keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
 }

 # 对R文件下的所有类及其方法，都不能被混淆
 -keepclassmembers class **.R* {
     *;
 }

 #######################################
 #微信
 ##################
 -keep class com.tencent.mm.sdk.** {
    *;
 }
 #####################
 #个推
 #####################
 -dontwarn com.igexin.**
 -keep class com.igexin.**{*;}
 #####################

 #eventbus
 -keepclassmembers class ** {
     public void onEvent*(**);
     void onEvent*(**);
 }

 #OKHTTP
 -dontwarn com.squareup.okhttp3.**
 -keep class com.squareup.okhttp3.** { *;}
 -dontwarn okio.**

 #Glide
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

 #retrofit2
 -dontwarn retrofit2.**
 -keep class retrofit2.** { *; }
 -keepattributes Signature
 -keepattributes Exceptions

 #Rxjava
 -dontwarn sun.misc.**
 -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode producerNode;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }

 #bean
 -keep public class com.idea.l.rxjavaandmvp.model.bean.** {*; }

 #eventbus
  -keepattributes *Annotation*
  -keepclassmembers class ** {
      @org.greenrobot.eventbus.Subscribe <methods>;
  }
  -keep enum org.greenrobot.eventbus.ThreadMode {
        *;
  }
  # Only required if you use AsyncExecutor
  -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
      <init>(java.lang.Throwable);
  }

 #umeng
 -keepclassmembers class * {
   public <init> (org.json.JSONObject);
 }
 -keep public class [com.idea.l.rxjavaandmvp].R$*{
   public static final int *;
 }
 -keepclassmembers enum * {
   public static **[] values(); public static ** valueOf(java.lang.String);
 }


-keep class * extends java.lang.annotation.Annotation
-keepclasseswithmembernames class * {
    native <methods>;
}
-dontwarn com.alipay.euler.andfix.**
-keep class com.alipay.euler.andfix.** {*;}