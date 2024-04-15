package cn.edu.zucc.jpa.qa.Bean;

public class RankBean {
    int id;
    String name;
    double time = 0;

    public int getId() {
        return id;
    }
    public RankBean(){
    }
    public RankBean( int id, String name, double time){
        this.id = id;
        this.name = name;
        this.time = time;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

}
