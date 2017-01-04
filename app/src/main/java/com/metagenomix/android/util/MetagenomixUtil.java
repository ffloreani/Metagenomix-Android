package com.metagenomix.android.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by filipfloreani on 14/12/2016.
 */

public class MetagenomixUtil {

    public static long getRandomLongWithLimit(int limit) {
        return ThreadLocalRandom.current().nextLong(limit);
    }
}
