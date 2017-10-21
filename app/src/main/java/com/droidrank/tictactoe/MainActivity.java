package com.droidrank.tictactoe;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    
    Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    TextView result;
    private Player p1 = null;
    private Player p2 = null;
    private boolean p1_turn = true;
    private int total = 0;
    private boolean gameOver = true;
    private boolean startNewGame = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        block1 = (Button) findViewById(R.id.bt_block1);
        block2 = (Button) findViewById(R.id.bt_block2);
        block3 = (Button) findViewById(R.id.bt_block3);
        block4 = (Button) findViewById(R.id.bt_block4);
        block5 = (Button) findViewById(R.id.bt_block5);
        block6 = (Button) findViewById(R.id.bt_block6);
        block7 = (Button) findViewById(R.id.bt_block7);
        block8 = (Button) findViewById(R.id.bt_block8);
        block9 = (Button) findViewById(R.id.bt_block9);
        block1.setOnClickListener(this);
        block2.setOnClickListener(this);
        block3.setOnClickListener(this);
        block4.setOnClickListener(this);
        block5.setOnClickListener(this);
        block6.setOnClickListener(this);
        block7.setOnClickListener(this);
        block8.setOnClickListener(this);
        block9.setOnClickListener(this);


        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);

        if(savedInstanceState==null){
            p1 = new Player();
            p2 = new Player();
        }else{
            p1_turn = savedInstanceState.getBoolean("p1_turn",p1_turn);
            total = savedInstanceState.getInt("total",total);
            gameOver = savedInstanceState.getBoolean("gameOver",gameOver);
            startNewGame = savedInstanceState.getBoolean("startNewGame",startNewGame);
            p1 = (Player) savedInstanceState.getSerializable("p1");
            p2 = (Player) savedInstanceState.getSerializable("p2");
            String resultStr = savedInstanceState.getString("result","");
            result.setText(resultStr);
            if(startNewGame){
                restart.setText(getResources().getString(R.string.restart_button_text_initially));
            }else{
                restart.setText(getResources().getString(R.string.restart_button_text_in_middle_of_game));
            }

            for (int i=1;i<=9;i++)
                if(p1.contains(i))
                    setBtnText(i,getResources().getString(R.string.player_1_move));

            for (int i=1;i<=9;i++)
                if(p2.contains(i))
                    setBtnText(i,getResources().getString(R.string.player_2_move));

        }



        /**
         * Restarts the game
         */
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(!startNewGame){
                        // launch restart dialog
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag("launching");
                        LaunchDialog launchDialog = new LaunchDialog();
                        launchDialog.show(getSupportFragmentManager(),"launching");
                    }
                    else{
                        p1 = new Player();
                        p2 = new Player();
                        p1_turn = true;
                        gameOver = false;
                        total = 0;
                        restart.setText(getResources().getString(R.string.restart_button_text_in_middle_of_game));// restart text
                        result.setText("");
                        clearBtnText();
                        startNewGame = false;
                    }
                }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("p1_turn",p1_turn);
        outState.putInt("total",total);
        outState.putBoolean("gameOver",gameOver);
        outState.putBoolean("startNewGame",startNewGame);
        outState.putString("result", (String) result.getText());
        outState.putSerializable("p1",p1);
        outState.putSerializable("p2",p2);
        super.onSaveInstanceState(outState);
    }

    private void setBtnText(int id,String text){
        switch (id){
            case 1:{
                block1.setText(text);
                break;
            }
            case 2:{
                block2.setText(text);
                break;
            }
            case 3:{
                block3.setText(text);
                break;
            }
            case 4:{
                block4.setText(text);
                break;
            }
            case 5:{
                block5.setText(text);
                break;
            }
            case 6:{
                block6.setText(text);
                break;
            }
            case 7:{
                block7.setText(text);
                break;
            }
            case 8:{
                block8.setText(text);
                break;
            }
            case 9:{
                block9.setText(text);
                break;
            }

        }
    }

    private void clearBtnText(){
        block1.setText("");
        block2.setText("");
        block3.setText("");
        block4.setText("");
        block5.setText("");
        block6.setText("");
        block7.setText("");
        block8.setText("");
        block9.setText("");
    }

    public void onOkClicked(){
        p1 = new Player();
        p2 = new Player();
        p1_turn = true;
        gameOver = false;
        total = 0;
        restart.setText(getResources().getString(R.string.restart_button_text_in_middle_of_game));
        result.setText("");
        clearBtnText();
        startNewGame = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_block1:{
                btnClicked(1);
                break;
            }
            case R.id.bt_block2:{
                btnClicked(2);
                break;
            }
            case R.id.bt_block3:{
                btnClicked(3);
                break;
            }
            case R.id.bt_block4:{
                btnClicked(4);
                break;
            }
            case R.id.bt_block5:{
                btnClicked(5);
                break;
            }
            case R.id.bt_block6:{
                btnClicked(6);
                break;
            }
            case R.id.bt_block7:{
                btnClicked(7);
                break;
            }
            case R.id.bt_block8:{
                btnClicked(8);
                break;
            }
            case R.id.bt_block9:{
                btnClicked(9);
                break;
            }
        }
    }

    private void btnClicked(int id){
        if(!p1.contains(id)&&!p2.contains(id)&&!gameOver){
            if(p1_turn){
                p1.addSelectedBtn(id);
                total++;
            }else{
                p2.addSelectedBtn(id);
                total++;
            }
            setText(id);
            if(total==9){
                if(p1.validate()){
                    result.setText(getResources().getString(R.string.player_1_wins));

                }else if(p2.validate()){
                    result.setText(getResources().getString(R.string.player_2_wins));

                }else{
                    result.setText(getResources().getString(R.string.draw));
                }
                gameOver = true;
                startNewGame = true;
                restart.setText(getResources().getString(R.string.restart_button_text_initially));
            }
            else if(p1_turn){
              if(p1.validate()){
                  result.setText(getResources().getString(R.string.player_1_wins));
                  startNewGame = true;
                  restart.setText(getResources().getString(R.string.restart_button_text_initially));
                  gameOver = true;
              }
            }else{
                if(p2.validate()){
                    result.setText(getResources().getString(R.string.player_2_wins));
                    startNewGame = true;
                    restart.setText(getResources().getString(R.string.restart_button_text_initially));
                    gameOver = true;
                }
            }
            p1_turn = !p1_turn;
        }
    }
    private void setText(int id){
        switch (id){
            case 1:{
                if(p1_turn)
                    block1.setText(getResources().getString(R.string.player_1_move));
                else
                    block1.setText(getResources().getString(R.string.player_2_move));
                break;
            }
            case 2:{
                if(p1_turn)
                    block2.setText(getResources().getString(R.string.player_1_move));
                else
                    block2.setText(getResources().getString(R.string.player_2_move));
                break;
            }
            case 3:{
                if(p1_turn)
                    block3.setText(getResources().getString(R.string.player_1_move));
                else
                    block3.setText(getResources().getString(R.string.player_2_move));
                break;
            }
            case 4:{
                if(p1_turn)
                    block4.setText(getResources().getString(R.string.player_1_move));
                else
                    block4.setText(getResources().getString(R.string.player_2_move));
                break;
            }
            case 5:{
                if(p1_turn)
                    block5.setText(getResources().getString(R.string.player_1_move));
                else
                    block5.setText(getResources().getString(R.string.player_2_move));
                break;
            }
            case 6:{
                if(p1_turn)
                    block6.setText(getResources().getString(R.string.player_1_move));
                else
                    block6.setText(getResources().getString(R.string.player_2_move));
                break;
            }
            case 7:{
                if(p1_turn)
                    block7.setText(getResources().getString(R.string.player_1_move));
                else
                    block7.setText(getResources().getString(R.string.player_2_move));
                break;
            }
            case 8:{
                if(p1_turn)
                    block8.setText(getResources().getString(R.string.player_1_move));
                else
                    block8.setText(getResources().getString(R.string.player_2_move));
                break;
            }
            case 9:{
                if(p1_turn)
                    block9.setText(getResources().getString(R.string.player_1_move));
                else
                    block9.setText(getResources().getString(R.string.player_2_move));
                break;
            }

        }
    }
}
