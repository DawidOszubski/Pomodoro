package com.example.pomodoro;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyKeyboardAllButtons  extends LinearLayout implements View.OnClickListener {

    private Button button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8,button_9, button_0,
                    button_q,button_w,button_e,button_r,button_t,button_y,button_u,button_i,button_o,button_p,
                button_a,button_s,button_d,button_f,button_g,button_h,button_j,button_k,button_l,
            button_z, button_x,button_c,button_v,button_b,button_n,button_m, button_delete, buttonEnter, button_spacebar, button_shift;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;
    private boolean isShiftClicked = false;

    public MyKeyboardAllButtons(Context context){
        this(context,null,0);
    }

    public MyKeyboardAllButtons(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }

    public MyKeyboardAllButtons(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
        init(context,attrs);
    }

    private  void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.keyboard_allbuttons, this, true);
        button_1 = (Button) findViewById(R.id.button_1);
        button_1.setOnClickListener(this);
        button_2 = (Button) findViewById(R.id.button_2);
        button_2.setOnClickListener(this);
        button_3 = (Button) findViewById(R.id.button_3);
        button_3.setOnClickListener(this);
        button_4 = (Button) findViewById(R.id.button_4);
        button_4.setOnClickListener(this);
        button_5 = (Button) findViewById(R.id.button_5);
        button_5.setOnClickListener(this);
        button_6 = (Button) findViewById(R.id.button_6);
        button_6.setOnClickListener(this);
        button_7 = (Button) findViewById(R.id.button_7);
        button_7.setOnClickListener(this);
        button_8 = (Button) findViewById(R.id.button_8);
        button_8.setOnClickListener(this);
        button_9 = (Button) findViewById(R.id.button_9);
        button_9.setOnClickListener(this);
        button_0 = (Button) findViewById(R.id.button_0);
        button_0.setOnClickListener(this);
        button_q = (Button) findViewById(R.id.button_q);
        button_q.setOnClickListener(this);
        button_w = (Button) findViewById(R.id.button_w);
        button_w.setOnClickListener(this);
        button_e = (Button) findViewById(R.id.button_e);
        button_e.setOnClickListener(this);
        button_r = (Button) findViewById(R.id.button_r);
        button_r.setOnClickListener(this);
        button_t = (Button) findViewById(R.id.button_t);
        button_t.setOnClickListener(this);
        button_y = (Button) findViewById(R.id.button_y);
        button_y.setOnClickListener(this);
        button_u = (Button) findViewById(R.id.button_u);
        button_u.setOnClickListener(this);
        button_i = (Button) findViewById(R.id.button_i);
        button_i.setOnClickListener(this);
        button_o = (Button) findViewById(R.id.button_o);
        button_o.setOnClickListener(this);
        button_p = (Button) findViewById(R.id.button_p);
        button_p.setOnClickListener(this);
        button_a = (Button) findViewById(R.id.button_a);
        button_a.setOnClickListener(this);
        button_s = (Button) findViewById(R.id.button_s);
        button_s.setOnClickListener(this);
        button_d = (Button) findViewById(R.id.button_d);
        button_d.setOnClickListener(this);
        button_f = (Button) findViewById(R.id.button_f);
        button_f.setOnClickListener(this);
        button_g = (Button) findViewById(R.id.button_g);
        button_g.setOnClickListener(this);
        button_h = (Button) findViewById(R.id.button_h);
        button_h.setOnClickListener(this);
        button_j = (Button) findViewById(R.id.button_j);
        button_j.setOnClickListener(this);
        button_k = (Button) findViewById(R.id.button_k);
        button_k.setOnClickListener(this);
        button_l = (Button) findViewById(R.id.button_l);
        button_l.setOnClickListener(this);
        button_z = (Button) findViewById(R.id.button_z);
        button_z.setOnClickListener(this);
        button_x = (Button) findViewById(R.id.button_x);
        button_x.setOnClickListener(this);
        button_c = (Button) findViewById(R.id.button_c);
        button_c.setOnClickListener(this);
        button_v = (Button) findViewById(R.id.button_v);
        button_v.setOnClickListener(this);
        button_b = (Button) findViewById(R.id.button_b);
        button_b.setOnClickListener(this);
        button_n = (Button) findViewById(R.id.button_n);
        button_n.setOnClickListener(this);
        button_m = (Button) findViewById(R.id.button_m);
        button_m.setOnClickListener(this);
        button_spacebar = (Button) findViewById(R.id.button_spacebar);
        button_spacebar.setOnClickListener(this);
        button_delete = (Button) findViewById(R.id.button_delete1);
        button_delete.setOnClickListener(this);
        buttonEnter = (Button) findViewById(R.id.button_enter1);
        buttonEnter.setOnClickListener(this);
        button_shift = (Button) findViewById(R.id.button_shift);
        button_shift.setOnClickListener(this);

        keyValues.put(R.id.button_1,"1");
        keyValues.put(R.id.button_2,"2");
        keyValues.put(R.id.button_3,"3");
        keyValues.put(R.id.button_4,"4");
        keyValues.put(R.id.button_5,"5");
        keyValues.put(R.id.button_6,"6");
        keyValues.put(R.id.button_7,"7");
        keyValues.put(R.id.button_8,"8");
        keyValues.put(R.id.button_9,"9");
        keyValues.put(R.id.button_0,"0");
        keyValues.put(R.id.button_q,"q");
        keyValues.put(R.id.button_w,"w");
        keyValues.put(R.id.button_e,"e");
        keyValues.put(R.id.button_r,"r");
        keyValues.put(R.id.button_t,"t");
        keyValues.put(R.id.button_y,"y");
        keyValues.put(R.id.button_u,"u");
        keyValues.put(R.id.button_i,"i");
        keyValues.put(R.id.button_o,"o");
        keyValues.put(R.id.button_p,"p");
        keyValues.put(R.id.button_a,"a");
        keyValues.put(R.id.button_s,"s");
        keyValues.put(R.id.button_d,"d");
        keyValues.put(R.id.button_f,"f");
        keyValues.put(R.id.button_g,"g");
        keyValues.put(R.id.button_h,"h");
        keyValues.put(R.id.button_j,"j");
        keyValues.put(R.id.button_k,"k");
        keyValues.put(R.id.button_l,"l");
        keyValues.put(R.id.button_z,"z");
        keyValues.put(R.id.button_x,"x");
        keyValues.put(R.id.button_c,"c");
        keyValues.put(R.id.button_v,"v");
        keyValues.put(R.id.button_b,"b");
        keyValues.put(R.id.button_n,"n");
        keyValues.put(R.id.button_m,"m");
        keyValues.put(R.id.button_spacebar," ");

    }

    @Override
    public void onClick(View view) {
        if(inputConnection==null) return;
        if(view.getId() == R.id.button_delete1){
            CharSequence selectedText = inputConnection.getSelectedText(0);
            if(TextUtils.isEmpty(selectedText)){
                inputConnection.deleteSurroundingText(1,0);
            }else{
                inputConnection.commitText("",1);
            }
        }else if(view.getId() == R.id.button_shift) {
            if(isShiftClicked){
                keyValues.put(R.id.button_q,"q");
                keyValues.put(R.id.button_w,"w");
                keyValues.put(R.id.button_e,"e");
                keyValues.put(R.id.button_r,"r");
                keyValues.put(R.id.button_t,"t");
                keyValues.put(R.id.button_y,"y");
                keyValues.put(R.id.button_u,"u");
                keyValues.put(R.id.button_i,"i");
                keyValues.put(R.id.button_o,"o");
                keyValues.put(R.id.button_p,"p");
                keyValues.put(R.id.button_a,"a");
                keyValues.put(R.id.button_s,"s");
                keyValues.put(R.id.button_d,"d");
                keyValues.put(R.id.button_f,"f");
                keyValues.put(R.id.button_g,"g");
                keyValues.put(R.id.button_h,"h");
                keyValues.put(R.id.button_j,"j");
                keyValues.put(R.id.button_k,"k");
                keyValues.put(R.id.button_l,"l");
                keyValues.put(R.id.button_z,"z");
                keyValues.put(R.id.button_x,"x");
                keyValues.put(R.id.button_c,"c");
                keyValues.put(R.id.button_v,"v");
                keyValues.put(R.id.button_b,"b");
                keyValues.put(R.id.button_n,"n");
                keyValues.put(R.id.button_m,"m");
                button_q.setText("q");
                button_w.setText("w");
                button_e.setText("e");
                button_r.setText("r");
                button_t.setText("t");
                button_y.setText("y");
                button_u.setText("u");
                button_i.setText("i");
                button_o.setText("o");
                button_p.setText("p");
                button_a.setText("a");
                button_s.setText("s");
                button_d.setText("d");
                button_f.setText("f");
                button_g.setText("g");
                button_h.setText("h");
                button_j.setText("j");
                button_k.setText("k");
                button_l.setText("l");
                button_z.setText("z");
                button_x.setText("x");
                button_c.setText("c");
                button_v.setText("v");
                button_b.setText("b");
                button_n.setText("n");
                button_m.setText("m");
                isShiftClicked=false;
            }else{
                keyValues.put(R.id.button_q,"Q");
                keyValues.put(R.id.button_w,"W");
                keyValues.put(R.id.button_e,"E");
                keyValues.put(R.id.button_r,"R");
                keyValues.put(R.id.button_t,"T");
                keyValues.put(R.id.button_y,"Y");
                keyValues.put(R.id.button_u,"U");
                keyValues.put(R.id.button_i,"I");
                keyValues.put(R.id.button_o,"O");
                keyValues.put(R.id.button_p,"P");
                keyValues.put(R.id.button_a,"A");
                keyValues.put(R.id.button_s,"S");
                keyValues.put(R.id.button_d,"D");
                keyValues.put(R.id.button_f,"F");
                keyValues.put(R.id.button_g,"G");
                keyValues.put(R.id.button_h,"H");
                keyValues.put(R.id.button_j,"J");
                keyValues.put(R.id.button_k,"K");
                keyValues.put(R.id.button_l,"L");
                keyValues.put(R.id.button_z,"Z");
                keyValues.put(R.id.button_x,"X");
                keyValues.put(R.id.button_c,"C");
                keyValues.put(R.id.button_v,"V");
                keyValues.put(R.id.button_b,"B");
                keyValues.put(R.id.button_n,"N");
                keyValues.put(R.id.button_m,"M");
                button_q.setText("Q");
                button_w.setText("W");
                button_e.setText("E");
                button_r.setText("R");
                button_t.setText("T");
                button_y.setText("Y");
                button_u.setText("U");
                button_i.setText("I");
                button_o.setText("O");
                button_p.setText("P");
                button_a.setText("A");
                button_s.setText("S");
                button_d.setText("D");
                button_f.setText("F");
                button_g.setText("G");
                button_h.setText("H");
                button_j.setText("J");
                button_k.setText("K");
                button_l.setText("L");
                button_z.setText("Z");
                button_x.setText("X");
                button_c.setText("C");
                button_v.setText("V");
                button_b.setText("B");
                button_n.setText("N");
                button_m.setText("M");
                isShiftClicked=true;
            }

        } else{
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value,1);
        }
inputConnection.performContextMenuAction(0);
    }



    public  void setInputConnection(InputConnection ic){
        inputConnection = ic;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return super.onKeyLongPress(keyCode, event);
    }


}
