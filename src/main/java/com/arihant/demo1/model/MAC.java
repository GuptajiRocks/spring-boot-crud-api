package com.arihant.demo1.model;
import jakarta.persistence.*;

@Entity
@Table(name="validmac")
public class MAC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="macadd")
    private String macadd;

    public MAC() {

    }

    public MAC(String macadd) {
        this.macadd = macadd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMacadd() {
        return macadd;
    }

    public void setMacadd(String macadd) {
        this.macadd = macadd;
    }
}
