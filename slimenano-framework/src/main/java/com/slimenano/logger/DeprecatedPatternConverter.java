package com.slimenano.logger;


import com.slimenano.utils.ClassUtils;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;

@Plugin(name = "DeprecatedPatternConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({"deprecated"})
public class DeprecatedPatternConverter extends LogEventPatternConverter {

    private static final DeprecatedPatternConverter INSTANCE = new DeprecatedPatternConverter();

    protected DeprecatedPatternConverter() {
        super("deprecated", "deprecated");
    }

    public static DeprecatedPatternConverter newInstance(final String[] options) { return INSTANCE; }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null){
                loader = this.getClass().getClassLoader();
            }
            Class<?> aClass = loader.loadClass(event.getSource().getClassName());
            if(ClassUtils.getAnnotation(aClass, Deprecated.class) != null) {
                toAppendTo.append("[δΈεζ―ζ]");
            }
        } catch (ClassNotFoundException ignore) {

        }
    }
}
