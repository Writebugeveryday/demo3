package com.example.demo.util;

import cn.hutool.core.codec.Base62;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.google.common.primitives.Longs;

public class IdWorker {

    Snowflake snowflake;

    public IdWorker(int workerId, int datacenterId) {
        snowflake = IdUtil.createSnowflake(workerId, datacenterId);
    }

    public String nextId() {
        return Base62.encode(Longs.toByteArray(snowflake.nextId()));
    }

    public long nextLong() {
        return snowflake.nextId();
    }

}
