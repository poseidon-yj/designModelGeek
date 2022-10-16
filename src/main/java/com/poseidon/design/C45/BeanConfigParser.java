package com.poseidon.design.C45;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public interface BeanConfigParser {
    List<BeanDefinition> parse(InputStream inputStream);
    List<BeanDefinition> parse(String configContent);
}


