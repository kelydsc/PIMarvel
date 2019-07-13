package br.com.digitalhouse.digital.pimarvel.event.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.event.adapter.RecyclerViewEventAdapter;
import br.com.digitalhouse.digital.pimarvel.event.data.database.DataBase;
import br.com.digitalhouse.digital.pimarvel.event.data.database.dao.EventDao;
import br.com.digitalhouse.digital.pimarvel.event.listener.RecyclerViewEventClickListener;
import br.com.digitalhouse.digital.pimarvel.event.model.Event;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EventFragment extends Fragment implements RecyclerViewEventClickListener {

    private RecyclerView recyclerView;
    private RecyclerViewEventAdapter adapter;
    private EventDao dao;
    private TextView eventTextViewTitle;
    private TextView eventTextViewDescription;
    private ImageView eventImageViewFavorite;

    public EventFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        recyclerView = view.findViewById(R.id.eventRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewEventAdapter(getEvents(), this);
        recyclerView.setAdapter(adapter);

        eventTextViewTitle = view.findViewById(R.id.eventTextViewTitle);
        eventTextViewDescription = view.findViewById(R.id.eventTextViewDescription);



        dao = DataBase.getDatabase(getContext()).eventDao();

        eventImageViewFavorite.setOnClickListener((View -> {
            // Salvar item no banco ao clicar
            String title = eventTextViewTitle.getEditableText().toString();
            String description = eventTextViewDescription.getEditableText().toString();

            new Thread(() -> {
                dao.insert(new Event(title, description));
                buscarTodosOsEventos();
            }).start();
        }));

        // buscar todos os item salvos na base de dados e carregar no recyclerview
        buscarTodosOsEventos();

        Spinner spinner = view.findViewById(R.id.spinner);
        String[] listItem = new String[]{"Tairo", "Jessica", "Vinicius"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.event_recyclerview_item, listItem);
        spinner.setAdapter(arrayAdapter);
        return view;
    }

    private List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("Memorial Event", "Event dedicated to the greatest comic book writer, yes him, Stan Lee!", R.drawable.stan_lee));
        events.add(new Event("Memorial Event", "Event dedicated to the greatest comic book writer, yes him, Stan Lee!", R.drawable.stan_lee));
        events.add(new Event("Memorial Event", "Event dedicated to the greatest comic book writer, yes him, Stan Lee!", R.drawable.stan_lee));
        events.add(new Event("Memorial Event", "Event dedicated to the greatest comic book writer, yes him, Stan Lee!", R.drawable.stan_lee));
        events.add(new Event("Memorial Event", "Event dedicated to the greatest comic book writer, yes him, Stan Lee!", R.drawable.stan_lee));
        return events;
    }

    @Override
    public void onClick(Event event) {
        Toast.makeText(getActivity(), "Your will be redirected to the event site.", Toast.LENGTH_LONG).show();
    }

    public void buscarTodosOsEventos() {

        // Uso de thread
        /*new Thread(() -> {
            List<Contato> contatos = dao.getAll();

            runOnUiThread(() -> {
                adapter.update(contatos);
            });

        }).start();*/

        dao.getAllRxJava()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(eventos -> {
                    adapter.update(eventos);
                }, throwable -> {
                    Log.i("TAG", "buscarTodosOsEventos: " + throwable.getMessage());
                });
    }

    @Override
    public void onItemClick(Event event) {
        // ao clicar no item, deletar e remover da lista
        new Thread(() -> {
            dao.delete(event);
            buscarTodosOsEventos();
        }).start();
    }
}

