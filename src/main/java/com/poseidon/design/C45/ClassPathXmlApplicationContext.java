package com.poseidon.design.C45;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-16
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    private BeansFactory beansFactory;
    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.beansFactory = new BeansFactory();
        this.beanConfigParser = new XmlBeanConfigParser();
        loadBeanDefinitions(configLocation);
    }

    private void loadBeanDefinitions(String configLocation) {
        try (InputStream in = this.getClass().getResourceAsStream("/" + configLocation)) {
            if (in == null) {
                throw new RuntimeException("Can not find config file: " + configLocation);
            }
            List<BeanDefinition> beanDefinitions = beanConfigParser.parse(in);
            beansFactory.addBeanDefinitions(beanDefinitions);
        } catch (Exception e) {
            // log
        }
        // TODO: log error
    }

    @Override
    public Object getBeans(String beanId) throws Exception {
        return beansFactory.getBean(beanId);
    }
}
