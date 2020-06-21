package com.show.qixin.api.common.aspect;

import com.alibaba.fastjson.JSON;
import com.show.qixin.api.common.annotation.ControllerEndpoint;
import com.show.qixin.api.common.bean.LoginUser;
import com.show.qixin.api.common.domain.system.Log;
import com.show.qixin.api.common.utils.AddressUtil;
import com.show.qixin.api.common.utils.IPUtil;
import com.show.qixin.api.system.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class ControllerEndpointAspect extends AspectSupport {

    private Log sysLog = new Log();

    private long startTime;


    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.show.qixin.api.common.annotation.ControllerEndpoint)")
    public void pointcut() {
    }

    /**
     * 环绕通知
     * @param joinPoint
     */
    @Around("pointcut()")
    public Object saveSysLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result=null;
        //开始时间
        startTime=System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取注解
        ControllerEndpoint controllerEndpoint = method.getAnnotation(ControllerEndpoint.class);
        if(controllerEndpoint!=null){
            String operation = controllerEndpoint.operation();
            //注解上的操作描述
            sysLog.setOperation(operation);
        }

        //请求的参数
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        sysLog.setParams("paramName:"+ Arrays.toString(paramNames) +",args:"+ Arrays.toString(args));

        //请求的IP
        HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddr = IPUtil.getIpAddr(request);
        sysLog.setIp(ipAddr);
        //地理位置
        sysLog.setLocation(AddressUtil.getCityInfo(ipAddr));
        //操作人
        LoginUser loginUser= (LoginUser) SecurityUtils.getSubject().getPrincipal();
        sysLog.setUsername(loginUser.getUser().getUsername());
        //添加时间
        sysLog.setCreateTime(new Date());
        //执行目标方法
        result=joinPoint.proceed();
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()\n"
                +"\nresponse:"+postHandle(result));
        //执行耗时
        sysLog.setTime(System.currentTimeMillis()-startTime);
        //保存系统日志
        logService.saveLog(sysLog);

        return result;
    }

    /**
     * 返回数据
     * @param retVal
     * @return
     */
    private String postHandle(Object retVal) {
        if(null == retVal){
            return "";
        }
        return JSON.toJSONString(retVal);
    }




    /**
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point)  {
        //开始时间
        long start = System.currentTimeMillis();
        Object result;
        Method targetMethod = resolveMethod(point);
        ControllerEndpoint annotation = targetMethod.getAnnotation(ControllerEndpoint.class);


        String operation = annotation.operation();

        try {
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)) {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                        RequestContextHolder.getRequestAttributes();
                assert requestAttributes != null;
                HttpServletRequest request = requestAttributes.getRequest();
                logService.saveLog(point, targetMethod, request, operation, start);
            }
            return result;
        } catch (Throwable throwable) {
            String message = throwable.getMessage();
            return ResponseBean.error(message);
        }
    }
    **/


}



