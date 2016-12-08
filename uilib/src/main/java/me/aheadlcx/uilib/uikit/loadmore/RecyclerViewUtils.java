package me.aheadlcx.uilib.uikit.loadmore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

class RecyclerViewUtils {
    private static final String TAG = "RecyclerViewUtils";

    RecyclerViewUtils() {
    }

    public static void setVerticalLinearLayout(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext(), 1, false) {
            public void onLayoutChildren(Recycler recycler, State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    Log.e(RecyclerViewUtils.TAG, "meet an IndexOutOfBoundsException in RecyclerView");
                }
            }
        });
    }

    public static void setGridLayout(final RecyclerView rv, int spanCount) {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(rv.getContext(), spanCount, 1, false) {
            public void onLayoutChildren(Recycler recycler, State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    Log.e(RecyclerViewUtils.TAG, "meet an IndexOutOfBoundsException in RecyclerView");
                }
            }
        };
        gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(int position) {
                if (rv.getAdapter().getItemViewType(position) < 0) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        rv.setLayoutManager(gridLayoutManager);
    }

    public static void setStaggeredGridLayoutManager(RecyclerView rv, int spanCount) {
        rv.setLayoutManager(new StaggeredGridLayoutManager(spanCount, 1) {
            public void onLayoutChildren(Recycler recycler, State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    Log.e(RecyclerViewUtils.TAG, "meet an IndexOutOfBoundsException in RecyclerView");
                }
            }
        });
    }
}
