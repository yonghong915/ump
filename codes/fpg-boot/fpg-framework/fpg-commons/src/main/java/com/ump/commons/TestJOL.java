package com.ump.commons;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class TestJOL {
    static class InnerObj {
        String name;
        boolean bB;
        int age;
        char c;
        short s;
        long l;
        byte b;
        double d;
        float f;
    }

    public static void main(String[] args) {
        //InnerObj obj = new InnerObj();
        //String aa = ClassLayout.parseInstance(obj).toPrintable();
        //System.out.println(aa);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int run = compiler.run(null, null, null, "E:\\prd_jar\\com\\ump\\commons\\Test.java");
        System.out.println(run == 0 ? "编译成功" : "编译失败");

        // 通过反射运行编译好的类
        try {
//            File programRootDir = new File("E:\\prd_jar");
//            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
//            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
//            add.setAccessible(true);
//            add.invoke(classLoader, programRootDir.toURI().toURL());
//

            Classpath classPath = new Classpath(System.getProperty("java.class.path"));
            classPath.addClasspath("E:\\prd_jar");

            URL[] urls = new URL[]{new URL("file:/E:/prd_jar/")};
            URLClassLoader loader = new URLClassLoader(urls);
            Class<?> c = loader.loadClass("Test");

            // 调用加载类的main方法
            Method m = c.getMethod("main", String[].class);
            m.invoke(null, (Object) new String[]{});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
