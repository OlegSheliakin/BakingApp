package com.example.danilserbin.baking.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.danilserbin.baking.R;

public class BakingWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);

        Intent adapter = new Intent(context, BakingService.class);
        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        views.setRemoteAdapter(R.id.lvWidget, adapter);

        appWidgetManager.updateAppWidget(appWidgetId, views);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,
                R.id.lvWidget);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        int[] ids = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        if (ids != null) {
            for (int i : ids) {
                updateAppWidget(context, AppWidgetManager.getInstance(context), i);
            }
        }
    }
}