package com.dqf.myrecyclerview;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * 作者：董庆丰
 * 时间: 2016/12/13 11:44
 * 邮箱：13261888814@163.com
 * QQ :3232919961
 * Android 互帮互助
 */
class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
{

	private List<String> mDatas;
	private LayoutInflater mInflater;

	public interface OnItemClickLitener
	{
		void onItemClick(View view, int position);
		void onItemLongClick(View view, int position);
	}

	private OnItemClickLitener mOnItemClickLitener;

	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
	{
		this.mOnItemClickLitener = mOnItemClickLitener;
	}
	

	public HomeAdapter(Context context, List<String> datas)
	{
		mInflater = LayoutInflater.from(context);
		mDatas = datas;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		MyViewHolder holder = new MyViewHolder(mInflater.inflate(
				R.layout.item_home, parent, false));
		return holder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position)
	{
		holder.tv.setText(mDatas.get(position));

		// 如果设置了回调，则设置点击事件
		if (mOnItemClickLitener != null)
		{
			holder.itemView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemClick(holder.itemView, pos);
				}
			});
			
			holder.itemView.setOnLongClickListener(new OnLongClickListener()
			{
				@Override
				public boolean onLongClick(View v)
				{
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
					removeData(pos);
					return false;
				}
			});
		}
	}

	@Override
	public int getItemCount()
	{
		return mDatas.size();
	}

	public void addData(int position)
	{
		mDatas.add(position, "Insert One");
		notifyItemInserted(position);
	}


	public void removeData(int position)
	{
		mDatas.remove(position);
		notifyItemRemoved(position);
	}

	class MyViewHolder extends ViewHolder
	{

		TextView tv;

		public MyViewHolder(View view)
		{
			super(view);
			tv = (TextView) view.findViewById(R.id.id_num);
		
		
		}
	}
}