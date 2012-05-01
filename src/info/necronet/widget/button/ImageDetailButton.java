package info.necronet.widget.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class ImageDetailButton extends ImageButton {

	private static final int DEFAULT_TEXT_SIZE=10;
	private CharSequence detailText="SOMETHING";
	private Drawable detailDrawable;
	private int detailColor;
	
	private int detailTextSize;
		
	public ImageDetailButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ImageDetailButton);

		CharSequence s=a.getString(R.styleable.ImageDetailButton_detailText);
		if(s!=null)
			setDetailText(s);

		
		setDetailColor(a.getColor(R.styleable.ImageDetailButton_detailColor, Color.BLACK));
		setDetailTextSize(a.getDimensionPixelSize(R.styleable.ImageDetailButton_detailTextSize, DEFAULT_TEXT_SIZE));
		setDetailDrawable(a.getDrawable(R.styleable.ImageDetailButton_detailDrawable));
		a.recycle();
		
	}
	
	public void setDetailTextSize(int detailTextSize){
		this.detailTextSize=detailTextSize;
	}
	
	public void setDetailColor(int color) {
		this.detailColor=color;
	}

	@Override
	public void onDraw(Canvas canvas){
		
		super.onDraw(canvas);
		
		if(detailDrawable==null) return; 
		
		Paint paint=new Paint();
		
		int drawableFinalCoordX=getWidth()-getPaddingRight();
		int boundY=getBottom()-getPaddingBottom()-getPaddingTop();

		
		paint.setAntiAlias(true);
		
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setColor(detailColor);
		paint.setTextSize(detailTextSize);
		
		if(getBackground()==null){
			detailDrawable.setBounds(getPaddingLeft(), boundY-20, getWidth(),boundY);
			detailDrawable.draw(canvas);
			
			canvas.drawText((String)detailText, (drawableFinalCoordX+getPaddingLeft()-paint.measureText((String)detailText))/2, boundY+15,paint);
		}else{
			detailDrawable.setBounds(getWidth()/5, getDrawable().getBounds().bottom-40, 4*getWidth()/5,getDrawable().getBounds().bottom-20);
			detailDrawable.draw(canvas);
			
			canvas.drawText((String)detailText, (drawableFinalCoordX+getPaddingLeft()-paint.measureText((String)detailText))/2,  getDrawable().getBounds().bottom-25,paint);
		}
			
		/*
		//canvas.save();
		
		int drawableFinalCoordX=getWidth()-getPaddingRight();
		
		//canvas.translate(getPaddingLeft(), getBottom()-getPaddingBottom()-getPaddingTop());
		
		int boundY=getBottom()-getPaddingBottom()-getPaddingTop();
		detailDrawable.setBounds(getPaddingLeft(), boundY, drawableFinalCoordX,boundY+20);
		
		detailDrawable.draw(canvas);
		
		paint.setAntiAlias(true);
		
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setColor(detailColor);
		paint.setTextSize(detailTextSize);
		
		canvas.drawText((String)detailText, (drawableFinalCoordX+getPaddingLeft()-paint.measureText((String)detailText))/2, boundY+15,paint);
		*/
	}
	
	
	public void setDetailDrawable(Drawable detailDrawable){
		this.detailDrawable=detailDrawable;
	}
	
	public void setDetailText(CharSequence detailText){
		this.detailText=detailText;
		invalidate();
	}
	
	

}
