package org.apache.pulsar.io.mongodb;

import org.apache.pulsar.common.classification.InterfaceAudience;
import org.apache.pulsar.common.classification.InterfaceStability;

@InterfaceAudience.Public
@InterfaceStability.Stable
public enum SyncType {

    /**
     * Synchronize all data.
     */
    FULL_SYNC("fullSync"),

    /**
     * Synchronize incremental data.
     */
    INCR_SYNC("incrSync");

    private final String value;

    SyncType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
