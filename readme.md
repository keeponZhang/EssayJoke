1. support版本是基于27.0.0
2. support-compat 是包含在support-v4下面

3. android.arch.lifecycle：runtime
4. appcompat-v7 依赖 com.android.support：support-annotations；support-compat；support-fragment；collections；cursoradapter；support-core-utils；support-vector-drawable；animated-vector-drawable
5. support-v4 依赖 com.android.support：support-compat；support-fragment；support-core-ui；support-core-utils；support-media-compat
6. support-fragment 依赖 com.android.support：support-compat；support-core-ui；support-core-utils；support-annotations；loader；
7. support-compat 依赖 com.android.support：support-annotations；collections；versionedparcelable
8. support-core-ui 依赖 com.android.support：support-annotations；support-compat；support-core-utils；customview；viewpager；coordinatorlayout；drawerlayout
9. recyclerview-v7 依赖 com.android.support：support-annotations；support-compat；support-core-utils；support-core-ui
10. design 依赖 com.android.support：support-v4；appcompat-v7；recyclerview-v7；transition；
11. support-compat里面又分support-v4和v13
gradlew -q :custom_support_sample:dependencies > dependencies.txt
