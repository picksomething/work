package com.VertexVerveInc.Games;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class Block extends Button
{
	private boolean isCovered; //是否是覆盖状态
	private boolean isMined; // 下面是否有雷
	private boolean isFlagged; // 是否标记成雷
	private boolean isQuestionMarked; // 是否标记成问号
	private boolean isClickable; // 是否可以点击
	private int numberOfMinesInSurrounding; // 附近8格的地雷数

	public Block(Context context)
	{
		super(context);
	}

	public Block(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	/*public Block(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}*/

	//对没一个小方格设置默认属性
	public void setDefaults()
	{
		isCovered = true;
		isMined = false;
		isFlagged = false;
		isQuestionMarked = false;
		isClickable = true;
		numberOfMinesInSurrounding = 0;

		this.setBackgroundResource(R.drawable.square_blue);
		setBoldFont();
	}

	// 打开或者把方块标记成不可点状态
	// 同时更新方块周边的地雷数
	public void setNumberOfSurroundingMines(int number)
	{
		this.setBackgroundResource(R.drawable.square_grey);
		
		updateNumber(number);
	}

	// 为某一方格设置成地雷图标
	// 如果参数是假就把方格设置成无法使用或者打开状态
	public void setMineIcon(boolean enabled)
	{
		this.setText("M");

		if (!enabled)
		{
			this.setBackgroundResource(R.drawable.square_grey);
			this.setTextColor(Color.RED);
		}
		else
		{
			this.setTextColor(Color.BLACK);
		}
	}

	// 标记某一格
	// 如果参数为假则设置成无法点击或者打开
	public void setFlagIcon(boolean enabled)
	{
		this.setText("F");

		if (!enabled)
		{
			this.setBackgroundResource(R.drawable.square_grey);
			this.setTextColor(Color.RED);
		}
		else
		{
			this.setTextColor(Color.BLACK);
		}
	}

	// 标记成问号
	// 如果参数为假则设置成无法点击或者打开
	public void setQuestionMarkIcon(boolean enabled)
	{
		this.setText("?");
		
		if (!enabled)
		{
			this.setBackgroundResource(R.drawable.square_grey);
			this.setTextColor(Color.RED);
		}
		else
		{
			this.setTextColor(Color.BLACK);
		}
	}

	// 如果参数为假则设置成无法点击或者打开
	// 否则设置成可用/关闭
	public void setBlockAsDisabled(boolean enabled)
	{
		if (!enabled)
		{
			this.setBackgroundResource(R.drawable.square_grey);
		}
		else
		{
			this.setBackgroundResource(R.drawable.square_blue);
		}
	}

	// 清除所有的图标/文字
	public void clearAllIcons()
	{
		this.setText("");
	}

	//设置字体为粗体
	private void setBoldFont()
	{
		this.setTypeface(null, Typeface.BOLD);
	}

	//打开某一块方格
	public void OpenBlock()
	{
		//不能打开不是覆盖着的方格
		if (!isCovered)
			return;

		setBlockAsDisabled(false);
		isCovered = false;

		//检查是否有地雷
		if (hasMine())
		{
			setMineIcon(false);
		}
		//更新附近地雷数
		else
		{
			setNumberOfSurroundingMines(numberOfMinesInSurrounding);
		}
	}

	// 设置附近的地雷数
	public void updateNumber(int text)
	{
		if (text != 0)
		{
			this.setText(Integer.toString(text));

			// 地雷数不同，数字颜色不同
			// 不标记0个地雷
			switch (text)
			{
				case 1:
					this.setTextColor(Color.BLUE);
					break;
				case 2:
					this.setTextColor(Color.rgb(0, 100, 0));
					break;
				case 3:
					this.setTextColor(Color.RED);
					break;
				case 4:
					this.setTextColor(Color.rgb(85, 26, 139));
					break;
				case 5:
					this.setTextColor(Color.rgb(139, 28, 98));
					break;
				case 6:
					this.setTextColor(Color.rgb(238, 173, 14));
					break;
				case 7:
					this.setTextColor(Color.rgb(47, 79, 79));
					break;
				case 8:
					this.setTextColor(Color.rgb(71, 71, 71));
					break;
				case 9: 
					this.setTextColor(Color.rgb(205, 205, 0));
					break;
			}
		}
	}

	//将某一格下面设置成地雷
	public void plantMine()
	{
		isMined = true;
	}

	// 地雷被打开
	// 改变方格的图标和颜色
	public void triggerMine()
	{
		setMineIcon(true);
		this.setTextColor(Color.RED);
	}

	// 是否还是处于未点开状态
	public boolean isCovered()
	{
		return isCovered;
	}

	// 方块后面是否有地雷
	public boolean hasMine()
	{
		return isMined;
	}

	// 设置附近的雷数
	public void setNumberOfMinesInSurrounding(int number)
	{
		numberOfMinesInSurrounding = number;
	}

	// get number of nearby mines
	public int getNumberOfMinesInSorrounding()
	{
		return numberOfMinesInSurrounding;
	}

	// 是否标记了
	public boolean isFlagged()
	{
		return isFlagged;
	}

	// 设置标记
	public void setFlagged(boolean flagged)
	{
		isFlagged = flagged;
	}

	// 是否设置成问号标记了
	public boolean isQuestionMarked()
	{
		return isQuestionMarked;
	}

	// 做问号标记
	public void setQuestionMarked(boolean questionMarked)
	{
		isQuestionMarked = questionMarked;
	}

	// 方格是否可以接收点击
	public boolean isClickable()
	{
		return isClickable;
	}

	// 设置点击状态
	public void setClickable(boolean clickable)
	{
		isClickable = clickable;
	}
}
