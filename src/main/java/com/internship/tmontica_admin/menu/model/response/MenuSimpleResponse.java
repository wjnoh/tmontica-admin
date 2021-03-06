package com.internship.tmontica_admin.menu.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuSimpleResponse {
    private int id;
    private String nameEng;
    private String nameKo;
    private String imgUrl;
    private int stock;

    public void setImgUrl(String imgUrl){
        this.imgUrl = "/images/".concat(imgUrl);
    }
}
