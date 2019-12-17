package com.camps.homework.ui.activitys;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.camps.frame.widgets.emoji.EmojiPanelView;
import com.camps.homework.R;
import com.camps.homework.others.DataCenter;


/**
 * emoji表情页面
 */
public class EmojiPanelActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_panel);
        EmojiPanelView emojiPanelView = findViewById(R.id.emoji_panel_view);
        emojiPanelView.initEmojiPanel(DataCenter.emojiDataSources);
        findViewById(R.id.show).setOnClickListener(v -> emojiPanelView.showEmojiPanel());
    }
}
