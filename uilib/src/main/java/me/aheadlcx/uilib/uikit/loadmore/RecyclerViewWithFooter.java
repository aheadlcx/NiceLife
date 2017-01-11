package me.aheadlcx.uilib.uikit.loadmore;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RecyclerViewWithFooter extends RecyclerView {
    public static final int STATE_EMPTY = 2;
    public static final int STATE_END = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_NONE = 3;
    public static final int STATE_PULL_TO_LOAD = 4;
    private static final String TAG = "RecyclerViewWithFooter";
    private AdapterDataObserver mAdapterDataObserver = new AdapterDataObserver() {
        public void onChanged() {
            super.onChanged();
            reset();
        }

        private void reset() {
            RecyclerViewWithFooter.this.mIsGettingData = false;
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            reset();
        }

        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            reset();
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            reset();
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            reset();
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            reset();
        }
    };
    private EmptyItem mEmptyItem = new DefaultEmptyItem();
    private FootItem mFootItem = new DefaultFootItem();
    private boolean mIsGettingData = false;
    private int mState = STATE_NONE;

    public class LoadMoreAdapter extends Adapter<ViewHolder> {
        public static final int EMPTY_VIEW_TYPE = -403;
        public static final int LOAD_MORE_VIEW_TYPE = -404;
        public Adapter mAdapter;

        class VH extends ViewHolder {
            public VH(View itemView) {
                super(itemView);
            }

            public void onBindViewHolder() {
            }
        }

        private class EmptyVH extends VH {
            public EmptyVH() {
                super(RecyclerViewWithFooter.this.mEmptyItem.onCreateView(RecyclerViewWithFooter.this));
            }

            public void onBindViewHolder() {
                super.onBindViewHolder();
                RecyclerViewWithFooter.this.mEmptyItem.onBindData(this.itemView);
            }
        }

        private class LoadMoreVH extends VH {
            private View mItemView = this.itemView;

            public LoadMoreVH() {
                super(RecyclerViewWithFooter.this.mFootItem.onCreateView(RecyclerViewWithFooter.this));
            }

            public void onBindViewHolder() {
                super.onBindViewHolder();
                if (RecyclerViewWithFooter.this.mState == RecyclerViewWithFooter.STATE_LOADING || RecyclerViewWithFooter.this.mState == 0 || RecyclerViewWithFooter.this.mState == RecyclerViewWithFooter.STATE_PULL_TO_LOAD) {
                    RecyclerViewWithFooter.this.mFootItem.onBindData(this.mItemView, RecyclerViewWithFooter.this.mState);
                }
            }
        }

        public LoadMoreAdapter(Adapter adapter) {
            this.mAdapter = adapter;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == LOAD_MORE_VIEW_TYPE) {
                return new LoadMoreVH();
            }
            if (viewType == EMPTY_VIEW_TYPE) {
                return new EmptyVH();
            }
            return this.mAdapter.onCreateViewHolder(parent, viewType);
        }

        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            super.registerAdapterDataObserver(observer);
            this.mAdapter.registerAdapterDataObserver(observer);
        }

        public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
            super.unregisterAdapterDataObserver(observer);
            this.mAdapter.unregisterAdapterDataObserver(observer);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            if (isFootView(position)) {
                if (RecyclerViewWithFooter.this.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                    LayoutParams layoutParams = (LayoutParams) holder.itemView.getLayoutParams();
                    if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                        ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
                    }
                }
                if (holder instanceof VH) {
                    ((VH) holder).onBindViewHolder();
                    return;
                }
                return;
            }
            this.mAdapter.onBindViewHolder(holder, position);
        }

        private boolean isFootView(int position) {
            return position == getItemCount() + -1 && RecyclerViewWithFooter.this.mState != RecyclerViewWithFooter.STATE_NONE;
        }

        public int getItemViewType(int position) {
            if (!isFootView(position)) {
                return this.mAdapter.getItemViewType(position);
            }
            if (RecyclerViewWithFooter.this.mState == RecyclerViewWithFooter.STATE_EMPTY && getItemCount() == RecyclerViewWithFooter.STATE_LOADING) {
                return EMPTY_VIEW_TYPE;
            }
            return LOAD_MORE_VIEW_TYPE;
        }

        public int getItemCount() {
            if (RecyclerViewWithFooter.this.mState == RecyclerViewWithFooter.STATE_NONE) {
                return this.mAdapter.getItemCount();
            }
            return this.mAdapter.getItemCount() + RecyclerViewWithFooter.STATE_LOADING;
        }
    }

    private class OnLoadMoreListenerWrapper implements OnLoadMoreListener {
        private OnLoadMoreListener mOnLoadMoreListener;

        public OnLoadMoreListenerWrapper(OnLoadMoreListener onLoadMoreListener) {
            this.mOnLoadMoreListener = onLoadMoreListener;
        }

        public void onLoadMore() {
            if (!RecyclerViewWithFooter.this.mIsGettingData && RecyclerViewWithFooter.this.isLoadMoreEnable()) {
                RecyclerViewWithFooter.this.mIsGettingData = true;
                setLoading();
                this.mOnLoadMoreListener.onLoadMore();
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public RecyclerViewWithFooter(Context context) {
        super(context);
        init();
    }

    public RecyclerViewWithFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerViewWithFooter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setVerticalLinearLayout();
    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    public void setVerticalLinearLayout() {
        RecyclerViewUtils.setVerticalLinearLayout(this);
    }

    public void setGridLayout(int span) {
        RecyclerViewUtils.setGridLayout(this, span);
    }

    public void setStaggeredGridLayoutManager(int spanCount) {
        RecyclerViewUtils.setStaggeredGridLayoutManager(this, spanCount);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        setPullToLoad();
        final OnLoadMoreListenerWrapper wrapper = new OnLoadMoreListenerWrapper(onLoadMoreListener);
        addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof LinearLayoutManager) {
                        if (recyclerView.getAdapter() == null)return;
                        if (((LinearLayoutManager) layoutManager).findLastVisibleItemPosition() >= recyclerView.getAdapter().getItemCount() - 1) {
//                            if (RecyclerViewWithFooter.this.mState == RecyclerViewWithFooter.STATE_PULL_TO_LOAD) {
//                                RecyclerViewWithFooter.this.setLoading();
//                            }
                            wrapper.onLoadMore();
                        }
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                        int[] last = new int[staggeredGridLayoutManager.getSpanCount()];
                        staggeredGridLayoutManager.findLastVisibleItemPositions(last);
                        int length = last.length;
                        for (int i = RecyclerViewWithFooter.STATE_END; i < length; i += RecyclerViewWithFooter.STATE_LOADING) {
                            int aLast = last[i];
                            Log.i(RecyclerViewWithFooter.TAG, aLast + "    " + recyclerView.getAdapter().getItemCount());
                            if (aLast >= recyclerView.getAdapter().getItemCount() - 1) {
//                                if (RecyclerViewWithFooter.this.mState == RecyclerViewWithFooter.STATE_PULL_TO_LOAD) {
//                                    RecyclerViewWithFooter.this.setLoading();
//                                }
                                wrapper.onLoadMore();
                            }
                        }
                    }
                }
            }
        });
    }

    public void setAdapter(Adapter adapter) {
        if (adapter instanceof LoadMoreAdapter) {
            ((LoadMoreAdapter) adapter).registerAdapterDataObserver(this.mAdapterDataObserver);
            super.setAdapter(adapter);
            return;
        }
        LoadMoreAdapter loadMoreAdapter = new LoadMoreAdapter(adapter);
        loadMoreAdapter.registerAdapterDataObserver(this.mAdapterDataObserver);
        super.setAdapter(loadMoreAdapter);
    }

    public RecyclerViewWithFooter applyLoadingText(CharSequence loadText) {
        this.mFootItem.loadingText = loadText;
        return this;
    }

    public RecyclerViewWithFooter applyPullToLoadText(CharSequence pullToLoadText) {
        this.mFootItem.pullToLoadText = pullToLoadText;
        return this;
    }

    public RecyclerViewWithFooter applyEndText(CharSequence endText) {
        this.mFootItem.endText = endText;
        return this;
    }

    public RecyclerViewWithFooter applyEmptyText(CharSequence emptyText, @DrawableRes int drawableId) {
        this.mEmptyItem.mEmptyIconRes = drawableId;
        this.mEmptyItem.mEmptyText = emptyText;
        return this;
    }

    public void setFootItem(FootItem footItem) {
        if (this.mFootItem != null) {
            if (footItem.endText == null) {
                footItem.endText = this.mFootItem.endText;
            }
            if (footItem.loadingText == null) {
                footItem.loadingText = this.mFootItem.loadingText;
            }
            if (footItem.pullToLoadText == null) {
                footItem.pullToLoadText = this.mFootItem.pullToLoadText;
            }
        }
        this.mFootItem = footItem;
    }

    public void setEmptyItem(EmptyItem emptyItem) {
        if (this.mEmptyItem != null) {
            if (emptyItem.mEmptyIconRes == -1) {
                emptyItem.mEmptyIconRes = this.mEmptyItem.mEmptyIconRes;
            }
            if (emptyItem.mEmptyText == null) {
                emptyItem.mEmptyText = this.mEmptyItem.mEmptyText;
            }
        }
        this.mEmptyItem = emptyItem;
    }

    public void setPullToLoad() {
        if (getAdapter() != null) {
            this.mState = STATE_PULL_TO_LOAD;
            this.mIsGettingData = false;
            getAdapter().notifyItemChanged(getAdapter().getItemCount() - 1);
            getAdapter().notifyDataSetChanged();
        }
    }

    public void setLoading() {
        Log.i(TAG, "setLoading: ");
        if (getAdapter() != null) {
            this.mState = STATE_LOADING;
            this.mIsGettingData = true;
            getAdapter().notifyItemChanged(getAdapter().getItemCount() - 1);
            getAdapter().notifyDataSetChanged();
        }
    }

    public void setEnd(CharSequence end) {
        if (getAdapter() != null) {
            this.mIsGettingData = false;
            this.mState = STATE_END;
            this.mFootItem.endText = end;
            getAdapter().notifyItemChanged(getAdapter().getItemCount() - 1);
            getAdapter().notifyDataSetChanged();
        }
    }

    public void setEnd() {
        if (getAdapter() != null) {
            this.mIsGettingData = false;
            this.mState = STATE_END;
            getAdapter().notifyItemChanged(getAdapter().getItemCount() - 1);
            getAdapter().notifyDataSetChanged();
        }
    }

    public void setEmpty(CharSequence empty, @DrawableRes int resId) {
        if (getAdapter() != null) {
            this.mState = STATE_EMPTY;
            this.mEmptyItem.mEmptyText = empty;
            this.mEmptyItem.mEmptyIconRes = resId;
            if (isEmpty()) {
                getAdapter().notifyDataSetChanged();
            }
        }
    }

    public void setEmpty() {
        if (getAdapter() != null) {
            this.mState = STATE_EMPTY;
            if (isEmpty()) {
                getAdapter().notifyDataSetChanged();
            }
        }
    }

    private boolean isEmpty() {
        if (this.mState == STATE_NONE && getAdapter().getItemCount() == 0) {
            return true;
        }
        if (this.mState == STATE_NONE || getAdapter().getItemCount() != STATE_LOADING) {
            return false;
        }
        return true;
    }

    public boolean isLoadMoreEnable() {
        return this.mState != STATE_LOADING;
    }
}
