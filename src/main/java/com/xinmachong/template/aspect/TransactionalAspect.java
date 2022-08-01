package com.xinmachong.template.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Aspect
@Configuration
@ConditionalOnProperty(value = "database.usable",matchIfMissing = true)
public class TransactionalAspect {

    /**
     * 定义切点路径
     */
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.xinmachong.template.service.*.*(..))";

    private static final int TX_METHOD_TIMEOUT=600;

    @Resource
    private PlatformTransactionManager transactionManager;

    @Resource
    private List<ITransactionAdvice> transactionAdvice;

    @Bean
    public TransactionInterceptor txAdvice() {

        RuleBasedTransactionAttribute requireRule=new RuleBasedTransactionAttribute();
        /*抛出异常后执行切点回滚*/
        requireRule.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        /*PROPAGATION_REQUIRED:事务隔离性为1，若当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。 */
        requireRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        /*设置事务失效时间，如果超过600秒，则回滚事务*/
        requireRule.setTimeout(TX_METHOD_TIMEOUT);

        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //todo:关闭只读操作，后面需要单独处理
//        txAttr_REQUIRED_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        fillExtendTransactionPrefix(source,requireRule,txAttr_REQUIRED_READONLY);
        return new TransactionInterceptor(transactionManager, source);
    }

    /**
     * 添加扩展事务前缀
     * @param source
     */
    private void fillExtendTransactionPrefix(NameMatchTransactionAttributeSource source, RuleBasedTransactionAttribute requireRule, DefaultTransactionAttribute txAttr_REQUIRED_READONLY){
        for (ITransactionAdvice advicePlugin:transactionAdvice) {
            //处理开启事务配置
            List<String> requireTransPrefixes = advicePlugin.openTransactionPrefixes();
            if(requireTransPrefixes != null){
                for (String prefix:requireTransPrefixes) {
                    source.addTransactionalMethod(prefix, requireRule);
                }
            }

            //处理只读事务配置
            List<String> readonlyTransPrefixes = advicePlugin.readonlyTransactionPrefixes();
            if(readonlyTransPrefixes != null){
                for (String prefix:readonlyTransPrefixes) {
                    source.addTransactionalMethod(prefix, txAttr_REQUIRED_READONLY);
                }
            }
        }
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
