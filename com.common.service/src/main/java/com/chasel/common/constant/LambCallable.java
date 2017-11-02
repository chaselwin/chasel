package com.chasel.common.constant;

import com.chasel.common.exception.DuplicateRecordException;

/**
 * Lambda表达式，没有返回值
 * 
 * @author chasel
 *
 */
public interface LambCallable {

	Object run() throws DuplicateRecordException;

}
