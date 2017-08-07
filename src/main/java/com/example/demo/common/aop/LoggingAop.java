package com.example.demo.common.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 共通ロギング処理クラス。
 *
 */
@Slf4j
@Aspect
@Component
public class LoggingAop {

    /**
     * ログ出力共通処理。
     * コントローラークラスのログを出力する。
     *
     * @param joinPoint JoinPoint:ポイントカットされた処理。マッチした処理を行う等に使用できる
     * @return result:行った処理の結果オブジェクト
     * @throws Throwable Exception
     */
    @Around("execution(* com.example.demo.controller..*.*(..))")
    public Object logController(ProceedingJoinPoint joinPoint)
            throws Throwable {

        // クラス名#メソッド名
        String target =
            joinPoint.getTarget().getClass().toString()
                + "#"
                + joinPoint.getSignature().getName();

        // Log
        log.info(target + " ***** BEGIN *****");
        log.info(
            target
                + " ***** INPUT PARAMETERS ***** params="
                + ArrayUtils.toString(joinPoint.getArgs()));

        Object result = null;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
            // resultがnullでない場合は内容を出力し、nullの場合はnullと出力する
            if (result != null) {
                // resultがリダイレクトURL（String型）の場合はそのまま出力
                if (result instanceof String) {
                    log.info(target + " ***** OUTPUT ***** " + result);
                } else {
                    log.info(
                        target + " ***** OUTPUT ***** " + result.toString());
                }
            } else {
                log.info(target + " ***** OUTPUT ***** " + null);
            }
        } catch (Throwable e) {
            // サービスからのException
            log.error(target + " ***** SYSTEM ***** ", e);
            throw e;
        } finally {
            log.info(target + " ***** END *****");
        }
        return result;
    }

    /**
     * ログ出力共通処理。
     * サービスクラスのログを出力する。
     *
     * @param joinPoint JoinPoint:ポイントカットされた処理。マッチした処理を行う等に使用できる
     * @return result:行った処理の結果オブジェクト
     * @throws Throwable Exception
     */
    @Around("execution(* com.example.demo.service..*.*(..))")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {

        // クラス名#メソッド名
        String target =
            joinPoint.getTarget().getClass().toString()
                + "#"
                + joinPoint.getSignature().getName();

        // Log
        log.info(target + " ***** BEGIN *****");
        log.info(
            target
                + " ***** INPUT PARAMETERS ***** params="
                + ArrayUtils.toString(joinPoint.getArgs()));

        Object result = null;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
            // resultがnullでない場合は内容を出力し、nullの場合はnullと出力する
            if (result != null) {
                // resultがリダイレクトURL（String型）の場合はそのまま出力
                if (result instanceof String) {
                    log.info(target + " ***** OUTPUT ***** " + result);
                } else {
                    log.info(
                        target + " ***** OUTPUT ***** " + result.toString());
                }
            } else {
                log.info(target + " ***** OUTPUT ***** " + null);
            }
        } catch (Throwable e) {
            log.error(target + " ***** SYSTEM ***** ", e);
            throw e;
        } finally {
            log.info(target + " ***** END *****");
        }
        return result;
    }

}
