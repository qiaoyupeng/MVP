MVP：一款结合Retrofit+Rx+ButterKnife+Glide+Material的综合阅读类app

项目地址：https://github.com/qypcyj/MVP

项目介绍：这个app使用了现在较为流行的网络请求框架(Retrofit),注解绑定框架(ButterKnife),异步编程框架(Rxjava),图片加载框架(Glide)，并且采用了MVP模式的设计模式。

功能主要有;新闻阅读,美图浏览,以及天气查询功能

使用的新闻图片API均来自易源(比较坑的就是免费新闻api已经改成收费,而且变更后没有给予提醒导致我排除了好久的bug)

项目展示:


简单技术讲解：主页面采用BottomNavigationView加载4个版块。各个版块使用Retrofit实现网络请求，新闻端Retrofit+OKHttpclient实现离线缓存，支持无网络访问，采用RecylerView展示新闻列表。美女版块，使用Glide图片加载框架结合瀑布流展示图片列表。天气版块，因为图方便使用的是网络定位，不需要下载地图sdk，通过网络实现实时定位获取天气。下拉刷新则使用的是PullRefresfLayout。

Retrofit详解：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/1016/3588.html

Rxjava：http://blog.csdn.net/lzyzsd/article/details/41833541

ButterKnife:https://github.com/JakeWharton/butterknife

MVP设计模式:https://kymjs.com/code/2015/11/09/01/这篇文章讲解的是TheMvp模式与传统的MVP模式还是有区别，前者主要考虑到了视图的复用，将Actvity当做Presenter来写并在其中包含进来一个视图层的代理Delegate跟ios中的代理不谋而合，通过视图层的代理去操纵View。前者的优势在于支持界面复用，当有界面可以直接复用时可以减少代码量。 建议多阅读思考几次，设计模式要多理解。

附上MVP模式讲解连接：http://blog.csdn.net/vector_yi/article/details/24719873

受开源项目帮助：http://blog.csdn.net/ly502541243/article/details/50837334

对于新手上路来说这个demo说难也不难，说简单也不简单，每个coder我想都可能都有一颗代码改变世界的心，多实践就会变得很简单,毕竟努力的人运气不会很差。

如果有不懂的无法运行可以在github上联系我;https://github.com/qypcyj/MVP，有帮助的话可以多star

或者邮箱联系我:1437690325@qq.com

ps：如果新闻加载不了,很可能是新闻接口失效了,毕竟我只充值了几个月的费用,毕竟我qiong。希望能够帮助到需要帮助的人。
