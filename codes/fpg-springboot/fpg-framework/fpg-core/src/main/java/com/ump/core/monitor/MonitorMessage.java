package com.ump.core.monitor;

import lombok.Data;

import java.io.Serializable;

@Data
public class MonitorMessage implements Serializable {
    /**
     *
     */
    private String content;

    /**
     *
     */
    private String monitorTime;
}
