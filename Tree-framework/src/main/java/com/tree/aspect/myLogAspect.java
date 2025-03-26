package com.tree.aspect;


import com.alibaba.fastjson.JSON;
import com.tree.annotation.mySystemLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @author 35238
 * @date 2023/7/30 0030 21:37
 */

@Component
@Aspect
@Slf4j
public class myLogAspect {

    // 定义切点，匹配被 @mySystemlog 注解标注的方法
    @Pointcut("@annotation(com.tree.annotation.mySystemLog)")
    public void xxpt() {
    }

    // 环绕通知，在切点方法执行前后记录日志
    @Around("xxpt()")
    public Object xxprintLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            log.info("=======================end=======================" + System.lineSeparator());
        }
        return ret;
    }

    // 处理方法执行前的日志记录
    private void handleBefore(ProceedingJoinPoint joinPoint) {
        //首先，通过 RequestContextHolder.getRequestAttributes() 获取当前请求的属性信息，
        // 并将其转换为 ServletRequestAttributes 类型。然后，从 ServletRequestAttributes 中获取 HttpServletRequest 对象，
        // 以便获取请求的详细信息，如 URL、请求方式、IP 地址等。
        //接着，调用 getSystemLog(joinPoint) 方法获取被增强方法上的 @mySystemLog 注解对象，从而获取接口的描述信息。
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        mySystemLog systemlog = getSystemLog(joinPoint);

        log.info("======================Start======================");
        log.info("请求URL   : {}", request.getRequestURL());
        log.info("接口描述   : {}", systemlog.xxbusinessName());
        log.info("请求方式   : {}", request.getMethod());
        log.info("请求类名   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), ((MethodSignature) joinPoint.getSignature()).getName());
        log.info("访问IP    : {}", request.getRemoteHost());
        log.info("传入参数   : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    // 处理方法执行后的日志记录
    private void handleAfter(Object ret) {
        log.info("返回参数   : {}", JSON.toJSONString(ret));
    }

    // 获取被增强方法的 @mySystemlog 注解对象
//    首先，通过 joinPoint.getSignature() 获取被增强方法的签名，并将其转换为 MethodSignature 类型。
//    MethodSignature 是 Signature 的一个子接口，它提供了与方法签名相关的操作，如获取方法名、参数类型等。
//    然后，从 MethodSignature 中获取方法对象（methodSignature.getMethod()），
//    并调用 getAnnotation(mySystemLog.class) 方法获取该方法上的 @mySystemLog 注解对象。
//    最后，将获取到的注解对象返回，以便在其他方法中使用注解的属性信息，如获取接口描述等。
    private mySystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(mySystemLog.class);
    }
}
