# xRouter

## 一.引入:
   1. 依赖远程库
      >compile 'com.winterrunner.router:router:1.0.0'
   2. 下载router源码，依赖library

   3. 混淆添加
            -keep  class com.winterrunner.router.bean.**{*;}
            -keep  class * extends com.winterrunner.router.action.Action{*; }


## 二.目的：模块化开发，解耦
    1. 任意模块的新增和去除，都不会影响到别的模块的运行。
    2. 模块之间通过约定好的方式进行数据交互和跳转，这里我们对模块之间的交互加了一个中间层-->Router,由它完成数据交互和跳转，并实现解耦。


## 三.使用方法：
    1. 每个模块都依赖router(用于模块间交互)，basecommon(公共的工具类，BaseActivity,BaseFragment等)
    2. 模块之间的跳转：这里只写了个主页跳转到订单模块的逻辑，跳转后的结果返回有两种，同步或者异步返回结果
                   同步返回结果：处理Action中的两个参数的invoke方法
                   异步返回结果：处理Action中的三个参数的invoke方法，使用方法看action中的说明



## 四.注意：
    1. 模块化开发，主要目的是为了解耦。
    2. 当项目功能越来越多的时候，这时候我们运行一次项目可能需要耗费好几分钟。开发阶段，我们可以单独运行自己的module，来达到快速运行开发（具体配置方式，请自行百度喽，以后会在demo中慢慢集成进来的）

