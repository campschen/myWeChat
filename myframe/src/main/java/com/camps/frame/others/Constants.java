package com.camps.frame.others;

import com.camps.frame.R;


public class Constants {
    public static final String EMOJI_REGEX = "\\[([\u4e00-\u9fa5\\w])+\\]|[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]";

    public static final String URL_REGEX = "(((http|https)://)|((?<!((http|https)://))www\\.))" + ".*?" + "(?=(&nbsp;|[\\u4e00-\\u9fa5]|\\s|　|<br />|$|[<>]))";

    public static final String TOPIC_REGEX = "#[\\p{Print}\\p{InCJKUnifiedIdeographs}&&[^#]]+#";

    public static final String AT_REGEX = "@[\u4e00-\u9fa5a-zA-Z0-9_-·\\.]+[\u200B]";

    public static final String SCHEME_URL = "com.kcrason.url//";

    public static final String SCHEME_EMOJI = "com.kcrason.emoji//";

    public static final String SCHEME_TOPIC = "com.kcrason.topic//";

    public static final String SCHEME_AT = "com.kcrason.at//";

    public static final String BLUE = "#ff0000";


    public static String[] TYPE02_EMOJI_NAME = {"[emoji_02_angel]",
            "[emoji_02_angry]", "[emoji_02_astonished]", "[emoji_02_astonished_1]", "[emoji_02_confused]", "[emoji_02_cool]", "[emoji_02_cool_1]", "[emoji_02_cry]",
            "[emoji_02_cry_1]", "[emoji_02_devil]", "[emoji_02_dizzy]", "[emoji_02_expressionless]",
            "[emoji_02_flushed]", "[emoji_02_happy]", "[emoji_02_happy_1]",
            "[emoji_02_happy_2]", "[emoji_02_in_love]", "[emoji_02_injury]", "[emoji_02_joy]", "[emoji_02_kiss]", "[emoji_02_kiss_1]",
            "[emoji_02_kiss_2]", "[emoji_02_mask]", "[emoji_02_mute]", "[emoji_02_neutral]",
            "[emoji_02_sad]", "[emoji_02_sad_1]", "[emoji_02_scared]", "[emoji_02_scared_1]", "[emoji_02_secret]", "[emoji_02_shocked]", "[emoji_02_sick]",
            "[emoji_02_sleeping]", "[emoji_02_smile]", "[emoji_02_smile_1]", "[emoji_02_smiling]", "[emoji_02_smiling_1]",
            "[emoji_02_smirking]", "[emoji_02_surprised]", "[emoji_02_sweat]", "[emoji_02_thinking]", "[emoji_02_tired]", "[emoji_02_tongue]",
            "[emoji_02_tongue_1]", "[emoji_02_tongue_2]", "[emoji_02_vomiting]", "[emoji_02_wink]"};


    public static String[] TYPE01_EMOJI_NAME = {
            "[emoji_01_angry]", "[emoji_01_angry_1]", "[emoji_01_bored]", "[emoji_01_bored_1]",
            "[emoji_01_bored_2]", "[emoji_01_confused]", "[emoji_01_confused_1]", "[emoji_01_crying]",
            "[emoji_01_crying_1]", "[emoji_01_embarrassed]", "[emoji_01_emoticons]", "[emoji_01_happy]",
            "[emoji_01_happy_1]", "[emoji_01_happy_2]", "[emoji_01_happy_3]", "[emoji_01_happy_4]",
            "[emoji_01_ill]", "[emoji_01_in_love]", "[emoji_01_kissing]", "[emoji_01_mad]",
            "[emoji_01_nerd]", "[emoji_01_ninja]", "[emoji_01_quiet]", "[emoji_01_sad]", "[emoji_01_secret]",
            "[emoji_01_smart]", "[emoji_01_smile]", "[emoji_01_smiling]", "[emoji_01_surprised]",
            "[emoji_01_surprised_1]", "[emoji_01_suspicious]", "[emoji_01_suspicious_1]", "[emoji_01_tongue_out]",
            "[emoji_01_tongue_out_1]", "[emoji_01_unhappy]", "[emoji_01_wink]"
    };


    public static int[] TYPE01_EMOJI_DREWABLES = {
            R.drawable.emoji_01_angry, R.drawable.emoji_01_angry_1, R.drawable.emoji_01_bored,
            R.drawable.emoji_01_bored_1, R.drawable.emoji_01_bored_2, R.drawable.emoji_01_confused,
            R.drawable.emoji_01_confused_1, R.drawable.emoji_01_crying, R.drawable.emoji_01_crying_1,
            R.drawable.emoji_01_embarrassed, R.drawable.emoji_01_emoticons, R.drawable.emoji_01_happy,
            R.drawable.emoji_01_happy_1, R.drawable.emoji_01_happy_2, R.drawable.emoji_01_happy_3,
            R.drawable.emoji_01_happy_4, R.drawable.emoji_01_ill, R.drawable.emoji_01_in_love,
            R.drawable.emoji_01_kissing, R.drawable.emoji_01_mad, R.drawable.emoji_01_nerd,
            R.drawable.emoji_01_ninja, R.drawable.emoji_01_quiet, R.drawable.emoji_01_sad,
            R.drawable.emoji_01_secret, R.drawable.emoji_01_smart, R.drawable.emoji_01_smile,
            R.drawable.emoji_01_smiling, R.drawable.emoji_01_surprised, R.drawable.emoji_01_surprised_1,
            R.drawable.emoji_01_suspicious, R.drawable.emoji_01_suspicious_1, R.drawable.emoji_01_tongue_out,
            R.drawable.emoji_01_tongue_out_1, R.drawable.emoji_01_unhappy, R.drawable.emoji_01_wink
    };

    public static int[] TYPE02_EMOJI_DREWABLES = {
            R.drawable.emoji_02_angel, R.drawable.emoji_02_angry,
            R.drawable.emoji_02_astonished, R.drawable.emoji_02_astonished_1,
            R.drawable.emoji_02_confused, R.drawable.emoji_02_cool,
            R.drawable.emoji_02_cool_1, R.drawable.emoji_02_cry,
            R.drawable.emoji_02_cry_1, R.drawable.emoji_02_devil,
            R.drawable.emoji_02_dizzy, R.drawable.emoji_02_expressionless,
            R.drawable.emoji_02_flushed, R.drawable.emoji_02_happy, R.drawable.emoji_02_happy_1,
            R.drawable.emoji_02_happy_2, R.drawable.emoji_02_in_love, R.drawable.emoji_02_injury,
            R.drawable.emoji_02_joy, R.drawable.emoji_02_kiss,
            R.drawable.emoji_02_kiss_1, R.drawable.emoji_02_kiss_2, R.drawable.emoji_02_mask,
            R.drawable.emoji_02_mute, R.drawable.emoji_02_neutral, R.drawable.emoji_02_sad, R.drawable.emoji_02_sad_1,
            R.drawable.emoji_02_scared, R.drawable.emoji_02_scared_1, R.drawable.emoji_02_secret,
            R.drawable.emoji_02_shocked, R.drawable.emoji_02_sick, R.drawable.emoji_02_sleeping,
            R.drawable.emoji_02_smile, R.drawable.emoji_02_smile_1, R.drawable.emoji_02_smiling, R.drawable.emoji_02_smiling_1,
            R.drawable.emoji_02_smirking, R.drawable.emoji_02_surprised, R.drawable.emoji_02_sweat,
            R.drawable.emoji_02_thinking, R.drawable.emoji_02_tired,
            R.drawable.emoji_02_tongue, R.drawable.emoji_02_tongue_1, R.drawable.emoji_02_tongue_2,
            R.drawable.emoji_02_vomiting, R.drawable.emoji_02_wink
    };


    public final static class FriendCircleType {
        //纯文字
        public final static int FRIEND_CIRCLE_TYPE_ONLY_WORD = 0;
        //文字和图片
        public final static int FRIEND_CIRCLE_TYPE_WORD_AND_IMAGES = 1;
        //分享链接
        public final static int FRIEND_CIRCLE_TYPE_WORD_AND_URL = 2;
    }

    public final static class CommentType {
        //单一评论
        public final static int COMMENT_TYPE_SINGLE = 0;
        //回复评论
        public final static int COMMENT_TYPE_REPLY = 1;
    }

    public final static class EmojiType {
        //单一评论
        public final static int EMOJI_TYPE_01 = 1;
        //回复评论
        public final static int EMOJI_TYPE_02 = 2;
    }

}
