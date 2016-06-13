package com.momo.neng.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momo.neng.app.R;
import com.momo.neng.app.recyclerViewpager.RecyclerViewPager;
import com.momo.neng.app.recyclerViewpager.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Home Fragment 帖子内容
 * <p/>
 * User: MoMo - LiNen
 * Date: 2015-11-18
 */
public class ViewPagetFragment extends Fragment {
    private String TAG = this.getClass().getSimpleName();

    private Context mContext;
    private View mViewRoot;
    private RecyclerViewPager mRecyclerView;

    private ViewPagetFragmentAdapter mHomeViewPageAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private LinearLayoutManager layout;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mViewRoot == null) {
            mViewRoot = inflater.inflate(R.layout.home_horizontal, container, false);
        } else if (mViewRoot.getParent() != null) {
            ((ViewGroup) mViewRoot.getParent()).removeView(mViewRoot);
        }
        return mViewRoot;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerViewPager) view.findViewById(R.id.list);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("My this page: " + i);
        }
        initRecyclerView(list);
    }

    private void initRecyclerView(List<String> list) {
        layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layout);


        mHomeViewPageAdapter = new ViewPagetFragmentAdapter(mContext, list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        mRecyclerView.setAdapter(mHomeViewPageAdapter);

        updateState(RecyclerView.SCROLL_STATE_IDLE);


        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mHomeViewPageAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);


        //滑动状态
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                updateState(scrollState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            }


        });

        //切换状态
        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                if (newPosition > oldPosition) {   //正常、下一个

                } else if (newPosition < oldPosition) {//返回、上一个
                }
            }
        });

    }


    private void updateState(int scrollState) {
        String stateName = "Undefined";
        switch (scrollState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                stateName = "Idle";
                break;

            case RecyclerView.SCROLL_STATE_DRAGGING:
                stateName = "Dragging";
                break;

            case RecyclerView.SCROLL_STATE_SETTLING:
                stateName = "Flinging";
                break;
        }
    }


}
