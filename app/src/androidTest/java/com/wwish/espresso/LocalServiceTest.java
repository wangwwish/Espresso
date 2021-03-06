/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wwish.espresso;

import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 test that uses a {@link ServiceTestRule} to interact with a bound service.
 * <p>
 * {@link ServiceTestRule} is a JUnit rule that provides a
 * simplified mechanism to start and shutdown your service before
 * and after the duration of your test. It also guarantees that the service is successfully
 * connected when starting (or binding to) a service. The service can be started
 * (or bound) using one of the helper methods. It will automatically be stopped (or unbound) after
 * the test completes and any methods annotated with
 * <a href="http://junit.sourceforge.net/javadoc/org/junit/After.html"><code>After</code></a> are
 * finished.
 * <p>
 * Note: This rule doesn't support {@link android.app.IntentService} because it's automatically
 * destroyed when {@link android.app.IntentService#onHandleIntent(Intent)} finishes
 * all outstanding commands. So there is no guarantee to establish a successful connection
 * in a timely manner.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)//设置测试运行环境
public class LocalServiceTest {
    //ServiceTestRule类是一个JUnit 4 规则它在你的单元测试方法运行之前启动你的Service，
    // 在测试完成的时候关闭Service。通过使用这个测试规则，你可以确保在你的测试方法运行之前你的Service的连接总是建立的。
    //注意：ServiceTestRule 类不支持测试IntentService对象。如果你需要测试IntentService对象，
    // 你应该在一个独立的类中加入逻辑同时创建一个相匹配的单元测试。
    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();
    //成功绑定到了本地service并且service的返回值正确
    @Test
    public void testWithBoundService() throws TimeoutException {
        // Create the service Intent.
        Intent serviceIntent =
                new Intent(InstrumentationRegistry.getTargetContext(), LocalService.class);

        // Data can be passed to the service via the Intent.
        serviceIntent.putExtra(LocalService.SEED_KEY, 42L);

        // Bind the service and grab a reference to the binder.
        IBinder binder = mServiceRule.bindService(serviceIntent);

        // Get the reference to the service, or you can call public methods on the binder directly.
        LocalService service = ((LocalService.LocalBinder) binder).getService();

        // Verify that the service is working correctly.
        assertThat(service.getRandomInt(), is(any(Integer.class)));
    }
}
