/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pulsar.io.mongodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * Configuration class for the MongoDB Sink Connectors.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MongoSinkConfig extends MongoAbstractConfig {

    private static final long serialVersionUID = 8805978990904614084L;

    public static MongoSinkConfig load(String yamlFile) throws IOException {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        final MongoSinkConfig cfg = mapper.readValue(new File(yamlFile), MongoSinkConfig.class);

        return cfg;
    }

    public static MongoSinkConfig load(Map<String, Object> map) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final MongoSinkConfig cfg = mapper.readValue(new ObjectMapper().writeValueAsString(map), MongoSinkConfig.class);

        return cfg;
    }

    @Override
    public void validate() {
        super.validate();
        Preconditions.checkArgument(!StringUtils.isEmpty(getDatabase()),
                "Required MongoDB database name is not set.");
        Preconditions.checkArgument(!StringUtils.isEmpty(getCollection()),
                "Required MongoDB collection name is not set.");
    }
}
