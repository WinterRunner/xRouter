# xRouter

项目有两个模块，订单和商品，分别对应两个module,ordermanage和goodmanage

目的：模块化开发，解耦。任意模块的新增和去除，都不会影响到别的模块的运行。
     模块之间通过约定好的方式进行数据交互和跳转，这里我们对模块之间的交互加了一个中间层-->Router,由它完成数据交互和跳转，并实现解耦。


使用方法：
    1.每个模块都依赖router(用于模块间交互)，basecommon(公共的工具类，BaseActivity,BaseFragment等)

    2.模块之间的跳转：这里只写了个主页跳转到订单模块的逻辑，跳转后的结果返回有两种，同步或者异步返回结果

                   同步返回结果：处理Action中的两个参数的invoke方法
                   异步返回结果：处理Action中的三个参数的invoke方法，使用方法看action中的说明





