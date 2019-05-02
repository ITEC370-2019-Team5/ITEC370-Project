package com.mygdx.game;

public class Computer{
    private String name;
    private String ip;
    private String ipv6;
    private String mask;
    private String gateway;

    Computer(String name, String ip, String ipv6, String mask, String gateway)
    {
        this.name = name;
        this.ip = ip;
        this.ipv6 = ipv6;
        this.mask = mask;
        this.gateway = gateway;
    }
    String getip()
    {
        return ip;
    }
    String getipv6()
    {
        return ipv6;
    }
    String getMask()
    {
        return mask;
    }
    String getGateway()
    {
        return gateway;
    }
    String getName()
    {
        return name;
    }
    void setip(String input)
    {
        this.ip = input;
    }
    void setipv6(String input)
    {
        this.ipv6 = input;
    }
    void setMask(String input)
    {
        this.mask = input;
    }
    void setGateway(String input)
    {
        this.gateway = input;
    }
    public void setName(String input)
    {
        this.name = input;
    }
}