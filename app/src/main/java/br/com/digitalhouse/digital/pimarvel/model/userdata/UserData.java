package br.com.digitalhouse.digital.pimarvel.model.userdata;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import br.com.digitalhouse.digital.pimarvel.model.favorite.Favorite;

public class UserData {

    private static FirebaseUser user;

    public UserData() {
    }

    public static FirebaseUser getUser() {
        return user;
    }

    public static void setUser(FirebaseUser user) {
        UserData.user = user;
    }

    public void atualizaFavoritosUsuario(Context context, Comic comicFavorite) {

        //Atualiza favoritos do usuário no Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios");

        usuarioReference
                .child(getUser().getUid())
                .child("favoritos")
                .child(comicFavorite.getId())
                .setValue(new Favorite());

    }
/*
    public void removeFavoritosUsuario(Context context, Object objectFavorites) {

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child(getUser().getUid());
        DatabaseReference objectReference = usuarioReference.child("favoritos").child(objectFavorites.getObjectid().toString());

        //Adicionamos o listener para buscar o objeto gravado em favoritos
        objectReference.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Object objectLocal = dataSnapshot.getValue(Object.class);

                //Somente excluir a obra de favoritos se não houver classificação de estrelas
                if (objectLocal.getCountStarsFavorites() == 0) {

                    //Remove o registro da tabela
                    objectReference.removeValue();

                } else { //Somente atualizar o flag de favoritos como "falso"
                    objectLocal.setFavorite(false);

                    //Atualiza o registro na tabela
                    objectReference.setValue(objectLocal);
                }
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    */

    public void inicializaDados() {

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child(getUser().getUid());

    }
}
