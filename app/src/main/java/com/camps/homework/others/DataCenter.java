package com.camps.homework.others;

import com.camps.frame.others.Constants;
import com.camps.frame.widgets.emoji.beans.EmojiBean;
import com.camps.frame.widgets.emoji.beans.EmojiDataSource;

import java.util.ArrayList;
import java.util.List;


/**
 * 数据初始化类
 */
public class DataCenter {

    public static void init() {
        new Thread(DataCenter::loadEmojis).start();
    }

    public static final List<EmojiDataSource> emojiDataSources = new ArrayList<>();

    /**
     * 加载emoji资源
     */
    public static void loadEmojis() {
        for (int i = 0; i < 2; i++) {
            EmojiDataSource emojiDataSource = new EmojiDataSource();
            List<EmojiBean> typeEmojiBeans = new ArrayList<>();
            if (i == 0) {
                for (int j = 0; j < Constants.TYPE01_EMOJI_NAME.length; j++) {
                    EmojiBean emojiBean = new EmojiBean();
                    emojiBean.setEmojiName(Constants.TYPE01_EMOJI_NAME[j]);
                    emojiBean.setEmojiResource(Constants.TYPE01_EMOJI_DREWABLES[j]);
                    typeEmojiBeans.add(emojiBean);
                }
                emojiDataSource.setEmojiType(Constants.EmojiType.EMOJI_TYPE_01);
            } else {
                for (int j = 0; j < Constants.TYPE02_EMOJI_NAME.length; j++) {
                    EmojiBean emojiBean = new EmojiBean();
                    emojiBean.setEmojiName(Constants.TYPE02_EMOJI_NAME[j]);
                    emojiBean.setEmojiResource(Constants.TYPE02_EMOJI_DREWABLES[j]);
                    typeEmojiBeans.add(emojiBean);
                }
                emojiDataSource.setEmojiType(Constants.EmojiType.EMOJI_TYPE_02);
            }
            emojiDataSource.setEmojiList(typeEmojiBeans);
            emojiDataSources.add(emojiDataSource);
        }
    }




}
