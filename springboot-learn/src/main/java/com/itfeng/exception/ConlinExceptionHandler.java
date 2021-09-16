//package com.itfeng.exception;
//
//import com.conlin.common.api.vo.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
///**
// * 异常处理器
// *
// * @Author scott
// * @Date 2019
// */
//@RestControllerAdvice
//@Slf4j
//public class ConlinExceptionHandler {
//
//	@ExceptionHandler(NoHandlerFoundException.class)
//	public Result<?> handlerNoFoundException(Exception e) {
//		log.error(e.getMessage(), e);
//		return Result.error(404, "路径不存在，请检查路径是否正确");
//	}
//
//	@ExceptionHandler(DuplicateKeyException.class)
//	public Result<?> handleDuplicateKeyException(DuplicateKeyException e){
//		log.error(e.getMessage(), e);
//		return Result.error("数据库中已存在该记录");
//	}
//
//	@ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
//	public Result<?> handleAuthorizationException(AuthorizationException e){
//		log.error(e.getMessage(), e);
//		return Result.error("没有权限，请联系管理员授权");
//	}
//
//	@ExceptionHandler(Exception.class)
//	public Result<?> handleException(Exception e){
//		log.error(e.getMessage(), e);
//		return Result.error(e.getMessage());
//	}
//
//	/**
//	 * @Author 政辉
//	 * @param e
//	 * @return
//	 */
//	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//	public Result<?> HttpRequestMethodNotSupportedException(Exception e){
//		log.error(e.getMessage(), e);
//		return Result.error("没有权限，请联系管理员授权");
//	}
//
//}
