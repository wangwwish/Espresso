# Espresso
单元测试
集成测试
Multi-Process Espresso only works on API level 26 and above

基础
获得View
* 
* withId方式

onView(withId(R.id.my_view))

* 
* withText方式

onView(withText("Hello World!"))

执行View行为
* 
* 点击

onView(...).perform(click());

* 
* 文本内容输入

onView(...).perform(typeText("Hello"), click());

* 
* scrollTo 滑动

onView(...).perform(scrollTo(), click());

检验View
* 
* 检验View的文本内容

onView(...).check(matches(withText("Hello!")));

* 
* 检验View的显示状态

onView(...).check(matches(isDisplayed()));

https://developer.android.com/training/testing/espresso/index.html#packages
https://developer.android.google.cn/reference/android/support/test/rule/ActivityTestRule.html#runOnUiThread(java.lang.Runnable)
https://github.com/googlesamples/android-testing
