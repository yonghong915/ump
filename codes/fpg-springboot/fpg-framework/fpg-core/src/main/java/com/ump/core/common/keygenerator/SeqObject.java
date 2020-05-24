package com.ump.core.common.keygenerator;

import lombok.Data;

import java.io.Serializable;

@Data
public class SeqObject implements Serializable {
    /**
     *
     */
    private String objName;

    /**
     *
     */
    private Integer objValue;

    /**
     *
     */
    private Integer maxValue;

    /**
     *
     */
    private String objDate;

    /**
     *
     */
    private String zipCacheDate;
}
