package com.guigarage.dolphin;

import com.canoo.dolphin.server.event.Topic;

/**
 * Created by hendrikebbers on 11.01.16.
 */
public interface ServerConstants {

    Topic<String> STOCK_UPDATED_TOPIC = Topic.create("stockUpdated");

    Topic<String> STOCK_LOCKED_TOPIC = Topic.create("stockLocked");

    Topic<String> STOCK_UNLOCKED_TOPIC = Topic.create("stockUnlocked");

}
