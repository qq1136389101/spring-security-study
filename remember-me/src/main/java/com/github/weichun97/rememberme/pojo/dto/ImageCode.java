package com.github.weichun97.rememberme.pojo.dto;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Date;

public class ImageCode implements Serializable {

    private static final long serialVersionUID = 1924526774698862560L;

    private BufferedImage image;

    private String code;

    private Date expireTime;

    public ImageCode(BufferedImage image, String code, Date expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
