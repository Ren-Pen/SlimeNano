package com.slimenano.plugin;

import com.slimenano.api.annotation.Framework;
import com.slimenano.api.exception.inner.DuplicateMainPluginException;
import com.slimenano.api.logger.Marker;
import com.slimenano.api.plugin.Information;
import com.slimenano.spring.DynamicJarClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.slimenano.utils.Directorys.pluginDir;

@Slf4j
@Marker("插件注册器")
@Framework
public class PluginRegister implements ApplicationListener<ContextRefreshedEvent> {

    private final PluginManager pluginManager;


    public PluginRegister(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext parent = event.getApplicationContext();
        if (parent.getParent() != null) return;


        File[] files = pluginDir.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files == null) {
            log.error("无法读取插件文件夹 plugins");
            return;
        }
        for (File pluginFile : files) {

            DynamicJarClassLoader classLoader = new DynamicJarClassLoader(pluginFile);
            try (InputStream ymlFileSteam = classLoader.getResourceAsStream("plugin.yml")) {
                if (ymlFileSteam == null) {
                    log.error("未加载到插件描述文件，当前插件加载进程终止 文件：{}", pluginFile);
                    try {
                        classLoader.close();
                    } catch (IOException ex) {
                        log.error("关闭文件时出现错误", ex);
                    }
                    continue;
                }

                Yaml yaml = new Yaml();
                Information pluginInfo = yaml.loadAs(ymlFileSteam, Information.class);
                if (pluginManager.containsKey(pluginInfo.getMain())) {
                    throw new DuplicateMainPluginException("插件主类：" + pluginInfo.getMain() + " 重复加载，已加载：" + pluginManager.get(pluginInfo.getMain()).jarFile().getName() + " 冲突：" + pluginFile.getName());
                }


                Class<?> pluginClass = classLoader.loadClass(pluginInfo.getMain());
                GenericApplicationContext context = new AnnotationConfigApplicationContext();
                context.setParent(parent);
                context.setClassLoader(classLoader);
                context.registerBean(pluginClass);
                context.refresh();
                Object plugin = context.getBean(pluginClass);
                pluginManager.put(pluginInfo.getMain(), new PluginMeta(pluginInfo.getMain(), pluginFile, plugin, classLoader, context));

            } catch (IOException | ClassNotFoundException | IllegalArgumentException e) {
                log.error("加载插件文件时出现了错误", e);
                try {
                    classLoader.close();
                } catch (IOException ex) {
                    log.error("关闭文件时出现错误", ex);
                }
            }


        }
    }

}
