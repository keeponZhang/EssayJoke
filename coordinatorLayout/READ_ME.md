# CoordinatorLayout 
## 1. CoordinatorLayout 的 behavior 怎么工作的
### 1.1 自定义Behavior 放的全类名
* parseBehavior 解析布局文件中制定的 Behavior
### 1.2 Behavior到底是怎么实例的，调用 parseBehavior 解析我们设置的 layout_behavior , 
* 1.获取设置好的全类名  xxx.xx.xx 还可以是  .xx 
* 2.通过类名获取 class 然后获取两个参数的构造方法
* 3.通过反射创建 Behavior 对象 newInatence() , 把所有的 behavior
 放入了集合 
### 1.3 CoordinatorLayout的调用
* 1.CoordinatorLayout 里面有一个 onNestedScroll
  方法，里面for循环调用子View的Behavior的onNestedScroll的方法
  CoordinatorLayout 自己并没有调用 onNestedScroll方法
* 2.RecyclerView 里面 startNestedScroll -> ViewParentCompat.onStartNestedScroll() -> IMPL.onStartNestedScroll()
-> ViewParentCompatLollipopImpl  
### 1.4  CoordinatorLayout的LayoutParams
* Behavior 是layout_behavior 只能由
  CoordinatorLayout的LayoutParams去解析 
### 1.5 Behavior和NestedScrollingParent2的关系
* Behavior的onNestedPreScroll和onNestedScroll都是在NestedScrollingParent2中的对应方法调用的(循环调用子View的Behavior的onNestedScroll的方法
)



