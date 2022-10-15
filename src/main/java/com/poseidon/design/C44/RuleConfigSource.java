package com.poseidon.design.C44;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-15
 */


public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) throws Exception {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = RuleConfigParserFactory2.createParser(ruleConfigFileExtension);
        if (parser == null) {
            throw new Exception("Rule config file format is not supported: " );
        }

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }


}