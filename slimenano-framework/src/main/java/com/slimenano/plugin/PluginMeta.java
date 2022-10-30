package com.slimenano.plugin;


import org.springframework.context.support.GenericApplicationContext;

import java.io.File;

/**
 * 插件元数据
 * @param main 插件类路径
 * @param pluginLoader 插件加载器
 * @param plugin 插件对象
 * @param jarFile 插件包文件
 */
public record PluginMeta (
        String main,
        File jarFile,
        Object plugin,
        ClassLoader pluginLoader,
        GenericApplicationContext context
) { }
