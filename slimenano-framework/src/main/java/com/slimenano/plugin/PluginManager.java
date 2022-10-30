package com.slimenano.plugin;

import com.slimenano.api.annotation.Framework;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 插件管理器，用于获取当前载入的插件
 */
@Framework
public class PluginManager extends ConcurrentHashMap<String, PluginMeta> {


}
