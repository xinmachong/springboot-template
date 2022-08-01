package com.xinmachong.template.aspect;

import java.util.List;

/**
 * 事务切面插件
 */
public interface ITransactionAdvice {

    /**
     * 默认需要打开事务的前缀
     * @return
     */
    List<String> openTransactionPrefixes();

    /**
     * 只读事务前缀
     * @return
     */
    List<String> readonlyTransactionPrefixes();

}
