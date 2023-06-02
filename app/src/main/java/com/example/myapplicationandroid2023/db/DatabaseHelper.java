package com.example.myapplicationandroid2023.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "prog20db";
    private static final int DATABASE_VERSION = 1;

    private static final String ARTICLE_TABLE = "article";
    private static final String TAG_TABLE = "tag";
    private static final String ARTICLE_TAG_TABLE = "article_tag";

    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private static final String KEY_STATUS = "status";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_NAME = "name";
    private static final String KEY_ARTICLE_ID = "article_id";
    private static final String KEY_TAG_ID = "tag_id";

    private static final String LOG = "DatabaseHelper";

    private static final String CREATE_ARTICLE_TABLE =
        "CREATE TABLE IF NOT EXISTS "+ARTICLE_TABLE+"("+
            KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_TITLE+" TEXT,"+
            KEY_URL+" TEXT,"+
            KEY_STATUS+" INTEGER,"+
            KEY_CREATED_AT+" DATETIME"+
        ")";

    private static final String CREATE_TAG_TABLE =
        "CREATE TABLE IF NOT EXISTS "+TAG_TABLE+"("+
            KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_NAME+" TEXT"+
        ")";

    private static final String CREATE_ARTICLE_TAG_TABLE =
        "CREATE TABLE IF NOT EXISTS "+ARTICLE_TAG_TABLE+"("+
            KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_ARTICLE_ID+" INTEGER,"+
            KEY_TAG_ID+" INTEGER"+
        ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TAG_TABLE);
        db.execSQL(CREATE_ARTICLE_TABLE);
        db.execSQL(CREATE_ARTICLE_TAG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ARTICLE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TAG_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ ARTICLE_TAG_TABLE);
        this.onCreate(db);
    }

    public long linkArticleTag(long article_id, long tag_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ARTICLE_ID, article_id);
        values.put(KEY_TAG_ID, tag_id);
        return db.insert(ARTICLE_TAG_TABLE, null, values);
    }

    public Article createArticle(String title, String url, int status, long[] tag_ids) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Article article = new Article(title, url, status);
        values.put(KEY_TITLE, article.title);
        values.put(KEY_URL, article.url);
        values.put(KEY_STATUS, article.status);
        values.put(KEY_CREATED_AT, this.getDateTime());
        article.id = (int) db.insert(ARTICLE_TABLE, null, values);

        for (long tag_id : tag_ids)
            this.linkArticleTag(article.id, tag_id);
        return article;
    }

    public Tag createTag(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Tag tag = new Tag(name);
        values.put(KEY_NAME, tag.name);
        tag.id = (int) db.insert(TAG_TABLE, null, values);

        return tag;
    }

    public Article getArticle(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
            "SELECT * FROM "+ARTICLE_TABLE+" "+
            "WHERE "+KEY_ID+" = "+id;

        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null) cursor.moveToFirst();

        Article article = new Article();
        article.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
        article.title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
        article.url = cursor.getString(cursor.getColumnIndex(KEY_URL));
        article.status = cursor.getInt(cursor.getColumnIndex(KEY_STATUS));
        article.created_at = cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT));

        return article;
    }

    public void close() {
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen())
            db.close();
    }

    public List<Article> getAllArticles(String tagName) {
        List<Article> articles = new ArrayList<Article>();
        String query;
        if(tagName=="")
            query = "SELECT * FROM "+ARTICLE_TABLE;
        else
            query =
                "SELECT * FROM "+
                ARTICLE_TABLE+" at, "+
                TAG_TABLE+" tt, "+
                ARTICLE_TAG_TABLE+" att "+
                "WHERE tt."+KEY_NAME+" = '"+tagName+"' "+
                "AND tt."+KEY_ID+" = att."+KEY_TAG_ID+" "+
                "AND at."+KEY_ID+" = att."+KEY_ARTICLE_ID;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
            do {
                Article article = new Article();
                article.id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                article.title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
                article.url = cursor.getString(cursor.getColumnIndex(KEY_URL));
                article.status = cursor.getInt(cursor.getColumnIndex(KEY_STATUS));
                article.created_at = cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT));
                articles.add(article);
            } while(cursor.moveToNext());
        return articles;
    }

    public int updateArticle(Article article) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, article.title);
        values.put(KEY_URL, article.url);
        values.put(KEY_STATUS, article.status);

        return db.update(ARTICLE_TABLE, values, KEY_ID+" = ?", new String[]{String.valueOf(article.id)});
    }

    public int removeArticle(long articleId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ARTICLE_TABLE, KEY_ID+" = ?", new String[]{String.valueOf(articleId)});
    }

    public int removeTag(Tag tag, boolean removeArticles) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(removeArticles) {
            List<Article> articles = this.getAllArticles(tag.name);
            for(Article article: articles)
                this.removeArticle(article.id);
        }
        return db.delete(TAG_TABLE, KEY_ID+" = ?", new String[]{String.valueOf(tag.id)});
    }

    private String getDateTime() {
        DateFormat datetime = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return datetime.format(date);
    }
}
