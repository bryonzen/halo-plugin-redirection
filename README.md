## 资源重定向插件

对指定的资源请求进行重定向操作。

建站之时设置的文章链接slug形式是`/archives/时间戳`，但是后面发现在谷歌/必应的搜索管理后台中，无法根据url判断是哪篇文章，必须点进去链接才知道，所以将文章链接形式改成了`/archives/hello-word`形式，slug使用用`-`连接的小写英文单词。

强迫症无法容忍一个站点两种形式的链接，把原来时间戳的链接都改成了新规则形式的链接，为了保证原来的链接仍然能访问，所以做了这个重定向的插件。

## 使用方式

1. 下载，可以在[Release](https://github.com/flycati/halo-plugin-redirection/releases)页面中下载最新版本的插件的Jar文件；
2. 安装，插件安装和更新方式可参考：https://docs.halo.run/user-guide/plugins。

## 用法说明

1. 在侧边栏点击插件选项，选中「资源重定向插件」，并进入设置tab页。
2. 选择启用功能，在下面增加重定向配置
3. 点击下面的添加按钮可添加多个重定向配置，点击对应配置右边的`x`按钮可以删除配置

![CleanShot 2024-06-22 at 14.56.32@2x](https://img-gh.flycat.tech/03/main/pg/202406/e9c82f2aeb9b3cceed279aff82018981825.webp)
