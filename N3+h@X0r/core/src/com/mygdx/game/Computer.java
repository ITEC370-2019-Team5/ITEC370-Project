package com.mygdx.game;

public class Computer{
    private String name;
    private String ip;
    private String ipv6;
    private String mask;
    private String gateway;

    public Computer(String name, String ip, String ipv6, String mask, String gateway)
    {
        this.name = name;
        this.ip = ip;
        this.ipv6 = ipv6;
        this.mask = mask;
        this.gateway = gateway;
    }
    public String getip()
    {
        return ip;
    }
    public String getipv6()
    {
        return ipv6;
    }
    public String getMask()
    {
        return mask;
    }
    public String getGateway()
    {
        return gateway;
    }public String getName()
    {
        return name;
    }
    public void setip(String input)
    {
        this.ip = input;
    }
    public void setipv6(String input)
    {
        this.ipv6 = input;
    }
    public void setMask(String input)
    {
        this.mask = input;
    }
    public void setGateway(String input)
    {
        this.gateway = input;
    }
    public void setName(String input)
    {
        this.name = input;
    }
}
