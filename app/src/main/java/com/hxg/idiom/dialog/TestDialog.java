//package com.hxg.idiom.dialog;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//
//import com.hxg.idiom.R;
//import com.hxg.idiom.dialog.base.BaseDialog;
//import com.hxg.idiom.util.Util;
//
//public class TestDialog extends BaseDialog {
//    private View mDialogView;
//    private ImageView smallImg, bigImg;
//
//    public TestDialog(Activity context) {
//        super(context);
//    }
//
//    @Override
//    protected View createDialogView(Activity activity) {
//        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mDialogView = layoutInflater.inflate(R.layout.book_cover_layout, null);
//        smallImg = mDialogView.findViewById(R.id.small_img);
//        bigImg = mDialogView.findViewById(R.id.big_img);
//
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cancel();
//            }
//        };
//
//        mDialogView.setOnClickListener(listener);
//        smallImg.setOnClickListener(listener);
//        bigImg.setOnClickListener(listener);
//
//        return mDialogView;
//    }
//
//    public void setImage(@NonNull String imageLink, boolean isQiMaoImg) {
//        if (smallImg != null) {
//            smallImg.setImageURI(imageLink);
//        }
//        if (bigImg != null) {
//            // 留出底部阴影距离
//            int dp_6 = Util.INSTANCE.dpToPx(mContext, R.dimen.dp_6);
//            ViewGroup.LayoutParams layoutParams = bigImg.getLayoutParams();
//            if (!isQiMaoImg) {
//                int dp_240 = Util.INSTANCE.dpToPx(mContext, R.dimen.dp_240);
//                int dp_320 = Util.INSTANCE.dpToPx(mContext, R.dimen.dp_320);
//                layoutParams.width = dp_240;
//                layoutParams.height = dp_320 + dp_6;
//                bigImg.setImageURI(imageLink);
//            } else {
//                int screenWidth = Util.INSTANCE.getScreenWidth(mContext);
//                layoutParams.width = screenWidth;
//                layoutParams.height = screenWidth * 4 / 3 + dp_6;
//                bigImg.setImageURI(imageLink);
//            }
//        }
//    }
//
//    @Override
//    public void showDialog() {
//        super.showDialog();
//        if (mDialogView != null) {
//            mDialogView.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void dismissDialog() {
//        super.dismissDialog();
//        if (mDialogView != null) {
//            mDialogView.setVisibility(View.GONE);
//        }
//    }
//
//    public void cancel() {
//        if (mContext instanceof BaseProjectActivity) {
//            BookStaticsUtil.onNewAggregateEvent("everypages_standard_gotit_click");
//            ((BaseProjectActivity) mContext).getDialogHelper().dismissDialogByType(BookCoverDialog.class);
//        } else {
//            dismissDialog();
//        }
//    }
//}