/*
 *  Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.alibaba.fescar.config;

import java.util.concurrent.ExecutorService;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The type File configuration test.
 *
 * @Author: jimin.jm @alibaba-inc.com
 * @Project: feats -all
 * @DateTime: 2019 /1/24 1:31 PM
 * @FileName: FileConfigurationTest
 * @Description:
 */
public class FileConfigurationTest {
    private final Config config;
    private final Configuration fileConfig = new FileConfiguration("file.conf");
    private static final String INT_DATAID = "transport.thread-factory.client-selector-thread-size";
    private static final String LONG_DATAID = "transport.thread-factory.worker-thread-size";
    private static final String BOOLEAN_DATAID = "service.disable";
    private static final String STRING_DATAID = "transport.type";
    private static final String PUT_DATAID = "transport.mock";
    private static final String NOT_EXIST_DATAID = "service.yyy.xxx";

    public FileConfigurationTest() {
        config = ConfigFactory.load("file.conf");
    }

    /**
     * Test get int.
     */
    @Test
    public void testGetInt() {
        Assert.assertEquals(fileConfig.getInt(INT_DATAID), config.getInt(INT_DATAID));
        Assert.assertEquals(fileConfig.getInt(NOT_EXIST_DATAID), 0);
    }

    /**
     * Test get int 1.
     */
    @Test
    public void testGetInt1() {
        Assert.assertEquals(fileConfig.getInt(INT_DATAID, 999), config.getInt(INT_DATAID));
        Assert.assertEquals(fileConfig.getInt(NOT_EXIST_DATAID, 999), 999);
    }

    /**
     * Test get int 2.
     */
    @Test
    public void testGetInt2() {
        Assert.assertEquals(fileConfig.getInt(INT_DATAID, 999, 1000), config.getInt(INT_DATAID));
        Assert.assertEquals(fileConfig.getInt(NOT_EXIST_DATAID, 999, 1000), 999);
    }

    /**
     * Test get long.
     */
    @Test
    public void testGetLong() {
        Assert.assertEquals(fileConfig.getLong(LONG_DATAID), config.getLong(LONG_DATAID));
        Assert.assertEquals(fileConfig.getLong(NOT_EXIST_DATAID), 0);
    }

    /**
     * Test get long 1.
     */
    @Test
    public void testGetLong1() {
        Assert.assertEquals(fileConfig.getLong(LONG_DATAID, 999L), config.getLong(LONG_DATAID));
        Assert.assertEquals(fileConfig.getLong(NOT_EXIST_DATAID, 999L), 999L);
    }

    /**
     * Test get long 2.
     */
    @Test
    public void testGetLong2() {
        Assert.assertEquals(fileConfig.getLong(LONG_DATAID, 999L, 1000), config.getLong(LONG_DATAID));
        Assert.assertEquals(fileConfig.getLong(NOT_EXIST_DATAID, 999L, 1000), 999L);
    }

    /**
     * Test get boolean.
     */
    @Test
    public void testGetBoolean() {
        Assert.assertEquals(fileConfig.getBoolean(BOOLEAN_DATAID), config.getBoolean(BOOLEAN_DATAID));
        Assert.assertEquals(fileConfig.getBoolean(NOT_EXIST_DATAID), false);
    }

    /**
     * Test get boolean 1.
     */
    @Test
    public void testGetBoolean1() {
        Assert.assertEquals(fileConfig.getBoolean(BOOLEAN_DATAID, true), config.getBoolean(BOOLEAN_DATAID));
        Assert.assertEquals(fileConfig.getBoolean(NOT_EXIST_DATAID, false), false);
    }

    /**
     * Test get boolean 2.
     */
    @Test
    public void testGetBoolean2() {
        Assert.assertEquals(fileConfig.getBoolean(BOOLEAN_DATAID, true, 1000), config.getBoolean(BOOLEAN_DATAID));
        Assert.assertEquals(fileConfig.getBoolean(NOT_EXIST_DATAID, false, 1000), false);
    }

    /**
     * Test get config.
     */
    @Test
    public void testGetConfig() {
        Assert.assertEquals(fileConfig.getConfig(STRING_DATAID), config.getString(STRING_DATAID));
        Assert.assertEquals(fileConfig.getConfig(NOT_EXIST_DATAID), null);
    }

    /**
     * Test get config 1.
     */
    @Test
    public void testGetConfig1() {
        Assert.assertEquals(fileConfig.getConfig(STRING_DATAID, 1000), config.getString(STRING_DATAID));
        Assert.assertEquals(fileConfig.getConfig(NOT_EXIST_DATAID, 1000), null);
    }

    /**
     * Test get config 2.
     */
    @Test
    public void testGetConfig2() {
        Assert.assertEquals(fileConfig.getConfig(STRING_DATAID, "123"), config.getString(STRING_DATAID));
        Assert.assertEquals(fileConfig.getConfig(NOT_EXIST_DATAID, "123"), "123");
    }

    /**
     * Test get config 3.
     */
    @Test
    public void testGetConfig3() {
        Assert.assertEquals(fileConfig.getConfig(STRING_DATAID, "123", 1000), config.getString(STRING_DATAID));
        Assert.assertEquals(fileConfig.getConfig(NOT_EXIST_DATAID, "123", 1000), "123");
    }

    /**
     * Test put config.
     */
    @Test
    public void testPutConfig() {
        Assert.assertTrue(fileConfig.putConfig(PUT_DATAID, "123"));
    }

    /**
     * Test put config 1.
     */
    @Test
    public void testPutConfig1() {
        Assert.assertTrue(fileConfig.putConfig(PUT_DATAID, "123", 5000));
    }

    /**
     * Test put config if absent.
     */
    @Test
    public void testPutConfigIfAbsent() {
        Assert.assertTrue(fileConfig.putConfigIfAbsent(PUT_DATAID, "123"));
    }

    /**
     * Test put config if absent 1.
     */
    @Test
    public void testPutConfigIfAbsent1() {
        Assert.assertTrue(fileConfig.putConfigIfAbsent(PUT_DATAID, "123", 5000));
    }

    /**
     * Test remove config.
     */
    @Test
    public void testRemoveConfig() {
        Assert.assertTrue(fileConfig.removeConfig(PUT_DATAID));
    }

    /**
     * Test remove config 1.
     */
    @Test
    public void testRemoveConfig1() {
        Assert.assertTrue(fileConfig.removeConfig(PUT_DATAID, 5000));
    }

    /**
     * Test add config listener.
     *
     * @param listener the listener
     */
    @Test(dataProvider = "listenerProvider")
    public void testAddConfigListener(ConfigChangeListener listener) {
        fileConfig.addConfigListener(INT_DATAID, listener);
        Assert.assertEquals(fileConfig.getConfigListeners(INT_DATAID).size(), 1);
    }

    /**
     * Test remove config listener.
     *
     * @param listener the listener
     */
    @Test(dataProvider = "listenerProvider")
    public void testRemoveConfigListener(ConfigChangeListener listener) {
        int currSize = fileConfig.getConfigListeners(INT_DATAID).size();
        fileConfig.addConfigListener(INT_DATAID, listener);
        fileConfig.removeConfigListener(INT_DATAID, listener);
        Assert.assertEquals(fileConfig.getConfigListeners(INT_DATAID).size(), currSize);
    }

    /**
     * Listener provider object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider
    public static Object[][] listenerProvider() {
        ConfigChangeListener listener = new ConfigChangeListener() {
            @Override
            public ExecutorService getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.print(configInfo);
            }
        };
        return new Object[][] {{listener}};
    }
}