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

import java.util.List;


/**
 * Main entry point for accessing users data.
 */
public interface UsersDataSource {

    interface GetUserCallback {

        void onUserLoaded(User user);

        void onDataNotAvailable();
    }

    @Nullable
    void getUser(@NonNull String phoneNumber, String password, GetUserCallback getUserCallback);

    @Nullable
    void getUser(@NonNull String phoneNumber, GetUserCallback getUserCallback);

    void saveTask(@NonNull User user);

}
