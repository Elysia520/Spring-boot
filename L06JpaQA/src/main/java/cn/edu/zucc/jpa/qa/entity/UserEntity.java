package cn.edu.zucc.jpa.qa.entity;

import javax.persistence.*;
@Entity
@Table(name = "user", schema = "js", catalog = "")
public class UserEntity {
    @Basic
    @Column(name = "name")
    String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "password")
    String password;
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    @Basic
    @Column(name = "type")
    String type;
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    @Lob
    @Column(name = "img")
    private byte[] img;
    public byte[] getImg() {
        return img;
    }
    public void setImg(byte[] img) {
        this.img = img;
    }
}
