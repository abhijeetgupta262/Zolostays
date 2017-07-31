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

package com.zolostays.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zolostays.data.User;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load user from the data sources into a cache.
 */
public class UsersRepository implements UsersDataSource {

    private static UsersRepository INSTANCE = null;

    private final UsersDataSource mTasksLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, User> mCachedTasks;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private UsersRepository(@NonNull UsersDataSource tasksLocalDataSource) {
        mTasksLocalDataSource = checkNotNull(tasksLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param tasksLocalDataSource the device storage data source
     * @return the {@link UsersRepository} instance
     */
    public static UsersRepository getInstance(UsersDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UsersRepository(tasksLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force delete this class instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void saveTask(@NonNull User task) {
        checkNotNull(task);
        mTasksLocalDataSource.saveTask(task);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.put(task.getId(), task);
    }

    /**
     * Gets tasks from local data source (sqlite) unless the table is new or empty. In that case it
     * uses the network data source. This is done to simplify the sample.
     */
    @Override
    public void getTask(@NonNull final String phoneNumber, String password, @NonNull final GetUserCallback callback) {
        checkNotNull(phoneNumber);
        checkNotNull(password);
        checkNotNull(callback);

        User cachedUser = getUserWithPhoneNumber(phoneNumber);

        // Respond immediately with cache if available
        if (cachedUser != null) {
            callback.onUserLoaded(cachedUser);
            return;
        }

        // Load from server/persisted if needed.

        // Is the task in the local data source? If not, query the network.
        mTasksLocalDataSource.getTask(phoneNumber, password, new GetUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                // Do in memory cache update to keep the app UI up to date
                if (mCachedTasks == null) {
                    mCachedTasks = new LinkedHashMap<>();
                }
                mCachedTasks.put(user.getPhoneNumber(), user);
                callback.onUserLoaded(user);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }


    @Nullable
    private User getUserWithPhoneNumber(@NonNull String phoneNumber) {
        checkNotNull(phoneNumber);
        if (mCachedTasks == null || mCachedTasks.isEmpty()) {
            return null;
        } else {
            return mCachedTasks.get(phoneNumber);
        }
    }
}
