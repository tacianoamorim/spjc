package br.ufrpe.framework.transaction;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TransactionProxy implements MethodInterceptor {
    /**
     * Cria uma nova instancia do proxy e pluga ela no objeto de negocio.
     */
    public static Object getInstance(Class<?> classeNegocio) {
        //cria o objeto do CGLIB que efetivamente pluga o proxy
        Enhancer e = new Enhancer();
        //configura o proxy para interceptar o objeto de negocio
        e.setSuperclass(classeNegocio);
        //cria uma nova instancia do print proxy para receptar as chamadas
        e.setCallback(new TransactionProxy());
        //cria uma nova instancia do objeto de negocio com o proxy plugado nela
        return e.create();

    }

    /**
     * Intercepta a chamada para qualquer objeto.
     */
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws
        Throwable, Exception {
        //resultado da execu��o do m�todo interceptado
        Object result = null;
        TransactionManager tm = TransactionManager.instance();
        try {
            //fazer um start Transaction
            tm.startTransaction();
            //executar o m�todo interceptado
            result = proxy.invokeSuper(obj, args);
        } catch (Throwable ex) {
            //fazer um set roll back only
            tm.setRollBackOnly();
            throw ex;
        } finally {
            //fazer um finish Transaction
            tm.finishTransaction();
        }
        //chama o m�todo do objeto interceptado
        return result;

    }

}