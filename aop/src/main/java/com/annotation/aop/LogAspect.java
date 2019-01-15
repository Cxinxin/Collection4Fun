package com.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    /**
     * 通过@Pointcut 指定切入点 ，这里指定的切入点为Log注解类型，也就是被@Log注解修饰的方法，进入该切入点。
     */
    @Pointcut(value = "@annotation(com.annotation.aop.Log)")
    private void pointcut() {

    }


    /**
     * 在方法执行前后
     *
     * @param point
     * @param log
     * @return
     */
    @Around(value = "pointcut() && @annotation(log)")
    public Object around(ProceedingJoinPoint point, Log log) {

        System.out.println("++++执行了around方法++++");

        String requestUrl = log.requestUrl();

        //拦截的类名
        Class clazz = point.getTarget().getClass();
        //拦截的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        System.out.println("执行了 类:" + clazz + " 方法:" + method + " 自定义请求地址:" + requestUrl);

        try {
            return point.proceed(); //执行程序
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }

    /**
     * 方法执行后
     * @param joinPoint
     * @param log
     * @param result
     * @return
     */
    @AfterReturning(value = "pointcut() && @annotation(log)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, Log log, Object result) {

        System.out.println("++++执行了afterReturning方法++++");

        System.out.println("执行结果：" + result);

        return result;
    }


    /**
     * 方法执行后 并抛出异常
     * @param joinPoint
     * @param log
     * @param ex
     */
    @AfterThrowing(value = "pointcut() && @annotation(log)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Log log, Exception ex) {
        System.out.println("++++执行了afterThrowing方法++++");
        System.out.println("请求：" + log.requestUrl() + " 出现异常");
    }



}
