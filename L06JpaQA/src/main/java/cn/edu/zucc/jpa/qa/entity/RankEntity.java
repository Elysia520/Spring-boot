package cn.edu.zucc.jpa.qa.entity;

import javax.persistence.*;

@Entity
@Table(name = "rank", schema = "js", catalog = "")
public class RankEntity {
    @Id
    @Column(name = "rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int rid;
    public int getRid() {
        return rid;
    }
    public void setRid(int rid) {
        this.rid = rid;
    }
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "time")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    double score;
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
