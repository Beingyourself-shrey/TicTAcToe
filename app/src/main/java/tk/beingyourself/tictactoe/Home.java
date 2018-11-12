package tk.beingyourself.tictactoe;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class Home extends AppCompatActivity {
    int chances=0;
    int [] gamestate={2,2,2,2,2,2,2,2,2};
    MediaPlayer mplayer;

    //check win or not
 public int checkWin() {
     int flag=2;
//horizontal check
   for(int j=0;j<7;j+=3)
   {
       if(gamestate[j]==gamestate[j+1]&&gamestate[j+1]==gamestate[j+2]&&gamestate[j+1]==1)
       {

           flag=1;

       }
       if(gamestate[j]==gamestate[j+1]&&gamestate[j+1]==gamestate[j+2]&&gamestate[j+1]==0)
       {

           flag=0;
       }


   }
   //vertical check
     for(int j=0;j<3;j++)
     {
         if(gamestate[j]==gamestate[j+3]&&gamestate[j+3]==gamestate[j+6]&&gamestate[j+3]==1)
         {
             flag= 1;
         }
         if(gamestate[j]==gamestate[j+3]&&gamestate[j+3]==gamestate[j+6]&&gamestate[j+3]==0)
         {
             flag=0;
         }

     }
     //diagonal check left to right
    int j=0;

         if(gamestate[j]==gamestate[j+4]&&gamestate[j+4]==gamestate[j+8]&&gamestate[j+4]==1)
         {
             flag=1;
         }
         if(gamestate[j]==gamestate[j+4]&&gamestate[j+4]==gamestate[j+8]&&gamestate[j+4]==0)
         {
             flag=0;
         }

     //diagonal check right to  left

     if(gamestate[2]==gamestate[4]&&gamestate[6]==gamestate[4]&&gamestate[4]==1)
     {
         flag=1;
     }
     if(gamestate[2]==gamestate[4]&&gamestate[6]==gamestate[4]&&gamestate[4]==0)
     {
         flag=0;
     }

return flag;

 }

//check draw
 public int  checkDraw()
 {
     int flagDraw=1;
     for(int k=0;k<9;k++)
     {
        if(gamestate[k]==2)
            flagDraw=0;
     }
     return flagDraw;
 }


 //onclick
 public void fade(View view) {

     ImageView counter = (ImageView) view;
    int tagname=Integer.parseInt(counter.getTag().toString());

     if (gamestate[tagname]==2) {

         counter.setTranslationY(-1000f);
         if (chances % 2 == 0) {
             gamestate[tagname]=0;
             counter.setImageResource(R.drawable.x);
             counter.animate().translationYBy(1000f).setDuration(300);


         }
         else {
             gamestate[tagname]=1;
             counter.setImageResource(R.drawable.o);
             counter.animate().translationYBy(1000f).setDuration(300);


         }
         int ret=checkWin();
         if(ret==0)
         {

             TextView tex=(TextView)findViewById(R.id.msg);
             tex.setText("Player X Wins" );
            RelativeLayout layout =(RelativeLayout)findViewById(R.id.relativePop);
             layout.setVisibility(View.VISIBLE);
             for (int j=0;j<9;j++)
                 gamestate[j]=1;

         }
         else if(ret==1)
         {

             TextView tex=(TextView)findViewById(R.id.msg);
             tex.setText("Player O Wins" );
             RelativeLayout layout =(RelativeLayout)findViewById(R.id.relativePop);
             layout.setVisibility(View.VISIBLE);

             for (int j=0;j<9;j++)
                 gamestate[j]=0;
         }
         else
         {
             int draw=checkDraw();
             if(draw==1)
             {

                 TextView tex=(TextView)findViewById(R.id.msg);
                 tex.setText("Sorry Draw" );
                 RelativeLayout layout =(RelativeLayout)findViewById(R.id.relativePop);
                 layout.setVisibility(View.VISIBLE);
             }
         }
         chances++;
     }


 }

 //playAgain
    public void playAgain(View view){

        RelativeLayout layout =(RelativeLayout)findViewById(R.id.relativePop);
        layout.setVisibility(View.INVISIBLE);
        for(int j=0;j<9;j++)
            gamestate[j]=2;
        chances=0;
        GridLayout grid=(GridLayout)findViewById(R.id.grid);
        for(int i=0;i<grid.getChildCount();i++)
        {
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         mplayer=MediaPlayer.create(this,R.raw.song2);
        mplayer.setLooping(true);
        mplayer.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    protected void onDestroy(){
        super.onDestroy();
        mplayer.setLooping(false);
        mplayer.stop();
    }

}
