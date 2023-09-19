package day0918.bobing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Gift {
    private int id;
    private String name;
    private String grade;

    @Override
    public String toString() {
        return "恭喜你在本次博饼中骰出了" + this.grade + ", 获得了" + this.name + "!";
    }
}
