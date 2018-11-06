package com.example.yunwen.testdemo1022.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;


import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseBackActivity;
import com.example.yunwen.testdemo1022.helper.DialogHelper;

import java.util.List;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2018/01/01
 *     desc  : demo about PermissionUtils
 *     con : 对权限请求的调用功能
 * </pre>
 */
public class PermissionActivity extends BaseBackActivity {

    TextView tvAboutPermission;
    String permissions;

    public static void start(Context context) {
        Intent starter = new Intent(context, PermissionActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_permission;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        getToolBar().setTitle(getString(R.string.demo_permission));

        tvAboutPermission = findViewById(R.id.tv_about_permission);
        findViewById(R.id.btn_open_app_settings).setOnClickListener(this);
        findViewById(R.id.btn_request_calendar).setOnClickListener(this);
        findViewById(R.id.btn_request_record_audio).setOnClickListener(this);
        findViewById(R.id.btn_request_calendar_and_record_audio).setOnClickListener(this);

        //对应用当前清单中的权限列表的拼接
        StringBuilder sb = new StringBuilder();
        for (String s : PermissionUtils.getPermissions()) {
            LogUtils.e("permission ----" + s);
            sb.append(s.substring(s.lastIndexOf('.') + 1)).append("\n");
        }
        permissions = sb.toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAboutPermission();
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_app_settings:
                //打开跳转应用程序设置界面
                PermissionUtils.launchAppDetailsSettings();
                break;
            case R.id.btn_request_calendar:
                PermissionUtils.permission(PermissionConstants.CALENDAR)
                        //拒绝后的权限调用
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            @Override
                            public void rationale(final ShouldRequest shouldRequest) {
                                //请求的权限是弹窗出现后点击拒绝后，再次请求权限，权限为询问状态时，调用
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        //状态回调
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                //点击同意该权限时，调用
                                updateAboutPermission();
                                LogUtils.e(permissionsGranted);
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever,
                                                 List<String> permissionsDenied) {
                                //请求的权限是被拒绝状态时，走这个方法
                                if (!permissionsDeniedForever.isEmpty()) {
                                    DialogHelper.showOpenAppSettingDialog();
                                }
                                LogUtils.e(permissionsDeniedForever, permissionsDenied);
                            }
                        })
                        //对状态栏的显示隐藏功能的添加或不添加
                        .theme(new PermissionUtils.ThemeCallback() {
                            @Override
                            public void onActivityCreate(Activity activity) {
                                //请求权限弹窗显示，--》状态栏隐藏
                                ScreenUtils.setFullScreen(activity);
                            }
                        })
                        .request();
                break;
            case R.id.btn_request_record_audio:
                PermissionUtils.permission(PermissionConstants.MICROPHONE)
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            @Override
                            public void rationale(final ShouldRequest shouldRequest) {
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                updateAboutPermission();
                                LogUtils.d(permissionsGranted);
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever,
                                                 List<String> permissionsDenied) {
                                if (!permissionsDeniedForever.isEmpty()) {
                                    DialogHelper.showOpenAppSettingDialog();
                                }
                                LogUtils.d(permissionsDeniedForever, permissionsDenied);
                            }
                        })
                        .request();
                break;
            case R.id.btn_request_calendar_and_record_audio:
                PermissionUtils.permission(PermissionConstants.CALENDAR, PermissionConstants.MICROPHONE)
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            @Override
                            public void rationale(final ShouldRequest shouldRequest) {
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                updateAboutPermission();
                                LogUtils.d(permissionsGranted);
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever,
                                                 List<String> permissionsDenied) {
                                if (!permissionsDeniedForever.isEmpty()) {
                                    DialogHelper.showOpenAppSettingDialog();
                                }
                                LogUtils.d(permissionsDeniedForever, permissionsDenied);
                            }
                        })
                        .request();
                break;
        }
    }

    /**
     * 对当前的所有权限的text显示和要请求的权限的显示和拼接功能 ---更新显示（onResume和同意授权权限时调用）
     */
    private void updateAboutPermission() {
        tvAboutPermission.setText(new SpanUtils()
                .append(permissions).setBold()
                .appendLine("READ_CALENDAR: " + PermissionUtils.isGranted(Manifest.permission.READ_CALENDAR))
                .appendLine("RECORD_AUDIO: " + PermissionUtils.isGranted(Manifest.permission.RECORD_AUDIO))
                .create());
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onCompleted() {

    }
}
