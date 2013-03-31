/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.form;

/**
 *
 * @author Mark
 */
public class OrderRequest {
    private String videotitle;
    private Integer videoid;
    private String orderType;

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }
    

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public Integer getVideoid() {
        return videoid;
    }

    public void setVideotitle(String videotitle) {
        this.videotitle = videotitle;
    }

    public String getVideotitle() {
        return videotitle;
    }
}
