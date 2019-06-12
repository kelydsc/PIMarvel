package br.com.digitalhouse.digital.pimarvel.menu.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.menu.listener.RecyclerViewAvatarClickListener;
import br.com.digitalhouse.digital.pimarvel.menu.adapter.RecyclerViewAvatarAdapter;
import br.com.digitalhouse.digital.pimarvel.menu.model.Avatar;

public class SettingsActivity extends AppCompatActivity implements RecyclerViewAvatarClickListener {

    private RecyclerView recyclerView;
    private RecyclerViewAvatarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Seta a toolbar e o botão voltar(back)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Para popular dinâmicamente as possibilidades de heróis,
        coloquei um Recycler View listando quantos heróis tiverem na lista de Avatares*/
        recyclerView = findViewById(R.id.settginsRecyclerViewAvatars);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new RecyclerViewAvatarAdapter(getAvatars(), this);
        recyclerView.setAdapter(adapter);
    }

    private List<Avatar> getAvatars() {
        List<Avatar> avatars = new ArrayList<>();
        avatars.add(new Avatar(R.drawable.avatar1, "Captain America"));
        avatars.add(new Avatar(R.drawable.avatar2, "Captain America"));
        avatars.add(new Avatar(R.drawable.avatar3, "Captain America"));
        avatars.add(new Avatar(R.drawable.avatar4, "Iron Man"));
        avatars.add(new Avatar(R.drawable.avatar5, "Iron Man"));
        avatars.add(new Avatar(R.drawable.avatar6, "Spider Man"));
        avatars.add(new Avatar(R.drawable.avatar7, "Thor"));
        avatars.add(new Avatar(R.drawable.avatar8, "Thor"));
        avatars.add(new Avatar(R.drawable.avatar9, "Hulk"));
        return avatars;
    }

    @Override
    public void onClick(Avatar avatar) {
        Toast.makeText(getApplicationContext(),"Welcome back to the Avenger's HQ " + avatar.getHeroName() + "!", Toast.LENGTH_LONG).show();
    }
}
