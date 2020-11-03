package com.fpg.api.message;

import lombok.Data;

/**
 * @author fangyh
 * @version 1.0.0
 * @date 2020-11-2
 * @since 1.0.0
 */
@Data
public class Result {
    private ReqHeader reqHeader;
    private ReqBody reqBody;
}
