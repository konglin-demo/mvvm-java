package com.mxnavi.hase.hongqi.datarepository.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BeanOfficialAccount {
    @SerializedName("children")
    private List<String> children;
    @SerializedName("courseId")
    private int courseid;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("order")
    private int order;
    @SerializedName("parentChapterId")
    private int parentchapterid;
    @SerializedName("userControlSetTop")
    private boolean usercontrolsettop;
    @SerializedName("visible")
    private int visible;
}
