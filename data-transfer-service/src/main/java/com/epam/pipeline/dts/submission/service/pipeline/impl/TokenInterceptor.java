/*
 * Copyright 2017-2019 EPAM Systems, Inc. (https://www.epam.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.pipeline.dts.submission.service.pipeline.impl;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenInterceptor implements Interceptor {

    private final String token;

    /**
     * Adds an authorization header with the provided {@link #token} on the request
     * or response.
     * @see Interceptor
     */
    @Override
    public Response intercept(final Interceptor.Chain chain) throws IOException {
        final Request original = chain.request();
        final Request request = original.newBuilder()
                .header("Authorization", String.format("Bearer %s", token))
                .build();
        return chain.proceed(request);
    }
}
