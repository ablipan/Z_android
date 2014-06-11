/**
 * author :  lipan
 * filename :  ViewAnimation.java
 * create_time : 2014年6月9日 下午3:47:38
 */
package com.pp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.pp.R;
import com.pp.utils.ViewUtils;

/**
 * @author : lipan
 * @create_time : 2014年6月9日 下午3:47:38
 * @desc : View animation
 * @update_person:
 * @update_time :
 * @update_desc :
 *
 */
@SuppressLint("NewApi")
public class LayoutAnimation extends Activity
{
    private LinearLayout layout;
    private LinearLayout layout1;
    private LinearLayout layout11;
    private LinearLayout layout2;
    private LinearLayout layout22;
    Animator defaultAppearingAnim, defaultDisappearingAnim;
    Animator defaultChangingAppearingAnim, defaultChangingDisappearingAnim;
    Animator customAppearingAnim, customDisappearingAnim;
    Animator customChangingAppearingAnim, customChangingDisappearingAnim;
    Animator currentAppearingAnim, currentDisappearingAnim;
    Animator currentChangingAppearingAnim, currentChangingDisappearingAnim;
    
    public void BtnClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn1:
                if(ViewUtils.isShowing(layout1))
                {
                    ViewUtils.hide(layout1);
                    toggle(layout11 , ExpandCollapseAnimation.EXPAND);
                }else
                {
                    ViewUtils.show(layout1);
                    toggle(layout11 , ExpandCollapseAnimation.COLLAPSE);
                }
                break;
            case R.id.btn2:
                if(ViewUtils.isShowing(layout2))
                {
                    hideAnim(layout2);
                    ViewUtils.show(layout22);
                }else
                {
                    ViewUtils.show(layout2);
                    hideAnim(layout22);
                }
                break;

            default:
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_layout_animation);
        
        initView();
        final LayoutTransition transitioner = new LayoutTransition();
        createCustomAnimations(transitioner);
        
//        layout.setLayoutTransition(transitioner);
        
        //默认的layout动画
        defaultAppearingAnim = transitioner.getAnimator(LayoutTransition.APPEARING);
        defaultDisappearingAnim =
                transitioner.getAnimator(LayoutTransition.DISAPPEARING);
        defaultChangingAppearingAnim =
                transitioner.getAnimator(LayoutTransition.CHANGE_APPEARING);
        defaultChangingDisappearingAnim =
                transitioner.getAnimator(LayoutTransition.CHANGE_DISAPPEARING);

//        defaultAppearingAnim.setStartDelay(20000);
        transitioner.setAnimator(LayoutTransition.APPEARING,customChangingAppearingAnim);
    }
    /**
     * 
     */
    private void initView()
    {
        layout = (LinearLayout)findViewById(R.id.main_parent);
        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout11 = (LinearLayout)findViewById(R.id.layout11);
        layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout22 = (LinearLayout)findViewById(R.id.layout22);
        
        
    }
    
    private void createCustomAnimations(LayoutTransition transition) {
        // Changing while Adding
        PropertyValuesHolder pvhLeft =
                PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop =
                PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight =
                PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder pvhBottom =
                PropertyValuesHolder.ofInt("bottom", 0, 1);
        PropertyValuesHolder pvhScaleX =
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY =
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
        customChangingAppearingAnim = ObjectAnimator.ofPropertyValuesHolder(
                        this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY).
                setDuration(transition.getDuration(LayoutTransition.CHANGE_APPEARING));
        customChangingAppearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });

        // Changing while Removing
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation =
                PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        customChangingDisappearingAnim = ObjectAnimator.ofPropertyValuesHolder(
                        this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).
                setDuration(transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        customChangingDisappearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotation(0f);
            }
        });

        // Adding
        customAppearingAnim = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f).
                setDuration(transition.getDuration(LayoutTransition.APPEARING));
        customAppearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });

        // Removing
        customDisappearingAnim = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f).
                setDuration(transition.getDuration(LayoutTransition.DISAPPEARING));
        customDisappearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationX(0f);
            }
        });

    }
    
    private void hideAnim(final View v)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1, 1, 0, 1, 1);
        scaleAnimation.setDuration(100);
        scaleAnimation.setAnimationListener(new AnimationListener()
        {
            
            @Override
            public void onAnimationStart(Animation animation)
            {
            }
            
            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
            
            @Override
            public void onAnimationEnd(Animation animation)
            {
               ViewUtils.hide(v);
            }
        });
        v.startAnimation(scaleAnimation);
    }
    private void toggle(final View v , int type)
    {
        ExpandCollapseAnimation scaleAnimation = new ExpandCollapseAnimation(v , type);
        scaleAnimation.setDuration(100);
        scaleAnimation.setAnimationListener(new AnimationListener()
        {
            
            @Override
            public void onAnimationStart(Animation animation)
            {
            }
            
            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
            
            @Override
            public void onAnimationEnd(Animation animation)
            {
                ViewUtils.show(v);
            }
        });
        v.startAnimation(scaleAnimation);
    }
}
