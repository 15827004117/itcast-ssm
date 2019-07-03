package com.ssm.domain;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *  订单信息
 */
import java.util.Date;
import java.util.List;

public class Orders {

    private String id;
    private String orderNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;  // 下单时间

    private int orderStatus; // 订单状态（0未支付 1已支付）
    private String orderStatusStr;
    private int peopleCount; // 出行人数
    private Product product;
    private List<Traveller> travellers;
    private Member member;
    private Integer payType; // 支付方式（0支付宝 1微信 2其他）
    private String payTypeStr;
    private String orderDesc;

    private Integer productId; //产品id
    private Integer memberId;  //会员id

    public String getOrderStatusStr() {
        if(orderStatus == 0){
            orderStatusStr = "未支付";
        }
        if(orderStatus == 1){
            orderStatusStr = "已支付";
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        if(payType == 0){
            payTypeStr = "支付宝";
        }
        if(payType == 1){
            payTypeStr = "微信";
        }
        if(payType == 2){
            payTypeStr = "其他";
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
