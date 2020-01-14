package com.zhang.app.draw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ReplacementSpan;
import android.widget.TextView;

import com.zhang.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * createBy	 keepon
 */
public class CustomSpanActivity extends AppCompatActivity {
	private TextView textView;
	private TextView textView2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_span);
		textView = (TextView) findViewById(R.id.tv);
		textView2 = (TextView) findViewById(R.id.tv2);
		textView.setTextSize(30);

		List<ReplacementSpan> spans = new ArrayList<>();
		String content = "Android是一种基于Linux的自由及开放源代码的操作系统，主要使用于移动设备，如智能手机和平板电脑，由Google公司和开放手机联盟领导及开发。尚未有统一中文名称，中国大陆地区较多人使用“安卓”或“安致”。";
		StringBuilder stringBuilder = new StringBuilder();
		//第一个Span
		stringBuilder.append(" ");
		IconTextSpan topSpan = new IconTextSpan(getApplicationContext(), R.color.colorPrimary, "置顶");
		spans.add(topSpan);
		//第二个Span
		stringBuilder.append(" ");
		IconTextSpan hotSpan = new IconTextSpan(getApplicationContext(), R.color.colorAccent, "热");
		hotSpan.setRightMarginDpValue(5);
		spans.add(hotSpan);

		stringBuilder.append(content);
		SpannableString spannableString = new SpannableString(stringBuilder.toString());
		//循环遍历设置Span
		for (int i = 0; i < spans.size(); i++) {
			spannableString.setSpan(spans.get(i), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		textView.setText(spannableString);



		List<ReplacementSpan> spans2 = new ArrayList<>();
		String content2 = "Android是一种基于Linux的自由及开放系统Android是一种基于Linux的自由及开放系统Android是一种基于Linux的自由及开放系统Android是一种基于Linux的自由及开放系统";
		StringBuilder stringBuilder2 = new StringBuilder();
		//第一个Span
		stringBuilder2.append(" ");
		IconTextSpan topSpan2 = new IconTextSpan(getApplicationContext(), R.color.colorPrimary, "置顶2");
		spans2.add(topSpan2);
		//第二个Span
		stringBuilder2.append(" ");
		IconTextSpan hotSpan2 = new IconTextSpan(getApplicationContext(), R.color.colorAccent, "热2");
		hotSpan2.setRightMarginDpValue(5);
		spans2.add(hotSpan2);

		stringBuilder2.append(content2);
		SpannableString spannableString2 = new SpannableString(stringBuilder2.toString());
		//循环遍历设置Span
		for (int i = 0; i < spans.size(); i++) {
			spannableString2.setSpan(spans.get(i), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		textView2.setText(spannableString2);
	}
}

