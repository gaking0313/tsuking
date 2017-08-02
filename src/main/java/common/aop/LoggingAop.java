package common.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * AOPクラス.
 * <pre>
 * [概要]
 * 共通ロギング処理クラス。
 * [備考]
 * なし。
 * </pre>
 * 
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggingAop {

	/**
	 * Log Aspect.
	 * <pre>
	 * [概要]
	 * ログ出力共通処理。
	 * ポイントカットされた時に、開始と終了のログを出力する。
	 * [備考]
	 * ポイントカット = "com.example.demo.controller.*.*(..))"の部分。
	 * 指定されている内容にマッチした場合、処理を行う。
	 * コントローラークラスのログを出力する。
	 * </pre>
	 * @param joinPoint JoinPoint:ポイントカットされた処理。マッチした処理を行う等に使用できる
	 * @return result:行った処理の結果オブジェクト
	 * @throws Throwable Exception
	 */
	@Around("execution(* com.example.demo.controller.*.*(..))")
	public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {

		// クラス名#メソッド名
		String target = joinPoint.getTarget().getClass().toString() + "#" + joinPoint.getSignature().getName();

		// Log
		log.info(target + " ***** BEGIN *****");
		log.info(target + " ***** INPUT PARAMETERS ***** params=" + ArrayUtils.toString(joinPoint.getArgs()));

		Object result = null;
		try {
			result = joinPoint.proceed(joinPoint.getArgs());
			// resultがnullでない場合は内容を出力し、nullの場合はnullと出力する
			if (result != null) {
				// resultがリダイレクトURL（String型）の場合はそのまま出力
				if (result instanceof String) {
					log.info(target + " ***** OUTPUT ***** " + result);
				} else {
					log.info(target + " ***** OUTPUT ***** " + result.toString());
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

}
