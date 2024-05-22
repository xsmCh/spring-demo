1、自定义Representer的作用

​	因为整合sharding-jdbc需要snakeyaml至少2.2，以及因为安全问题需要升级snakeyaml，但低版本的springboot整合的snakeyaml是1.x版本，升级后不能应用不能启动。

​	自定义Representer后，添加了无参构造方法，覆盖jar包中的Representer。

​	参考资料：https://blog.csdn.net/weixin_44981472/article/details/131943181