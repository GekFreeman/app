package model;

import base.BaseModel;

/**
 * Created by chizhang on 15/4/20.
 */
public class Atys extends BaseModel {

    // model columns ,include user table information
    public final static String COL_ID = "id";
    public final static String COL_USER_NAME = "username";
    public final static String COL_USER_FACE = "userface";
    public final static String COL_TITLE = "title";
    public final static String COL_CONTENT = "content";
    public final static String COL_COMMENT_ID = "commentid";
    public final static String COL_PUBTIME = "pubtime";
    public final static String COL_PICTURE = "picture";
    public final static String COL_JOIN_COUNT = "joincount";
    public final static String COL_LIKE_COUNT = "likecount";

    private String id;
    private String username;
    private String userface;
    private String title;
    private String content;
    private String commentid;
    private String pubtime;
    private String picture;
    private String joincount;
    private String likecount;

    public Atys(String id, String username, String userface, String title, String content,
                String comment_id, String pubtime, String picture,
                String join_count, String like_count) {
        setId(id);
        setUsername(username);
        setUserface(userface);
        setTitle(title);
        setContent(content);
        setCommentid(comment_id);
        setPubtime(pubtime);
        setPicture(picture);
        setJoincount(join_count);
        setLikecount(like_count);

    }

    public Atys() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getJoincount() {
        return joincount;
    }

    public void setJoincount(String joincount) {
        this.joincount = joincount;
    }

    public String getLikecount() {
        return likecount;
    }

    public void setLikecount(String likecount) {
        this.likecount = likecount;
    }
}
