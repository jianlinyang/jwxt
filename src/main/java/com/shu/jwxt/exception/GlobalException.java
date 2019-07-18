package com.shu.jwxt.exception;

import com.shu.jwxt.result.CodeMsg;

/**
 * @author yang
 * @date 2019/6/29 9:00
 */
public class GlobalException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    private CodeMsg codeMsg;

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public GlobalException(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }
}
