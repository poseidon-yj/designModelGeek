package com.poseidon.design.C45;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-16
 */
public class XmlBeanConfigParser implements BeanConfigParser {

    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        String content = null;
        // TODO:...
        return parse(content);
    }

    @Override
    public List<BeanDefinition> parse(String configContent) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        // TODO:...
        return beanDefinitions;
    }

}
