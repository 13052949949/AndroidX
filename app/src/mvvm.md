@[TOC](安卓MVVM架构学习)
#  1、lifecycle：界面生命周期感知
#  2、dataBinding：数据和UI双向绑定
#  3、ViewModel：业务逻辑
#  4、liveData：可观察数据项


#       lifecycle
##          功能使用
###             1.lifecycle类说明、作用和意义
###             2.监听Activity生命周期的变化，LifecycleEventObserver
###             3.使用@OnLifeCycleEvent监听特定生命周期(反射)
###             4.使用APT实现3(回调)
###             5.最佳应用方式
##          源码分析：
###             1.Activity如何监听生命周期变化的？
###             2.@OnLifecycleEvent如何使用的？反射调用过程
###             3.使用APT时，回调方法的过程
###             4.匿名内部类作为LifecycleObserver时，有什么特殊

#     DataBinding
##          功能使用
###             1.字段单向数据绑定
###             2.字段双向数据绑定
###             3.界面事件绑定
                    onclick
                    onLongClick
                    onItemClick
                    onCheckChanged
                    onItemChanged
###             4.绑定内容表达式支持
                    ==、>、<、>=、<=、?:
###             5.导入其它类，类别名
###             6.嵌套绑定问题
                    ~include其它布局文件
                    ~绑定的数据共享
###             7.自定义BindingAdapter的方式
###             8.Glide自定义BindingAdapter实现
                    ~在xml文件中直接给ImageView设置显示图片
                    ~图片可以是网络图片http、本地图片文件地址、drawable资源文件、assets资源文件等
```xml
                    <ImageView
                        android:layout_width="100dp"
                        android:layout_height="100dp"
                        app:binding_src="@{viewModel.image}"/>
```
###            9.RecyclerView通用Adapter实现
                    不需要再写Adapter、Holder类
```xml
                    <RecyclerView
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        app:itemBinding="@{viewModel.itemBinding}"/>
```
                
##      源码分析
###         1.双向数据绑定实现原理
###         2.如何自定义BindingAdapter？
###         3.自定义BindingAdapter是如何调用的？
###         4.dataBinding对原始的xml文件做了哪些修改？
                编译后的xml文件和原始xml文件有什么区别？区别的内容有什么作用？
###         5.dataBinding使用APT生成了哪些代码？
###         6.dataBinding和viewBinding的区别？

#ViewModel
##      功能使用
###         1.每个Activity(Fragment)配备一个ViewModel，ViewModel中处理这个Activity(Fragment)的业务逻辑
###         2.ViewModel对象的创建
               new运算符创建：不推荐的方式(不能复用，无法自动清理)
               用ViewModelProvider来创建
```kotlin
                    val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
```
               用kotlin扩展函数创建
###         3.ViewModel的生命周期
                同Activity，onDestroy执行时onClear清理ViewModel
##      源码分析
###         1.有几种创建ViewModel的方式？
###         2.如何在应用中所有的ViewModel中都传入某个全局对象，例如http请求类的单例？而不需要在创建ViewModel时显示传入
###         3.如何自定义创建ViewModel的Factory？安卓有哪些现成的Factory？分别有什么作用？
###         4.ViewModel如何在多个Activity中共享？
###         5.ViewModel如何实现在Activity销毁时自动清理的？

#liveData
##      功能使用
##      源码分析