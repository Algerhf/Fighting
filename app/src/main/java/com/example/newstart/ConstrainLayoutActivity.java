package com.example.newstart;

import android.content.Context;
import android.content.Intent;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ConstrainLayoutActivity extends BaseActivity {

    @Override
    protected int getResId() {
        return R.layout.activity_constrain_layout;
    }

    @Override
    public void initView() {
        super.initView();

        /**
         * 一、定位
         *   1.1 相对定位
         *   app:layout_constraintTop_toTopOf=""
         *   app:layout_constraintBottom_toBottomOf=""
         *   app:layout_constraintLeft_toLeftOf=""
         *   app:layout_constraintRight_toRightOf=""
         *   app:layout_constraintStart_toStartOf=""
         *   app:layout_constraintEnd_toEndOf=""
         *   app:layout_constraintBaseline_toBaselineOf=""
         *   app:layout_constraintTop_toBottomOf=""
         *   app:layout_constraintBottom_toTopOf=""
         *   app:layout_constraintLeft_toRightOf=""
         *   app:layout_constraintRight_toLeftOf=""
         *   app:layout_constraintStart_toEndOf=""
         *   app:layout_constraintEnd_toStartOf=""
         *   以上属性值需要另一个控件的 id 或 parent（父容器）作为参考
         *
         *   1.2 角度定位
         *   app:layout_constraintCircle=""         该属性值需要参考另一个控件的id
         *   app:layout_constraintCircleAngle="45"  以y轴负方向为起始方向，顺时针旋转45度
         *   app:layout_constraintCircleRadius="120dp"  此控件的中心点与参考控件中心点之间的距离
         *
         * 二、居中和偏移
         *   2.1 居中：同时约束左右、或者同时约束上下时，可以使控件在约束范围内水平居中或垂直居中
         *   app:layout_constraintTop_toTopOf=""
         *   app:layout_constraintBottom_toBottomOf=""
         *
         *   2.2 偏移：属性值范围是 0-1  0.5表示居中显示，0表示靠左显示，1表示靠右显示
         *   app:layout_constraintHorizontal_bias=""
         *   app:layout_constraintVertical_bias=""
         *
         * 三、间距
         *   android:layout_marginStart
         *   android:layout_marginEnd
         *   android:layout_marginLeft
         *   android:layout_marginTop
         *   android:layout_marginRight
         *   android:layout_marginBottom
         *   以上属性需要大于等于0dp，且该方向上存在约束条件才会生效
         *
         *   gone属性导致间距失效时，可以使用以下属性
         *   layout_goneMarginStart
         *   layout_goneMarginEnd
         *   layout_goneMarginLeft
         *   layout_goneMarginTop
         *   layout_goneMarginRight
         *   layout_goneMarginBottom
         *
         * 四、尺寸
         *   4.1 固定尺寸
         *
         *   4.2 自适应尺寸 wrap_content
         *      4.2.1：当内容比较长时，wrap_content可能失效，可以将如下属性值设置为true
         *      app:layout_constrainedWidth=”true|false”
         *      app:layout_constrainedHeight=”true|false”
         *
         *      4.2.2：指定最大/小的高/宽度，wrap_content时才会生效
         *      android:minWidth 设置布局的最小宽度
         *      android:minHeight 设置布局的最小高度
         *      android:maxWidth 设置布局的最大宽度
         *      android:maxHeight 设置布局的最大高度
         *
         *  4.3 0dp时，默认占满整个屏幕，但受以下属性的约束
         *      4.3.1 指定最大/小的高/宽度
         *      app:layout_constraintHeight_min=""
         *      app:layout_constraintHeight_max=""
         *      app:layout_constraintWidth_min=""
         *      app:layout_constraintWidth_max=""
         *
         *      4.3.2 宽高比   被计算的那一边必须是0dp  也可以显示地指定计算哪一边，如计算高度 H,1:2
         *      app:layout_constraintDimensionRatio="1:2"  // 宽度：高度 = 1 ：2
         *
         *      4.3.3 默认高宽约束，属性值有 spread占满（默认值）  wrap(自适应)   percent（百分比）
         *      app:layout_constraintWidth_default=""
         *      app:layout_constraintHeight_default=""
         *
         *      当上面的属性值取百分比时，以下属性值范围是 0-1
         *      app:layout_constraintWidth_percent=""
         *      app:layout_constraintHeight_percent=""
         *
         * 五、链
         *      5.1 chainStyle
         *      属性有三个取值 spread spread_inside packed  当属性是packed时可以使用bias
         *      app:layout_constraintHorizontal_chainStyle=""
         *      app:layout_constraintVertical_chainStyle=""
         *
         *      5.2 权重
         *      app:layout_constraintHorizontal_weight=""
         *      app:layout_constraintVertical_weight=""
         *
         * 六、辅助类
         *     6.1 屏障 androidx.constraintlayout.widget.Barrier
         *     app:barrierDirection=""            // left right  top  bottom  start end
         *      app:constraint_referenced_ids=""  // 参考控件
         *     app:barrierAllowsGoneWidgets=""    // 允许gone
         *     app:barrierMargin="20dp"
         *
         *     6.2 引导线 GuideLine
         *     android:orientation="vertical"
         *     app:layout_constraintGuide_begin=""
         *     app:layout_constraintGuide_end=""
         *
         *     6.3 Group
         *     app:constraint_referenced_ids=""  // 参考控件的id
         *
         *     6.4 androidx.constraintlayout.widget.Placeholder
         *     app:content=""  // 要被移动到这里的控件id
         */

    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, ConstrainLayoutActivity.class));
    }
}