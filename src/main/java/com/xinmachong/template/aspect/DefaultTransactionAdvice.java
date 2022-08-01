package com.xinmachong.template.aspect;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 默认事务切面实现类
 */
@Component
@ConditionalOnProperty(value = "database.usable",matchIfMissing = true)
public class DefaultTransactionAdvice implements ITransactionAdvice {

    @Override
    public List<String> openTransactionPrefixes() {
        return Arrays.asList("save*","add*","insert*","delete*","update*","modify*","exec*","set*","tx*");
    }

    @Override
    public List<String> readonlyTransactionPrefixes() {
        return Arrays.asList("get*","query*","find*","list*","count*","is*","select*");
    }
}
