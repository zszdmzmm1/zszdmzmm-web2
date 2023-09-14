package day0912.mybatis.po;

import lombok.Data;

@Data
public class BlogList {
    private int id;
    private int userId;
    private String title;
    private String cover;
    private String publishTime;
    private String lastUpdateTime;
    private String content;
    private int view;
    private String description;
    private int status;
}
