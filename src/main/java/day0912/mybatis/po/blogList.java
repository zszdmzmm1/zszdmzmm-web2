package day0912.mybatis.po;

import lombok.Data;

@Data
public class blogList {
    int id;
    int userId;
    String title;
    String cover;
    String publishTime;
    String lastUpdateTime;
    String content;
    int view;
    String description;
    int status;
}
