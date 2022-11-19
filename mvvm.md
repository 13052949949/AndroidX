@[TOC](安卓MVVM架构学习)

# 1、lifecycle：界面生命周期感知

# 2、dataBinding：数据和UI双向绑定

# 3、ViewModel：业务逻辑

# 4、liveData：可观察数据项

# lifecycle

## 功能使用

### 1.lifecycle类说明、作用和意义

                    Lifecycle类：定义一个具有生命周期的对象

```java
                    //添加一个LifecycleObserver(生命周期观察者)，它将在 LifecycleOwner 更改状态时收到通知。
public abstract void addObserver(@NonNull LifecycleObserver observer);

//从观察者列表中删除给定的观察者。
public abstract void removeObserver(@NonNull LifecycleObserver observer);

//返回生命周期的当前状态
public abstract State getCurrentState();

public enum Event {
    //LifecycleOwner的onCreate事件的常量。
    ON_CREATE,
    //LifecycleOwner的onStart事件的常量。
    ON_START,
    //LifecycleOwner的onResume事件的常量。
    ON_RESUME,
    //LifecycleOwner的onPause事件的常量。
    ON_PAUSE,
    //LifecycleOwner的onStop事件的常量。
    ON_STOP,
    //LifecycleOwner的onDestroy事件的常量。
    ON_DESTROY,
    //可用英语匹配所有事件
    ON_ANY,
}

public enum State {
    //LifecycleOwner的销毁状态。在此事件之后，此Lifecycle将不再派发任何事件。
    // 例如，对于Activity，此状态就在Activity的onDestroy调用之前达到。   
    DESTROYED,
    //LifecycleOwner的初始化状态。
    // 对于Activity，这是构造时的状态，但尚未收到onCreate。
    INITIALIZED,
    // LifecycleOwner创建状态。
    // 对于Activity，在两种情况下会达到此状态：1.在onCreate调用之后 2.在onStop调用之前
    CREATED,
    //LifecycleOwner的开始状态。
    // 对于Activity，在两种情况下会达到此状态：1.在onStart调用之后 2.在onPause调用之前
    STARTED,
    //LifecycleOwner 的恢复状态。
    // 对于Activity，在调用onResume后达到此状态。
    RESUMED;
}
```

### 2.监听Activity生命周期的变化，LifecycleEventObserver

 ```kotlin
  lifecycle.addObserver(object : LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        println(event.name)
    }
})
```

### 3.使用@OnLifeCycleEvent监听特定生命周期(反射)

```kotlin
lifecycle.addObserver(object : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        println("annotation:ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        println("annotation:ON_RESUME")
    }
    //...
})
```

### 4.使用APT实现3(回调)

**启用apt**
在build.gradle顶层添加

```groovy
        id 'org.jetbrains.kotlin.kapt'
```

        在dependencies中引入

```groovy
        kapt 'androidx.lifecycle:lifecycle-compiler:2.5.1'
```

**实现**
创建MainViewModel类，实现LifecycleObserver接口

```kotlin
class MainViewModel : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        println("kapt:ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        println("kapt:ON_RESUME")
    }
    //...

}
```

在MainActivity中调用

```kotlin
lifecycle.addObserver(MainViewModel())
```

### 5.最佳应用方式

## 源码分析：

### 1.Activity如何监听生命周期变化的？

### 2.@OnLifecycleEvent如何使用的？反射调用过程

### 3.使用APT时，回调方法的过程

### 4.匿名内部类作为LifecycleObserver时，有什么特殊

        不会自动生成相关代码

# DataBinding

## 功能使用

### 1.字段单向数据绑定

### 2.字段双向数据绑定

### 3.界面事件绑定

                    onclick
                    onLongClick
                    onItemClick
                    onCheckChanged
                    onItemChanged

### 4.绑定内容表达式支持

                    ==、>、<、>=、<=、?:

### 5.导入其它类，类别名

### 6.嵌套绑定问题

                    ~include其它布局文件
                    ~绑定的数据共享

### 7.自定义BindingAdapter的方式

### 8.Glide自定义BindingAdapter实现

                    ~在xml文件中直接给ImageView设置显示图片
                    ~图片可以是网络图片http、本地图片文件地址、drawable资源文件、assets资源文件等

```xml

<ImageView android:layout_width="100dp" android:layout_height="100dp"
    app:binding_src="@{viewModel.image}" />
```

### 9.RecyclerView通用Adapter实现

                    不需要再写Adapter、Holder类

```xml

<RecyclerView android:layout_width="match_parent" android:layout_height="match_parent"
    app:itemBinding="@{viewModel.itemBinding}" />
```

## 源码分析

### 1.双向数据绑定实现原理

### 2.如何自定义BindingAdapter？

### 3.自定义BindingAdapter是如何调用的？

### 4.dataBinding对原始的xml文件做了哪些修改？

                编译后的xml文件和原始xml文件有什么区别？区别的内容有什么作用？

### 5.dataBinding使用APT生成了哪些代码？

### 6.dataBinding和viewBinding的区别？

# ViewModel

## 功能使用

### 1.每个Activity(Fragment)配备一个ViewModel，ViewModel中处理这个Activity(Fragment)的业务逻辑

### 2.ViewModel对象的创建

               new运算符创建：不推荐的方式(不能复用，无法自动清理)
               用ViewModelProvider来创建

```kotlin
                    val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
```

               用kotlin扩展函数创建

### 3.ViewModel的生命周期

                同Activity，onDestroy执行时onClear清理ViewModel

## 源码分析

### 1.有几种创建ViewModel的方式？

### 2.如何在应用中所有的ViewModel中都传入某个全局对象，例如http请求类的单例？而不需要在创建ViewModel时显示传入

### 3.如何自定义创建ViewModel的Factory？安卓有哪些现成的Factory？分别有什么作用？

### 4.ViewModel如何在多个Activity中共享？

### 5.ViewModel如何实现在Activity销毁时自动清理的？

# liveData

## 功能使用

## 源码分析