package com.obdo.data;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

/**
 * Manage connection with the database
 * @param <H> SQLite database open helper. Manage when the application needs to create or upgrade its database.
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.DatabaseHelper
 */
public class DatabaseManager<H extends OrmLiteSqliteOpenHelper> {
    /**
     * SQLite database open helper. Manage when the application needs to create or upgrade its database.
     * @since 12/22/2014
     * @see com.obdo.data.DatabaseHelper
     */
    private H helper;

    /**
     * Create and return an instance of the SQLite (There can be only one connection with the SQLite)
     * @param context Context of the activity that calls it
     * @return SQLite database open helper. Manage when the application needs to create or upgrade its database.
     * @see com.obdo.data.DatabaseHelper
     */
    public H getHelper(Context context) {
        if(helper == null) {
            helper = (H) OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return helper;
    }

    /**
     * Close connection with the database (There can be only one connection with the SQLite)
     * @param helper SQLite database open helper. Manage when the application needs to create or upgrade its database.
     * @see com.obdo.data.DatabaseHelper
     */
    public void releaseHelper(H helper) {
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }
}
