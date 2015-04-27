package sqlite;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import base.BaseSqlite0;
import model.Atys;
import model.Atys;

/**
 * Created by chizhang on 15/4/24.
 */
public class AtySqlite extends BaseSqlite0 {
    public AtySqlite(Context context) {
        super(context);
    }

    @Override
    protected String tableName() {
        return "activities";
    }

    @Override
    protected String[] tableColumns() {
        String[] columns = {
                Atys.COL_ID,
                Atys.COL_USER_FACE,
                Atys.COL_USER_NAME,
                Atys.COL_PUBTIME,
                Atys.COL_PICTURE,
                Atys.COL_TITLE,
                Atys.COL_CONTENT,
                Atys.COL_LIKE_COUNT,
                Atys.COL_JOIN_COUNT,
                Atys.COL_COMMENT_ID,
        };
        return columns;
    }

    @Override
    protected String createSql() {
        return "CREATE TABLE " + tableName() + " (" +
                Atys.COL_ID + " INTEGER PRIMARY KEY, " +
                Atys.COL_USER_FACE + " TEXT, " +
                Atys.COL_USER_NAME + " TEXT, " +
                Atys.COL_PUBTIME + " TEXT, " +
                Atys.COL_PICTURE + " TEXT, " +
                Atys.COL_TITLE + " TEXT, " +
                Atys.COL_CONTENT + " TEXT, " +
                Atys.COL_LIKE_COUNT + " TEXT, " +
                Atys.COL_JOIN_COUNT + " TEXT, " +
                Atys.COL_COMMENT_ID + " TEXT" +
                ");";
    }

    @Override
    protected String upgradeSql() {
        return "DROP TABLE IF EXISTS " + tableName();
    }

    public boolean updateAtys (Atys Atys) {
        // prepare Atys data
        ContentValues values = new ContentValues();
        values.put(Atys.COL_ID, Atys.getId());
        values.put(Atys.COL_USER_FACE, Atys.getUserface());
        values.put(Atys.COL_USER_NAME, Atys.getUsername());
        values.put(Atys.COL_PUBTIME, Atys.getPubtime());
        values.put(Atys.COL_PICTURE, Atys.getPicture());
        values.put(Atys.COL_TITLE, Atys.getTitle());
        values.put(Atys.COL_CONTENT, Atys.getContent());
        values.put(Atys.COL_LIKE_COUNT, Atys.getLikecount());
        values.put(Atys.COL_JOIN_COUNT, Atys.getJoincount());
        values.put(Atys.COL_COMMENT_ID, Atys.getCommentid());
        // prepare sql
        String whereSql = Atys.COL_ID + "=?";
        String[] whereParams = new String[]{Atys.getId()};
        // create or update
        try {
            if (this.exists(whereSql, whereParams)) {
                this.update(values, whereSql, whereParams);
            } else {
                this.create(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public ArrayList<Atys> getAllAtyss () {
        ArrayList<Atys> AtysList = new ArrayList<Atys>();
        try {
            ArrayList<ArrayList<String>> rList = this.query(null, null);
            int rCount = rList.size();
            for (int i = 0; i < rCount; i++) {
                ArrayList<String> rRow = rList.get(i);
                Atys Atys = new Atys();
                Atys.setId(rRow.get(0));
                Atys.setUserface(rRow.get(1));
                Atys.setUsername(rRow.get(2));
                Atys.setPubtime(rRow.get(3));
                Atys.setPicture(rRow.get(4));
                Atys.setTitle(rRow.get(5));
                Atys.setContent(rRow.get(6));
                Atys.setLikecount(rRow.get(7));
                Atys.setJoincount(rRow.get(8));
                Atys.setCommentid(rRow.get(9));
                AtysList.add(Atys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AtysList;
    }
}
