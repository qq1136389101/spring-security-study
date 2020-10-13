package com.github.weichun97.sms.pojo;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The type Sms code.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsCode {
    private String code;
    private Date expireTime;

    /**
     * 是否过期
     *
     * @return boolean
     */
    public boolean isExpire() {
        return DateUtil.compare(expireTime, new Date()) == -1;
    }
}