package com.example.cadastrorevistas;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterRevista extends BaseAdapter {

    private List<Revista> revistaList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterRevista(Context context, List<Revista> listaRevistas){
        this.revistaList = listaRevistas;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return revistaList.size();
    }

    @Override
    public Object getItem(int i) {
        return revistaList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return revistaList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if( convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista, null);

            item = new ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.tvListaNome);
            item.tvCategoria = convertView.findViewById(R.id.tvListaCategoria);
            item.tvAno = convertView.findViewById(R.id.tvListaAno);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag( item );
        }else {
            item = (ItemSuporte) convertView.getTag();
        }

        Revista revista = revistaList.get(i);
        item.tvNome.setText(  revista.nome );
        item.tvCategoria.setText(  revista.categoria );
        item.tvAno.setText(  String.valueOf( revista.getAno() ) );

        if( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else {
            item.layout.setBackgroundColor( Color.WHITE );
        }
        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome, tvCategoria, tvAno;
        LinearLayout layout;
    }


}