/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zolostays.data.source.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.zolostays.data.User;
import com.zolostays.data.source.UsersDataSource;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Concrete implementation of a data source as a db.
 */
public class UsersLocalDataSource implements UsersDataSource {

    private static UsersLocalDataSource INSTANCE;

    private UsersDbHelper mDbHelper;

    private SQLiteDatabase mDb;

    // Prevent direct instantiation.
    @Inject
    public UsersLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new UsersDbHelper(context);
        mDb = mDbHelper.getWritableDatabase();
    }

    public static UsersLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UsersLocalDataSource(context);
        }
        return INSTANCE;
    }

    /**
     * Note: {@link GetUserCallback#onDataNotAvailable()} is fired if the {@link com.zolostays.data.User} isn't
     * found.
     */
    @Override
    public void getTask(@NonNull String phoneNumber, String password, GetUserCallback callback) {
        try {
            String[] projection = {
                    UsersPersistenceContract.UserEntry.COLUMN_NAME_USER_ID,
                    UsersPersistenceContract.UserEntry.COLUMN_NAME_NAME,
                    UsersPersistenceContract.UserEntry.COLUMN_NAME_EMAIL,
                    UsersPersistenceContract.UserEntry.COLUMN_NAME_PHONE_NUMBER,
                    UsersPersistenceContract.UserEntry.COLUMN_NAME_PASSWORD
            };

            String selection = UsersPersistenceContract.UserEntry.COLUMN_NAME_PHONE_NUMBER + " LIKE ? and " + UsersPersistenceContract.UserEntry.COLUMN_NAME_PASSWORD + " LIKE ?";
            String[] selectionArgs = {phoneNumber, password};

            Cursor c = mDb.query(
                    UsersPersistenceContract.UserEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            User user = null;

            if (c != null && c.getCount() > 0) {
                c.moveToFirst();
                String userId = c.getString(c.getColumnIndexOrThrow(UsersPersistenceContract.UserEntry.COLUMN_NAME_USER_ID));
                String name = c.getString(c.getColumnIndexOrThrow(UsersPersistenceContract.UserEntry.COLUMN_NAME_NAME));
                String email = c.getString(c.getColumnIndexOrThrow(UsersPersistenceContract.UserEntry.COLUMN_NAME_EMAIL));
                String strPhoneNumber = c.getString(c.getColumnIndexOrThrow(UsersPersistenceContract.UserEntry.COLUMN_NAME_PHONE_NUMBER));
                String strPassword = c.getString(c.getColumnIndexOrThrow(UsersPersistenceContract.UserEntry.COLUMN_NAME_PASSWORD));

                user = new User(userId, name, strPhoneNumber, email, strPassword);
            }
            if (c != null) {
                c.close();
            }

            if (user != null) {
                callback.onUserLoaded(user);
            } else {
                callback.onDataNotAvailable();
            }
        } catch (IllegalStateException e) {
            // Send to analytics, log etc
        }
    }

    @Override
    public void saveTask(@NonNull User user) {
        try {
            checkNotNull(user);

            ContentValues values = new ContentValues();
            values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_USER_ID, user.getId());
            values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_NAME, user.getName());
            values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_EMAIL, user.getEmail());
            values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_PHONE_NUMBER, user.getPhoneNumber());
            values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_PASSWORD, user.getPassword());

            mDb.insert(UsersPersistenceContract.UserEntry.TABLE_NAME, null, values);
        } catch (IllegalStateException e) {
            // Send to analytics, log etc
        }
    }
}
